package com.pankov.roadtosenior.onlineshop.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public static String encryptPassword(String password, String generatedSalt) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest((password + generatedSalt).getBytes());

            BigInteger bigInteger = new BigInteger(1, messageDigest);

            String hashText = bigInteger.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            return hashText;
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }
}
