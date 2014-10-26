package me.ppxpp.project.activity;

import android.os.Bundle;
import android.view.MenuItem;

import me.ppxpp.project.R;
import me.ppxpp.project.fragment.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
