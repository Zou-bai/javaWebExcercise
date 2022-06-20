package com.atguigu.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {
    public static void main(String[] args) throws Exception {
        String content="这是需要base64编码的内容";
        // 创建一个 Base64 编码器
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 执行 Base64 编码操作
        String encodedSting = base64Encoder.encode(content.getBytes("UTF-8"));
        System.out.println(encodedSting);
        // 创建 Base64 解码器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        // 解码操作
        byte [] bytes = base64Decoder.decodeBuffer(encodedSting);

        String str =new String(bytes,"UTF-8");

        System.out.println(str);
    }
}
