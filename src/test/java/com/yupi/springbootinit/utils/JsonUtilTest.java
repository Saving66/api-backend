package com.yupi.springbootinit.utils;

import com.saving.validjsonsdk.utils.ValidRequestParamsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class JsonUtilTest {

    @Resource(name = "ValidRequestParamsUtil")
    private ValidRequestParamsUtil validRequestParamsUtil;

    @Test
    void testJsonUtil() {
        String requestParams = "{\"name\":\"string\",\"age\":integer}";
        String interfaceName = "a";
        boolean jsonSchema = validRequestParamsUtil.createJsonSchema(requestParams, interfaceName);
        if (jsonSchema) {
            System.out.println("生成成功");
        } else {
            System.out.println("生成失败");
        }
    }

    @Test
    void testJsonUtilValid() throws IOException {
        String requestParams = "{\"name\":\"a\",\"age\":a}";
        String interfaceName = "a";
        boolean valid = validRequestParamsUtil.valid(requestParams, interfaceName);
        if (valid) {
            System.out.println("校验成功");
        } else {
            System.out.println("校验失败");
        }
    }
}
