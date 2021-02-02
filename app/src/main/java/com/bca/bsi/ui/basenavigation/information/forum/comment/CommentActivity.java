package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.PostImageAdapter;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.SpacesItemDecoration;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentActivity extends BaseActivity implements View.OnClickListener, CommentAdapter.onReport, ICommentCallback, PostImageAdapter.onImageClick {

    public static final String DATA = "data";
    public static final int REPOST_NEWS = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, REPOST_GENERAL = 5;

    private EditText etComment;
    //    private RoundedImageView rivProfile;
//    private TextView tvName, tvDate, tvType, tvAmountCharacter, tvContent, tvViewMore, tvLike, tvAmountComment, tvShare, tvTransactionTypeShareTrade, tvContentShareTrade, tvNameProductShareTrade, tvPriceProductShareTrade, tvManagerInvestShareTrade;
//    private ImageButton imgBtnMore;
//    private ConstraintLayout clShareTrade;
    private CommentAdapter commentAdapter;
    private CommentViewModel viewModel;
    private Forum.Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initVar();
    }

    private void initVar() {

        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        TextView tvComment = findViewById(R.id.tv_post_new_comment);
        RecyclerView recyclerComment = findViewById(R.id.recycler_comment);

        etComment = findViewById(R.id.et_post_new_comment);

//        rivProfile = findViewById(R.id.recycler_img_profile_child_main_forum);
//        tvName = findViewById(R.id.recycler_tv_name_child_main_forum);
//        tvDate = findViewById(R.id.recycler_tv_date_child_main_forum);
//        tvType = findViewById(R.id.recycler_tv_type_child_main_forum);
//        tvContent = findViewById(R.id.recycler_tv_content_child_main_forum);
//        tvViewMore = findViewById(R.id.recycler_tv_view_more_child_main_forum);
//        tvLike = findViewById(R.id.recycler_tv_like_child_main_forum);
//        tvAmountComment = findViewById(R.id.recycler_tv_comment_child_main_forum);
//        tvShare = findViewById(R.id.recycler_tv_share_child_main_forum);
//        imgBtnMore = findViewById(R.id.recycler_img_btn_more_child_main_forum);
//        tvAmountCharacter = findViewById(R.id.tv_counter_character_comment);
//
//        clShareTrade = findViewById(R.id.cl_share_trade);
//        tvTransactionTypeShareTrade = findViewById(R.id.tv_transaction_type_share_trade);
//        tvContentShareTrade = findViewById(R.id.tv_content_share_trade);
//        tvNameProductShareTrade = findViewById(R.id.tv_name_product_share_trade);
//        tvPriceProductShareTrade = findViewById(R.id.tv_price_product_share_trade);
//        tvManagerInvestShareTrade = findViewById(R.id.tv_name_manager_invest_share_trade);

        commentAdapter = new CommentAdapter(this);
        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.comment));

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);
//        tvViewMore.setVisibility(View.GONE);
//        tvType.setVisibility(View.GONE);

        recyclerComment.setLayoutManager(new LinearLayoutManager(this));
        recyclerComment.setAdapter(commentAdapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            String postID = intent.getStringExtra(DATA);
            viewModel.loadData(postID);
        } else {
            onBackPressed();
        }

        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                tvAmountCharacter.setText(count + "/160");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgBack.setOnClickListener(this);
        tvComment.setOnClickListener(this);

//        tvTransactionTypeShareTrade.setOnClickListener(this);
//        imgBtnMore.setOnClickListener(this);
//        tvLike.setOnClickListener(this);
//        tvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.tv_post_new_comment:

                break;
            case R.id.recycler_img_btn_more_child_main_forum:

                break;
            case R.id.tv_transaction_type_share_trade:

                break;
            case R.id.recycler_tv_like_comment:
                break;
            case R.id.recycler_tv_share_comment:
                break;
        }
    }

    @Override
    public void onReportClick(Forum.Comment comment) {
        viewModel.onReport(comment);
    }

    @Override
    public void onLoadComment(Forum.Post post, List<Forum.Comment> commentList, int type) {
        this.post = post;

        commentAdapter.setCommentList(commentList);
        commentAdapter.notifyDataSetChanged();

        ConstraintLayout clComment = findViewById(R.id.cl_comment);
        ConstraintLayout clRepostNews = findViewById(R.id.layout_repost_news_comment);
        ConstraintLayout clRepostGeneral = findViewById(R.id.layout_repost_child_main_comment);
        ConstraintLayout clShareTrade = findViewById(R.id.cl_share_trade);
        TextView tvLike = findViewById(R.id.recycler_tv_like_comment);
        TextView tvComment = findViewById(R.id.recycler_tv_comment_comment);
        TextView tvShare = findViewById(R.id.recycler_tv_share_comment);

        RoundedImageView rivProfile, rivRepostProfile;
        TextView tvDatePost;
        TextView tvContent;
        TextView tvContentNews;
        TextView tvName, tvRepostName;
        TextView tvLookMore;
        ImageView imgNewsContent;

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        if (null != post.getStatusShare()) {
            int drawableShare = post.getStatusShare().equalsIgnoreCase("true") ? R.drawable.ic_share_yellow : R.drawable.ic_share;
            tvShare.setText(post.getShare());
            tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableShare, 0);
        }

        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());

        switch (type) {
            case REPOST_NEWS:
            case REPOST_GENERAL:
                rivProfile = findViewById(R.id.recycler_img_profile_child_main_forum);
                rivRepostProfile = findViewById(R.id.recycler_img_profile_post_source_repost_news);
                tvName = findViewById(R.id.recycler_tv_name_child_main_forum);
                tvRepostName = findViewById(R.id.recycler_tv_date_source_repost_news);
                tvDatePost = findViewById(R.id.recycler_tv_date_child_main_forum);
                tvLookMore = findViewById(R.id.recycler_tv_view_more_child_main_forum);
                tvContent = findViewById(R.id.recycler_tv_content_child_main_forum);
                tvContentNews = findViewById(R.id.recycler_tv_content_news);
                imgNewsContent = findViewById(R.id.recycler_img_thumbnail_news);

                findViewById(R.id.recycler_tv_like_child_main_forum).setVisibility(View.GONE);
                findViewById(R.id.recycler_tv_comment_child_main_forum).setVisibility(View.GONE);
                findViewById(R.id.recycler_tv_share_child_main_forum).setVisibility(View.GONE);

                Picasso.get()
                        .load(post.getImageProfile())
                        .into(rivProfile);

                tvName.setText(post.getName());
                tvDatePost.setText(post.getDate());

                if (null != post.getPost()) {
                    Forum.Post post1 = post.getPost();

                    Picasso.get()
                            .load(post1.getImageProfile())
                            .into(rivRepostProfile);

                    tvRepostName.setText(post1.getName());

                    switch (type) {
                        case REPOST_NEWS:
                            clRepostNews.setVisibility(View.VISIBLE);

                            tvContent.setText(post1.getContent());
                            tvContentNews.setText(post1.getPromoNews().getContent());

                            Picasso.get()
                                    .load(post1.getPromoNews().getImage())
                                    .into(imgNewsContent);
                            break;
                        case REPOST_GENERAL:
                            RecyclerView recyclerImageRepost = findViewById(R.id.recycler_rv_img_repost_child_main_forum);

                            clRepostGeneral.setVisibility(View.VISIBLE);
                            tvContent.setText(post1.getContent());

                            if (tvContent.getText().toString().equals(post1.getContent())) {
                                tvLookMore.setVisibility(View.GONE);
                            }

                            if (null != post1.getImagePostList() && post1.getImagePostList().size() > 0) {
                                PostImageAdapter postImageAdapter = new PostImageAdapter();

                                if (post.getImagePostList().size() == 1) {
                                    recyclerImageRepost.setLayoutManager(new LinearLayoutManager(this));
                                } else if (post.getImagePostList().size() == 2) {
                                    recyclerImageRepost.setLayoutManager(new GridLayoutManager(this, 2));
                                    recyclerImageRepost.addItemDecoration(new GridSpacingItemDecoration(2, 0, true));
                                } else {
                                    GridLayoutManager glm = new GridLayoutManager(this, 2);
                                    glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                        @Override
                                        public int getSpanSize(int position) {
                                            return (position % 3) > 0 ? 1 : 2;
                                        }
                                    });
                                    recyclerImageRepost.setLayoutManager(glm);
                                }
                                recyclerImageRepost.addItemDecoration(new SpacesItemDecoration(5));

                                postImageAdapter.setImageList(post.getImagePostList());
                                recyclerImageRepost.setAdapter(postImageAdapter);
                            }

                            clRepostGeneral.setOnClickListener(this);
                            break;
                    }
                }
                break;
            case STRATEGY:
            case NEWS:
            case SHARE_TRADE:
                RecyclerView recyclerImage = findViewById(R.id.recycler_image_comment);

                rivProfile = findViewById(R.id.recycler_img_profile_comment);
                tvDatePost = findViewById(R.id.recycler_tv_date_comment);
                tvContent = findViewById(R.id.recycler_tv_content_comment);
                imgNewsContent = findViewById(R.id.recycler_img_thumbnail_news);
                tvContentNews = findViewById(R.id.recycler_tv_content_news);
                tvName = findViewById(R.id.recycler_tv_name_comment);

                clComment.setVisibility(View.VISIBLE);

                tvName.setText(post.getName());
                tvDatePost.setText(post.getDate());
                tvContent.setText(post.getContent());

                Picasso.get()
                        .load(post.getImageProfile())
                        .into(rivProfile);

                switch (type) {
                    case STRATEGY:
                        if (null != post.getImagePostList() && post.getImagePostList().size() > 0) {
                            recyclerImage.setVisibility(View.VISIBLE);

                            PostImageAdapter postImageAdapter = new PostImageAdapter();
                            postImageAdapter.setOnImageClick(this);

                            if (post.getImagePostList().size() == 1) {
                                recyclerImage.setLayoutManager(new LinearLayoutManager(this));
                            } else if (post.getImagePostList().size() == 2) {
                                recyclerImage.setLayoutManager(new GridLayoutManager(this, 2));
                                recyclerImage.addItemDecoration(new GridSpacingItemDecoration(2, 0, true));
                            } else {
                                GridLayoutManager glm = new GridLayoutManager(this, 2);
                                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        return (position % 3) > 0 ? 1 : 2;
                                    }
                                });
                                recyclerImage.setLayoutManager(glm);
                            }
                            recyclerImage.addItemDecoration(new SpacesItemDecoration(5));

                            postImageAdapter.setImageList(post.getImagePostList());
                            recyclerImage.setAdapter(postImageAdapter);
                        }
                        break;
                    case NEWS:
                        imgNewsContent.setVisibility(View.VISIBLE);
                        tvContentNews.setVisibility(View.VISIBLE);

                        if (null != post.getPromoNews()) {
                            PromoNews promoNews = post.getPromoNews();
                            Picasso.get()
                                    .load(promoNews.getImage())
                                    .into(imgNewsContent);
                            tvContentNews.setText(post.getContent());
                        }
                        break;
                    case SHARE_TRADE:
                        TextView tvTransactionType = findViewById(R.id.tv_transaction_type_share_trade);
                        TextView tvDateShareTrade = findViewById(R.id.tv_name_manager_invest_share_trade);
                        TextView tvValueShareTrade = findViewById(R.id.tv_price_product_share_trade);
                        TextView tvContentShareTrade = findViewById(R.id.tv_content_share_trade);

                        clShareTrade.setVisibility(View.VISIBLE);

                        if (null != post.getShareTrade()) {
                            Forum.ShareTrade shareTrade = post.getShareTrade();

                            String value, content, shareTradeType;
                            int background;

                            if (shareTrade.getType().equalsIgnoreCase("Jual") || shareTrade.getType().equalsIgnoreCase("Beli")) {
                                tvDateShareTrade.setVisibility(View.INVISIBLE);

                                value = "Rp " + Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                                if (shareTrade.getType().equalsIgnoreCase("Jual")) {
                                    background = R.drawable.rectangle_rounded_stroke_yellow;
                                    content = "Saya baru saja menjual";
                                    shareTradeType = "Jual";
                                } else {
                                    background = R.drawable.rectangle_rounded_stroke_ziggurat2;
                                    content = "Saya baru saja membeli";
                                    shareTradeType = "Beli";
                                }
                            } else {
                                tvDateShareTrade.setVisibility(View.VISIBLE);

                                if (Double.parseDouble(shareTrade.getValue()) == 0) {
                                    value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                                    background = R.drawable.rectangle_rounded_stroke_ziggurat;
                                    shareTradeType = "Tetap";
                                } else if (Double.parseDouble(shareTrade.getValue()) < 0) {
                                    value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue())) + "%";
                                    tvValueShareTrade.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_black_down, 0, 0, 0);
                                    background = R.drawable.rectangle_rounded_stroke_your_pink;
                                    shareTradeType = "Turun";
                                } else {
                                    value = "+" + Utils.formatUang3(Double.parseDouble(shareTrade.getValue())) + "%";
                                    tvValueShareTrade.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_black_up, 0, 0, 0);
                                    background = R.drawable.rectangle_rounded_stroke_fringy_flower;
                                    shareTradeType = "Naik";
//                                    clShareTrade.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_stroke_ziggurat));
                                }
                                content = "Nilai investasi saya";
                                tvDateShareTrade.setText("pembelian pertama: " + shareTrade.getDate());
                            }

                            clShareTrade.setBackground(ContextCompat.getDrawable(this, background));

                            tvValueShareTrade.setText(value);
                            tvContentShareTrade.setText(content);
                            tvTransactionType.setText(shareTradeType);
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onSuccessReport() {

    }

    @Override
    public void onImageClickWith(String URl) {

    }
}