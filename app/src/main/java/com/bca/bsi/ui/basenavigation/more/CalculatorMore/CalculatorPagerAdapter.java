package com.bca.bsi.ui.basenavigation.more.CalculatorMore;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class CalculatorPagerAdapter extends FragmentPagerAdapter {

    private  int numbOfTabs;

    public CalculatorPagerAdapter(FragmentManager fm, int numbOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BesarInvestasiBulananFragment();
            case 1:
                return new BesarHasilInvestasiFragment();
            case 2:
                return new DurasiInvestasiFragment();
            case 3:
                return new BesarRoRFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
