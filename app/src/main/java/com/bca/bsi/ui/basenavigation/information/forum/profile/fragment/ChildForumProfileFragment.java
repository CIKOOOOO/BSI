package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Constant;

public class ChildForumProfileFragment extends BaseFragment {

    private static final String BUNDLE_DATA = "bundle_data";

    private ChildForumProfileViewModel viewModel;

    public static ChildForumProfileFragment newInstance(int param1) {
        ChildForumProfileFragment fragment = new ChildForumProfileFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_DATA, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_forum_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_child_forum_profile);

        viewModel = new ViewModelProvider(this).get(ChildForumProfileViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            int pos = bundle.getInt(BUNDLE_DATA, -1);
            if (pos == -1) {
                mActivity.onBackPressed();
            } else {
                viewModel.loadData(Constant.FORUM_MENU[pos]);
            }
        }

    }
}