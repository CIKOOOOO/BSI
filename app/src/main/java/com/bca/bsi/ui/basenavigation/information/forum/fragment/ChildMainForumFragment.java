package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Constant;

import java.util.List;

public class ChildMainForumFragment extends BaseFragment implements IChildMainForumCallback, ChildMainForumAdapter.OnPostClick {
    private static final String PARCEL_DATA = "parcel_data";

    private ChildMainForumViewModel viewModel;
    private ChildMainForumAdapter adapter;

    public static ChildMainForumFragment newInstance(int param1) {
        ChildMainForumFragment fragment = new ChildMainForumFragment();
        Bundle args = new Bundle();
        args.putString(PARCEL_DATA, Constant.FORUM_MENU[param1]);
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
        return inflater.inflate(R.layout.fragment_child_main_forum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_child_main_forum);

        viewModel = new ViewModelProvider(this).get(ChildMainForumViewModel.class);
        viewModel.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = bundle.getString(PARCEL_DATA);
            if (type != null) {
                type = type.toLowerCase();
                adapter = new ChildMainForumAdapter(type, prefConfig.getProfileID(), this);
//                Log.e("asd", "1st : "+type);
                recyclerView.setAdapter(adapter);
                viewModel.loadForumPost(type);
//                Log.e("asd", "2nd : "+type);
            }
        }
    }

    @Override
    public void onLoadData(List<Forum.Post> postList) {
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onDetailPost(String postID) {

    }

    @Override
    public void onPostLike(String postID) {

    }

    @Override
    public void onReport(String postID) {

    }

    @Override
    public void onSavedPost(String postID) {

    }

    @Override
    public void onOtherProfile(String profileID) {

    }

    @Override
    public void onMyProfile() {

    }

    @Override
    public void onDetailNews(String newsID) {

    }
}