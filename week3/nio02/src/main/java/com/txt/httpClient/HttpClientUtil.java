package com.txt.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Description :
 * @Date : 2022-01-21
 */
public class HttpClientUtil {
    public static String request(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            stringBuilder.append("响应状态：" + response.getStatusLine() + "\n");
            stringBuilder.append("响应类型：" + httpEntity.getContentType() + "\n");
            stringBuilder.append("响应长度：" + httpEntity.getContentLength() + "\n");
            stringBuilder.append("响应内容：" + EntityUtils.toString(httpEntity) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}
