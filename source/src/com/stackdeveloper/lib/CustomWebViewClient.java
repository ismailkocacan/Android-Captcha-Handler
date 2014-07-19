package com.stackdeveloper.lib;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient 
{
	public HandlerPageLoad mHandlerPageLoad;
	
	public CustomWebViewClient()
	{
		
	}
	
	public CustomWebViewClient(HandlerPageLoad handler)
	{
		mHandlerPageLoad = handler;
	}
	
	public void addHandlerPageLoad(HandlerPageLoad handler)
	{
		mHandlerPageLoad = handler;
	}
	
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler,SslError error) 
	{
		handler.proceed();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) 
	{
		super.onPageFinished(view, url);
		if (mHandlerPageLoad != null) mHandlerPageLoad.onPageLoad(view, url);
	}
}
