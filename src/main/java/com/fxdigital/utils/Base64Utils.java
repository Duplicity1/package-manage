package com.fxdigital.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    private static Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    /**
     * 加密
     * 如果password 为 非空 则加密 ,否则返回原值
     *
     * @param password
     * @return
     */
    public static String base64encoder(String password) {
        if (!StringUtils.isEmpty(password)) {
            try {
                password = Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error("base64encoder error", e);
            }
        }
        logger.debug("BASE64加密后的值为:{}", password);
        return password;
    }

    /**
     * 解密
     *
     * @param base64Str
     * @throws IOException
     */
    public static String base64Decoder(String base64Str) {
        byte[] s = Base64.getDecoder().decode(base64Str);
        String str = null;
        try {
            str = new String(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("base64Decoder error", e);
        }
        logger.debug("base64解密后字符串：{}", str);
        return str;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String s = base64encoder("123456");
        System.out.println("加密的密码:" + s);
        String str = base64Decoder("12345678");
        System.out.println("解密的密码:" + str);
    }
}
