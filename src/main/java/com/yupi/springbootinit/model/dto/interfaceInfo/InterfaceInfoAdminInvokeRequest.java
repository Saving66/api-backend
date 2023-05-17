package com.yupi.springbootinit.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 编辑请求
 *
 * @author <a href="https://github.com/Saving66">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class InterfaceInfoAdminInvokeRequest implements Serializable {

    /**
     * 调用者id
     */
    private Long userId;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */
    private Map<String, String> requestHeader;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求参数
     */
    private String requestParams;

    private static final long serialVersionUID = 1L;
}