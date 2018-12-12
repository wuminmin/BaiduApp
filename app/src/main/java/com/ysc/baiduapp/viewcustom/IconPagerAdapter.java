package com.ysc.baiduapp.viewcustom;

/**
 * Created by ysc on 2016-1-20.
 */
public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();




}
