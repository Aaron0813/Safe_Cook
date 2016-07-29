package com.example.administrator.safecook.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/29.
 */
public class StreamUtils {

    public static String decodeStream(InputStream in) throws IOException {
        int len=0;
        // 底层流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while((len=in.read(buf))>0){
            baos.write(buf, 0, len);
        }
        String date=baos.toString();
        return date;

    }

}
