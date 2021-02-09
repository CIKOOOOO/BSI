package com.bca.bsi.ui.basenavigation.information.forum.otherprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.comment.CommentActivity;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.ui.basenavigation.information.forum.inbox.InboxActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.ForumProfileActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.connection.ConnectionActivity;
import com.bca.bsi.ui.basenavigation.information.promonews.detail.DetailPromoNewsActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.CustomLoading;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OtherProfileActivity extends BaseActivity implements View.OnClickListener, IOtherProfileCallback, OnPostClick {

    public static final String DATA = "data";

    private RoundedImageView roundedImageView;
    private TextView tvFollow, tvFollower, tvFollowing, tvName, tvTitle;
    private OtherProfileViewModel viewModel;
    private ChildMainForumAdapter adapter;
    private CustomLoading customLoading;
    private ImageView imgBackground;
    private Forum.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        initVar();
    }

    private void initVar() {
        ImageButton imgActionBtn = findViewById(R.id.img_btn_action_toolbar_back_with_image);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar_with_image);
        RecyclerView recyclerView = findViewById(R.id.recycler_other_profile);
        LinearLayout llForumProfile = findViewById(R.id.ll_forum_profile);

        roundedImageView = findViewById(R.id.rounded_image_view_other_profile_forum);
        tvFollow = findViewById(R.id.tv_follow_other_profile);
        tvFollower = findViewById(R.id.tv_follower_other_forum_profile);
        tvFollowing = findViewById(R.id.tv_following_other_forum_profile);
        tvName = findViewById(R.id.tv_name_other_profile);
        tvTitle = findViewById(R.id.tv_title_toolbar_back_with_image);
        imgBackground = findViewById(R.id.img_view_header_forum_profile);

        adapter = new ChildMainForumAdapter(Type.PROFILE, prefConfig.getProfileID(), this);

        viewModel = new ViewModelProvider(this).get(OtherProfileViewModel.class);
        viewModel.setCallback(this);

        imgActionBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_inbox_logo));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            customLoading = new CustomLoading();
            customLoading.show(getSupportFragmentManager(), "");
            String data = intent.getStringExtra(DATA);
            viewModel.loadOtherProfile(prefConfig.getTokenUser(), data);
        } else {
            onBackPressed();
        }

        imgBack.setOnClickListener(this);
        imgActionBtn.setOnClickListener(this);
        llForumProfile.setOnClickListener(this);

        tvFollow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar_with_image:
                onBackPressed();
                break;
            case R.id.img_btn_action_toolbar_back_with_image:
                Intent intent = new Intent(this, InboxActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_follow_other_profile:
                if (null != this.user.getProfileID())
                    viewModel.followUnFollowProfile(prefConfig.getTokenUser(), prefConfig.getProfileID(), this.user.getProfileID());
                break;
            case R.id.ll_forum_profile:
                if (null != this.user) {
                    Intent intent1 = new Intent(this, ConnectionActivity.class);
                    intent1.putExtra(ConnectionActivity.DATA, this.user.getProfileID());
                    startActivity(intent1);
                }
                break;
        }
    }

    @Override
    public void onLoadData(Forum.User user, List<Forum.Post> postList) {
        if (null != customLoading && null != customLoading.getTag()) {
            customLoading.dismiss();
        }

        if (null != user) {
            this.user = user;
            tvTitle.setText(user.getUsername());
            tvName.setText(user.getUsername());
            tvFollowing.setText(user.getFollowingCount());
            tvFollower.setText(user.getFollowerCount());

            if (!user.getImgProfileUrl().isEmpty()) {
                Picasso.get()
                        .load(Utils.imageURL(user.getImgProfileUrl()))
                        .into(roundedImageView);
            }

            if (!user.getImgBackgroundUrl().isEmpty()) {
                Picasso.get()
                        .load(Utils.imageURL(user.getImgProfileUrl()))
                        .into(imgBackground);
            }

            int drawable, color;
            String followStatus;

            if (user.getFollowStatus().equalsIgnoreCase("true")) {
                drawable = R.drawable.rectangle_rounded_white_5dp;
                color = R.color.color_base_welma;
                followStatus = "Unfollow";
            } else {
                drawable = R.drawable.rectangle_rounded_welma_5dp;
                color = R.color.white_palette;
                followStatus = "Follow";
            }

            tvFollow.setBackground(ContextCompat.getDrawable(this, drawable));
            tvFollow.setTextColor(getResources().getColor(color));
            tvFollow.setText(followStatus);
        }

        if (null != postList && postList.size() > 0) {
            adapter.setForumList(postList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadData(Forum.User user) {
        int drawable, color;
        String followStatus;

        if (user.getFollowStatus().equalsIgnoreCase("true")) {
            drawable = R.drawable.rectangle_rounded_white_5dp;
            color = R.color.color_base_welma;
            followStatus = "Unfollow";
        } else {
            drawable = R.drawable.rectangle_rounded_welma_5dp;
            color = R.color.white_palette;
            followStatus = "Follow";
        }

        tvFollow.setBackground(ContextCompat.getDrawable(this, drawable));
        tvFollow.setTextColor(getResources().getColor(color));
        tvFollow.setText(followStatus);
    }

    @Override
    public void onFailed(String msg) {
        if (null != customLoading && null != customLoading.getTag()) {
            customLoading.dismiss();
        }
        showSnackBar(msg);
    }

    @Override
    public void onDetailPost(String postID) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(CommentActivity.DATA, postID);
        startActivity(intent);
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
        Intent intent = new Intent(this, OtherProfileActivity.class);
        intent.putExtra(OtherProfileActivity.DATA, profileID);
        startActivity(intent);
    }

    @Override
    public void onMyProfile() {
        startActivity(new Intent(this, ForumProfileActivity.class));
    }

    @Override
    public void onDetailNews(String newsID) {
        Intent intent = new Intent(this, DetailPromoNewsActivity.class);
        intent.putExtra(DetailPromoNewsActivity.DATA, newsID);
        startActivity(intent);
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