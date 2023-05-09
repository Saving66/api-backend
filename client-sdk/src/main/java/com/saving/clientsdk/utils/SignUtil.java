package com.saving.clientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {

    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA512);

        String digestHex = md5.digestHex(body + "." + secretKey);

        return digestHex;
    }
}
