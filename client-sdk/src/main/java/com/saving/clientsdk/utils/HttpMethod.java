package com.saving.clientsdk.utils;

public enum HttpMethod {

    GET, POST, PUT;

    public static String fromString(String method) {
        if (method != null) {
            for (HttpMethod httpMethod : HttpMethod.values()) {
                if (method.equalsIgnoreCase(httpMethod.name())) {
                    return httpMethod.toString();
                }
            }
        }
        throw new IllegalArgumentException("No constant with text " + method + " found");
    }
}
