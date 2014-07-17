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
		jsExecute("jsObject.onGetHtmlContent(document.documentElement.innerHTML);");
	}
	
	public String jsElementsByAttributeName()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("function getElementsByAttributeName(tagName, attributeName, attributeValue) { ");
		sb.append("  var i, n, objs=[], els=document.getElementsByTagName(tagName), len=els.length;");
		sb.append("  for (i=0; i<len; i++) { ");
		sb.append("    n = els[i][attributeName]; ");
		sb.append("    if (n && (n==attributeValue)) { ");
		sb.append("      objs.push(els[i]);");
		sb.append("    }");
		sb.append("  }");
		sb.append("  return objs;");
		sb.append("}");
		return sb.toString();
	}
	
	public void jsExecute(String javaScript)
	{
		StringBuilder sb = new StringBuilder();
	    sb.append("javascript:");
	    sb.append("{ ");
	    sb.append(javaScript);
		sb.append("}");
		mWebView.loadUrl(sb.toString());
	}
}
