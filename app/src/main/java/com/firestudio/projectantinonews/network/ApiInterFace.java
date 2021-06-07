package com.firestudio.projectantinonews.network;

import com.firestudio.projectantinonews.models.NewsArticle;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {

    @GET("top-headlines?country=in&apiKey=d1c7a30b76ea4462a4c2e4f10ee83df2")
    Call<NewsArticle>getNewsArticle();
}
