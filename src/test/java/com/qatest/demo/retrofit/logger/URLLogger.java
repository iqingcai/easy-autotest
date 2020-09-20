package com.qatest.demo.retrofit.logger;

import lombok.extern.log4j.Log4j;
import okhttp3.Request;
import okio.Buffer;

import java.io.IOException;

@Log4j
public class URLLogger {

    public static void writeCurlRequest(Request request) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("curl -X %s -i '%s'",
      		  request.method(), request.url()));

        // Append headers
        if(request.headers().size() > 0){
            for(String header: request.headers().toString().split("\n")) {
                sb.append(" -H \"");
                sb.append(header);
                sb.append("\"");
            }
        }
        
        // Get body string builder
        if(request.body() != null && request.body().toString().length() > 0){
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            sb.append(" -d '");
            sb.append(buffer.readUtf8());
            sb.append("'");
          }

    	log.info(sb.toString());
      }
}