package com.test.captchahandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void btnGetCaptchaOnClick(View sender)
	{
		Toast.makeText(this,"I'am working !", Toast.LENGTH_LONG).show();
	}
}
