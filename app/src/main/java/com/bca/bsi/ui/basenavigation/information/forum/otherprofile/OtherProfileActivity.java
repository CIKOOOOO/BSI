package com.bca.bsi.ui.basenavigation.information.forum.otherprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.information.forum.inbox.InboxActivity;
import com.bca.bsi.utils.BaseActivity;
import com.makeramen.roundedimageview.RoundedImageView;

public class OtherProfileActivity extends BaseActivity implements View.OnClickListener {

    public static final String DATA = "data";

    private RoundedImageView roundedImageView;
    private TextView tvFollow, tvFollower, tvFollowing, tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        initVar();
    }

    private void initVar() {
        ImageButton imgActionBtn = findViewById(R.id.img_btn_action_toolbar_back_with_image);
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back_with_image);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar_with_image);
        RecyclerView recyclerView = findViewById(R.id.recycler_other_profile);

        roundedImageView = findViewById(R.id.rounded_image_view_other_profile_forum);
        tvFollow = findViewById(R.id.tv_follow_other_profile);
        tvFollower = findViewById(R.id.tv_follower_other_forum_profile);
        tvFollowing = findViewById(R.id.tv_following_other_forum_profile);
        tvName = findViewById(R.id.tv_name_other_profile);

        imgActionBtn.setBackground(getDrawable(R.drawable.ic_inbox_logo));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {

        } else {
            onBackPressed();
        }

        imgBack.setOnClickListener(this);
        imgActionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar_with_image:
                onBackPressed();
                break;
            case R.id.img_btn_action_toolbar_back_with_image:
                Intent intent = new Intent(this, InboxActivity.class);

                startActivity(intent);
                break;
        }
    }
}