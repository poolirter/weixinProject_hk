package com.example.hk_ht.Util;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HttpUtil {
    public static final String UTF_8="UTF-8";
    public static String doGet(String url, String params) throws IOException {
        String web;
        web= url+params;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            HttpGet httpGet = new HttpGet(web);
            response = httpclient.execute(httpGet);
            // int statusCode = response.getStatusLine().getStatusCode();

            entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, UTF_8);
            }
        } finally {
            // 最后别忘了关闭应该关闭的资源，适当的释放资源
            try {
                // 这个方法也可以把底层的流给关闭了
                EntityUtils.consume(entity);
                // if (null != response) {
                // response.close();
                // }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
