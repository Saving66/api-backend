package com.yupi.springbootinit.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 编辑请求
 *
 * @author <a href="https://github.com/Saving66">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class InterfaceInfoUserInvokeRequest implements Serializable {

    /**
     * 接口id
     */
    private Long id;

    /**
     * 请求参数
     */
    private String requestParams;

    private static final long serialVersionUID = 1L;
}