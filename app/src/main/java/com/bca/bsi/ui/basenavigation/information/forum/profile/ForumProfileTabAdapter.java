package com.bca.bsi.ui.basenavigation.information.forum.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumFragment;
import com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.ChildForumProfileFragment;


public class ForumProfileTabAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public ForumProfileTabAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ChildForumProfileFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
