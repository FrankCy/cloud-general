package com.bdjr.common.web.util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RequestUtil {

    private  RequestUtil(){}

    /**
     * 从request中读取请求数据
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inStream = request.getInputStream();
        int len = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while ((len = inStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        String xmlBody = new String(out.toByteArray(), "utf-8");
        return xmlBody;
    }
}
