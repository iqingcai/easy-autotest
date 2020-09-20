package com.qatest.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qatest.demo.response.BasicResponse;
import retrofit2.Response;

import java.io.IOException;

public class ConvertResponse {

    public static <T extends BasicResponse> T convertResponse(Response<T> response, Class<T> tClass) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        T classResponse;
        if(response.isSuccessful()){
            //code >= 200 && code < 300
            classResponse = (T)response.body();
            classResponse.setHttpCode(response.code());
        }else {

            classResponse = objectMapper.readValue(response.errorBody().string().replace("/n", ""), tClass);
            classResponse.setHttpCode(response.code());

        }
        return classResponse;
    }

}
