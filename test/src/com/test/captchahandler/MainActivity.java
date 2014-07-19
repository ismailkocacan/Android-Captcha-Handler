package com.test.captchahandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.stackdeveloper.lib.HandlerImageDataResult;
import com.stackdeveloper.lib.ImageUtil;
import com.stackdeveloper.lib.JsObject;

public class MainActivity extends Activity 
{

	private final static String URL_FORM="http://runnable.com/U8rWe5yFx-sLegW6/output";
	
	private JsObject jsObject;
	private MyHandlerImageDataResult mMyHandlerImageDataResult;
	private ImageView mImgCaptcha;
	
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
		mMyHandlerImageDataResult = new MyHandlerImageDataResult();
		jsObject.addImageDataResultHandler(mMyHandlerImageDataResult);
		mImgCaptcha = (ImageView)findViewById(R.id.imgCaptcha);	
	}


	public class MyHandlerImageDataResult implements HandlerImageDataResult
	{
		@Override
		public void onConvertComplete(final byte[] imageData) 
		{
			runOnUiThread(new Runnable() 
			{
				public void run() 
				{
				  ImageUtil.setImageViewWithByteArray(mImgCaptcha, imageData);
				}
			});
		}
	}
		
	public void btnGetCaptchaOnClick(View sender)
	{
	    jsObject.getWebView().loadUrl(URL_FORM);
	}
}
