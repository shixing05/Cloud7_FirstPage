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
		// �õ���Ļ�Ŀ��
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		// �õ���Ļ�Ŀ��
		mMenuWidth = mScreenWidth - paddingWidth;
		mContentWidth = mScreenWidth;
		mHalfMenuWidth = mMenuWidth / 2;
		// ���ݿ��ֵ�������ؼ��Ĵ�С
		mMenu.getLayoutParams().width = mMenuWidth;
		mContent.getLayoutParams().width = mContentWidth;

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// ���ò��ֳ�ʼ�İڷ�״̬
		//if (changed) {
			this.scrollTo(mMenuWidth, 0);
	//	}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		//�õ���ǰHorizontalScrollView�Ļ�������,��ȫ ��ʾ��ʱ�����Ϊ0,��ȫ���ص�ʱ�����Ϊ1.
		float scale = l*1.0f/mMenuWidth;	
		//���ݻ����ı�������������������ű���,�˵��������ű���,�Լ��˵�����͸���ȵı仯
		float contentScale = 0.8f + scale*0.2f;	//�Ӵ�������С
		float menuSale = 1.0f - scale * 0.3f;;	//��С�������
		float alphaScale = 0.4f+(1-scale)*0.6f;//��͸��������ò�͸��
		
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.8f);//���ò˵������Զ��� 
		
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
			// ��û����ľ���
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth) {
				// ���ز˵���
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

