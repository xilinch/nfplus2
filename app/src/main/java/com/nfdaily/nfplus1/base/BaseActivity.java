package com.nfdaily.nfplus1.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.nfdaily.nfplus1.util.ActivityCollector;
import com.nfdaily.nfplus1.util.UtilInput;

/**
 * Created by xilinch on 17-12-8.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected String TAG = this.getClass().getSimpleName();

    public <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 设置监听器
     */
    protected abstract void initListener();

    /**
     * 配置数据
     */
    protected abstract void initData();


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActivityCollector.addActivityToStack(this);
        Log.i("my", TAG + " onCreate");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("my", TAG + " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("my", TAG + " onStop");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("my", TAG + " onResume");
    }


    @Override
    public void finish() {
        super.finish();
        UtilInput.hiddenInputMethod(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("my", TAG + " onDestroy");
        ActivityCollector.delActivityFromStack(this);
    }

}
