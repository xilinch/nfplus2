package com.nfdaily.nfplus1.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;


/**
 * @Title DensityUtils
 * @Package
 * @Description DensityUtils是一个像素与dp转换的工具
 * @author
 * @date
 * @version
 */
public class UtilsDensity {
	/**
	 * 根据手机的分辨率dp 的单位转成px(像素)
	 * 
	 * @param context
	 * @param dpValue
	 *            dp
	 * @return 返回像素
	 */
	public static int dipTopx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dpValue*scale + 0.5f*(dpValue>=0?1:-1)); 
	}

	/**
	 * 根据手机的分辨率px(像素) 的单位 转成dp
	 * 
	 * @param context
	 * @param pxValue
	 *            像素
	 * @return 返回dp
	 */
	public static int pxTodip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int pxTosp(Context context,float pxValue,float fontScale){
		return (int)(pxValue/fontScale + 0.5f);
	}
	
	public static int spTopx(float spValue,float fontScale){
		return (int)(spValue * fontScale + 0.5f);
	}
	
	@SuppressLint("NewApi")
	public static int[] getScreenSize(Context context) {
		int[] screenSize = new int[2];
		int measuredWidth = 0;
		int measuredheight = 0;
		Point size = new Point();
		WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			measuredWidth = size.x;
			measuredheight = size.y;
		} else {
			Display d = w.getDefaultDisplay();
			measuredWidth = d.getWidth();
			measuredheight = d.getHeight();
		}
		screenSize[0] = measuredWidth;
		screenSize[1] = measuredheight;

		return screenSize;
	}


	/**
	 * 获取屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * convert sp to its equivalent px
	 *
	 * 将sp转换为px
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
}
