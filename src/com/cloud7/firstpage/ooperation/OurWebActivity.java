package com.cloud7.firstpage.ooperation;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cloud7.firstpage.R;

public class OurWebActivity extends Activity {
	    private WebView webview;  
	    @Override 
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.web_view_for_cloud7);  
	        webview = (WebView) findViewById(R.id.webView);  
	        //设置WebView属性，能够执行Javascript脚本  
	        webview.getSettings().setJavaScriptEnabled(true);  
	        //加载需要显示的网页  
	        webview.loadUrl("http://www.cloud7.com.cn");  
	        //设置Web视图  
	        webview.setWebViewClient(new HelloWebViewClient ());  
	    }  
	      
	    @Override 
	    //设置回退  
	    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {  
	            webview.goBack(); 
	            return true;  
	        }  
	        return false;  
	    }  
	      
	    //Web视图  
	    private class HelloWebViewClient extends WebViewClient {  
	        @Override 
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
	            view.loadUrl(url);  
	            return true;  
	        }  
	    }  
	} 
