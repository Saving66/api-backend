package com.yupi.springbootinit.model.dto.interfaceInfo;

import com.yupi.springbootinit.model.entity.RequestHeader;
import com.yupi.springbootinit.model.entity.RequestParam;
import com.yupi.springbootinit.model.entity.ResponseHeader;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/Saving66">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {


    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求头
     */
    private List<RequestHeader> requestHeader;

    /**
     * 响应头
     */
    private List<ResponseHeader> responseHeader;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求参数
     */
    private List<RequestParam> requestParams;

    private static final long serialVersionUID = 1L;
}