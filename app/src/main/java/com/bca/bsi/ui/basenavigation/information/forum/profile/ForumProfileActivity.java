package com.bca.bsi.ui.basenavigation.information.forum.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.adapter.TabAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.inbox.InboxActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.connection.ConnectionActivity;
import com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.bookmark.BookmarkFragment;
import com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.posting.PostingFragment;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.dummydata.DummyData;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForumProfileActivity extends BaseActivity implements View.OnClickListener, ChooseImageAdapter.onImageClick, IForumProfileCallback {

    private static final int PROFILE_PICTURE = 0, BACKGROUND = 1;

    private TextView tvFollower, tvFollowing, tvTitleBottomSheet;
    private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;
    private RecyclerView recycler_choose_image;
    private FrameLayout frameLayout;
    private ChooseImageAdapter chooseImageAdapter;
    private EditText etName;
    private ImageView imgRedDot, imgHeader;
    private ForumProfileViewModel viewModel;
    private RoundedImageView imageProfile;
    private ConstraintLayout.LayoutParams layoutParams;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private PostingFragment postingFragment;
    private BookmarkFragment bookmarkFragment;

    private String imageID;
    private int typeUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_profile);
        initVar();
    }

    private void initVar() {
        typeUpload = -1;
        imageID = "";

        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back_with_image);
        ImageButton imgActionBtn = findViewById(R.id.img_btn_action_toolbar_back_with_image);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar_with_image);
        ImageButton imgEditUsername = findViewById(R.id.img_btn_edit_name_forum_profile);
        ImageButton imgBtnBackground = findViewById(R.id.img_btn_background_forum_profile);
        ImageButton imgBtnProfilePhoto = findViewById(R.id.img_btn_photo_profile_forum_profile);
        ConstraintLayout constraintLayout = findViewById(R.id.cl_choose_image);
        LinearLayout linearLayout = findViewById(R.id.ll_forum_profile);
        Button btnUpdate = findViewById(R.id.bs_btn_update_choose_image);

        tvFollower = findViewById(R.id.tv_follower_forum_profile);
        tvFollowing = findViewById(R.id.tv_following_forum_profile);
        frameLayout = findViewById(R.id.frame_forum_profile);
        recycler_choose_image = findViewById(R.id.bs_recycler_choose_image);
        tvTitleBottomSheet = findViewById(R.id.bs_tv_title_choose_image);
        etName = findViewById(R.id.et_name_forum_profile);
        imgRedDot = findViewById(R.id.iv_red_dot_toolbar_back_with_image);
        imageProfile = findViewById(R.id.rounded_image_view_profile_forum);
        imgHeader = findViewById(R.id.img_view_header_forum_profile);

        layoutParams = (ConstraintLayout.LayoutParams) recycler_choose_image.getLayoutParams();
        gridSpacingItemDecoration = new GridSpacingItemDecoration(2, 20, true);

        bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        chooseImageAdapter = new ChooseImageAdapter(this);
        viewModel = new ViewModelProvider(this).get(ForumProfileViewModel.class);
        viewModel.setCallback(this);

        setupTab();

        viewModel.loadProfile(prefConfig.getTokenUser(), prefConfig.getProfileID());

        tvTitle.setText(prefConfig.getUsername());
        etName.setText(prefConfig.getUsername());

        imgActionBtn.setBackground(getDrawable(R.drawable.ic_inbox_logo));

        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback);

        imgActionBtn.setOnClickListener(this);
        imgEditUsername.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgBtnBackground.setOnClickListener(this);
        imgBtnProfilePhoto.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            int visibility = newState == BottomSheetBehavior.STATE_EXPANDED ? View.VISIBLE : View.GONE;
            frameLayout.setVisibility(visibility);
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar_with_image:
                onBackPressed();
                break;
            case R.id.img_btn_action_toolbar_back_with_image:
                Intent intents = new Intent(this, InboxActivity.class);

                startActivity(intents);
                break;
            case R.id.img_btn_edit_name_forum_profile:
                if (etName.isEnabled()) {
                    etName.setEnabled(false);
                } else {
                    etName.setEnabled(true);
                    etName.requestFocus();
                }
                break;
            case R.id.img_btn_photo_profile_forum_profile:
                imageID = "";

                typeUpload = PROFILE_PICTURE;

                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                recycler_choose_image.setLayoutParams(layoutParams);

                chooseImageAdapter.setType(ChooseImageAdapter.GRID_IMAGE);
                tvTitleBottomSheet.setText(getString(R.string.choose_profile_photo));
                recycler_choose_image.setLayoutManager(new GridLayoutManager(this, 2));
                recycler_choose_image.removeItemDecoration(gridSpacingItemDecoration);
                recycler_choose_image.addItemDecoration(gridSpacingItemDecoration);

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                recycler_choose_image.setAdapter(chooseImageAdapter);

                viewModel.loadPicture(typeUpload, prefConfig.getTokenUser(), prefConfig.getProfileID());
                break;
            case R.id.img_btn_background_forum_profile:
                imageID = "";

                typeUpload = BACKGROUND;

                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                recycler_choose_image.setLayoutParams(layoutParams);

                chooseImageAdapter.setType(ChooseImageAdapter.SINGLE_IMAGE);
                tvTitleBottomSheet.setText(getString(R.string.choose_header));
                recycler_choose_image.removeItemDecoration(gridSpacingItemDecoration);
                recycler_choose_image.setLayoutManager(new LinearLayoutManager(this));

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                recycler_choose_image.setAdapter(chooseImageAdapter);
                viewModel.loadPicture(typeUpload, prefConfig.getTokenUser(), prefConfig.getProfileID());
                break;
            case R.id.ll_forum_profile:
                Intent intent = new Intent(this, ConnectionActivity.class);
                startActivity(intent);
                break;
            case R.id.bs_btn_update_choose_image:
                if (this.imageID.isEmpty()) {
                    showSnackBar("Mohon pilih gambar");
                } else {
                    viewModel.updatePicture(typeUpload, prefConfig.getTokenUser(), prefConfig.getProfileID(), imageID);
                }
                break;
        }
    }

    private void setupTab() {
        TabLayout tabLayout = findViewById(R.id.tl_forum_profile);
        final ViewPager viewPager = findViewById(R.id.vp_forum_profile);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());

        postingFragment = new PostingFragment();
        bookmarkFragment = new BookmarkFragment();

        tabAdapter.addTab(postingFragment, Constant.FORUM_PROFILE_MENU[0]);
        tabAdapter.addTab(bookmarkFragment, Constant.FORUM_PROFILE_MENU[1]);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < Constant.FORUM_PROFILE_MENU.length; i++) {
                    String name = Constant.FORUM_PROFILE_MENU[i];
                    if (tab.getText() != null && tab.getText().equals(name)) {
                        viewPager.setCurrentItem(i);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        if (tabLayout.getTabCount() <= 2) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

        postingFragment.loadData(DummyData.getPostStrategyList());
        bookmarkFragment.loadData(DummyData.getRepostGeneralList());
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(Forum.ProfilePicture profilePicture) {
        this.imageID = profilePicture.getId();
    }

    @Override
    public void onLoadData(Forum.User user, List<Forum.Post> postList, List<Forum.Post> bookmarkList) {

        int visibility = user.getStatusInbox().equalsIgnoreCase("read") ? View.GONE : View.VISIBLE;

        if (!user.getImgProfileUrl().isEmpty()) {
            Picasso.get()
                    .load(user.getImgProfileUrl())
                    .into(imageProfile);
        }

        if (!user.getImgBackgroundUrl().isEmpty()) {
            Picasso.get()
                    .load(user.getImgBackgroundUrl())
                    .into(imgHeader);
        }

        postingFragment.loadData(postList);
        bookmarkFragment.loadData(bookmarkList);

        imgRedDot.setVisibility(visibility);

        tvFollower.setText(user.getFollowerCount());
        tvFollowing.setText(user.getFollowingCount());
    }

    @Override
    public void onLoadProfilePicture(List<Forum.ProfilePicture> profilePictureList) {
        chooseImageAdapter.setImageList(profilePictureList);
        chooseImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onDismissBottomNavigation() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}