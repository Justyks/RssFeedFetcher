package com.example.rss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var URL: String? = intent.getStringExtra("URL")
        val webView: WebView = findViewById(R.id.webView)
        if (URL != null) {
            webView.loadUrl(URL)
        }
    }
}