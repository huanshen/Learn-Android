package com.example.shenjiaqi.webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private  WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webview);
      //  myWebView.loadUrl("file:///android_asset/my.html");
      //  myWebView.loadUrl("http://www.baidu.com");

        myWebView.loadData("<html> <body> Hello，world！</body> </html>" ,
                "text/html" ,"UTF-8");


        WebSettings webSettings = myWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setJavaScriptEnabled(true);

        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        myWebView.setWebViewClient(new MyWebViewClient());

        myWebView.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("MyApplication", message + " -- From line "
                        + lineNumber + " of "
                        + sourceID);
            }
        });

       /*
        PackageInfo webViewPackageInfo = WebView.getCurrentWebViewPackage();
        Log.d("MY_APP_TAG", "WebView version: " + webViewPackageInfo.versionName);
*/

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            if (!detail.didCrash()){
                Log.e("MY_APP_TAG", "System killed the WebView rendering process " +
                        "to reclaim memory. Recreating...");

                if (myWebView != null) {
                    ViewGroup webViewContainer =
                            (ViewGroup) findViewById(R.id.root);
                    webViewContainer.removeView(myWebView);
                    myWebView.destroy();
                    myWebView = null;
                }
                return true;
            }

            // access violation.
            Log.e("MY_APP_TAG", "The WebView rendering process crashed!");

            // In this example, the app itself crashes after detecting that the
            // renderer crashed. If you choose to handle the crash more gracefully
            // and allow your app to continue executing, you should 1) destroy the
            // current WebView instance, 2) specify logic for how the app can
            // continue executing, and 3) return "true" instead.
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
