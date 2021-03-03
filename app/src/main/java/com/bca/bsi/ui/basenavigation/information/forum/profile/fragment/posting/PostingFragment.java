package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.posting;

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
import com.bca.bsi.ui.basenavigation.information.forum.comment.CommentActivity;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.ui.basenavigation.information.forum.otherprofile.OtherProfileActivity;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.ui.basenavigation.information.promonews.detail.DetailPromoNewsActivity;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana.DetailReksaDanaActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.bca.bsi.utils.dialog.DeleteDialog;
import com.bca.bsi.utils.dialog.ReshareDialog;

import java.util.List;

public class PostingFragment extends BaseFragment implements OnPostClick, IPostingCallback, DeleteDialog.onDelete, ReshareDialog.onReshare {

    private ChildMainForumAdapter adapter;
    private PostingViewModel viewModel;
    private DeleteDialog deleteDialog;
    private ReshareDialog reshareDialog;

    public void loadData(List<Forum.Post> postList) {
        adapter.setDataExist(false);
        adapter.notifyDataSetChanged();
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
        return inflater.inflate(R.layout.fragment_posting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_posting_fragment);

        viewModel = new ViewModelProvider(this).get(PostingViewModel.class);
        viewModel.setCallback(this);

        adapter = new ChildMainForumAdapter(Type.PROFILE, prefConfig.getProfileID(), this, true);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDetailPost(String postID) {
        Intent intent = new Intent(mActivity, CommentActivity.class);
        intent.putExtra(CommentActivity.DATA, postID);
        startActivity(intent);
    }

    @Override
    public void onPostLike(String postID) {
        viewModel.likePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
    }

    @Override
    public void onReport(String postID, String type) {
        // cannot report your own post
    }

    @Override
    public void onSavedPost(String postID) {
        // Cannot save in here
    }

    @Override
    public void onOtherProfile(String profileID) {
        Intent intent = new Intent(mActivity, OtherProfileActivity.class);
        intent.putExtra(OtherProfileActivity.DATA, profileID);
        startActivity(intent);
    }

    @Override
    public void onMyProfile() {
        // do nothing
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
    public void onDeleteSuccess(String postID) {
        adapter.removePost(postID);
        showSnackBar("Hapus post sukses");
    }

    @Override
    public void onReshareResult(boolean isReshare, String postID) {
        adapter.setReshareStatus("true", postID);
        showSnackBar("Share post berhasil");
    }

    @Override
    public void onFailed(String msg) {
        adapter.setDataExist(false);
        adapter.notifyDataSetChanged();
        showSnackBar(msg);
    }

    @Override
    public void onLikeResult(Forum.LikePost likePost) {
        adapter.setLikePost(likePost);
    }

    @Override
    public void onSessionExpired() {
        sessionExpired();
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
}