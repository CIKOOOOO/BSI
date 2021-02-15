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
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Type;
import com.bca.bsi.utils.dialog.ReshareDialog;

import java.util.List;

public class BookmarkFragment extends BaseFragment implements OnPostClick, IBookmarkCallback, ReshareDialog.onReshare {

    private ChildMainForumAdapter adapter;
    private BookmarkViewModel viewModel;
    private ReshareDialog reshareDialog;

    public void loadData(List<Forum.Post> postList) {
        adapter.setForumList(postList);
        adapter.notifyDataSetChanged();
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
        viewModel.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDetailPost(String postID) {

    }

    @Override
    public void onPostLike(String postID) {

    }

    @Override
    public void onReport(String postID, String type) {
        viewModel.loadReportData(prefConfig.getTokenUser(), type, postID);
    }

    @Override
    public void onSavedPost(String postID) {
        viewModel.savedPost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
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
        String info = isReshare ? "Apakah Anda ingin menghapus reshare postingan ini?" : "Apakah Anda ingin reshare postingan ini?";
        reshareDialog = new ReshareDialog(info, isReshare, this, postID);
        reshareDialog.show(getChildFragmentManager(), "");
    }

    @Override
    public void onDeletePost(String postID) {
        // do nothing, user cannot delete other post
    }

    @Override
    public void onEditPost(Forum.Post post) {

    }

    @Override
    public void onSaveResult(Forum.SavePost savePost) {
        adapter.removePost(savePost.getPostID());
        String saveStatus = savePost.getSaveStatus().equalsIgnoreCase("true") ? "Save post berhasil" : "Unsave post berhasil";
        showSnackBar(saveStatus);
    }

    @Override
    public void onReshareResult(boolean isReshare, String postID) {
        adapter.setReshareStatus("true", postID);
        showSnackBar("Share post berhasil");
    }

    @Override
    public void onLoadReportData(List<Forum.Report> reportList, String type, String postID) {
        BaseNavigationActivity.loadReport(reportList, postID, type);
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
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
}