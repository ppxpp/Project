package me.ppxpp.project.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import me.ppxpp.project.R;
import me.ppxpp.project.activity.LoginActivity;
import me.ppxpp.project.activity.SettingActivity;
import me.ppxpp.project.entity.MenuItem;

/**
 * Created by ppxpp on 2014/10/18.
 */
public class LeftMenuFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View mTitleBar;
    private ImageView mHead;
    private ListView mMenuList;
    private MenuAdapter menuAdapter;
    private ImageButton mSettingBtn;
    private MenuItem mLastSelectedItem;
    private MenuSelectedListener mMenuSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MenuSelectedListener)){
            throw new IllegalArgumentException("Activity must implement MenuSelectedListener");
        }
        mMenuSelectedListener = (MenuSelectedListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_left_menu,null);
        mTitleBar = view.findViewById(R.id.title_bar);
        mHead = (ImageView) view.findViewById(R.id.head);
        mHead.setOnClickListener(this);
        mMenuList = (ListView) view.findViewById(R.id.menu_list);
        menuAdapter = new MenuAdapter(getMenu());
        mMenuList.setAdapter(menuAdapter);
        mSettingBtn = (ImageButton) view.findViewById(R.id.setting_btn);
        mSettingBtn.setOnClickListener(this);
        mMenuList.setOnItemClickListener(this);
        mLastSelectedItem = (MenuItem) menuAdapter.getItem(0);
        mLastSelectedItem.selected = true;
        return view;
    }

    private List<MenuItem> getMenu(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        String[] menus = {"首页","今日热门","收藏","频道","业界动态","人人专栏","产品经理","产品设计","产品运营","视觉交互","频道订阅"};
        int[] type = {      1,      1,      1,    0,     1,          1,      1,      1,          1,        1,        1,      1};
        int[] resId = {R.drawable.ic_main_normal, R.drawable.ic_hot_normal, R.drawable.ic_flavor_normal,
                        0, R.drawable.default_channel_icon_normal, R.drawable.default_channel_icon_normal,
                R.drawable.default_channel_icon_normal,R.drawable.default_channel_icon_normal,
                R.drawable.default_channel_icon_normal,R.drawable.default_channel_icon_normal,
                R.drawable.ic_channel_setting_normal};
        for (int i = 0; i < menus.length; i++){
            MenuItem item = new MenuItem();
            item.title = menus[i];
            item.type = type[i];
            item.iconResId = resId[i];
            item.selected = false;
            menuItems.add(item);
        }
        menuItems.get(0).selected = true;
        menuItems.get(2).id = MenuItem.FLAVOR_MENU_ITEM_ID;
        return menuItems;
    }

    private void onHeadClicked(){
        boolean hasLogined = false;
        if (!hasLogined){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_btn:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.head:
                onHeadClicked();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MenuItem item = (MenuItem) menuAdapter.getItem(position);
        if (item.type == 0){
            return;
        }
        if (item.id == MenuItem.FLAVOR_MENU_ITEM_ID){
            if (mMenuSelectedListener != null){
                mMenuSelectedListener.onMenuSelected(item);
            }
            return;
        }
        item.selected = true;
        mLastSelectedItem.selected = false;
        mLastSelectedItem = item;
        menuAdapter.notifyDataSetChanged();
        if (mMenuSelectedListener != null){
            mMenuSelectedListener.onMenuSelected(item);
        }
    }


    private class MenuAdapter extends BaseAdapter{
        List<MenuItem> menuItems;

        public MenuAdapter(List<MenuItem> menuItems){
            this.menuItems = menuItems;
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public Object getItem(int position) {
            return menuItems.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return menuItems.get(position).type;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null){
                ViewHolder holder = new ViewHolder();
                if (getItemViewType(position) == 0){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_menu_item_1, parent, false);
                }else {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_menu_item_2, parent, false);
                    holder.icon = (ImageView) view.findViewById(R.id.menu_icon);
                }
                holder.title = (TextView) view.findViewById(R.id.menu_title);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            MenuItem item = (MenuItem) getItem(position);
            holder.title.setText(item.title);
            if (item.selected)
                view.setBackgroundResource(R.drawable.menu_item_bg_sel);
            else
                view.setBackgroundResource(R.drawable.menu_item_bg);
            if (item.type == 1){
                holder.icon.setImageResource(item.iconResId);
            }
            if (item.selected){
                view.setBackgroundResource(R.drawable.menu_item_bg_sel);
            }else{
                view.setBackgroundResource(R.drawable.menu_item_bg);
            }
            return view;
        }

        class ViewHolder{
            ImageView icon;
            TextView title;
        }
    }

    public interface MenuSelectedListener{
        public void onMenuSelected(MenuItem menuItem);
    }
}
