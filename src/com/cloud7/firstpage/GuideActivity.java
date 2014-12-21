package com.cloud7.firstpage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.cloud7.firstpage.utils.CacheUtils;

public class GuideActivity extends Activity implements OnPageChangeListener, OnClickListener {

	private List<ImageView> imageViewList;
	private ViewPager mViewPager;
	private LinearLayout llPointGroup;
	private View selectPoint;
	private int basicWidth;
	private Button btnStartExperience; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guid);

		init();
	}

	private void init() {
		mViewPager = (ViewPager) findViewById(R.id.vp_welcome);
		btnStartExperience = (Button) findViewById(R.id.open);
		llPointGroup = (LinearLayout) findViewById(R.id.ll_welcome_point_group);
		selectPoint = findViewById(R.id.select_point);

		initData();

		// 设置适配器给ViewPager
		MyAdapter mAdapter = new MyAdapter();
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		btnStartExperience.setOnClickListener(this);

		// 获取间距
		selectPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						// 把当前的事件给移除监听.
						selectPoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

						// 获取间距
						basicWidth = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
						System.out.println("basicWidth: " + basicWidth);
					}
				});
	}
	private void initData() {
		int[] imageResIDs = { R.drawable.welcome01, R.drawable.welcome02,
				R.drawable.welcome03, R.drawable.welcome04,
				R.drawable.welcome05, R.drawable.welcome06 };

		imageViewList = new ArrayList<ImageView>();

		ImageView iv;
		View v;
		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);
			imageViewList.add(iv);

			v = new View(this);
			v.setBackgroundResource(R.drawable.point_normal);
			LayoutParams params = new LayoutParams(14, 14);
			if (i != 0) {
				params.leftMargin = 15;
			}
			v.setLayoutParams(params);
			llPointGroup.addView(v);
		}
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = imageViewList.get(position);
			container.addView(imageView);
			return imageView;
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

		float leftMargin = basicWidth * (position + positionOffset);

		RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) selectPoint
				.getLayoutParams();
		lp.leftMargin = (int) leftMargin;
		selectPoint.setLayoutParams(lp);
	}
	@Override
	public void onPageSelected(int position) {
		if (position == imageViewList.size() - 1) {
			btnStartExperience.setVisibility(View.VISIBLE);
		} else {
			btnStartExperience.setVisibility(View.GONE);
		}
	}
	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onClick(View v) {
		CacheUtils.putBoolean(this, WelcomActivity.IS_FIRST_OPEN, false);
		startActivity(new Intent(this, CenterActivity.class));

		finish();
	}
}

