package com.example.unknown

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.unknown.databinding.FragmentUnknownBinding

class UnknownFragment: Fragment(R.layout.fragment_unknown) {

    lateinit var binding: FragmentUnknownBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUnknownBinding.bind(view)
        val url = "https://kinopoiskapiunofficial.tech/"

        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
            loadUrl(url)
        }

        binding.webView.webViewClient = object : WebViewClient() {

            lateinit var currentUsl: String

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                currentUsl = url
                if (url.startsWith("http") || url.startsWith("https")){
                    return false
                }
                if(url.startsWith("intent")){
                    try {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                        if (fallbackUrl != null) {
                            binding.webView.loadUrl(fallbackUrl)
                            return true
                        }
                    }catch (ex: Exception){

                    }
                }
                return true
            }


        }
    }

}