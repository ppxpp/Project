package me.ppxpp.project.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import me.ppxpp.project.R;
import me.ppxpp.project.fragment.ArticleListFragment;

/**
 * Created by ppxpp on 2014/10/22.
 */
public class FavorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.disableFooter();
        fragment.setLastUpdateLabel(null);
        fragment.setOnPullToRefreshListener(mOnRefreshListener);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
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

    PullToRefreshBase.OnRefreshListener2 mOnRefreshListener = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            debug("onPullDownToRefresh in " + tag);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            debug("onPullUpToRefresh in "+tag);
        }
    };
}
