package com.saving.clientsdk.utils;

import java.util.HashSet;
import java.util.Set;

public class HttpMethodChecker {

    private static final Set<String> METHODS = new HashSet<>();

    static {
        METHODS.add("GET");
        METHODS.add("POST");
        METHODS.add("PUT");
    }

    public static boolean isValidHttpMethod(String method) {
        return METHODS.contains(method.toUpperCase());
    }
}
