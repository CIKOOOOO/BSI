package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PostImageAdapter;
import com.bca.bsi.adapter.ReportAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.forum.otherprofile.OtherProfileActivity;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.ForumProfileActivity;
import com.bca.bsi.ui.basenavigation.products.detail.DetailProductActivity;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana.DetailReksaDanaActivity;
import com.bca.bsi.ui.basenavigation.transaction.detail_transaction.DetailTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.SpacesItemDecoration;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.dialog.DeleteDialog;
import com.bca.bsi.utils.dialog.ImageDialog;
import com.bca.bsi.utils.dialog.ReshareDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

/**
 * Comment Activity is same as Detail Post Activity
 * It will show detail of every post type and list of comment as well
 * It can report / save post if post wasn't made by current user and
 * It will show edit / delete post if post was made by current user
 * It can post a new comment & delete comment
 */

public class CommentActivity extends BaseActivity implements View.OnClickListener, CommentAdapter.onCommentClick, ICommentCallback, CommentImageAdapter.onImageClick, ReportAdapter.onReportClick, ImageDialog.onDismissView, ReshareDialog.onReshare, DeleteDialog.onDelete {

    public static final String DATA = "data";
    public static final int REPOST_NEWS = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, REPOST_GENERAL = 5;

    private EditText etComment;
    //    private RoundedImageView rivProfile;
//    private TextView tvName, tvDate, tvType, tvAmountCharacter, tvContent, tvViewMore, tvLike, tvAmountComment, tvShare, tvTransactionTypeShareTrade, tvContentShareTrade, tvNameProductShareTrade, tvPriceProductShareTrade, tvManagerInvestShareTrade;
//    private ConstraintLayout clShareTrade;
    private CommentAdapter commentAdapter;
    private CommentViewModel viewModel;
    private Forum.Post post;
    private Button btnReport;
    private TextView tvTitleReport;
    private BottomSheetBehavior<ConstraintLayout> bsReport;
    private FrameLayout frameLayout;
    private ReportAdapter reportAdapter;
    private Forum.Report report;
    private ImageDialog imageDialog;
    private ImageButton imgBtnMore;
    private TextView tvLike, tvShare, tvComment;
    private String reportType, postID;
    private ReshareDialog reshareDialog;
    private DeleteDialog deleteDialog;
    private int postType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initVar();
    }

    private void initVar() {
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        RecyclerView recyclerComment = findViewById(R.id.recycler_comment);
        ConstraintLayout clBSReport = findViewById(R.id.cl_choose_image);
        RecyclerView recyclerReport = findViewById(R.id.bs_recycler_choose_image);
        ImageButton imgSendComment = findViewById(R.id.img_btn_send_comment);

        etComment = findViewById(R.id.et_post_new_comment);
        frameLayout = findViewById(R.id.frame_blur);
        btnReport = findViewById(R.id.bs_btn_update_choose_image);
        tvTitleReport = findViewById(R.id.bs_tv_title_choose_image);
        tvLike = findViewById(R.id.recycler_tv_like_comment);
        tvShare = findViewById(R.id.recycler_tv_share_comment);
        tvComment = findViewById(R.id.recycler_tv_comment_comment);

        reportAdapter = new ReportAdapter(this);

        commentAdapter = new CommentAdapter(this, prefConfig.getProfileID());
        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        viewModel.setCallback(this);

        ConstraintLayout.LayoutParams recyclerReportLayoutParams = (ConstraintLayout.LayoutParams) recyclerReport.getLayoutParams();
        recyclerReportLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerReportLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerReportLayoutParams.bottomMargin = 20;

        recyclerReport.setLayoutParams(recyclerReportLayoutParams);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) clBSReport.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        clBSReport.setLayoutParams(layoutParams);

        recyclerReport.setLayoutManager(new LinearLayoutManager(this));
        recyclerReport.setAdapter(reportAdapter);

        bsReport = BottomSheetBehavior.from(clBSReport);

//        rivProfile = findViewById(R.id.recycler_img_profile_child_main_forum);
//        tvName = findViewById(R.id.recycler_tv_name_child_main_forum);
//        tvDate = findViewById(R.id.recycler_tv_date_child_main_forum);
//        tvType = findViewById(R.id.recycler_tv_type_child_main_forum);
//        tvContent = findViewById(R.id.recycler_tv_content_child_main_forum);
//        tvViewMore = findViewById(R.id.recycler_tv_view_more_child_main_forum);
//        tvAmountComment = findViewById(R.id.recycler_tv_comment_child_main_forum);
//        tvShare = findViewById(R.id.recycler_tv_share_child_main_forum);
//        tvAmountCharacter = findViewById(R.id.tv_counter_character_comment);
//
//        clShareTrade = findViewById(R.id.cl_share_trade);
//        tvTransactionTypeShareTrade = findViewById(R.id.tv_transaction_type_share_trade);
//        tvContentShareTrade = findViewById(R.id.tv_content_share_trade);
//        tvNameProductShareTrade = findViewById(R.id.tv_name_product_share_trade);
//        tvPriceProductShareTrade = findViewById(R.id.tv_price_product_share_trade);
//        tvManagerInvestShareTrade = findViewById(R.id.tv_name_manager_invest_share_trade);

        tvTitle.setText(getString(R.string.comment));

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);
//        tvViewMore.setVisibility(View.GONE);
//        tvType.setVisibility(View.GONE);

        recyclerComment.setFocusable(false);
        recyclerComment.setLayoutManager(new LinearLayoutManager(this));
        recyclerComment.setAdapter(commentAdapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            String postID = intent.getStringExtra(DATA);
            viewModel.loadData(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
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
        bsReport.addBottomSheetCallback(bottomSheetCallback);
        btnReport.setOnClickListener(this);
        imgSendComment.setOnClickListener(this);

//        tvTransactionTypeShareTrade.setOnClickListener(this);
//        imgBtnMore.setOnClickListener(this);
//        tvLike.setOnClickListener(this);
//        tvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.bs_btn_update_choose_image:
                if (this.report == null) {
                    showSnackBar("Mohon pilih jenis laporan");
                } else {
                    viewModel.reportPostOrForumWith(this.report, postID, prefConfig.getTokenUser(), prefConfig.getProfileID(), reportType);
                }
                break;
            case R.id.img_btn_send_comment:
            case R.id.tv_post_new_comment:
                String content = etComment.getText().toString().trim();
                if (content.isEmpty()) {
                    showSnackBar("Mohon isi komentar");
                } else {
                    viewModel.sendComment(prefConfig.getTokenUser(), prefConfig.getProfileID(), this.post.getPostID(), content);
                }
                break;
            case R.id.recycler_img_btn_more_repost_news_main_forum:
            case R.id.recycler_img_btn_more_repost_main_forum:
            case R.id.recycler_img_btn_more_child_main_forum:
                PopupMenu popup = new PopupMenu(v.getContext(), imgBtnMore);

                int layout = -1;

                if (postType == REPOST_GENERAL || postType == REPOST_NEWS) {
                    if (this.post.getProfileID().equalsIgnoreCase(prefConfig.getProfileID())) {
                        layout = R.menu.menu_repost;
                    }
                } else {
                    layout = prefConfig.getProfileID().equals(this.post.getProfileID()) ? R.menu.menu_self_post : R.menu.menu_other_post;
                }

                popup.getMenuInflater()
                        .inflate(layout, popup.getMenu());

                if (popup.getMenu().findItem(R.id.menu_save) != null) {
                    String save = this.post.getStatusSave().equalsIgnoreCase("true") ? "Saved" : "Save";
                    popup.getMenu().findItem(R.id.menu_save).setTitle(save);
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_report:
                                reportType = "post";
                                viewModel.onReport(prefConfig.getTokenUser(), post);
                                break;
                            case R.id.menu_save:
                                viewModel.savePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), post.getPostID());
                                break;
                            case R.id.menu_delete:
                                deleteDialog = new DeleteDialog(postID, CommentActivity.this, "");
                                deleteDialog.show(getSupportFragmentManager(), "post-delete");
                                break;
                            case R.id.menu_edit:
                                Intent intent = new Intent(CommentActivity.this, PostActivity.class);
                                intent.putExtra(PostActivity.POST_TYPE, PostActivity.EDIT_POST);
                                intent.putExtra(PostActivity.DATA, Utils.toJSON(post));
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;
            case R.id.tv_transaction_type_share_trade:
                if (null != post.getShareTrade()) {
                    Forum.ShareTrade shareTrade = post.getShareTrade();
                    if (shareTrade.getType().equalsIgnoreCase("Jual") || shareTrade.getType().equalsIgnoreCase("Beli")) {
                        intent = new Intent(this, DetailReksaDanaActivity.class);
                        intent.putExtra(DetailTransactionActivity.PARCEL_DATA, shareTrade.getReksadanaID());
                    } else {
                        intent = new Intent(this, DetailProductActivity.class);
                        intent.putExtra(DetailProductActivity.PRODUCT_TYPE, 0);
                    }
                    startActivity(intent);
                }
                break;
            case R.id.recycler_tv_like_comment:
                viewModel.likePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), post.getPostID());
                break;
            case R.id.recycler_tv_share_comment:
                reshareDialog = new ReshareDialog("Apakah Anda ingin reshare postingan ini?", false, this, postID);
                reshareDialog.show(getSupportFragmentManager(), "");
                break;
            case R.id.recycler_img_profile_child_main_forum:
            case R.id.recycler_img_profile_comment:
            case R.id.recycler_tv_name_child_main_forum:
            case R.id.recycler_tv_name_comment:
                if (prefConfig.getProfileID().equalsIgnoreCase(this.post.getProfileID())) {
                    startActivity(new Intent(this, ForumProfileActivity.class));
                } else {
                    intent = new Intent(this, OtherProfileActivity.class);
                    intent.putExtra(OtherProfileActivity.DATA, this.post.getProfileID());
                    startActivity(intent);
                }
                break;
            case R.id.recycler_cv_repost_news_main_forum:
                if (null != this.post.getPost()) {
                    intent = new Intent(this, CommentActivity.class);
                    intent.putExtra(DATA, this.post.getPost().getPostID());
                    startActivity(intent);
                }
                break;
            case R.id.cl_share_trade:
                Forum.ShareTrade shareTrade = this.post.getShareTrade();
                intent = new Intent(this, DetailReksaDanaActivity.class);
                intent.putExtra(DetailReksaDanaActivity.REKSA_DANA_ID, shareTrade.getReksadanaID());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onReportClick(Forum.Comment comment) {
        this.postID = comment.getCommentID();
        viewModel.onReport(prefConfig.getTokenUser(), comment);
    }

    @Override
    public void onDeleteComment(Forum.Comment comment) {
        deleteDialog = new DeleteDialog(comment.getCommentID(), this, "Komentar Anda akan dihapus dan orang lain tidak bisa melihat komentar Anda.");
        deleteDialog.show(getSupportFragmentManager(), "comment-delete");
    }

    @Override
    public void onDetailProfile(String profileID) {
        Intent intent;
        if (profileID.equalsIgnoreCase(prefConfig.getProfileID())) {
            intent = new Intent(this, ForumProfileActivity.class);
        } else {
            intent = new Intent(this, OtherProfileActivity.class);
            intent.putExtra(OtherProfileActivity.DATA, profileID);
        }
        startActivity(intent);
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                frameLayout.setVisibility(View.VISIBLE);
            } else {
                frameLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };


    @Override
    public void onLoadComment(Forum.Post post, List<Forum.Comment> commentList, int type) {
        this.post = post;
        this.postType = type;

        commentAdapter.setCommentList(commentList);
        commentAdapter.notifyDataSetChanged();

        ConstraintLayout clComment = findViewById(R.id.cl_comment);
        ConstraintLayout clShareTrade = findViewById(R.id.cl_share_trade);
        FrameLayout frameComment = findViewById(R.id.frame_comment);

        ConstraintLayout clRepost;
        RoundedImageView rivProfile, rivRepostProfile;
        TextView tvDatePost, tvDateRepost;
        TextView tvContent;
        TextView tvContentNews, tvTitleNews;
        TextView tvName, tvRepostName;
        TextView tvLookMore;
        ImageView imgNewsContent;

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        if (null != post.getStatusShare()) {
            int drawableShare = post.getStatusShare().equalsIgnoreCase("true") ? R.drawable.ic_share_yellow : R.drawable.ic_share;
            tvShare.setText(post.getShare());
            tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableShare, 0);
            tvShare.setOnClickListener(this);
        }

        tvLike.setText(post.getLike());
        tvComment.setText(post.getComment());

        tvLike.setOnClickListener(this);

        switch (type) {
            case REPOST_NEWS:
            case REPOST_GENERAL:
                int layout = type == REPOST_NEWS ? R.layout.recycler_repost_news_main_forum : R.layout.recycler_repost_child_main_forum;

                clRepost = (ConstraintLayout) getLayoutInflater().inflate(layout, null);

                frameComment.addView(clRepost);

                CardView cardView = findViewById(R.id.recycler_cv_repost_news_main_forum);

                rivProfile = findViewById(R.id.recycler_img_profile_child_main_forum);
                rivRepostProfile = findViewById(R.id.recycler_img_profile_post_source_repost_news);
                tvName = findViewById(R.id.recycler_tv_name_child_main_forum);
                tvRepostName = findViewById(R.id.recycler_tv_name_post_source_repost_news);
                tvDatePost = findViewById(R.id.recycler_tv_date_child_main_forum);
                tvDateRepost = findViewById(R.id.recycler_tv_date_source_repost_news);
                tvLookMore = findViewById(R.id.recycler_tv_view_more_child_main_forum);
                tvContent = findViewById(R.id.recycler_tv_content_child_main_forum);
                tvContentNews = findViewById(R.id.recycler_tv_content_news);
                imgNewsContent = findViewById(R.id.recycler_img_thumbnail_news);

                findViewById(R.id.recycler_tv_like_child_main_forum).setVisibility(View.GONE);
                findViewById(R.id.recycler_tv_comment_child_main_forum).setVisibility(View.GONE);
                findViewById(R.id.recycler_tv_share_child_main_forum).setVisibility(View.GONE);
                findViewById(R.id.recycler_view_01).setVisibility(View.GONE);

                Picasso.get()
                        .load(Utils.imageURL(post.getImageProfile()))
                        .into(rivProfile);

                tvName.setText(post.getName());
                try {
                    String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_4, Constant.DATE_FORMAT_5, post.getDate());
                    tvDatePost.setText(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                rivProfile.setOnClickListener(this);
                tvName.setOnClickListener(this);
                cardView.setOnClickListener(this);

                if (null != post.getPost()) {
                    Forum.Post post1 = post.getPost();

                    Picasso.get()
                            .load(Utils.imageURL(post1.getImageProfile()))
                            .into(rivRepostProfile);

                    tvRepostName.setText(post1.getName());
                    try {
                        String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_4, Constant.DATE_FORMAT_5, post1.getDate());
                        tvDateRepost.setText(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    switch (type) {
                        case REPOST_NEWS:
                            imgBtnMore = findViewById(R.id.recycler_img_btn_more_repost_news_main_forum);
                            clRepost.setVisibility(View.VISIBLE);

                            tvContent.setText(post1.getContent());
                            tvContentNews.setText(post1.getPromoNews().getContent());

                            Picasso.get()
                                    .load(Utils.imageURL(post1.getPromoNews().getImage()))
                                    .into(imgNewsContent);

                            break;
                        case REPOST_GENERAL:
                            RecyclerView recyclerImageRepost = findViewById(R.id.recycler_rv_img_repost_child_main_forum);
                            imgBtnMore = findViewById(R.id.recycler_img_btn_more_repost_main_forum);

                            clRepost.setVisibility(View.VISIBLE);
                            tvContent.setText(post1.getContent());

                            if (tvContent.getText().toString().equals(post1.getContent())) {
                                tvLookMore.setVisibility(View.GONE);
                            }

                            if (null != post1.getImagePostList() && post1.getImagePostList().size() > 0) {
                                PostImageAdapter postImageAdapter = new PostImageAdapter();

                                if (post1.getImagePostList().size() == 1) {
                                    recyclerImageRepost.setLayoutManager(new LinearLayoutManager(this));
                                } else if (post1.getImagePostList().size() == 2) {
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

                                postImageAdapter.setImageList(post1.getImagePostList());
                                recyclerImageRepost.setAdapter(postImageAdapter);
                            }

                            clRepost.setOnClickListener(this);
                            break;
                    }
                    if (this.post.getProfileID().equalsIgnoreCase(prefConfig.getProfileID())) {
                        imgBtnMore.setOnClickListener(this);
                    } else {
                        imgBtnMore.setVisibility(View.GONE);
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
                imgNewsContent = findViewById(R.id.recycler_img_thumbnail_news_comment);
                tvContentNews = findViewById(R.id.recycler_tv_content_news_comment);
                tvName = findViewById(R.id.recycler_tv_name_comment);
                imgBtnMore = findViewById(R.id.recycler_img_btn_more_child_main_forum);

                clComment.setVisibility(View.VISIBLE);

                tvName.setText(post.getName());
                try {
                    String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_4, Constant.DATE_FORMAT_5, post.getDate());
                    tvDatePost.setText(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tvContent.setText(post.getContent());

                Picasso.get()
                        .load(Utils.imageURL(post.getImageProfile()))
                        .into(rivProfile);

                imgBtnMore.setOnClickListener(this);
                rivProfile.setOnClickListener(this);
                tvName.setOnClickListener(this);

                switch (type) {
                    case STRATEGY:
                        if (null != post.getImagePostList() && post.getImagePostList().size() > 0) {
                            recyclerImage.setVisibility(View.VISIBLE);

                            CommentImageAdapter commentImageAdapter = new CommentImageAdapter();
                            commentImageAdapter.setOnImageClick(this);

                            recyclerImage.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                            recyclerImage.addItemDecoration(new SpacesItemDecoration(5));

                            commentImageAdapter.setImageList(post.getImagePostList());
                            recyclerImage.setAdapter(commentImageAdapter);
                        }
                        break;
                    case NEWS:
                        tvTitleNews = findViewById(R.id.recycler_tv_title_news_comment);

                        imgNewsContent.setVisibility(View.VISIBLE);
                        tvContentNews.setVisibility(View.VISIBLE);
                        tvTitleNews.setVisibility(View.VISIBLE);

                        if (null != post.getPromoNews()) {
                            PromoNews promoNews = post.getPromoNews();
                            Picasso.get()
                                    .load(Utils.imageURL(promoNews.getImage()))
                                    .into(imgNewsContent);
                            tvContentNews.setText(promoNews.getContent());
                            tvTitleNews.setText(promoNews.getTitle());
                        }
                        break;
                    case SHARE_TRADE:
                        TextView tvTransactionType = findViewById(R.id.tv_transaction_type_share_trade);
                        TextView tvDateShareTrade = findViewById(R.id.tv_name_manager_invest_share_trade);
                        TextView tvValueShareTrade = findViewById(R.id.tv_price_product_share_trade);
                        TextView tvContentShareTrade = findViewById(R.id.tv_content_share_trade);
                        TextView tvProductName = findViewById(R.id.tv_name_product_share_trade);

                        clShareTrade.setVisibility(View.VISIBLE);

                        tvTransactionType.setOnClickListener(this);

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
                                }
                                content = "Nilai investasi saya";
                                tvDateShareTrade.setText("pembelian pertama: " + shareTrade.getDate());
                            }

                            clShareTrade.setBackground(ContextCompat.getDrawable(this, background));

                            tvValueShareTrade.setText(value);
                            tvContentShareTrade.setText(content);
                            tvTransactionType.setText(shareTradeType);
                            tvProductName.setText(shareTrade.getProductName());

                            clShareTrade.setOnClickListener(this);
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
        this.postID = "";
        this.reportType = "";
        this.report = null;
        bsReport.setState(BottomSheetBehavior.STATE_COLLAPSED);
        showSnackBar("Report sukses dikirim.");
    }

    @Override
    public void onLoadReport(List<Forum.Report> reportList, Forum.Comment comment) {
        reportType = "comment";
        loadReport(reportList);
    }

    @Override
    public void onLoadReport(List<Forum.Report> reportList, Forum.Post post) {
        this.postID = post.getPostID();
        loadReport(reportList);
    }

    @Override
    public void onLikeResult(Forum.LikePost likePost) {
        this.post.setStatusLike(likePost.getLike());
        int amountLike = post.getStatusLike().equalsIgnoreCase("true")
                ? Integer.parseInt(this.post.getLike()) + 1 : Integer.parseInt(this.post.getLike()) - 1;
        this.post.setLike(String.valueOf(amountLike));

        int drawableLike = post.getStatusLike().equalsIgnoreCase("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);
        tvLike.setText(this.post.getLike());
    }

    @Override
    public void onRepostSuccess() {
        this.post.setStatusShare("true");
        this.post.setShare(String.valueOf(Integer.parseInt(this.post.getShare()) + 1));
        tvShare.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_share_yellow, 0);
        tvShare.setText(this.post.getShare());
        showSnackBar("Share post berhasil");
    }

    @Override
    public void onDeleteSuccess() {
        setResult(1);
        finish();
    }

    @Override
    public void onDeleteCommentSuccess(String commentID) {
        commentAdapter.removeComment(commentID);
        this.post.setComment(String.valueOf(Integer.parseInt(this.post.getComment()) - 1));
        tvComment.setText(this.post.getComment());
        showSnackBar("Hapus Komentar berhasil");
    }

    @Override
    public void onSaveResult(Forum.SavePost savePost) {
        this.post.setStatusSave(savePost.getSaveStatus());
        String saveStatus = savePost.getSaveStatus().equalsIgnoreCase("true") ? "Save post berhasil" : "Unsave post berhasil";
        showSnackBar(saveStatus);
    }

    @Override
    public void onSuccessSendComment(Forum.Comment comment) {
        etComment.setText("");
        this.post.setComment(String.valueOf(Integer.parseInt(this.post.getComment()) + 1));
        comment.setImage(prefConfig.getImageProfile());
        comment.setName(prefConfig.getUsername());
        comment.setProfileID(prefConfig.getProfileID());
        tvComment.setText(this.post.getComment());
//        Log.e("asd", Utils.toJSON(comment));
//        try {
//            String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_7, Constant.DATE_FORMAT_5, comment.getDate());
//            comment.setDate(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        comment.setDate(comment.getDate());
        commentAdapter.addComment(comment);
        Utils.hideSoftKeyboard(this);
        showSnackBar("Comment success");
    }

    @Override
    public void onSessionExpired() {
        sessionExpired();
    }

    @Override
    public void onImageClickWith(String URl) {
        imageDialog = new ImageDialog(URl, this);
        imageDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onItemReportChoose(Forum.Report report) {
        btnReport.setEnabled(true);
        btnReport.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        btnReport.setTextColor(getResources().getColor(android.R.color.black));
        this.report = report;
    }

    @Override
    public void onDismissImageDialog() {
        if (null != imageDialog && null != imageDialog.getTag()) {
            imageDialog.dismiss();
        }
    }

    private void loadReport(List<Forum.Report> reportList) {
        bsReport.setState(BottomSheetBehavior.STATE_EXPANDED);

        tvTitleReport.setText("Alasan Pelaporan");
        btnReport.setText("Laporkan");

        btnReport.setEnabled(false);
        btnReport.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnReport.setTextColor(getResources().getColor(android.R.color.white));

        reportAdapter.setReportList(reportList);
        reportAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResharePost(String postID) {
        if (reshareDialog != null && reshareDialog.getTag() != null) {
            reshareDialog.dismiss();
        }
        viewModel.sharePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), post.getPostID());
    }

    @Override
    public void onUndoResharePost(String postID) {

    }

    @Override
    public void onSendDeletePost(String postID) {
        if (null != deleteDialog && null != deleteDialog.getTag()) {
            if (deleteDialog.getTag().equalsIgnoreCase("post-delete")) {
                viewModel.deletePost(prefConfig.getTokenUser(), prefConfig.getProfileID(), post.getPostID());
            } else {
                viewModel.deleteComment(prefConfig.getTokenUser(), prefConfig.getProfileID(), postID);
            }
            deleteDialog.dismiss();
        }
    }
}