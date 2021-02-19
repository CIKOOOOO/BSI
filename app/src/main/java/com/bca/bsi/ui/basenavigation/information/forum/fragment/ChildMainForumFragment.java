package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.content.Intent;
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
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.information.forum.comment.CommentActivity;
import com.bca.bsi.ui.basenavigation.information.forum.otherprofile.OtherProfileActivity;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.ForumProfileActivity;
import com.bca.bsi.ui.basenavigation.information.promonews.detail.DetailPromoNewsActivity;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana.DetailReksaDanaActivity;
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
    private RecyclerView recycler_post;

    private int page;
    private String type, postID;

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

        recycler_post = view.findViewById(R.id.recycler_child_main_forum);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        viewModel = new ViewModelProvider(this).get(ChildMainForumViewModel.class);
        viewModel.setCallback(this);

        recycler_post.setLayoutManager(linearLayoutManager);

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(PARCEL_DATA);
            if (type != null) {
                type = type.toLowerCase();
                adapter = new ChildMainForumAdapter(type, prefConfig.getProfileID(), this, true);
                recycler_post.setAdapter(adapter);
                viewModel.loadForumPost(type, page, prefConfig.getTokenUser(), prefConfig.getProfileID(), prefConfig.getProfileRisiko());
            }
        }

        recycler_post.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
//                    Log.e("asd", "start");
                }
                if (!recyclerView.canScrollVertically(1)) {
//                    Log.e("asd", "last");
                    viewModel.loadForumPost(type, page, prefConfig.getTokenUser(), prefConfig.getProfileID(), prefConfig.getProfileRisiko());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("asd", "On resume");
        this.page = 1;
        viewModel.loadForumPost(type, page, prefConfig.getTokenUser(), prefConfig.getProfileID(), prefConfig.getProfileRisiko());
    }

    @Override
    public void onLoadData(List<Forum.Post> postList) {
        this.page++;
        adapter.setDataExist(false);
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        adapter.setDataExist(false);
        adapter.notifyDataSetChanged();
        showSnackBar(msg);
    }

    @Override
    public void onLoadReportData(List<Forum.Report> reportList, String postID, String type) {
        BaseNavigationActivity.loadReport(reportList, postID, type);
    }

    @Override
    public void onOutOfData() {
        List<Forum.Post> postList = new ArrayList<>();
        postList.add(new Forum.Post());
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReshareResult(boolean isReshare, String postID) {
        adapter.setReshareStatus("true", postID);
        showSnackBar("Share post berhasil");
    }

    @Override
    public void onSaveResult(Forum.SavePost savePost) {
        adapter.setSavePost(savePost);
        String saveStatus = savePost.getSaveStatus().equalsIgnoreCase("true") ? "Save post berhasil" : "Unsave post berhasil";
        showSnackBar(saveStatus);
    }

    @Override
    public void onLikeResult(Forum.LikePost likePost) {
        adapter.setLikePost(likePost);
//        String saveStatus = savePost.getSaveStatus().equalsIgnoreCase("true") ? "Save post berhasil" : "Unsave post berhasil";
//        showSnackBar(saveStatus);
    }

    @Override
    public void onDeleteSuccess(String postID) {
        adapter.removePost(postID);
        showSnackBar("Hapus post sukses");
    }

    @Override
    public void onDetailPost(String postID) {
        Intent intent = new Intent(mActivity, CommentActivity.class);
        intent.putExtra(CommentActivity.DATA, postID);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onPostLike(String postID) {
        viewModel.likePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
    }

    @Override
    public void onReport(String postID, String type) {
        this.postID = postID;
        viewModel.loadReportData(prefConfig.getTokenUser(), type, postID);
    }

    @Override
    public void onSavedPost(String postID) {
        viewModel.savedPost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
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
//        String info = isReshare ? "Apakah Anda ingin menghapus reshare postingan ini?" : "Apakah Anda ingin reshare postingan ini?";
        reshareDialog = new ReshareDialog("Apakah Anda ingin reshare postingan ini?", isReshare, this, postID);
        reshareDialog.show(getChildFragmentManager(), "");
    }

    @Override
    public void onDeletePost(String postID) {
        deleteDialog = new DeleteDialog(postID, this, "");
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
    public void onDetailProduct(String reksadanaID) {
        Intent intent = new Intent(mActivity, DetailReksaDanaActivity.class);
        intent.putExtra(DetailReksaDanaActivity.REKSA_DANA_ID, reksadanaID);
        startActivity(intent);
    }

    @Override
    public void onSendDeletePost(String postID) {
        if (deleteDialog != null && deleteDialog.getTag() != null) {
            deleteDialog.dismiss();
        }
        viewModel.sendDeleteConfirmation(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
    }

    @Override
    public void onResharePost(String postID) {
        if (reshareDialog != null && reshareDialog.getTag() != null) {
            reshareDialog.dismiss();
        }
        viewModel.resharePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
    }

    @Override
    public void onUndoResharePost(String postID) {
        if (reshareDialog != null && reshareDialog.getTag() != null) {
            reshareDialog.dismiss();
        }
        viewModel.undoResharePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
    }

    public void goToTop() {
        recycler_post.smoothScrollToPosition(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            showSnackBar("Hapus post sukses");
        }
    }
}