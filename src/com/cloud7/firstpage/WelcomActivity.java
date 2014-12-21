package com.cloud7.firstpage;

import com.cloud7.firstpage.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class WelcomActivity extends Activity {
	// 判断用户是否是第一次打开应用
				public static final String IS_FIRST_OPEN = "is_first_open";

				@Override
				protected void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					requestWindowFeature(Window.FEATURE_NO_TITLE);
					setContentView(R.layout.activity_welcome);
					/**
					 * 模拟一个时间,这里需要修改为不定时连接网络的
					 */
					// TODO
					new Thread() {
						public void run() {
							try {

								Thread.sleep(2000); // 主线程需要渲染界面
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									enterWelcome(); // 进入下一个界面
								}

							});

						};
					}.start();

				}

				private void enterWelcome() {
					boolean isFirstOpen = CacheUtils.getBoolean(WelcomActivity.this,
							IS_FIRST_OPEN, true);
					if (isFirstOpen) {
						// 当前是第一次打开, 跳转到引导页面
						System.out.println("跳转到引导页面");
						startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
					} else {
						// 已经打开过, 跳转到主界面
						System.out.println("跳转到主界面");
						startActivity(new Intent(WelcomActivity.this, CenterActivity.class));
					}
					finish();
				}
	}
