package com.saving.validjsonsdk.utils;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author saving
 */
@Data
public class ValidRequestParamsUtil {

    /**
     * schema文件路径
     */
    private String schemaUrl;

    public ValidRequestParamsUtil(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public boolean createJsonSchema(String requestParams, String interfaceName) {
        Map<String, String> parameters = JSONUtil.toBean(requestParams, Map.class);
        List<String> requiredFields = new ArrayList<>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            requiredFields.add(entry.getKey());
        }
        JSONObject schema = new JSONObject();
        schema.put("type", "object");

        JSONObject properties = new JSONObject();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            JSONObject property = new JSONObject();
            property.put("type", entry.getValue().toLowerCase());
            properties.put(entry.getKey(), property);
        }

        schema.put("properties", properties);

        JSONArray requiredArray = new JSONArray();
        for (String field : requiredFields) {
            requiredArray.put(field.toLowerCase());
        }
        schema.put("required", requiredArray);
        String fileName = schemaUrl + interfaceName + ".json";
        File schemaFile = new File(fileName);
        try (FileWriter writer = new FileWriter(schemaFile)) {
            writer.write(schema.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(schema);
        return true;
    }

    public boolean valid(String jsonString, String interfaceName) throws IOException {
        // Load the JSON schema
        String schemaPath = schemaUrl + interfaceName + ".json";
        InputStream schemaStream = new FileInputStream(schemaPath);
        String schemaString = IOUtils.toString(schemaStream, StandardCharsets.UTF_8);
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaString));
        Schema schema = SchemaLoader.load(rawSchema);

        // Create the JSON object to validate
        JSONObject jsonSubject = new JSONObject(jsonString);

        // Validate the JSON object
        try {
            schema.validate(jsonSubject);
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return false;
        }
        return true;
    }
}
