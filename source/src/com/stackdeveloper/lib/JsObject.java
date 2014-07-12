package com.stackdeveloper.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class JsObject 
{
	private Context mContext;
	private WebView mWebView;
	
	private WebViewHtmlContentHandler mWebViewHtmlContentHandler;
	
	public JsObject(Context context)
	{
		mContext = context;
		mWebView = new WebView(mContext);
		registerObject();
	}
	
	public JsObject(Context context,WebView webView)
	{
		mContext = context;
		mWebView = webView;
		registerObject();
	}
	
	@SuppressLint("JavascriptInterface")
	public void registerObject()
	{
		mWebView.addJavascriptInterface(this, "jsObject");
	}
	
	
	@JavascriptInterface
	public void onGetHtmlContent(String html)
	{
	  if (mWebViewHtmlContentHandler != null)
		  mWebViewHtmlContentHandler.onGetHtmlContent(html);
	}
 
	public void jsGetHtmlContent(WebViewHtmlContentHandler handler)
	{
		mWebViewHtmlContentHandler = handler; 
		StringBuilder sb = new StringBuilder();
	    sb.append("javascript:");
	    sb.append("{ ");
	    sb.append(" jsObject.onGetHtmlContent(document.documentElement.innerHTML);");
		sb.append("}");
		mWebView.loadUrl(sb.toString());
	}
}
