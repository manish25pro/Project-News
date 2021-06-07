package com.firestudio.projectantinonews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firestudio.projectantinonews.adapter.NewsAdapter;
import com.firestudio.projectantinonews.models.Article;
import com.firestudio.projectantinonews.models.NewsArticle;
import com.firestudio.projectantinonews.network.ApiClient;
import com.firestudio.projectantinonews.network.ApiInterFace;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Article> article;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news_RecyclerView);
        progressBar = findViewById(R.id.progressBar);
        loadData();

    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ApiInterFace apiInterFace = ApiClient.getRetrofit().create(ApiInterFace.class);
        Call<NewsArticle> articleCall = apiInterFace.getNewsArticle();
        article = new ArrayList<>();
        articleCall.enqueue(new Callback<NewsArticle>() {
            @Override
            public void onResponse(Call<NewsArticle> call, Response<NewsArticle> response) {
                article = response.body().getArticles();
                newsAdapter = new NewsAdapter(getApplicationContext(), article);
                recyclerView.setAdapter(newsAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<NewsArticle> call, Throwable t) {
               progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}