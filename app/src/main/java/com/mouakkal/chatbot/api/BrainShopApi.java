package com.mouakkal.chatbot.api;

import com.mouakkal.chatbot.model.BrainShopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface BrainShopApi {
     @GET
    Call<BrainShopResponse> getMessage(@Url String url);

}
