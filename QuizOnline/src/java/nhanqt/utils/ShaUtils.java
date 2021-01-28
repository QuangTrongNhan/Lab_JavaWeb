/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author pc
 */
public class ShaUtils {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static byte[] digest(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public String result(String pText) throws NoSuchAlgorithmException {
        String algorithm = "SHA-256";
        byte[] shaInBytes = ShaUtils.digest(pText.getBytes(UTF_8), algorithm);
        String result;
        result = bytesToHex(shaInBytes);
        return result;
    }
}
