package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

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
import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class ShareTradeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RoundedImageView roundedImageView;
    private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvLookMore, tvTitle, tvTransactionType, tvProductName, tvDateShareTrade, tvValueShareTrade, tvShareTradeType;
    private ImageButton imgBtnMore;
    private ConstraintLayout constraintLayout;

    private OnPostClick onPostClick;
    private Forum.Post post;
    private String profileID;

    public ShareTradeViewHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;

        roundedImageView = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
        tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
        tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
        tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
        tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
        tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
        tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvType = itemView.findViewById(R.id.recycler_tv_type_child_main_forum);
        tvTitle = itemView.findViewById(R.id.tv_content_share_trade);
        tvTransactionType = itemView.findViewById(R.id.textView35);
        tvProductName = itemView.findViewById(R.id.tv_name_product_share_trade);
        tvDateShareTrade = itemView.findViewById(R.id.tv_name_manager_invest_share_trade);
        tvValueShareTrade = itemView.findViewById(R.id.tv_price_product_share_trade);
        tvShareTradeType = itemView.findViewById(R.id.tv_transaction_type_share_trade);
        imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_child_main_forum);
        constraintLayout = itemView.findViewById(R.id.cl_share_trade);

        roundedImageView.setOnClickListener(this);
        tvName.setOnClickListener(this);
        imgBtnMore.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        tvLookMore.setOnClickListener(this);
    }

    public void loadData(Forum.Post post, String profileID, boolean isTrending) {
        this.post = post;
        this.profileID = profileID;

        if (isTrending) {
            tvType.setVisibility(View.VISIBLE);
            tvType.setText(post.getType());
        }

        Forum.ShareTrade shareTrade = post.getShareTrade();

        if (!post.getImageProfile().isEmpty()) {
            Picasso.get()
                    .load(Utils.imageURL(post.getImageProfile()))
                    .into(roundedImageView);
        }

        int visibilityOfContent = post.getContent().trim().isEmpty() ? View.GONE : View.VISIBLE;
        tvContent.setVisibility(visibilityOfContent);

        tvName.setText(post.getName());
        tvDate.setText(post.getDate());
        tvContent.setText(Utils.removeEnter(post.getContent()));
        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());

        if (shareTrade != null) {
            String value;
            int background;

            tvTitle.setText(shareTrade.getTitle());
            tvProductName.setText(shareTrade.getProductName());
            tvShareTradeType.setText(shareTrade.getType());
            tvValueShareTrade.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            if (shareTrade.getType().equalsIgnoreCase("jual") || shareTrade.getType().equalsIgnoreCase("beli")) {
                tvTransactionType.setVisibility(View.VISIBLE);
                tvDateShareTrade.setVisibility(View.INVISIBLE);

                value = "Rp " + Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                background = shareTrade.getType().equalsIgnoreCase("Jual") ? R.drawable.rectangle_rounded_stroke_yellow : R.drawable.rectangle_rounded_stroke_ziggurat2;

            } else {
                tvTransactionType.setVisibility(View.GONE);
                tvDateShareTrade.setVisibility(View.VISIBLE);

                if (Double.parseDouble(shareTrade.getValue()) == 0) {
                    value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                    background = R.drawable.rectangle_rounded_stroke_fringy_flower;
                } else if (Double.parseDouble(shareTrade.getValue()) < 0) {
                    value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue())) + "%";
                    tvValueShareTrade.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_black_down, 0, 0, 0);
                    background = R.drawable.rectangle_rounded_stroke_your_pink;
                } else {
                    value = "+" + Utils.formatUang3(Double.parseDouble(shareTrade.getValue())) + "%";
                    tvValueShareTrade.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_black_up, 0, 0, 0);
                    background = R.drawable.rectangle_rounded_stroke_ziggurat;
                }

                tvDateShareTrade.setText("pembelian pertama: " + shareTrade.getDate());
            }

            constraintLayout.setBackground(itemView.getResources().getDrawable(background));

            tvValueShareTrade.setText(value);
        }

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        int visibility = tvContent.getText().toString().equals(post.getContent()) ? View.GONE : View.VISIBLE;
        tvLookMore.setVisibility(visibility);

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
            case R.id.recycler_img_btn_more_child_main_forum:
                PopupMenu popup = new PopupMenu(v.getContext(), imgBtnMore);

                int layout = profileID.equals(post.getProfileID()) ? R.menu.menu_self_post : R.menu.menu_other_post;

                popup.getMenuInflater()
                        .inflate(layout, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_report:
                                onPostClick.onReport(post.getPostID());
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
            case R.id.recycler_tv_like_child_main_forum:
                onPostClick.onPostLike(post.getPostID());
                break;
            case R.id.recycler_tv_content_child_main_forum:
            case R.id.recycler_tv_view_more_child_main_forum:
            case R.id.recycler_tv_comment_child_main_forum:
                onPostClick.onDetailPost(post.getPostID());
                break;
        }
    }
}
