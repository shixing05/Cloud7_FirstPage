package com.cloud7.firstpage.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
	public static final String CACHE_FILE_NAME = "firstpage-westengine";
	private static SharedPreferences mSharedPreferences;

	public static void putBoolean(Context context, String key, boolean value) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	/**
	 * 取出一个boolean类型的值
	 * @param context
	 * @param key 键
	 * @param defValue 缺省值
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getBoolean(key, defValue);
	}

}

