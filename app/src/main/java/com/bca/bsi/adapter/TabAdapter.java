package com.bca.bsi.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> titleList;

    public TabAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    public void setTitleTab(Fragment fragment, String title) {
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i) == fragment) {
                titleList.set(i, title);
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void addTab(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
