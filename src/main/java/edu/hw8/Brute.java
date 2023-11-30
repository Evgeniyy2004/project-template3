package edu.hw8;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class Brute {

    private Brute() {
    }

    private static final int LEFTLIMIT = 97;
    private static final int RIGHTLIMIT = 122;

    public static void brute(HashMap<String, String> toadd, int size, String currstr)
        throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (toadd.size() == size || currstr.length() > 2 * 2) {
            return;
        }
        if (!currstr.isEmpty()) {
            int leftLimit = LEFTLIMIT; // letter 'a'
            int rightLimit = RIGHTLIMIT; // letter 'z'
            int targetStringLength = (2 * 2 * 2 * 2) - 1;
            Random random = new Random();
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
            byte[] bytesOfMessage = currstr.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            var theMD5digest = new String(md.digest(bytesOfMessage));
            toadd.put(theMD5digest, generatedString);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            brute(toadd, size, currstr + c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            brute(toadd, size, currstr + c);
        }
        for (char c = '0'; c <= '9'; c++) {
            brute(toadd, size, currstr + c);
        }
    }
}
