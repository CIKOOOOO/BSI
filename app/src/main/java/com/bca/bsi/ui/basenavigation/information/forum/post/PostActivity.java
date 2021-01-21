package com.bca.bsi.ui.basenavigation.information.forum.post;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PrivacyAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.Privacy;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends BaseActivity implements PrivacyAdapter.onPrivacyClick, PostImageAdapter.onItemClick, View.OnClickListener, CategoryAdapter.onCategoryClick, IPostCallback {

    public static final String NEW_STANDARD_POST = "new_standard_post";
    public static final String EDIT_POST = "edit_post";
    public static final String SHARE_NEWS = "share_news";

    public static final String DATA = "data";
    public static final String POST_TYPE = "post_type";

    private static final int REQUEST_GALLERY_THUMBNAIL = 101;
    private static final int GALLERY_THUMBNAIL = 202;

    private Privacy privacy;
    private PostImageAdapter postImageAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Bitmap> imagesEncodedList;
    private RecyclerView recyclerImageNews;
    private BottomSheetBehavior<ConstraintLayout> bsCategory;
    private FrameLayout frameLayout;
    private PostViewModel viewModel;
    private EditText etContent;
    private TextView tvCharacterCounter;
    private PromoNews promoNews;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initVar();
    }

    private void initVar() {
        RecyclerView recyclerPrivacy = findViewById(R.id.recycler_privacy_post);
        ConstraintLayout clShareTrade = findViewById(R.id.cl_share_trade_post);
        ConstraintLayout clCategory = findViewById(R.id.cl_choose_image);
        Button btnShare = findViewById(R.id.btn_share_post);
        RecyclerView recyclerCategory = findViewById(R.id.bs_recycler_choose_image);
        Button btnPost = findViewById(R.id.bs_btn_update_choose_image);
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvCurrentDate = findViewById(R.id.tv_date_post_activity);
        TextView tvName = findViewById(R.id.tv_name_post_activity);
        RoundedImageView imgProfile = findViewById(R.id.rounded_image_view_profile_post);

        recyclerImageNews = findViewById(R.id.recycler_image_news_post);
        frameLayout = findViewById(R.id.frame_post);
        etContent = findViewById(R.id.et_content_post);
        tvCharacterCounter = findViewById(R.id.tv_character_counter_post);

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(this);

        categoryAdapter = new CategoryAdapter(this);

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.setCallback(this);
        viewModel.loadCategoryData();

        bsCategory = BottomSheetBehavior.from(clCategory);

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        ConstraintLayout.LayoutParams recyclerReportLayoutParams = (ConstraintLayout.LayoutParams) recyclerCategory.getLayoutParams();
        recyclerReportLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerReportLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerReportLayoutParams.bottomMargin = 20;
        recyclerCategory.setLayoutParams(recyclerReportLayoutParams);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) clCategory.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        clCategory.setLayoutParams(layoutParams);

        imagesEncodedList = new ArrayList<>();

        recyclerCategory.setLayoutManager(new LinearLayoutManager(this));
        recyclerCategory.setAdapter(categoryAdapter);

        btnPost.setText(getString(R.string.post));
        tvCurrentDate.setText(Utils.getTime(Constant.DATE_FORMAT_1));
        tvName.setText(prefConfig.getUsername());

        if (!prefConfig.getImageProfile().isEmpty())
            Picasso.get()
                    .load(prefConfig.getImageProfile())
                    .into(imgProfile);

        Intent intent = getIntent();
        Gson gson = new Gson();
        if (intent != null && intent.hasExtra(POST_TYPE)) {
            type = intent.getStringExtra(POST_TYPE);
            if (type != null) {
                String titleToolbar = "";
                switch (type) {
                    case NEW_STANDARD_POST:
                        titleToolbar = getString(R.string.make_post);
                        postImageAdapter = new PostImageAdapter(PostImageAdapter.CHOOSE_IMAGE, this);
                        recyclerImageNews.setLayoutManager(new LinearLayoutManager(this));
                        recyclerImageNews.setAdapter(postImageAdapter);
                        break;
                    case SHARE_NEWS:
                        titleToolbar = getString(R.string.make_post);
                        if (intent.hasExtra(DATA)) {
                            String data = intent.getStringExtra(DATA);
                            this.promoNews = gson.fromJson(data, PromoNews.class);
                            postImageAdapter = new PostImageAdapter(PostImageAdapter.NEWS, this);
                            recyclerImageNews.setLayoutManager(new LinearLayoutManager(this));
                            postImageAdapter.setPromoNews(this.promoNews);
                            recyclerImageNews.setAdapter(postImageAdapter);
                        }
                        break;
                }
                tvTitleToolbar.setText(titleToolbar);
            } else {
                onBackPressed();
            }
        } else {
            onBackPressed();
        }

        bsCategory.addBottomSheetCallback(bottomSheetCallback);
        etContent.addTextChangedListener(characterCounter);

        recyclerPrivacy.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerPrivacy.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
        recyclerPrivacy.setAdapter(privacyAdapter);

        btnShare.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onItemPrivacyClick(Privacy privacy) {
        this.privacy = privacy;

    }

    @Override
    public void onOpenGallery() {
        openGallery();
    }

    @Override
    public void onRemoveImageAt(int pos) {
        imagesEncodedList.remove(pos);
        postImageAdapter.setBitmapList(imagesEncodedList);
        postImageAdapter.notifyDataSetChanged();
    }

    private void openGallery() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_GALLERY_THUMBNAIL);
            }
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_THUMBNAIL);
        }
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

    private TextWatcher characterCounter = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String counter = s.length() + "/1000";
            tvCharacterCounter.setText(counter);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_GALLERY_THUMBNAIL && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            showSnackBar(getString(R.string.permission_failed));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == GALLERY_THUMBNAIL && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                if (data.getData() != null) {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imagesEncodedList.add(bitmap);
                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imagesEncodedList.add(bitmap);
                        }
                    }
                }
                recyclerImageNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                postImageAdapter.setBitmapList(imagesEncodedList);
                postImageAdapter.notifyDataSetChanged();
            } else {
                showSnackBar("You haven't picked Image");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_share_post:
                ((TextView) findViewById(R.id.bs_tv_title_choose_image)).setText(getString(R.string.choose_category));
                bsCategory.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.bs_btn_update_choose_image:

                break;
        }
    }

    @Override
    public void onItemCategoryChoose(Forum.Category category) {

    }

    @Override
    public void onBackPressed() {
        if (bsCategory.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bsCategory.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else
            super.onBackPressed();
    }

    @Override
    public void onLoadCategoryData(List<Forum.Category> categoryList) {
        categoryAdapter.setReportList(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}