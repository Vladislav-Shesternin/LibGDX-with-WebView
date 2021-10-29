package com.veldan.libgdxwebview.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener
import androidx.fragment.app.Fragment
import com.veldan.libgdxwebview.activityMain
import com.veldan.libgdxwebview.databinding.FragmentWebviewBinding
import im.delight.android.webview.AdvancedWebView

lateinit var webViewFragment: WebViewFragment

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    private lateinit var webview: AdvancedWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewFragment = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWebviewBinding.inflate(inflater)

        webview = binding.webview.apply {
            setListener(activityMain, getAdvancedWebViewListener())
            setDownloadListener(getDownloadListener())
            setMixedContentAllowed(false)
            loadUrl("https://convertio.co/ru/")
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        webview.onResume()
    }

    override fun onPause() {
        webview.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        webview.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webview.onActivityResult(requestCode, resultCode, data)
    }

    private fun getAdvancedWebViewListener() = object : AdvancedWebView.Listener{
        override fun onPageStarted(url: String?, favicon: Bitmap?) {}
        override fun onPageFinished(url: String?) {}
        override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}
        override fun onDownloadRequested(url: String?, suggestedFilename: String?, mimeType: String?, contentLength: Long, contentDisposition: String?, userAgent: String?) {}
        override fun onExternalPageRequest(url: String?) {}
    }

    private fun getDownloadListener() = DownloadListener { url, _, _, _, _ ->
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        activityMain.startActivity(intent)
    }
}
