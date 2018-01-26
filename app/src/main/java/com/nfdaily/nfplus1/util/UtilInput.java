package com.nfdaily.nfplus1.util;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


/**
 * @author fengjingyu@foxmail.com
 */
public class UtilInput {

    /**
     * 弹出输入法
     */
    public static void openInputMethod(EditText view, final Context context) {
        // 弹出输入法
        view.setFocusable(true);
        view.requestFocus();
        view.selectAll();
        // 必须是handler.否则无法弹出 why?
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
            }
        }, 300);
    }

    public static void hiddenInputMethod(Context context) {
        if (((Activity) context).getCurrentFocus() != null) {
            if (((Activity) context).getCurrentFocus().getWindowToken() != null) {
                // 隐藏键盘
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
