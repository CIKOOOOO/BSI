package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
/**
 * Purpose : to show forum post with re-post news type only
 */
public class RepostNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvName, tvDate, tvSourceName, tvSourceDate, tvContent, tvNews, tvLike, tvComment, tvShare, tvLookMore;
    private RoundedImageView imgProfile, imgSourceProfile;
    private ImageView imgContentNews;
    private OnPostClick onPostClick;
    private ImageButton imgPopup;

    private Forum.Post post;
    private String profileID;

    public RepostNewsViewHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;

        CardView cardView = itemView.findViewById(R.id.recycler_cv_repost_news_main_forum);
        imgPopup = itemView.findViewById(R.id.recycler_img_btn_more_repost_news_main_forum);

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
        tvShare.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        imgPopup.setOnClickListener(this);
        tvComment.setOnClickListener(this);
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

        tvSourceName.setText(post.getName());
        tvSourceDate.setText(post.getDate());
        tvContent.setText(post.getContent());
        tvNews.setText(post.getPromoNews().getContent());

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
        }

        Picasso.get()
                .load(Utils.imageURL(post.getImageProfile()))
                .into(imgSourceProfile);

        Picasso.get()
                .load(Utils.imageURL(post.getPromoNews().getImage()))
                .into(imgContentNews);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPostClick.onDetailPost(post.getPostID());
            }
        });
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
                onPostClick.onDetailNews(post.getPost().getPromoNews().getNewsID());
                break;
            case R.id.recycler_tv_share_child_main_forum:
                onPostClick.onResharePost(post.getStatusShare().equalsIgnoreCase("true"), post.getPostID());
                break;
            case R.id.recycler_tv_like_child_main_forum:
                onPostClick.onPostLike(post.getPostID());
                break;
            case R.id.recycler_img_btn_more_repost_news_main_forum:
                PopupMenu popup = new PopupMenu(v.getContext(), imgPopup);

                popup.getMenuInflater()
                        .inflate(R.menu.menu_repost, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_delete:
                                onPostClick.onDeletePost(post.getPostID());
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;
            case R.id.recycler_tv_comment_child_main_forum:
                onPostClick.onDetailPost(post.getPostID());
                break;
        }
    }
}
