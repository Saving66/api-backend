package com.saving.clientsdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.saving.clientsdk.common.BaseResponse;
import com.saving.clientsdk.common.ResultUtils;
import com.saving.clientsdk.model.dto.InvokeRequest;
import com.saving.clientsdk.model.entry.User;
import com.saving.clientsdk.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;


public class ApiClient {

    private String accessKey;

    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        return result;
    }

    public Map<String, String> getHeaderMap(String userId, Map<String, String> requestHeader) {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        map.put("sign", SignUtil.genSign(userId, secretKey));
        map.put("nonce", RandomUtil.randomNumbers(5));
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.putAll(requestHeader);
        return map;
    }

    public String getNameByRestful(User user) {
        String json = JSONUtil.toJsonStr(user);
        Map<String, String> requestHeader = new HashMap<>();
        String result = HttpRequest.post("localhost:8123/api/name/user")
                .addHeaders(getHeaderMap(json, requestHeader))
                .body(json)
                .execute().body();
        return result;
    }

    public BaseResponse invokeApi(InvokeRequest invokeRequest) {

        // 1. 生成签名和请求头
        Map<String, String> headerMap = getHeaderMap(String.valueOf(invokeRequest.getUserId()), invokeRequest.getRequestHeader());
        // 2. 发送请求
        String method = invokeRequest.getMethod();
        HttpRequest request;
        switch (method.toUpperCase()) {
            case "GET":
                request = HttpRequest.get(invokeRequest.getUrl());
                break;
            case "POST":
                request = HttpRequest.post(invokeRequest.getUrl());
                break;
            case "PUT":
                request = HttpRequest.put(invokeRequest.getUrl());
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        String requestParams = invokeRequest.getRequestParams();
        String result = request
                .addHeaders(headerMap)
                .body(requestParams).execute().body();
        // 3. 返回结果
        return ResultUtils.success(result);
    }


}
