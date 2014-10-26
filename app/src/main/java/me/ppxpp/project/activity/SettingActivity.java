package me.ppxpp.project.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import me.ppxpp.project.R;
import me.ppxpp.project.fragment.SettingFragment;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, new SettingFragment(), "setting");
        transaction.commit();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
