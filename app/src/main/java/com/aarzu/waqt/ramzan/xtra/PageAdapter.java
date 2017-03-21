package com.aarzu.waqt.ramzan.xtra;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aarzu.waqt.ramzan.iftar.IftarTabFragment;
import com.aarzu.waqt.ramzan.sheri.DuaTabFragment;
import com.aarzu.waqt.ramzan.sheri.SherTabFragment;

/**
 * Created by Drac Android on 3/6/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SherTabFragment tab1 = new SherTabFragment();
                return tab1;
            case 1:
                IftarTabFragment tab2 = new IftarTabFragment();

                return tab2;
            case 2:
                DuaTabFragment tab3 = new DuaTabFragment();
                return tab3;
            /*case 2:
                DuaTabFragment tab3 = new DuaTabFragment();
                return tab3;
            */default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}