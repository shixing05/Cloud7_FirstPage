package com.cloud7.firstpage.ooperation;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cloud7.firstpage.CenterActivity;
import com.cloud7.firstpage.R;

public class EditActivity extends Activity implements OnClickListener{
	private ImageView iv_click;
	private ImageView iv_show;
	private ImageView iv_edit;
	private LinearLayout ll_back;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		sp = getSharedPreferences("CONFIG", MODE_PRIVATE);
		setContentView(R.layout.edit_first_page);
		iv_click = (ImageView) findViewById(R.id.iv_click);
		iv_show = (ImageView) findViewById(R.id.iv_show);
		iv_edit = (ImageView) findViewById(R.id.iv_edit);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setOnClickListener(this);
		iv_click.setOnClickListener(this);
		iv_edit.setOnClickListener(this);
	}
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    super.onActivityResult(requestCode, resultCode, data);  
	    if (requestCode==11) {  
	            if (null!=data) {  
	                Uri uri = data.getData();  
	                //根据需要，也可以加上Option这个参数  
	                InputStream inputStream;
					try {
						inputStream = getContentResolver().openInputStream(uri);
						 Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			               iv_show.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}  
	            }  
	        }  
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_click:
			Intent intent = new Intent();
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/*");
			//根据版本号不同使用不同的Action
			if (Build.VERSION.SDK_INT <19) {
				intent.setAction(Intent.ACTION_GET_CONTENT);
			}else {
				intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
			}
			startActivityForResult(intent, 11);
			break;
		case R.id.ll_back:
			Intent intent2 = new Intent();
			intent2.setClass(EditActivity.this, CenterActivity.class);
			startActivity(intent2);
		case R.id.iv_edit:
			Editor edit = sp.edit();
			int itemSize = sp.getInt("itemSize", 0);
			itemSize+=1;
			edit.putInt("itemSize", itemSize);
			edit.commit();
			Intent intent3 = new Intent();
			intent3.setClass(EditActivity.this, CenterActivity.class);
			startActivity(intent3);
		default:
			break;
		}
		
	}    
}

