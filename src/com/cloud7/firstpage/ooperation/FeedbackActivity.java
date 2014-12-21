package com.cloud7.firstpage.ooperation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloud7.firstpage.CenterActivity;
import com.cloud7.firstpage.R;

public class FeedbackActivity extends Activity implements OnClickListener {

	private LinearLayout ll_back;
	private LinearLayout ll_commint;
	private TextView text_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_feedback);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_commint = (LinearLayout) findViewById(R.id.ll_commint);
		text_title =(TextView) findViewById(R.id.text_title);
		
		text_title.setText("意见反馈");
		ll_back.setOnClickListener(this);
		ll_commint.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			Intent intent = new Intent(this,CenterActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.ll_commint:
			Toast.makeText(this, "稍等....", 0).show();
			break;
		default:
			break;
		}
		
	}
}
