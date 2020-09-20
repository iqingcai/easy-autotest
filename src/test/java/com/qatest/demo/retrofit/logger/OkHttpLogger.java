package com.qatest.demo.retrofit.logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;

@Log4j
public class OkHttpLogger implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //chain里面包含了request和response
        Request oldRequest = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        
        // 新的请求
        Request request = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

//        logger.info(request.method() + " "  + request.url());
//        logger.debug("headers: " + request.headers().toString());
        URLLogger.writeCurlRequest(request);

        //获取requestBody
        String requestString = null;
        RequestBody requestBody = request.body();
        if (requestBody != null && requestBody.contentLength() > 0) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            requestString = buffer.readUtf8();
        }

        if(requestString != null)
            log.info("body: " + requestString);

        long t1 = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long t2 = System.currentTimeMillis();//收到响应的时间

        //获取responseBody
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        log.info(response.code() + " ["+ (t2-t1) + "ms" + "] ");
        log.info("response: " + jsonFormatter(responseBody.string()));

        return response;
    }


    /*
     * json pretty print
     */
    public static String jsonFormatter(String uglyJsonString) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(uglyJsonString);
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;

    }
}