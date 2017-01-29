package com.mymoneycontrol.android.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.mymoneycontrol.android.database.model.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrizvan on 1/28/17.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    List<DataItem> itemList = new ArrayList<DataItem>();
    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context, List<DataItem> itemList) {
        super(fm);
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        /*if (position == 1){

        }
        else if (position == 2){

        }
        else*/
        PageFragment pageFragment = new PageFragment();
        pageFragment.setDataItemList(this.itemList);
        return pageFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
