package com.bca.bsi.ui.basenavigation.information.forum;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumFragment;


public class MainForumTabAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private ChildMainForumFragment childMainForumFragment;

    public MainForumTabAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    public ChildMainForumFragment getChildMainForumFragment() {
        return childMainForumFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        childMainForumFragment = ChildMainForumFragment.newInstance(position);
        return childMainForumFragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
