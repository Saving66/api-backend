package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saving.clientsdk.client.ApiClient;
import com.saving.clientsdk.common.BaseResponse;
import com.saving.clientsdk.model.dto.InvokeRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.InterfaceInfoService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.model.entity.RequestHeader;
import com.yupi.springbootinit.model.entity.RequestParam;
import com.yupi.springbootinit.model.entity.ResponseHeader;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author saving
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-05-05 23:53:39
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Resource
    private UserService userService;

    @Resource
    private ApiClient apiClient;

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name), ErrorCode.PARAMS_ERROR);
        }
        // TODO 校验参数
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public Long addInterfaceInfo(InterfaceInfo interfaceInfo, InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        // 1. 获取参数
        List<RequestHeader> requestHeader = interfaceInfoAddRequest.getRequestHeader();
        List<ResponseHeader> responseHeader = interfaceInfoAddRequest.getResponseHeader();
        List<RequestParam> requestParams = interfaceInfoAddRequest.getRequestParams();
        // 2. 请求参数、请求头、响应头转换为Map形式
        Map<String, String> requestParamMap = requestParams.stream().collect(Collectors.toMap(RequestParam::getParamName, RequestParam::getParamType));
        Map<String, String> requestHeaderMap = requestHeader.stream().collect(Collectors.toMap(RequestHeader::getRequestHeaderKey, RequestHeader::getRequestHeaderValue));
        Map<String, String> responseHeaderMap = responseHeader.stream().collect(Collectors.toMap(ResponseHeader::getResponseHeaderKey, ResponseHeader::getResponseHeaderValue));
        // 根据类型，转换为对应的值
        requestParamMap = transferValue(requestParamMap);
        // 3. 参数转换为Json形式
        String requestParamsJsonStr = JSONUtil.toJsonStr(requestParamMap);
        String requestHeaderJsonStr = JSONUtil.toJsonStr(requestHeaderMap);
        String responseHeaderJsonStr = JSONUtil.toJsonStr(responseHeaderMap);
        interfaceInfo.setRequestParams(requestParamsJsonStr);
        interfaceInfo.setRequestHeader(requestHeaderJsonStr);
        interfaceInfo.setResponseHeader(responseHeaderJsonStr);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = save(interfaceInfo);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newInterfaceInfoId = interfaceInfo.getId();
        return newInterfaceInfoId;
    }

    @NotNull
    private static Map<String, String> transferValue(Map<String, String> map) {
        map = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
            String value = entry.getValue();
            if ("String".equals(value)) {
                return "string";
            } else if ("Integer".equals(value)) {
                return "0";
            } else if ("Long".equals(value)) {
                return "0";
            } else if ("Double".equals(value)) {
                return "1.0";
            } else if ("Boolean".equals(value)) {
                return "true";
            } else {
                return "";
            }
        }));
        return map;
    }

    /**
     * 功能描述：校验接口是否可以使用
     *
     * @param interfaceInfo
     * @return boolean
     * @author Saving
     * @date 2023/5/15 10:30
     */
    @Override
    public boolean isVaildUse(InterfaceInfo interfaceInfo) {
        // TODO 校验是否可以使用
        // 1. 根据接口信息设置请求
        InvokeRequest target = new InvokeRequest();
        BeanUtil.copyProperties(interfaceInfo, target);
        // 2. 调用接口
        BaseResponse baseResponse = apiClient.invokeApi(target);
        // 3.输出结果
        System.out.println(baseResponse);
        return false;
    }

    @Override
    public String invokeInterface(InterfaceInfo interfaceInfo, Long userId) {
        // 1.根据调用接口用户初始化调用客户端
        User user = userService.getById(userId);
        ApiClient userApiClient = new ApiClient(user.getAccessKey(), user.getSecretKey());

        return null;
    }
}




