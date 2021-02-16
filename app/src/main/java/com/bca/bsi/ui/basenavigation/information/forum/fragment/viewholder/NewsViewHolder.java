package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RoundedImageView roundedProfile;
    private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvShare, tvLookMore, tvContentNews;
    private ImageView imgNews;
    private ImageButton imgBtnMore;
    private OnPostClick onPostClick;
    private String profileID;
    private Forum.Post post;

    public NewsViewHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;
        roundedProfile = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
        tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
        tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
        tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
        tvType = itemView.findViewById(R.id.recycler_tv_type_child_main_forum);
        tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
        tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
        tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvShare = itemView.findViewById(R.id.recycler_tv_share_child_main_forum);
        tvContentNews = itemView.findViewById(R.id.recycler_tv_content_news);
        imgNews = itemView.findViewById(R.id.recycler_img_thumbnail_news);
        imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_child_main_forum);

        roundedProfile.setOnClickListener(this);
        tvName.setOnClickListener(this);
        imgBtnMore.setOnClickListener(this);
        imgNews.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        tvLookMore.setOnClickListener(this);
        tvContentNews.setOnClickListener(this);
    }

    public void loadData(Forum.Post post, String profileID, boolean isTrending) {
        this.post = post;
        this.profileID = profileID;

        if (isTrending) {
            tvType.setVisibility(View.VISIBLE);
            tvType.setText(post.getType());
        }

        if (!post.getImageProfile().isEmpty())
            Picasso.get()
                    .load(Utils.imageURL(post.getImageProfile()))
                    .into(roundedProfile);

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;
        int drawableShare = post.getStatusShare().equalsIgnoreCase("true") ? R.drawable.ic_share_yellow : R.drawable.ic_share;

        tvDate.setText(post.getDate());
        tvContent.setText(Utils.removeEnter(post.getContent()));
        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());
        tvShare.setText(post.getShare());

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);
        tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableShare, 0);

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
        }

        tvName.setText(post.getName());

        if (null != post.getPromoNews()) {
            PromoNews promoNews = post.getPromoNews();
            if (!promoNews.getContent().isEmpty()) {
                tvContentNews.setText(Utils.removeEnter(promoNews.getContent()));
            }

            Picasso.get()
                    .load(Utils.imageURL(promoNews.getImage()))
                    .into(imgNews);
        }

        itemView.setOnClickListener(v -> onPostClick.onDetailPost(post.getPostID()));
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
            case R.id.recycler_img_btn_more_child_main_forum:
                PopupMenu popup = new PopupMenu(v.getContext(), imgBtnMore);

                int layout = profileID.equals(post.getProfileID()) ? R.menu.menu_self_post : R.menu.menu_other_post;

                popup.getMenuInflater()
                        .inflate(layout, popup.getMenu());

                if (popup.getMenu().findItem(R.id.menu_save) != null) {
                    String share = post.getStatusSave().equalsIgnoreCase("true") ? "Saved" : "Save";
                    popup.getMenu().findItem(R.id.menu_save).setTitle(share);
                }

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_report:
                                onPostClick.onReport(post.getPostID(),"post");
                                break;
                            case R.id.menu_save:
                                onPostClick.onSavedPost(post.getPostID());
                                break;
                            case R.id.menu_delete:
                                onPostClick.onDeletePost(post.getPostID());
                                break;
                            case R.id.menu_edit:
                                onPostClick.onEditPost(post);
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;
            case R.id.recycler_tv_content_child_main_forum:
            case R.id.recycler_tv_view_more_child_main_forum:
            case R.id.recycler_tv_comment_child_main_forum:
                onPostClick.onDetailPost(post.getPostID());
                break;
            case R.id.recycler_tv_like_child_main_forum:
                onPostClick.onPostLike(post.getPostID());
                break;
            case R.id.recycler_tv_content_news:
            case R.id.recycler_img_thumbnail_news:
                Log.e("asd", post.getPromoNews().getNewsID() + "");
                onPostClick.onDetailNews(post.getPromoNews().getNewsID());
                break;
            case R.id.recycler_tv_share_child_main_forum:
                onPostClick.onResharePost(post.getStatusShare().equalsIgnoreCase("true"), post.getPostID());
                break;
        }
    }
}
