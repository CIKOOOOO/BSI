package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.content.Intent;
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
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.information.forum.comment.CommentActivity;
import com.bca.bsi.ui.basenavigation.information.forum.otherprofile.OtherProfileActivity;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.ForumProfileActivity;
import com.bca.bsi.ui.basenavigation.information.promonews.detail.DetailPromoNewsActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.dialog.DeleteDialog;
import com.bca.bsi.utils.dialog.ReshareDialog;

import java.util.ArrayList;
import java.util.List;

public class ChildMainForumFragment extends BaseFragment implements IChildMainForumCallback, OnPostClick, DeleteDialog.onDelete, ReshareDialog.onReshare {
    private static final String PARCEL_DATA = "parcel_data";

    private ChildMainForumViewModel viewModel;
    private ChildMainForumAdapter adapter;
    private ReshareDialog reshareDialog;
    private DeleteDialog deleteDialog;
    private int page;
    private String type;

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

        page = 1;

        RecyclerView recyclerView = view.findViewById(R.id.recycler_child_main_forum);

        viewModel = new ViewModelProvider(this).get(ChildMainForumViewModel.class);
        viewModel.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(PARCEL_DATA);
            if (type != null) {
                type = type.toLowerCase();
                adapter = new ChildMainForumAdapter(type, prefConfig.getProfileID(), this);
                recyclerView.setAdapter(adapter);
                viewModel.loadForumPost(type, page);
            }
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadForumPost(type, page);
                }
            }
        });
    }

    @Override
    public void onLoadData(List<Forum.Post> postList) {
        this.page++;
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onLoadReportData(List<Forum.Report> reportList) {
        BaseNavigationActivity.loadReport(reportList);
    }

    @Override
    public void onOutOfData() {
        List<Forum.Post> postList = new ArrayList<>();
        postList.add(new Forum.Post());
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetailPost(String postID) {
        Intent intent = new Intent(mActivity, CommentActivity.class);
        intent.putExtra(CommentActivity.DATA, postID);
        startActivity(intent);
    }

    @Override
    public void onPostLike(String postID) {
        viewModel.likePost(postID);
    }

    @Override
    public void onReport(String postID) {
        viewModel.loadReportData();
    }

    @Override
    public void onSavedPost(String postID) {
        viewModel.savedPost(postID);
    }

    @Override
    public void onOtherProfile(String profileID) {
        Intent intent = new Intent(mActivity, OtherProfileActivity.class);
        intent.putExtra(OtherProfileActivity.DATA, profileID);
        startActivity(intent);
    }

    @Override
    public void onMyProfile() {
        startActivity(new Intent(mActivity, ForumProfileActivity.class));
    }

    @Override
    public void onDetailNews(String newsID) {
        Intent intent = new Intent(mActivity, DetailPromoNewsActivity.class);
        intent.putExtra(DetailPromoNewsActivity.DATA, newsID);
        startActivity(intent);
    }

    @Override
    public void onResharePost(boolean isReshare, String postID) {
        String info = isReshare ? "Apakah Anda ingin menghapus reshare postingan ini?" : "Apakah Anda ingin reshare postingan ini?";
        reshareDialog = new ReshareDialog(info, isReshare, this, postID);
        reshareDialog.show(getChildFragmentManager(), "");
    }

    @Override
    public void onDeletePost(String postID) {
        deleteDialog = new DeleteDialog(postID, this);
        deleteDialog.show(getChildFragmentManager(), "");
    }

    @Override
    public void onEditPost(Forum.Post post) {
        Intent intent = new Intent(mActivity, PostActivity.class);
        intent.putExtra(PostActivity.DATA, Utils.toJSON(post));
        intent.putExtra(PostActivity.POST_TYPE, PostActivity.EDIT_POST);
        startActivity(intent);
    }

    @Override
    public void onSendDeletePost(String postID) {
        if (deleteDialog != null && deleteDialog.getTag() != null) {
            deleteDialog.dismiss();
        }
        viewModel.sendDeleteConfirmation(postID);
    }

    @Override
    public void onResharePost(String postID) {
        if (reshareDialog != null && reshareDialog.getTag() != null) {
            reshareDialog.dismiss();
        }
        viewModel.resharePost(postID);
    }

    @Override
    public void onUndoResharePost(String postID) {
        if (reshareDialog != null && reshareDialog.getTag() != null) {
            reshareDialog.dismiss();
        }
        viewModel.undoResharePost(postID);
    }
}