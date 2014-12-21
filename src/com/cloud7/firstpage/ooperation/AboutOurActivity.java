package com.cloud7.firstpage.ooperation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud7.firstpage.CenterActivity;
import com.cloud7.firstpage.R;

public class AboutOurActivity extends Activity implements OnClickListener {
	private LinearLayout ll_back;
	private LinearLayout ll_commint;
	private TextView text_title;
	private TextView open_our;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about_our);
		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_commint = (LinearLayout) findViewById(R.id.ll_commint);
		text_title = (TextView) findViewById(R.id.text_title);
		open_our = (TextView) findViewById(R.id.open_our);

		open_our.setOnClickListener(this);
		ll_commint.setVisibility(View.INVISIBLE);
		text_title.setText("关于我们");
		ll_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			Intent intent = new Intent(this, CenterActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.open_our:
			Intent intent2 = new Intent(this, OurWebActivity.class);
			startActivity(intent2);
			finish();
			break;
		}

	}
}
