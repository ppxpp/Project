package me.ppxpp.project.entity;

/**
 * Created by ppxpp on 2014/10/22.
 */
public class MenuItem {

    public static int FLAVOR_MENU_ITEM_ID = 1;

    public int id;
    public String title;
    public int iconResId;
    //0父菜单，1子菜单
    public int type;
    public boolean selected;
}
