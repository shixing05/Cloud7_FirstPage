package com.cloud7.firstpage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglePage extends Activity implements OnClickListener {
	private ImageView iv_back;
	private TextView tv_model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.singlepage);
		
		iv_back = (ImageView) findViewById(R.id.iv_back);
		tv_model = (TextView) findViewById(R.id.tv_model);
		
		iv_back.setOnClickListener(this);
		tv_model.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			Intent intent = new Intent(this,CenterActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.tv_model:
			Intent intent1 = new Intent(this,VersionAndModel.class);
			startActivity(intent1);
			finish();
			break;

		default:
			break;
		}
		
	}

}
