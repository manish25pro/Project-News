package com.firestudio.projectantinonews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class NewsDisplayActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);

        webView = findViewById(R.id.WebView);
        Intent intent = getIntent();
        String newsUrl = intent.getStringExtra("url");

        webView.loadUrl(newsUrl);
    }
}