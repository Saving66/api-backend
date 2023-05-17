package com.saving.controller;

import cn.hutool.json.JSONUtil;
import com.saving.clientsdk.model.entry.User;
import com.saving.clientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Saving
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(@RequestParam String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping
    public String getNameByPost(@RequestBody String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");

        if (!"saving".equals(accessKey)) {
            throw new RuntimeException("无权限");
        }

        if (Long.parseLong(nonce) > 100000) {
            throw new RuntimeException("无权限");
        }

        if (System.currentTimeMillis() - Long.parseLong(timestamp) > 1000 * 60 * 5) {
            throw new RuntimeException("无权限");
        }

        String json = JSONUtil.toJsonStr(user);
        if (!sign.equals(SignUtil.genSign(json, "abcdefgh"))) {
            throw new RuntimeException("无权限");
        }
        return "POST 用户名字是" + user.getUsername();
    }
}
