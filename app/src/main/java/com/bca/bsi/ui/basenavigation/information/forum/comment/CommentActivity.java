package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.BaseActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CommentActivity extends BaseActivity implements View.OnClickListener, CommentAdapter.onReport, ICommentCallback {

    public static final String DATA = "data";

    private EditText etComment;
    private RoundedImageView rivProfile;
    private TextView tvName, tvDate, tvType, tvAmountCharacter, tvContent, tvViewMore, tvLike, tvAmountComment, tvShare, tvTransactionTypeShareTrade, tvContentShareTrade, tvNameProductShareTrade, tvPriceProductShareTrade, tvManagerInvestShareTrade;
    private ImageButton imgBtnMore;
    private ConstraintLayout clShareTrade;
    private CommentAdapter commentAdapter;
    private CommentViewModel viewModel;

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

        rivProfile = findViewById(R.id.recycler_img_profile_child_main_forum);
        tvName = findViewById(R.id.recycler_tv_name_child_main_forum);
        tvDate = findViewById(R.id.recycler_tv_date_child_main_forum);
        tvType = findViewById(R.id.recycler_tv_type_child_main_forum);
        tvContent = findViewById(R.id.recycler_tv_content_child_main_forum);
        tvViewMore = findViewById(R.id.recycler_tv_view_more_child_main_forum);
        tvLike = findViewById(R.id.recycler_tv_like_child_main_forum);
        tvAmountComment = findViewById(R.id.recycler_tv_comment_child_main_forum);
        tvShare = findViewById(R.id.recycler_tv_share_child_main_forum);
        imgBtnMore = findViewById(R.id.recycler_img_btn_more_child_main_forum);
        tvAmountCharacter = findViewById(R.id.tv_counter_character_comment);

        clShareTrade = findViewById(R.id.cl_share_trade);
        tvTransactionTypeShareTrade = findViewById(R.id.tv_transaction_type_share_trade);
        tvContentShareTrade = findViewById(R.id.tv_content_share_trade);
        tvNameProductShareTrade = findViewById(R.id.tv_name_product_share_trade);
        tvPriceProductShareTrade = findViewById(R.id.tv_price_product_share_trade);
        tvManagerInvestShareTrade = findViewById(R.id.tv_name_manager_invest_share_trade);

        commentAdapter = new CommentAdapter(this);
        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.comment));

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);
        tvViewMore.setVisibility(View.GONE);
        tvType.setVisibility(View.GONE);

        recyclerComment.setLayoutManager(new LinearLayoutManager(this));
        recyclerComment.setAdapter(commentAdapter);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(DATA)){
            String postID = intent.getStringExtra(DATA);
            viewModel.loadComment(postID);
        }

        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvAmountCharacter.setText(count + "/160");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgBack.setOnClickListener(this);
        tvComment.setOnClickListener(this);

//        tvTransactionTypeShareTrade.setOnClickListener(this);
        imgBtnMore.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        tvShare.setOnClickListener(this);
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
            case R.id.recycler_tv_like_child_main_forum:
                break;
            case R.id.recycler_tv_share_child_main_forum:
                break;
        }
    }

    @Override
    public void onReportClick(Forum.Comment comment) {
        viewModel.onReport(comment);
    }

    @Override
    public void onLoadComment(List<Forum.Comment> commentList) {
        commentAdapter.setCommentList(commentList);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onSuccessReport() {

    }
}