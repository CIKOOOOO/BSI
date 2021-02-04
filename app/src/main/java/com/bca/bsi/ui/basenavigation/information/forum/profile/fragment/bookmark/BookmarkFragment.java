package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.bookmark;

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
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Type;

import java.util.List;

public class BookmarkFragment extends BaseFragment implements OnPostClick {

    private ChildMainForumAdapter adapter;
    private List<Forum.Post> postList;
    private BookmarkViewModel viewModel;

    public void loadData(List<Forum.Post> postList) {
        this.postList = postList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_bookmark_fragment);

        adapter = new ChildMainForumAdapter(Type.PROFILE, prefConfig.getProfileID(), this);
        viewModel = new ViewModelProvider(this).get(BookmarkViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setForumList(this.postList);
        adapter.notifyDataSetChanged();
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
        // do nothing
    }

    @Override
    public void onDetailNews(String newsID) {

    }

    @Override
    public void onResharePost(boolean isReshare, String postID) {

    }

    @Override
    public void onDeletePost(String postID) {

    }

    @Override
    public void onEditPost(Forum.Post post) {

    }
}