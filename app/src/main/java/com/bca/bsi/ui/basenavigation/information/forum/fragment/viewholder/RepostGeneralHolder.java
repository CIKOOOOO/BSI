package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.adapter.PostImageAdapter;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.SpacesItemDecoration;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class RepostGeneralHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvName, tvDate, tvSourceName, tvSourceDate, tvContent, tvLike, tvComment, tvShare, tvLookMore;
    private RoundedImageView imgProfile, imgSourceProfile;
    private OnPostClick onPostClick;
    private RecyclerView recyclerImage;

    private Forum.Post post;
    private String profileID;

    public RepostGeneralHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;

        CardView cardView = itemView.findViewById(R.id.recycler_cv_repost_news_main_forum);

        tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
        tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
        tvSourceName = itemView.findViewById(R.id.recycler_tv_name_post_source_repost_news);
        tvSourceDate = itemView.findViewById(R.id.recycler_tv_date_source_repost_news);
        tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
        tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
        tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvShare = itemView.findViewById(R.id.recycler_tv_share_child_main_forum);
        tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
        imgProfile = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
        imgSourceProfile = itemView.findViewById(R.id.recycler_img_profile_post_source_repost_news);
        recyclerImage = itemView.findViewById(R.id.recycler_rv_img_repost_child_main_forum);

        recyclerImage.addItemDecoration(new SpacesItemDecoration(5));

        cardView.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        imgProfile.setOnClickListener(this);
    }

    public void setData(Forum.Post data, String profileID) {
        this.post = data;
        this.profileID = profileID;

        tvName.setText(data.getName());
        tvDate.setText(data.getDate());
        tvLike.setText(data.getLike());
        tvComment.setText(data.getComment());
        tvShare.setText(data.getShare());

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;
        int drawableShare = post.getStatusShare().equalsIgnoreCase("true") ? R.drawable.ic_share_yellow : R.drawable.ic_share;

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);
        tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableShare, 0);

        Picasso.get()
                .load(Utils.imageURL(data.getImageProfile()))
                .into(imgProfile);

        Forum.Post post = data.getPost();

        PostImageAdapter postImageAdapter = new PostImageAdapter();

        tvSourceName.setText(post.getName());
        tvSourceDate.setText(post.getDate());
        tvContent.setText(post.getContent());

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
        }

        Picasso.get()
                .load(Utils.imageURL(post.getImageProfile()))
                .into(imgSourceProfile);

        if (post.getImagePostList() != null) {
            if (post.getImagePostList().size() == 1) {
                recyclerImage.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            } else if (post.getImagePostList().size() == 2) {
                recyclerImage.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
                recyclerImage.addItemDecoration(new GridSpacingItemDecoration(2, 0, true));
            } else {
                GridLayoutManager glm = new GridLayoutManager(itemView.getContext(), 2);
                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (position % 3) > 0 ? 1 : 2;
                    }
                });
                recyclerImage.setLayoutManager(glm);
            }

            recyclerImage.setAdapter(postImageAdapter);
            postImageAdapter.setImageList(post.getImagePostList());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recycler_img_profile_child_main_forum:
            case R.id.recycler_tv_name_child_main_forum:
                if (profileID.equals(post.getProfileID())) {
                    onPostClick.onMyProfile();
                } else
                    onPostClick.onOtherProfile(post.getProfileID());
                break;
            case R.id.recycler_cv_repost_news_main_forum:
//                onPostClick.onDetailPost();
                break;
            case R.id.recycler_tv_share_child_main_forum:
                onPostClick.onResharePost(post.getStatusShare().equalsIgnoreCase("true"), post.getPost().getPostID());
                break;
        }
    }
}
