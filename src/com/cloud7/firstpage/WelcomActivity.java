package com.cloud7.firstpage;

import com.cloud7.firstpage.utils.CacheUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class WelcomActivity extends Activity {
	// �ж��û��Ƿ��ǵ�һ�δ�Ӧ��
				public static final String IS_FIRST_OPEN = "is_first_open";

				@Override
				protected void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					requestWindowFeature(Window.FEATURE_NO_TITLE);
					setContentView(R.layout.activity_welcome);
					/**
					 * ģ��һ��ʱ��,������Ҫ�޸�Ϊ����ʱ���������
					 */
					// TODO
					new Thread() {
						public void run() {
							try {

								Thread.sleep(2000); // ���߳���Ҫ��Ⱦ����
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									enterWelcome(); // ������һ������
								}

							});

						};
					}.start();

				}

				private void enterWelcome() {
					boolean isFirstOpen = CacheUtils.getBoolean(WelcomActivity.this,
							IS_FIRST_OPEN, true);
					if (isFirstOpen) {
						// ��ǰ�ǵ�һ�δ�, ��ת������ҳ��
						System.out.println("��ת������ҳ��");
						startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
					} else {
						// �Ѿ��򿪹�, ��ת��������
						System.out.println("��ת��������");
						startActivity(new Intent(WelcomActivity.this, CenterActivity.class));
					}
					finish();
				}
	}
