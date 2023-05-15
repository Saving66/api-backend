package com.yupi.springbootinit.utils;

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
     * 请求头值
     */
    private String requestHeaderValue;
}
