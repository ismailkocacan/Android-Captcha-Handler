package com.test.captchahandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.stackdeveloper.lib.CustomWebChromeClient;
import com.stackdeveloper.lib.CustomWebViewClient;
import com.stackdeveloper.lib.HandlerImageDataResult;
import com.stackdeveloper.lib.HandlerPageLoad;
import com.stackdeveloper.lib.ImageUtil;
import com.stackdeveloper.lib.JsObject;

public class MainActivity extends Activity
{

	private final static String CAPTCHA_MATCHING="captcha";
	private final static String URL_FORM="http://web-af910aac-7774-4560-990e-2ee3dbcf6d45.runnable.com";
	
	
	private JsObject jsObject;
	private ImageView mImgCaptcha;
	private MyWebViewPageLoadHandler mMyWebViewPageLoadHandler;
	private MyImageDataResultHandler mMyImageDataResultHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}
	
	
	private void initialize() 
	{
		jsObject = new JsObject(this);
		mMyImageDataResultHandler = new MyImageDataResultHandler();
		jsObject.addImageDataResultHandler(mMyImageDataResultHandler);
		
		CustomWebViewClient webViewClient = new CustomWebViewClient();
		mMyWebViewPageLoadHandler = new MyWebViewPageLoadHandler();
		webViewClient.addHandlerPageLoad(mMyWebViewPageLoadHandler);
		jsObject.getWebView().setWebViewClient(webViewClient);
		
		CustomWebChromeClient chromeClient = new CustomWebChromeClient();
		jsObject.getWebView().setWebChromeClient(chromeClient);
		
		mImgCaptcha = (ImageView)findViewById(R.id.imgCaptcha);	
	}

	public class MyImageDataResultHandler implements HandlerImageDataResult
	{
		@Override
		public void onConvertComplete(final byte[] imageData) 
		{
			runOnUiThread(new Runnable() 
			{
				public void run() 
				{
				  ImageUtil.setImageViewWithByteArray(mImgCaptcha, imageData);
				  Toast.makeText(getApplicationContext(), "Captcha Handle completed...", Toast.LENGTH_LONG).show();
				}
			}); 
		}
	}

	public class MyWebViewPageLoadHandler implements HandlerPageLoad
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{
			jsObject.getCaptchaImageFromImgAttributeAlt(CAPTCHA_MATCHING, mMyImageDataResultHandler);
		}
	}
	
	public void btnGetCaptchaOnClick(View sender)
	{
	    jsObject.getWebView().loadUrl(URL_FORM);
	}
 
}
