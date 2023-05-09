package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfaceInfo;

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
}
