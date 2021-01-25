package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class GeneralHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RoundedImageView roundedImageView;
    private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvShare, tvLookMore;
    private RecyclerView recyclerChild;
    private ImageButton imgBtnMore;
    private ConstraintLayout constraintLayout;
    private ChildMainForumAdapter.OnPostClick onPostClick;
    private Forum.Post post;
    private String profileID;

    public GeneralHolder(@NonNull View itemView, ChildMainForumAdapter.OnPostClick onPostClick) {
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
        constraintLayout = itemView.findViewById(R.id.cl_share_trade);
        imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_child_main_forum);
        recyclerChild = itemView.findViewById(R.id.recycler_child_rv_main_forum);

        tvLookMore.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        itemView.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        imgBtnMore.setOnClickListener(this);
    }

    public void setData(Forum.Post post, String profileID) {
        this.post = post;
        this.profileID = profileID;

        tvType.setVisibility(View.VISIBLE);
        tvType.setText(post.getType());

        if (!post.getImageProfile().isEmpty())
            Picasso.get()
                    .load(post.getImageProfile())
                    .into(roundedImageView);

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        tvDate.setText(post.getDate());
        tvContent.setText(Utils.removeEnter(post.getContent()));
        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());
        tvShare.setText(post.getShare());

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        if (tvContent.getText().toString().equals(post.getContent())) {
            tvLookMore.setVisibility(View.GONE);
        }

        tvName.setText(post.getName());
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

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;
        }
    }
}
