package com.yupi.springbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.InterfaceInfoService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.utils.RequestHeader;
import com.yupi.springbootinit.utils.RequestParam;
import com.yupi.springbootinit.utils.ResponseHeader;
import org.apache.commons.lang3.StringUtils;
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
        List<RequestHeader> requestHeaders = interfaceInfoAddRequest.getRequestHeaders();
        List<ResponseHeader> responseHeaders = interfaceInfoAddRequest.getResponseHeaders();
        List<RequestParam> requestParams = interfaceInfoAddRequest.getRequestParams();
        // 2. 请求参数转换为Map形式
        Map<String, String> paramMap = requestParams.stream().collect(Collectors.toMap(RequestParam::getParamName, RequestParam::getParamType));
        // 根据请求参数类型，转换为对应的值
//        paramMap = paramMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
//            String value = entry.getValue();
//            if ("String".equals(value)) {
//                return "";
//            } else if ("Integer".equals(value)) {
//                return "0";
//            } else if ("Long".equals(value)) {
//                return "0";
//            } else if ("Double".equals(value)) {
//                return "1.0";
//            } else if ("Float".equals(value)) {
//                return "1.0";
//            } else if ("Boolean".equals(value)) {
//                return "true";
//            } else if ("Date".equals(value)) {
//                return "2021-05-05 23:53:39";
//            } else if ("List".equals(value)) {
//                return "[1,2,3]";
//            } else {
//                return "";
//            }
//        }));
        // 3. 参数转换为Json形式
        String requestParamsJsonStr = JSONUtil.toJsonStr(paramMap);
        String requestHeadersJsonStr = JSONUtil.toJsonStr(requestHeaders);
        String responseHeadersJsonStr = JSONUtil.toJsonStr(responseHeaders);
        interfaceInfo.setRequestParams(requestParamsJsonStr);
        interfaceInfo.setRequestHeader(requestHeadersJsonStr);
        interfaceInfo.setResponseHeader(responseHeadersJsonStr);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = save(interfaceInfo);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newInterfaceInfoId = interfaceInfo.getId();
        return newInterfaceInfoId;
    }
}




