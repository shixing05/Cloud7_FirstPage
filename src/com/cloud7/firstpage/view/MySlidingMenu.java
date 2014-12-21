package com.cloud7.firstpage.view;

import com.cloud7.firstpage.utils.CommonUtils;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {
	private Context context;
	private LinearLayout mWrapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int paddingWidth;
	private int mMenuWidth;
	private int mContentWidth;
	private int mHalfMenuWidth;
	boolean isOpen;

	public MySlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public MySlidingMenu(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWrapper = (LinearLayout) getChildAt(0);
		mMenu = (ViewGroup) mWrapper.getChildAt(0);
		mContent = (ViewGroup) mWrapper.getChildAt(1);
		paddingWidth = CommonUtils.dip2px(context, 80);
		// 得到屏幕的宽度
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		// 得到屏幕的宽度
		mMenuWidth = mScreenWidth - paddingWidth;
		mContentWidth = mScreenWidth;
		mHalfMenuWidth = mMenuWidth / 2;
		// 根据宽度值来测量控件的大小
		mMenu.getLayoutParams().width = mMenuWidth;
		mContent.getLayoutParams().width = mContentWidth;

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// 设置布局初始的摆放状态
		//if (changed) {
			this.scrollTo(mMenuWidth, 0);
	//	}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		//得到当前HorizontalScrollView的滑动比例,完全 显示的时候比例为0,完全隐藏的时候比例为1.
		float scale = l*1.0f/mMenuWidth;	
		//根据滑动的比例来计算主窗体的缩放比例,菜单栏的缩放比例,以及菜单栏的透明度的变化
		float contentScale = 0.8f + scale*0.2f;	//从大慢慢变小
		float menuSale = 1.0f - scale * 0.3f;;	//从小慢慢变大
		float alphaScale = 0.4f+(1-scale)*0.6f;//从透明慢慢变得不透明
		
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.8f);//设置菜单的属性动画 
		
		ViewHelper.setScaleX(mMenu, menuSale);
		ViewHelper.setScaleY(mMenu, menuSale);
		ViewHelper.setAlpha(mMenu, alphaScale);
		
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, contentScale);
		ViewHelper.setScaleY(mContent, contentScale);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			// 获得滑动的距离
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth) {
				// 隐藏菜单栏
				this.smoothScrollTo(mMenuWidth, 0);
			} else {
				this.smoothScrollTo(0, 0);
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	public void toggle() {
		
		if(isOpen){
			closeMenu();
			isOpen = false;
		}else{
			openMenu();
			isOpen = true;	
		}
	}

	private void openMenu() {
		this.smoothScrollTo(0, 0);
	}
	private void closeMenu() {
		this.smoothScrollTo(mMenuWidth, 0);
	}
}

