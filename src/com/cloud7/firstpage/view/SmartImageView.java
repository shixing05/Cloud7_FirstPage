package com.cloud7.firstpage.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SmartImageView extends ImageView{
	public SmartImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public SmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public SmartImageView(Context context) {
		super(context);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bitmap bm = (Bitmap) msg.obj;
			SmartImageView.this.setImageBitmap(bm);
		};
	};
	public void setImageUrl(final String path){
		
		if(TextUtils.isEmpty(path)){
			return;
		}else{
			new Thread(){
				public void run() {
					try {
						URL url = new URL(path);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(5000);
						int code = conn.getResponseCode();
						if(code == 200){
							InputStream is = conn.getInputStream();
							Bitmap bm = BitmapFactory.decodeStream(is);
							
							Message msg = Message.obtain();
							msg.obj = bm;
							
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();
			
		}
		
	}

}
