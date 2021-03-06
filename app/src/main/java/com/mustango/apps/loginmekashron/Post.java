package com.mustango.apps.loginmekashron;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kalmaro on 14.09.2016.
 */
public class Post {
     final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
  //  final MediaType JSON= MediaType.parse("pplication/x-www-form-urlencoded; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String PostR(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {

                return response.body().string();
            }
        }

        String loginJson(String login, String password) {
            return "{'UserName':'"+login+"','Password':'"+password+"','IP':'192.168.1.1'}";
        }
}
