/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser.Bruker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jonathans https://dzone.com/articles/storing-passwords-java-web
 */
public class PassordHasher {

    public static final String SALT = "Brokke123";

    public String krypterPassord(String passord) {
        String saltedPassword = SALT + passord;
        String hashedPassword = genererHash(saltedPassword);

        return hashedPassword;

    }

    public String genererHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            return "Finner ikke forespurt algoritme";
        }

        return hash.toString();
    }

}
