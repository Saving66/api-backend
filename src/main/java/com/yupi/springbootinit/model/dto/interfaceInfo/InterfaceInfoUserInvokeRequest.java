package com.yupi.springbootinit.model.dto.interfaceInfo;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 请求参数
     */
    @NotNull(message = "请求参数不能为空")
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}