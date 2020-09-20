package com.qatest.demo.utils;

import java.util.HashMap;

public class GetHeaders {

    public static HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=utf-8");

        return headers;
    }
}
