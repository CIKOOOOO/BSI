package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.ChildMainForumAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

public class ShareTradeViewHolder extends RecyclerView.ViewHolder {

    private RoundedImageView roundedImageView;
    private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvLookMore, tvTitle, tvTransactionType, tvProductName, tvDateShareTrade, tvValueShareTrade, tvShareTradeType;
    private ConstraintLayout constraintLayout;

    private ChildMainForumAdapter.OnPostClick onPostClick;
    private Forum.Post post;
    private String profileID;

    public ShareTradeViewHolder(@NonNull View itemView, ChildMainForumAdapter.OnPostClick onPostClick) {
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
        constraintLayout = itemView.findViewById(R.id.cl_share_trade);
        tvTitle = itemView.findViewById(R.id.tv_content_share_trade);
        tvTransactionType = itemView.findViewById(R.id.textView35);
        tvProductName = itemView.findViewById(R.id.tv_name_product_share_trade);
        tvDateShareTrade = itemView.findViewById(R.id.tv_name_manager_invest_share_trade);
        tvValueShareTrade = itemView.findViewById(R.id.tv_price_product_share_trade);
        tvShareTradeType = itemView.findViewById(R.id.tv_transaction_type_share_trade);


    }

    public void loadData(Forum.Post post, String profileID){
        this.post = post;
        this.profileID = profileID;



    }
}
