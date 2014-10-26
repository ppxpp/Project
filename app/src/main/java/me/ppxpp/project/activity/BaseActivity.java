package me.ppxpp.project.activity;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by ppxpp on 2014/10/18.
 */
public class BaseActivity extends Activity {

    protected final String tag = getClass().getName();
    protected Context mContext = this;

    protected void debug(String msg){
        Log.d(tag, msg);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
