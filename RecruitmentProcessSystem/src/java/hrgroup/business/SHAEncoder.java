/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author tuananh
 */
public class SHAEncoder {
    public static String Encode(String input) throws NoSuchAlgorithmException {

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
