package com.juhyun.shorturl.util;

public class TinyUrl {

    // BASE62: 0-9(10) + a-z(26) + A-Z(26) = 62 possible characters
    private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int RADIX = 62;

    private TinyUrl() {
    }

    public static String idToShortUrl(long id) {
        StringBuilder sb = new StringBuilder();

        // Convert given integer id to a base 62 number
        while (id > 0) {
            sb.append(BASE62[(int) (id % 62)]);
            id = id / 62;
        }
        // Reverse shortURL to complete base conversion
        return sb.reverse().toString();
    }

    public static long shortUrlToId(String shortURL) {
        long id = 0; // initialize result

        // A simple base conversion logic
        for (int i = 0; i < shortURL.length(); i++) {
            if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z') {
                id = id * 62 + shortURL.charAt(i) - 'a';
            } else if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z') {
                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            } else {
                id = id * 62 + shortURL.charAt(i) - '0' + 52;
            }
        }
        return id;
    }

    public static String makeShortUrl(long id) {
        final StringBuilder sb = new StringBuilder();
        while (id > 0) {
            int i = (int) (id % RADIX);
            sb.append(BASE62[i]);
            id /= RADIX;
        }
        return sb.toString();
    }

    public static long makeId(String shortUrl) {
        long result = 0;
        long power = 1;
        for (int i = 0; i < shortUrl.length(); i++) {
            int digit = new String(BASE62).indexOf(shortUrl.charAt(i));
            result += digit * power;
            power *= RADIX;
        }
        return result;
    }

    // Driver Code
    public static void main(String[] args) {
        long id = 12345;
        String shortUrl = idToShortUrl(id);
        long newId = shortUrlToId(shortUrl);

        System.out.println(shortUrl + " " + newId);

        System.out.println(makeShortUrl(142142));
        System.out.println(makeId("M8K"));
    }
}
