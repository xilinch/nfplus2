package com.nfdaily.nfplus1.util;

import android.content.Context;
import android.widget.Toast;

import com.nfdaily.nfplus1.BuildConfig;
import com.nfdaily.nfplus1.application.ReaderApplication;

public class UtilToast {

	private static Toast toast;

	// toast重复显示不消失问题
	public static void toastShow(int arg) {
		if (toast == null ) {
			toast = Toast.makeText(ReaderApplication.getInstance(), arg, Toast.LENGTH_SHORT);
		} else {
			// toast.cancel(); //取消后只会显示一次
			toast.setText(arg);
		}
		if(toast != null && ReaderApplication.getInstance() != null){
			toast.show();
		}
	}

	// toast重复显示不消失问题
	public static void toastShowLong(String arg) {
		if (toast == null) {
			toast = Toast.makeText(ReaderApplication.getInstance(), arg, Toast.LENGTH_LONG);
		} else {
			// toast.cancel(); //取消后只会显示一次
			toast.setText(arg);
		}
		if(toast != null && ReaderApplication.getInstance() != null){
			toast.show();
		}
	}
	// toast重复显示不消失问题
	public static void toastShowLong(int arg) {
		if (toast == null ) {
			toast = Toast.makeText(ReaderApplication.getInstance(), arg, Toast.LENGTH_LONG);
		} else {
			// toast.cancel(); //取消后只会显示一次
			toast.setText(arg);
		}
		if(toast != null && ReaderApplication.getInstance() != null){
			toast.show();
		}
	}

	// toast重复显示不消失问题
		public static void toastShow(String arg) {
			if (toast == null ) {
				toast = Toast.makeText(ReaderApplication.getInstance(), arg, Toast.LENGTH_SHORT);
			} else {
				// toast.cancel(); //取消后只会显示一次
				toast.setText(arg);
			}
			if(toast != null && ReaderApplication.getInstance() != null){
				toast.show();
			}
		}

	/**
	 * 调试toast
	 */
	public static void dShortToastShow(String msg){
		if(BuildConfig.DEBUG){
			if (toast == null ) {
				toast = Toast.makeText(ReaderApplication.getInstance(), msg, Toast.LENGTH_SHORT);
			} else {
				// toast.cancel(); //取消后只会显示一次
				toast.setText(msg);
			}
			if(toast != null && ReaderApplication.getInstance() != null){
				toast.show();
			}
		} else {
			//do nothing
		}
	}
}
