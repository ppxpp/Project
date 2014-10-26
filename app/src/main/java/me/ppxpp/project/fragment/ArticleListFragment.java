package me.ppxpp.project.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;

import me.ppxpp.project.R;
import me.ppxpp.project.entity.Article;
import me.ppxpp.project.widget.ArticleListAdatper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends BaseFragment {


    public ArticleListFragment() {
        // Required empty public constructor
        mArticleLists = new ArrayList<Article>();
        isHeaderEnabled = true;
        isFooterEnabled = true;
    }

    private List<Article> mArticleLists;
    private PullToRefreshListView mPTRListView;
    private ListView mArticleListView;
    private ArticleListAdatper mAdatper;

    private String mPullLabel, mReleaseLabel, mRefreshLabel, mLastUpdateLabel;
    private boolean isHeaderEnabled, isFooterEnabled;
    private AdapterView.OnItemClickListener mOnItemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_list, container, false);
        mPTRListView = (PullToRefreshListView) view.findViewById(R.id.ptr_article_listview);
        mArticleListView = mPTRListView.getRefreshableView();
        for (int i = 0; i < 10; i++){
            Article article = new Article();
            article.setTitle("article title " + i);
            if (i % 2 == 0){
                article.setImageCount(Article.SINGLE_IMG);
            }else{
                article.setImageCount(Article.MULTI_IMG);
            }
            mArticleLists.add(article);
        }
        mAdatper = new ArticleListAdatper(mArticleLists);
        mPTRListView.setAdapter(mAdatper);
        mPTRListView.setOnRefreshListener(mRefreshListener);
        mPTRListView.setOnItemClickListener(mOnItemClickListener);

        if (isHeaderEnabled && isFooterEnabled)
            mPTRListView.setMode(PullToRefreshBase.Mode.BOTH);
        else if (isHeaderEnabled && !isFooterEnabled)
            mPTRListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        else if (!isHeaderEnabled && isFooterEnabled)
            mPTRListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        else
            mPTRListView.setMode(PullToRefreshBase.Mode.DISABLED);

        if (isHeaderEnabled) {

            mPullLabel = mPullLabel != null ? mPullLabel : getString(R.string.pull_up_to_load);
            mPullLabel = mPullLabel != null ? mPullLabel : getString(R.string.pull_to_refresh);
            mRefreshLabel = mRefreshLabel != null ? mRefreshLabel : getString(R.string.refreshing);

            ILoadingLayout headerLL = mPTRListView.getLoadingLayoutProxy(true, false);
            if (mPullLabel != null)
                headerLL.setPullLabel(mPullLabel);
            if (mReleaseLabel != null)
                headerLL.setReleaseLabel(mReleaseLabel);
            if (mRefreshLabel != null)
                headerLL.setRefreshingLabel(mRefreshLabel);
        }
        //headerLL.setLastUpdatedLabel(getString(R.string.last_refresh_time, 10, 9, 21, 31));
        if (isFooterEnabled) {
            ILoadingLayout footerLL = mPTRListView.getLoadingLayoutProxy(false, true);
            footerLL.setPullLabel(getString(R.string.pull_up_to_load));
            footerLL.setReleaseLabel(getString(R.string.release_up_to_load));
            footerLL.setRefreshingLabel(getString(R.string.pull_up_loading));
        }
        return view;
    }
    PullToRefreshBase.OnRefreshListener2 mExternalListener;
    PullToRefreshBase.OnRefreshListener2 mRefreshListener = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            if (mExternalListener != null){
                mExternalListener.onPullDownToRefresh(refreshView);
                return;
            }
            debug("onPullDownToRefresh in " + tag);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            if (mExternalListener != null){
                mExternalListener.onPullDownToRefresh(refreshView);
                return;
            }
            debug("onPullUpToRefresh in "+tag);
        }
    };

    public void setPullLabel(String label){
        mPullLabel = label;
        View view = getView();
        if (view != null){
            mPTRListView = (PullToRefreshListView) view.findViewById(R.id.ptr_article_listview);
            ILoadingLayout headerLL = mPTRListView.getLoadingLayoutProxy(true, false);
            headerLL.setPullLabel(mPullLabel);
        }
    }

    public void setReleaseLabel(String label){
        mReleaseLabel = label;
        View view = getView();
        if (view != null){
            mPTRListView = (PullToRefreshListView) view.findViewById(R.id.ptr_article_listview);
            ILoadingLayout headerLL = mPTRListView.getLoadingLayoutProxy(true, false);
            headerLL.setReleaseLabel(mReleaseLabel);
        }
    }

    public void setRefreshLabel(String label){
        mRefreshLabel = label;
        View view = getView();
        if (view != null){
            mPTRListView = (PullToRefreshListView) view.findViewById(R.id.ptr_article_listview);
            ILoadingLayout headerLL = mPTRListView.getLoadingLayoutProxy(true, false);
            headerLL.setRefreshingLabel(mRefreshLabel);
        }
    }

    public void setLastUpdateLabel(String label){
        mLastUpdateLabel = label;
        View view = getView();
        if (view != null){
            mPTRListView = (PullToRefreshListView) view.findViewById(R.id.ptr_article_listview);
            ILoadingLayout headerLL = mPTRListView.getLoadingLayoutProxy(true, false);
            headerLL.setLastUpdatedLabel(mLastUpdateLabel);
        }
    }

    public void setOnPullToRefreshListener(PullToRefreshBase.OnRefreshListener2 listener){
        mExternalListener = listener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void disableFooter(){
        isFooterEnabled = false;
    }



}
