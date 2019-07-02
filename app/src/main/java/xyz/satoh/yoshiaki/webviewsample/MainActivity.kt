package xyz.satoh.yoshiaki.webviewsample

import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import android.graphics.Bitmap


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById(R.id.webView) as WebView
        val targetUrl = "https://wrong.host.badssl.com/";
        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                takeMessage("shouldOverrideUrlLoading : " + url);
                return true;
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                takeMessage("onPageStarted : " + url);

            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                takeMessage("onPageFinished : " + url);
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                takeMessage("onReceivedSslError : " + error.toString());
                handler.cancel();
            }
        })
        webView.loadUrl(targetUrl)

    }

    fun takeMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
        Log.d("INFO", message);

    }
}
