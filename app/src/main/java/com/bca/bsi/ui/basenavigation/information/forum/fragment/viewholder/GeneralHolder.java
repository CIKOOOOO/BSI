package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PostImageAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.SpacesItemDecoration;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class GeneralHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RoundedImageView roundedImageView;
    private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvShare, tvLookMore, tvPrivacy;
    private RecyclerView recyclerChild;
    private ImageButton imgBtnMore;
    private OnPostClick onPostClick;
    private Forum.Post post;
    private PostImageAdapter postImageAdapter;
    private ImageView imgPrivacy;

    private String profileID;

    public GeneralHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;
        roundedImageView = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
        tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
        tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
        tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
        tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
        tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
        tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvShare = itemView.findViewById(R.id.recycler_tv_share_child_main_forum);
        tvType = itemView.findViewById(R.id.recycler_tv_type_child_main_forum);
        recyclerChild = itemView.findViewById(R.id.recycler_child_rv_main_forum);
        imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_child_main_forum);
        recyclerChild = itemView.findViewById(R.id.recycler_child_rv_main_forum);
        tvPrivacy = itemView.findViewById(R.id.recycler_tv_privacy);
        imgPrivacy = itemView.findViewById(R.id.recycler_img_privacy);

        postImageAdapter = new PostImageAdapter();
        recyclerChild.addItemDecoration(new SpacesItemDecoration(5));

        tvLookMore.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        imgBtnMore.setOnClickListener(this);

        itemView.setOnClickListener(v -> onPostClick.onDetailPost(post.getPostID()));
    }

    public void setData(Forum.Post post, String profileID, boolean isTrending) {
        this.post = post;
        this.profileID = profileID;
        int drawablePrivacy;
        String privacy;
        int visibilityShare;

        if (isTrending) {
            privacy = "public";
            drawablePrivacy = R.drawable.ic_public;
            visibilityShare = View.VISIBLE;

            tvType.setVisibility(View.VISIBLE);
            tvType.setText(post.getType());
            tvShare.setOnClickListener(this);
        } else {
//            Log.e("asd", post.getPostID() + " post ID");
//            Log.e("asd", Utils.toJSON(post));
             if (null == post.getPrivacy() || post.getPrivacy().equalsIgnoreCase("public")) {
                privacy = "public";
                 visibilityShare = View.VISIBLE;
                 drawablePrivacy = R.drawable.ic_public;
                 tvShare.setOnClickListener(this);
             } else if (post.getPrivacy().equalsIgnoreCase("followers")) {
                drawablePrivacy = R.drawable.ic_followers;
                 visibilityShare = View.GONE;
                 privacy = post.getPrivacy();
            } else {
                 visibilityShare = View.GONE;
                 drawablePrivacy = R.drawable.ic_direct_message;
                privacy = post.getPrivacy();
             }
        }
        tvShare.setVisibility(visibilityShare);

        if (!post.getImageProfile().isEmpty())
            Picasso.get()
                    .load(Utils.imageURL(post.getImageProfile()))
                    .into(roundedImageView);

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;
        int drawableShare = post.getStatusShare().equalsIgnoreCase("true") ? R.drawable.ic_share_yellow : R.drawable.ic_share;

        imgPrivacy.setImageResource(drawablePrivacy);

        tvDate.setText(post.getDate());
        if (null != post.getContent())
            tvContent.setText(Utils.removeEnter(post.getContent()));
        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());
        tvShare.setText(post.getShare());
        tvName.setText(post.getName());
        tvPrivacy.setText(privacy);

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);
        tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableShare, 0);

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
        }

        if (post.getImagePostList() != null) {
            if (post.getImagePostList().size() == 1) {
                recyclerChild.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            } else if (post.getImagePostList().size() == 2) {
                recyclerChild.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
                recyclerChild.addItemDecoration(new GridSpacingItemDecoration(2, 0, true));
            } else {
                GridLayoutManager glm = new GridLayoutManager(itemView.getContext(), 2);
                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (position % 3) > 0 ? 1 : 2;
                    }
                });
                recyclerChild.setLayoutManager(glm);
            }

            recyclerChild.setAdapter(postImageAdapter);
            postImageAdapter.setImageList(post.getImagePostList());
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            case R.id.recycler_tv_view_more_child_main_forum:
            case R.id.recycler_tv_comment_child_main_forum:
                onPostClick.onDetailPost(post.getPostID());
                break;
            case R.id.recycler_tv_like_child_main_forum:
                onPostClick.onPostLike(post.getPostID());
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
                                onPostClick.onReport(post.getPostID(), "post");
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
            case R.id.recycler_tv_share_child_main_forum:
                onPostClick.onResharePost(post.getStatusShare().equalsIgnoreCase("true"), post.getPostID());
                break;
        }
    }
}
