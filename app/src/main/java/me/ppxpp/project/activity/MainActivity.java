package me.ppxpp.project.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import me.ppxpp.project.R;
import me.ppxpp.project.entity.MenuItem;
import me.ppxpp.project.fragment.ArticleListFragment;
import me.ppxpp.project.fragment.LeftMenuFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener, LeftMenuFragment.MenuSelectedListener{

    private SlidingMenu mSlidingMenu;
    private final int MENU_OFFSET = 70;//offset in dp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HashMap a;
        setContentView(R.layout.activity_main);
        setupMenu();
        View barView = LayoutInflater.from(mContext).inflate(R.layout.actionbar, null);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(barView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        barView.findViewById(R.id.actionbar_left_btn).setOnClickListener(this);
        barView.findViewById(R.id.actionbar_right_btn).setOnClickListener(this);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.content, new ArticleListFragment(),"aa");
        transaction.commit();
        //禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);
        //定义发送策略
        MobclickAgent.updateOnlineConfig(this);
    }

    private void setupMenu(){
        mSlidingMenu = new SlidingMenu(mContext);
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        Resources r = getResources();
        float offset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, MENU_OFFSET, r.getDisplayMetrics());
        mSlidingMenu.setBehindOffset((int) offset);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setMenu(R.layout.left_menu);
        mSlidingMenu.setSecondaryMenu(R.layout.right_menu);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.left_menu, new LeftMenuFragment(), "left_menu");
        transaction.commit();
    }

    public void toggleLeftMenu(){
        mSlidingMenu.toggle(true);
    }

    public void toggleRightMenu(){
        if (mSlidingMenu.isSecondaryMenuShowing()){
            mSlidingMenu.showContent(true);
        }else {
            mSlidingMenu.showSecondaryMenu(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionbar_left_btn:
                toggleLeftMenu();
                break;
            case R.id.actionbar_right_btn:
                toggleRightMenu();
                break;
        }
    }

    @Override
    public void onMenuSelected(MenuItem menuItem) {
        if (menuItem.id == MenuItem.FLAVOR_MENU_ITEM_ID){
            startActivity(new Intent(this, FavorActivity.class));
            return;
        }
        mSlidingMenu.toggle(true);
        Toast.makeText(this, "select " + menuItem.title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mSlidingMenu.isMenuShowing()){
            mSlidingMenu.toggle(true);
            return;
        }
        super.onBackPressed();
    }
}
