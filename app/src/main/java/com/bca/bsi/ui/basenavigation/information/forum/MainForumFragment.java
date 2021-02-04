package com.bca.bsi.ui.basenavigation.information.forum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Constant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainForumFragment extends BaseFragment implements IMainForumCallback, View.OnClickListener {

    private MainForumViewModel viewModel;
    private TabLayout tabLayout;
    private onRecyclerView onRecyclerView;

    public interface onRecyclerView{
        void onGoToTop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRecyclerView = (MainForumFragment.onRecyclerView) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main_forum:
                Intent intent = new Intent(mActivity, PostActivity.class);
                intent.putExtra(PostActivity.POST_TYPE, PostActivity.NEW_STANDARD_POST);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_forum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_main_forum);
        if (prefConfig.isForumAccountBanned().equals("true")) {
            FrameLayout frameLayout = view.findViewById(R.id.fl_main_forum);
            frameLayout.setVisibility(View.VISIBLE);
            floatingActionButton.hide();
            frameLayout.setOnClickListener(this);
        } else {
            viewModel = new ViewModelProvider(this).get(MainForumViewModel.class);
            viewModel.setCallback(this);

            setupTab(view);

            floatingActionButton.setOnClickListener(this);
        }
    }

    private void setupTab(View view) {
        tabLayout = view.findViewById(R.id.tl_main_forum);

        final ViewPager viewPager = view.findViewById(R.id.vp_main_forum);
        MainForumTabAdapter adapter = new MainForumTabAdapter(getChildFragmentManager(), Constant.FORUM_MENU.length);

        for (String name : Constant.FORUM_MENU) {
            tabLayout.addTab(tabLayout.newTab().setText(name));
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < Constant.FORUM_MENU.length; i++) {
                    String name = Constant.FORUM_MENU[i];
                    if (tab.getText() != null && tab.getText().equals(name)) {
                        viewPager.setCurrentItem(i);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        if (tabLayout.getTabCount() <= 2) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            tabLayout.getTabAt(4).select();
        }
    }
}