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
	        //����WebView���ԣ��ܹ�ִ��Javascript�ű�  
	        webview.getSettings().setJavaScriptEnabled(true);  
	        //������Ҫ��ʾ����ҳ  
	        webview.loadUrl("http://www.cloud7.com.cn");  
	        //����Web��ͼ  
	        webview.setWebViewClient(new HelloWebViewClient ());  
	    }  
	      
	    @Override 
	    //���û���  
	    //����Activity���onKeyDown(int keyCoder,KeyEvent event)����  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {  
	            webview.goBack(); 
	            return true;  
	        }  
	        return false;  
	    }  
	      
	    //Web��ͼ  
	    private class HelloWebViewClient extends WebViewClient {  
	        @Override 
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
	            view.loadUrl(url);  
	            return true;  
	        }  
	    }  
	} 
