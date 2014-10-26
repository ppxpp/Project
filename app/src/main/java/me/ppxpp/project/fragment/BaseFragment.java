package me.ppxpp.project.fragment;

import android.app.Fragment;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected String tag = getClass().getName();

    protected void debug(String msg){
        Log.d(tag, msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(tag);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(tag);
    }
}
