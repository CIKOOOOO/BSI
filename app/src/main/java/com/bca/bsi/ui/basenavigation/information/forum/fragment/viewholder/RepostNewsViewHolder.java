package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

public class RepostNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvName, tvDate, tvSourceName, tvSourceDate, tvContent, tvNews, tvLike, tvComment, tvShare, tvLookMore;
    private RoundedImageView imgProfile, imgSourceProfile;
    private ImageView imgContentNews;
    private ChildMainForumAdapter.OnPostClick onPostClick;

    private Forum.Post post;
    private String profileID;

    public RepostNewsViewHolder(@NonNull View itemView, ChildMainForumAdapter.OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;

        CardView cardView = itemView.findViewById(R.id.recycler_cv_repost_news_main_forum);

        tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
        tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
        tvSourceName = itemView.findViewById(R.id.recycler_tv_name_post_source_repost_news);
        tvSourceDate = itemView.findViewById(R.id.recycler_tv_date_source_repost_news);
        tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
        tvNews = itemView.findViewById(R.id.recycler_tv_content_news);
        tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
        tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvShare = itemView.findViewById(R.id.recycler_tv_share_child_main_forum);
        tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
        imgProfile = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
        imgSourceProfile = itemView.findViewById(R.id.recycler_img_profile_post_source_repost_news);
        imgContentNews = itemView.findViewById(R.id.recycler_img_thumbnail_news);

        cardView.setOnClickListener(this);
        tvName.setOnClickListener(this);
        imgProfile.setOnClickListener(this);
    }

    public void setData(Forum.Post data, String profileID) {
        this.post = data;
        this.profileID = profileID;

        tvName.setText(data.getName());
        tvDate.setText(data.getDate());
        tvLike.setText(data.getLike());
        tvComment.setText(data.getComment());

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;
        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
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
        }
    }
}