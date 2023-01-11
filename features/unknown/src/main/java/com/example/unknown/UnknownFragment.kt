package com.example.unknown

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.unknown.databinding.FragmentUnknownBinding
import kotlinx.coroutines.*


class UnknownFragment: Fragment(R.layout.fragment_unknown) {

    lateinit var binding: FragmentUnknownBinding
    lateinit var job: Job

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUnknownBinding.bind(view)
        val url = "https://www.imdb.com/"

        //requireActivity().u

        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
            job = CoroutineScope(Dispatchers.Main).launch(start = CoroutineStart.DEFAULT) {
                loadUrl(url)
                delay(1500)
                binding.progressBar.isVisible = false
            }
        }

        binding.webView.webViewClient = object : WebViewClient() {

            lateinit var currentUrl: String

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                currentUrl = url
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

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}