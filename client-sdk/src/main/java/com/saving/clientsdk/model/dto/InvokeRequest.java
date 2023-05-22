package com.saving.clientsdk.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class InvokeRequest {

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

    /**
     * 响应头
     */
    private Map<String, String> responseHeader;

}
