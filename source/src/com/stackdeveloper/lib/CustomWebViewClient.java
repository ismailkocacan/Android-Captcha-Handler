package com.stackdeveloper.lib;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient 
{
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler,SslError error) 
	{
		handler.proceed();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) 
	{
		super.onPageFinished(view, url);
	}
}
