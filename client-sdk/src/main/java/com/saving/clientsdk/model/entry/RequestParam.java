package com.saving.clientsdk.model.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求参数
 * @author saving
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数名称
     */
    private String paramName;
}
