package com.yupi.springbootinit.utils;


import cn.hutool.json.JSONUtil;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonSchemaTest {

    @Test
    void createSchema() throws JSONException {
        String requestParams = "{\"name\":\"string\",\"age\":integer}";
        String interfaceName = "test";
        Map<String, String> parameters = JSONUtil.toBean(requestParams, Map.class);
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requiredFields.add("age");
        JSONObject schema = new JSONObject();
        schema.put("type", "object");

        JSONObject properties = new JSONObject();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            JSONObject property = new JSONObject();
            property.put("type", entry.getValue());
            properties.put(entry.getKey(), property);
        }

        schema.put("properties", properties);

        JSONArray requiredArray = new JSONArray();
        for (String field : requiredFields) {
            requiredArray.put(field);
        }
        schema.put("required", requiredArray);
        String fileName = "/Users/saving/Developer/Java/schema/" + interfaceName + ".json";
        File schemaFile = new File(fileName);
        try (FileWriter writer = new FileWriter(schemaFile)) {
            writer.write(schema.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(schema);
    }

    @Test
    void validRequestParams() throws IOException, JSONException {
        // Load the JSON schema
        String schemaPath = "/Users/saving/Developer/Java/schema/test.json";
        InputStream schemaStream = new FileInputStream(schemaPath);
        String schemaString = IOUtils.toString(schemaStream, StandardCharsets.UTF_8);
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaString));
        Schema schema = SchemaLoader.load(rawSchema);

        // Create the JSON object to validate
        String jsonString = "{\"name\":\"John\", \"age\":30}";  // Replace this with your JSON data
        JSONObject jsonSubject = new JSONObject(jsonString);

        // Validate the JSON object
        try {
            schema.validate(jsonSubject);  // throws a ValidationException if this object is invalid
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
