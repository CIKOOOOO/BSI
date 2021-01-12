package com.bca.bsi.ui.basenavigation.information.forum;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumFragment;


public class MainForumTabAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public MainForumTabAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ChildMainForumFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
