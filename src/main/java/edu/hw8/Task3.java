package edu.hw8;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.Executors;

public class Task3 {



    public static HashMap<String, String> multiThreadHacker(HashMap<String, String> users) {
        HashMap<String, String> result = new HashMap<>();
        try (var all = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            for (char c = 'a'; c <= 'z'; c++) {
                char finalC = c;
                all.execute(()->perm(users, "" + finalC, result));
            }
            for (char c = 'A'; c <= 'Z'; c++) {
                char finalC = c;
                all.execute(()->perm(users, "" + finalC, result));
            }
            for (char c = '0'; c <= '9'; c++) {
                char finalC = c;
                all.execute(()->perm(users, "" + finalC, result));
            }
        }
        return result;
    }

    public static void hasPassword(HashMap<String, String> all, String curr, HashMap<String, String> put)
        throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = curr.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        var theMD5digest =new String(md.digest(bytesOfMessage));
        if (all.get(theMD5digest) != null) {
            var name = all.get(theMD5digest);
            put.put(curr, name);
        }

    }

    public static void perm(HashMap<String, String> all, String curr, HashMap<String, String> put) {
        try {
            if (all.size() == put.size()) {
                return;
            }
            if (curr.length() > 4) {
                return;
            }
            hasPassword(all, curr, put);
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
            e.printStackTrace();
        }
    }
}
