package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashPassword {
    private static final String HASH_ALG = "SHA-1";
    private static final MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance(HASH_ALG);
        } catch (NoSuchAlgorithmException e) {
            // If the specified algorithm is not available, throw a runtime exception
            throw new RuntimeException(e);
        }
    }
    
public static String hashPassword(String password){
    String encoded = Base64.getEncoder().encodeToString(md.digest(password.getBytes()));
    return encoded;
}
}
