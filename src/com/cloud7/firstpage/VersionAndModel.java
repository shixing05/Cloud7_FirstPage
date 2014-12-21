package com.cloud7.firstpage;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloud7.firstpage.fragment.FragmentMode;
import com.cloud7.firstpage.fragment.FragmentSinglePage;

public class VersionAndModel extends Activity implements OnClickListener {
	private TextView tv_model;
	private TextView tv_single_page;
	
	private FragmentManager fm;
	private ImageView iv_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.version_mode);
		fm = getFragmentManager();
		tv_model = (TextView) findViewById(R.id.tv_model);
		tv_single_page = (TextView) findViewById(R.id.tv_single_page);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		openModeFragment();
		tv_model.setOnClickListener(this);
		tv_single_page.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_model:
			tv_model.setBackgroundResource(R.drawable.background_shape_version_select);
			tv_single_page.setBackgroundResource(R.drawable.background_shape_single_page);
			// 跳转到 模板页fragment
			openModeFragment();
			break;
		case R.id.tv_single_page:
			tv_single_page.setBackgroundResource(R.drawable.background_shape_single_page_select);
			tv_model.setBackgroundResource(R.drawable.background_shape_version_norm);
			// 跳转到单页fragment
			openSinglePageFragment();
			break;
		case R.id.iv_back:
			startActivity(new Intent(VersionAndModel.this,CenterActivity.class));
			overridePendingTransition(R.anim.activity_down, R.anim.activity_normal);
			finish();
			break;
		default:
			break;
		}
	}
	private void openSinglePageFragment() {
		// 使用管理器对象开启事物, 并且得到事务的对象
		FragmentTransaction ft = fm.beginTransaction();
		// 进行替换
		ft.replace(R.id.framelayout, new FragmentSinglePage());
		// 提交事务
		ft.commit();
	}

	private void openModeFragment() {
		// 使用管理器对象开启事物, 并且得到事务的对象
		FragmentTransaction ft = fm.beginTransaction();
		// 进行替换
		ft.replace(R.id.framelayout, new FragmentMode());
		// 提交事务
		ft.commit();
	}
}

