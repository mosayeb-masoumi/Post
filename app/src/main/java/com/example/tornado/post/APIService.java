package com.example.tornado.post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/posts")
    @FormUrlEncoded
    Call<Model> model(@Field("title") String title ,
                      @Field("body")String body,
                      @Field("userId") Long userId);

}