package com.qatest.demo.retrofit.service;

import com.qatest.demo.api.CommonApi;
import com.qatest.demo.retrofit.logger.OkHttpLogger;
import com.qatest.demo.utils.TestContext;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitManager {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TestContext.getTestContext().getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build();

    public static CommonApi getCommonApi() {
        return retrofit.create(CommonApi.class);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        //日志默认只输出到console，可以设置日志类型：NONE，BASIC, HEADERS,BODY
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //自定义拦截器OkHttpLogger将日志输出到文本
        builder.interceptors().add(new OkHttpLogger());
        return builder.build();
    }


}