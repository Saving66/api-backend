package com.saving.clientsdk.model.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应头信息
 * @author saving
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHeader {

    /**
     * 响应头名称
     */
    private String responseHeaderKey;

    /**
     * 响应头值
     */
    private String responseHeaderValue;
}
