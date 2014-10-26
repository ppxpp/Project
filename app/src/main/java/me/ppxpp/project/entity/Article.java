package me.ppxpp.project.entity;

/**
 * Created by ppxpp on 2014/10/18.
 */
public class Article {

    public static final int SINGLE_IMG = 1;
    public static final int MULTI_IMG = 2;

    private String title;
    private int imageCount = SINGLE_IMG;

    public void setImageCount(int imageCount){
        this.imageCount = imageCount;
    }

    public int getImageCount(){
        return imageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
