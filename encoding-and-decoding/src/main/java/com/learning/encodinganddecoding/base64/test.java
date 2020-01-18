package com.learning.encodinganddecoding.base64;

import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class test {
    public static void main(String[] args) throws IOException {
//        String code = new String("eJy9VtFu2zAM/Bc/B4JIkRK5XymKIGuCNVubDImzrSj676Oc2JZdGRi2Yn6xJUp31PlE6bVpX77vmk/N8+b0bdeun/bntlk12027aT693nrz1+f2YX05b7vvw+7n+vtp/5DnCaN3kXz32MzD5jl3D8NXzcPj5vDF+tiBrAzxVzE3euf9MPd5fxhjlMBRHGI/jk+XDpkleMJAhA5VVSiqhfdbC4F9fL68rMccrOO8e3rqe24ZXbHW281LxgNicR6TZxG26OPxMmYB3rs0ZpjRl7LviPrgJDIgrtt9zgM4qfcEyfQ4HS+HnLs0b6tm1z5WRQYGF/1c42F0qbHiVGNgdJKqEgOpA36vMBKCBlTJ60tJNZH0EuNUYkvhTyQmFUjOE4kX9TONgYKDUJV4lvxfKExzhY/nqsLBBUSd6duPHfWNjsJUX5soVXWDQwr4zr3BMwKBGTc50OjVx9SLG2biHs9/IC5Egvw4kziiJJzJa+IiYkXcSeb/bF7T9uG4P9xKRX7lZHvIW5VYFQWl/ypLxWnT2mD/dt0Kc5BuF1Qhip1QQph+M4jrb65jjH97wHjrR66Ltd38c9et877ctHdd1vely+66LO4NqD1ttrv11/PxMC+nYMN6VbtdvrpWVRk3hbXBYSzaEB1rGZ9U4UgpJaecALxgTokLEt+TGAhhCUIOpSTh0glzEiJgsqpOCilJyizwf2iCH2iuW+wqmTiFAmZanoU9l46f00hIzKwuJrBNipB54n/iAaIbk2iAgckLTU2Q2EmB7O2I1LDIRAGColhpYLDCABw7I5aHTMV63S4b/1bXpEmT0zQ6rl1J2NrBZ4AF13UIPEWYNokW8WMUcZirJ18Jaob7WIaa1zqGWGBg6YgcDbzEEAIHZLtVAGgCu3Es+OyjOeoeA7tGFRabs1iUwxKLQDBz2TmGdvCwJr3aqzhhK/bqKjD1eVojhLEBKkVkOI/BrmZqywWy60Na8FWeHqGYTloAM9aAJVmJ");

        String json = "{\"type\":\"ping\"}";
        System.out.println("原始字符串："+json);

        //压缩
        String json2 = compress(json);
        System.out.println("压缩后："+json2);
        //编码
        byte[] jsonByte = Base64.getEncoder().encode(json2.getBytes(StandardCharsets.UTF_8));
        System.out.println("编码后："+ Arrays.toString(jsonByte));
        //解码
        byte[] jsonByte2 = Base64.getDecoder().decode(jsonByte);
        String json3 = new String(jsonByte2, StandardCharsets.UTF_8);
        System.out.println("解码后："+json3);
        System.out.println(json2.equals(json3));
        //解压缩
        String json4 = uncompress(json3);
        System.out.println("解压缩后："+json4);
    }

    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }


    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
//        return out.toString("utf-8");
        return out.toString("ISO-8859-1");
    }

    public static String uncompress(String old) throws IOException {
        if (old == null || old.length() == 0) {
            return old;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(old.getBytes("ISO-8859-1"));
//        ByteArrayInputStream in = new ByteArrayInputStream(old.getBytes(StandardCharsets.UTF_8));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer))>= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return out.toString();
    }
}
