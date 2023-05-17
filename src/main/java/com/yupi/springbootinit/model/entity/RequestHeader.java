package com.yupi.springbootinit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求头信息
 * @author saving
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader {

    /**
     * 请求头名称
     */
    private String requestHeaderKey;

    /**
     * 请求头值
     */
    private String requestHeaderValue;
}
