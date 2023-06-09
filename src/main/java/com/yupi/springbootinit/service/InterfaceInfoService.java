package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.yupi.springbootinit.model.entity.InterfaceInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @author saving
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-05-05 23:53:39
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    Long addInterfaceInfo(InterfaceInfo interfaceInfo, InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request);

    boolean isVaildUse(InterfaceInfo interfaceInfo);

    BaseResponse invokeInterface(InterfaceInfo interfaceInfo, Long userId, String requestParams) throws IOException;
}
