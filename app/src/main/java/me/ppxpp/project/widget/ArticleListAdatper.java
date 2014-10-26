package me.ppxpp.project.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.ppxpp.project.R;
import me.ppxpp.project.entity.Article;

/**
 * Created by ppxpp on 2014/10/18.
 */
public class ArticleListAdatper extends BaseAdapter {

    List<Article> mArticleList;

    public ArticleListAdatper(List<Article> articles){
        mArticleList = articles;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mArticleList.get(position).getImageCount() ==Article.SINGLE_IMG ? 0 : 1;
    }

    @Override
    public int getCount() {
        return mArticleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArticleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        TextView title;
        ImageView[] imageViews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        Article article = (Article) getItem(position);
        if (view == null){
            int layoutResId = article.getImageCount() == Article.SINGLE_IMG ? R.layout.article_item_1_layout : R.layout.article_item_2_layout;
            view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            /*viewHolder.imageViews = new ImageView[article.getImageCount()];
            int[] imageViewIDs = {R.id.picture1, R.id.picture2, R.id.picture3};
            if (article.getImageCount() == Article.SINGLE_IMG){
                viewHolder.imageViews[0] = (ImageView) view.findViewById(R.id.picture);
            }else{
                for (int i = 0; i < imageViewIDs.length; i++){
                    viewHolder.imageViews[i] = (ImageView) view.findViewById(imageViewIDs[i]);
                }
            }*/
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        //viewHolder.title.setText(article.getTitle());
        return view;
    }
}
