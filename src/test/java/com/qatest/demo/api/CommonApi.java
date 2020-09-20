package com.qatest.demo.api;

import com.google.gson.JsonObject;
import com.qatest.demo.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author hzzhouxiaoqing
 * API定义,更多请参考retrofit2文档
 */
public interface CommonApi {

    //静态添加header
    @Headers("Accept:application/json")
   /* @Headers({
            "Accept:application/json",
            "Charset:UTF-8"
    })*/
    @GET("demo?Action=GetCurrentVersion&Version=2020-02-02")
    Call<DescribeServiceVersionResponse> getCurrentVersion();

    // 动态添加header
    @GET("demo?Action=GetCurrentVersion")
    Call<DescribeServiceVersionResponse> getCurrentVersion1(@HeaderMap Map<String, String> headers);

    //@HTTP 替换@GET、@POST、@PUT、@DELETE、@HEAD注解的作用及更多功能拓展，接口定义请统一使用@HTTP
    @HTTP(method = "GET", path = "demo?Action=GetCurrentVersion", hasBody = false)
    Call<DescribeServiceVersionResponse> getCurrentVersion2(@HeaderMap Map<String, String> headers);

    //针对参数校验相关接口定义，可将字段定义成Object，传null的时候，url里面就不会拼接这个字段，传""时url拼接param=, 也支持字段非法类型的测试
    @HTTP(method = "GET", path = "demo?Action=DescribeProject", hasBody = false)
    Call<DescribeProjectResponse> describeProject(@HeaderMap Map<String, String> headers,
                                                  @Query("ProjectId") Object projectId);

    //增加FormUrlEncoded使用方法示例
    @FormUrlEncoded
    @HTTP(method = "POST", path = "demo?Action=ModifyParameter", hasBody = true)
    Call<BasicResponse> modifyParameter(@HeaderMap Map<String, String> headers,
                                        @Query("ProjectId") String projectId,
                                        @Query("ClusterIdentifier") String clusterIdentifier,
                                        @Field("Parameter") String parameter);

    //POST类型接口定义
    @HTTP(method = "POST", path = "demo?Action=ModifyPassword", hasBody = true)
    Call<BasicResponse> modifyPassword(@HeaderMap Map<String, String> headers,
                                              @Query("ProjectId") String projectId,
                                              @Body Map<String, String> body);

    //使用@Url替换BaseUrl
    @HTTP(method = "GET", hasBody = false)
    Call<DescribeProjectResponse> describeProject4(@Url String url, @HeaderMap Map<String, String> headers,
                                                   @QueryName JsonObject request);

    //可执行示例
    @HTTP(method = "POST", path = "project/createUser", hasBody = true)
    Call<UserResponse> createUser(@HeaderMap Map<String, String> headers);

    @HTTP(method = "GET", path = "project/getInfo/{userId}")
    Call<PersonResponse> getInfo(@HeaderMap Map<String, String> headers, @Path("userId") int userId);
}
