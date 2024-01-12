package edu.hw8;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OneThreadPasswords {

    private OneThreadPasswords() {
    }

    public static HashMap<String, String> hacker(HashMap<String, String> users) {
        HashMap<String, String> result = new HashMap<>();
            for (char c = 'a'; c <= 'z'; c++) {
                perm(users, "" + c, result);
            }
            for (char c = 'A'; c <= 'Z'; c++) {

                perm(users, "" + c, result);
            }
            for (char c = '0'; c <= '9'; c++) {
                perm(users, "" + c, result);
            }

        return result;
    }

    public static void hasPassword(HashMap<String, String> all, String curr, HashMap<String, String> put)
        throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = curr.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        var theMD5digest = new String(md.digest(bytesOfMessage));
        if (all.get(theMD5digest) != null) {
            var name = all.get(theMD5digest);
            put.put(curr, name);
        }
    }

    public static void perm(HashMap<String, String> all, String curr, HashMap<String, String> put) {
        try {
            hasPassword(all, curr, put);
            if (all.size() == put.size() || curr.length() > 2 * 2) {
                return;
            }
            for (char c = 'a'; c <= 'z'; c++) {
                perm(all, curr + c, put);
            }
            for (char c = 'A'; c <= 'Z'; c++) {
                perm(all, curr + c, put);
            }
            for (char c = '0'; c <= '9'; c++) {
                perm(all, curr + c, put);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.info(e.getMessage(), e);
        }
    }
}
