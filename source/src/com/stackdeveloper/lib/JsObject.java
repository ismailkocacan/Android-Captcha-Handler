package com.stackdeveloper.lib;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JsObject 
{
	private Context mContext;
	private WebView mWebView;
	private byte[] mDecodedImage;
	private HandlerImageDataResult mImageDataResultHandler;
	private HandlerWebViewHtmlContent mWebViewHtmlContentHandler;
	
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
	
	public void registerObject()
	{
		mWebView.addJavascriptInterface(this, "jsObject");
	}
	
	/**
	 * alone can not be called!
	 * @param base64Image
	 */
	@JavascriptInterface
	public void getBase64ImageString(String base64Image)
	{
		String cleanBase64Image = base64Image.replace("data:image/png;base64,", "");
		try 
		{
		  mDecodedImage = android.util.Base64.decode(cleanBase64Image, android.util.Base64.DEFAULT);	
		  if (mDecodedImage.length == 0) return;
		  if (mDecodedImage != null) mImageDataResultHandler.onConvertComplete(mDecodedImage);
		} catch (Exception e) 
		{
		  Toast.makeText(mContext, "Byte Conversion Error ! \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void addImageDataResultHandler(HandlerImageDataResult handler)
	{
		mImageDataResultHandler = handler;
	}
	
	/**
	 * alone can not be called!
	 * @param html
	 */
	@JavascriptInterface
	public void onGetHtmlContent(String html)
	{
	  if (mWebViewHtmlContentHandler != null)
		  mWebViewHtmlContentHandler.onGetHtmlContent(html);
	}
 
	public void jsGetHtmlContent(HandlerWebViewHtmlContent handler)
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
	
	public String jsElementsByName(String elementName,String elementValue)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("document.getElementsByName('"+elementName+"')[0].value ='" + elementValue + "';");
		return sb.toString();
	}
	
	public String jsSumbitFormByName(String name)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("document.forms['"+name+"'].submit();");
		return sb.toString();
	}
	
	public String jsDrawImage()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("var canvas = document.createElement(\"canvas\");");
		sb.append("document.body.appendChild(canvas);");
		sb.append("canvas.width = img.width;");
		sb.append("canvas.height = img.height;");
		sb.append("var ctx = canvas.getContext(\"2d\");");
		sb.append("ctx.drawImage(img,0,0,img.clientWidth,img.clientHeight);");
		sb.append("var dataURL = canvas.toDataURL();");
		sb.append("jsObject.getBase64ImageString(dataURL.toString());");
		return sb.toString();
	}
	
	public void getCaptchaImageFromImgAttributeSrc(String src,HandlerImageDataResult handler)
	{
		mImageDataResultHandler = handler;
		StringBuilder sb = new StringBuilder();
		sb.append(jsElementsByAttributeName().toString());
		sb.append("var img = getElementsByAttributeName('img', 'src', '"+src+"')[0];");
		sb.append(jsDrawImage());
		jsExecute(sb.toString());
	}
	
	public void getCaptchaImageFromImgAttributeId(String id,HandlerImageDataResult handler)
	{
		mImageDataResultHandler = handler;
		StringBuilder sb = new StringBuilder();
		sb.append(jsElementsByAttributeName().toString());
		sb.append("var img = document.getElementById('"+id+"');");
		sb.append(jsDrawImage());
		jsExecute(sb.toString());
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
