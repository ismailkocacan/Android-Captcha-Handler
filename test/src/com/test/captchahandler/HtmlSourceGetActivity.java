package com.test.captchahandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.stackdeveloper.lib.CustomWebChromeClient;
import com.stackdeveloper.lib.CustomWebViewClient;
import com.stackdeveloper.lib.HandlerPageLoad;
import com.stackdeveloper.lib.HandlerWebViewHtmlContent;
import com.stackdeveloper.lib.JsObject;

public class HtmlSourceGetActivity extends Activity 
{
	private final static String URL="http://ismailkocacan.blogspot.com.tr";
	
	private JsObject mJsObject;
	private WebView mWebView1;
	private MyWebViewPageLoadHandler mMyWebViewPageLoadHandler;
	private MyHandlerWebViewHtmlContent mMyHandlerWebViewHtmlContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_html_source_get);
		initialize();
	}
	
	public class MyWebViewPageLoadHandler implements HandlerPageLoad
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{		 
			mJsObject.jsGetHtmlContent(mMyHandlerWebViewHtmlContent);
		}	
	}
	
	public class MyHandlerWebViewHtmlContent implements HandlerWebViewHtmlContent
	{
		@Override
		public void onGetHtmlContent(String htmlContent) 
		{
		   Toast.makeText(getApplicationContext(), htmlContent, Toast.LENGTH_LONG).show();
		}	
	}
	
	
	private void initialize()
	{
		mWebView1 = (WebView)findViewById(R.id.webView1);
		mJsObject = new JsObject(this, mWebView1);
		
		mMyWebViewPageLoadHandler = new MyWebViewPageLoadHandler();
		mMyHandlerWebViewHtmlContent = new MyHandlerWebViewHtmlContent();
		
		CustomWebViewClient webViewClient = new CustomWebViewClient();
		webViewClient.addHandlerPageLoad(mMyWebViewPageLoadHandler);
		mJsObject.getWebView().setWebViewClient(webViewClient);
		
		CustomWebChromeClient chromeClient = new CustomWebChromeClient();
		mJsObject.getWebView().setWebChromeClient(chromeClient);
	}
	
	
	public void btnGetHtmlCode(View sender)
	{
		mWebView1.loadUrl(URL);
	}
}
