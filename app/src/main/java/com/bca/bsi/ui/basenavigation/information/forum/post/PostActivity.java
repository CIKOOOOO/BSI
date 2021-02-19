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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PrivacyAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.Privacy;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.forum.post.direct.DirectShareActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.CustomLoading;
import com.bca.bsi.utils.GridSpacingItemDecoration;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostActivity extends BaseActivity implements PrivacyAdapter.onPrivacyClick, PostImageAdapter.onItemClick, View.OnClickListener, CategoryAdapter.onCategoryClick, IPostCallback {

    public static final String NEW_STANDARD_POST = "new_standard_post";
    public static final String EDIT_POST = "edit_post";
    public static final String SHARE_NEWS = "share_news";
    public static final String SHARE_TRADE_INFORMATION = "share_trade_information";
    public static final String SHARE_TRADE_HISTORY = "share_trade_history";

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
    private Portfolio.Information information;
    private Portfolio.History history;
    private Forum.Category category;
    private CustomLoading customLoading;
    private Forum.Post post;

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
        TextView tvTransactionType = findViewById(R.id.tv_share_trade_type_post);
        TextView tvNameShareTrade = findViewById(R.id.tv_name_share_trade_post);
        TextView tvNab = findViewById(R.id.tv_price_share_trade_post);
        ImageView imageShareTrade = findViewById(R.id.img_share_trade_post);

        recyclerImageNews = findViewById(R.id.recycler_image_news_post);
        frameLayout = findViewById(R.id.frame_post);
        etContent = findViewById(R.id.et_content_post);
        tvCharacterCounter = findViewById(R.id.tv_character_counter_post);

        PrivacyAdapter privacyAdapter = new PrivacyAdapter(this);

        categoryAdapter = new CategoryAdapter(this);
        customLoading = new CustomLoading();

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.setCallback(this);
        viewModel.loadCategoryData(prefConfig.getTokenUser());

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
                    .load(Utils.imageURL(prefConfig.getImageProfile()))
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

                        } else {
                            onBackPressed();
                        }
                        break;
                    case SHARE_TRADE_INFORMATION:
                        titleToolbar = getString(R.string.make_post);
                        clShareTrade.setVisibility(View.VISIBLE);
                        if (intent.hasExtra(DATA)) {
                            String data = intent.getStringExtra(DATA);
                            this.information = gson.fromJson(data, Portfolio.Information.class);
                            String value;
                            int drawable;
                            if (this.information.getRaise() > 0) {
                                value = getString(R.string.up);
                                drawable = R.drawable.img_share_trade_up;
                            } else if (this.information.getRaise() < 0) {
                                value = getString(R.string.down);
                                drawable = R.drawable.img_share_trade_down;
                            } else {
                                value = getString(R.string.stay);
                                drawable = R.drawable.img_share_trade_stay;
                            }
                            tvTransactionType.setText(value);
                            tvNameShareTrade.setText(this.information.getName());
                            tvNab.setText("Rp " + Utils.formatUang3(this.information.getNab()));
                            imageShareTrade.setBackground(ContextCompat.getDrawable(this, drawable));
//                            Glide.with(this)
//                                    .load(drawable)
//                                    .into(imageShareTrade);
                        } else {
                            onBackPressed();
                        }
                        break;
                    case SHARE_TRADE_HISTORY:
                        titleToolbar = getString(R.string.make_post);
                        clShareTrade.setVisibility(View.VISIBLE);
                        if (intent.hasExtra(DATA)) {
                            String data = intent.getStringExtra(DATA);
                            this.history = gson.fromJson(data, Portfolio.History.class);
                            String value;
                            int drawable;
                            if (this.history.getTransactionType().equalsIgnoreCase("Pembelian")) {
                                value = getString(R.string.buy);
                                drawable = R.drawable.img_share_trade_buy;
                            } else {
                                value = getString(R.string.sell);
                                drawable = R.drawable.img_share_trade_sell;
                            }
                            tvTransactionType.setText(value);
                            tvNameShareTrade.setText(this.history.getReksadanaName());
                            tvNab.setText("Rp " + Utils.formatUang3(this.history.getNab()));
                            imageShareTrade.setBackground(ContextCompat.getDrawable(this, drawable));
//                            Glide.with(this)
//                                    .load(drawable)
//                                    .into(imageShareTrade);
                        } else {
                            onBackPressed();
                        }
                        break;
                    case EDIT_POST:
                        titleToolbar = getResources().getString(R.string.edit_post);
                        String data = intent.getStringExtra(DATA);
                        this.post = gson.fromJson(data, Forum.Post.class);
                        if (null != post.getPrivacy() && !post.getPrivacy().trim().isEmpty())
                            this.privacy = privacyAdapter.getCategoryDetail(post.getPrivacy());
                        else
                            this.privacy = privacyAdapter.getCategoryDetail("public");
                        if (!post.getContent().trim().isEmpty()) {
                            etContent.setText(post.getContent());
                            tvCharacterCounter.setText(post.getContent().length() + "/1000");
                            etContent.requestFocus();
                            etContent.setSelection(etContent.getText().length());
                        }
                        switch (post.getType().toLowerCase()) {
                            case "general":
                            case Type.STRATEGY:
                                if (null != post.getImagePostList() && post.getImagePostList().size() > 0) {
                                    postImageAdapter = new PostImageAdapter(PostImageAdapter.CHOOSE_IMAGE, this);
                                    recyclerImageNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                                    recyclerImageNews.setAdapter(postImageAdapter);
                                    this.imagesEncodedList = viewModel.convertToBitmap(post.getImagePostList());
                                    postImageAdapter.setBitmapList(this.imagesEncodedList);
                                    postImageAdapter.notifyDataSetChanged();
                                }
                                break;
                            case Type.SHARE_TRADE:
                                clShareTrade.setVisibility(View.VISIBLE);
                                if (null != post.getShareTrade()) {
                                    Forum.ShareTrade shareTrade = post.getShareTrade();
                                    tvTransactionType.setText(shareTrade.getType());
                                    tvNameShareTrade.setText(shareTrade.getProductName());

                                    int drawable;
                                    String value;

                                    if (shareTrade.getType().equalsIgnoreCase("beli")) {
                                        drawable = R.drawable.img_share_trade_buy;
                                        value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                                    } else if (shareTrade.getType().equalsIgnoreCase("jual")) {
                                        drawable = R.drawable.img_share_trade_sell;
                                        value = Utils.formatUang3(Double.parseDouble(shareTrade.getValue()));
                                    } else if (shareTrade.getType().equalsIgnoreCase(getString(R.string.up))) {
                                        drawable = R.drawable.img_share_trade_up;
                                        value = shareTrade.getValue();
                                    } else if (shareTrade.getType().equalsIgnoreCase(getString(R.string.down))) {
                                        drawable = R.drawable.img_share_trade_down;
                                        value = shareTrade.getValue();
                                    } else {
                                        drawable = R.drawable.img_share_trade_stay;
                                        value = shareTrade.getValue();
                                    }
                                    tvNab.setText(value);
                                    imageShareTrade.setBackground(ContextCompat.getDrawable(this, drawable));
                                }
                                break;
                            case Type.NEWS:
                                if (null != post.getPromoNews()) {
                                    postImageAdapter = new PostImageAdapter(PostImageAdapter.NEWS, this);

                                    recyclerImageNews.setLayoutManager(new LinearLayoutManager(this));
                                    postImageAdapter.setPromoNews(post.getPromoNews());
                                    recyclerImageNews.setAdapter(postImageAdapter);
                                }
                                break;
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
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
        if (requestCode == REQUEST_GALLERY_THUMBNAIL
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
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
            if (resultCode == 10) {
                setResult(0);
                finish();
            } else if (requestCode == GALLERY_THUMBNAIL && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                if (data.getData() != null) {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
//                    Bitmap bitmap = DecodeBitmap.decodeSampleBitmapFromUri(data.getData(), 70, 70, this);
                    imagesEncodedList.add(bitmap);
                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        int count = 0;
                        if (imagesEncodedList.size() < 5) {
                            count = Math.min(mClipData.getItemCount(), 5);
                            if (count + imagesEncodedList.size() > 5) {
                                count = 5 - imagesEncodedList.size();
                            }
                        }
                        for (int i = 0; i < count; i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
//                            BitmapFactory.Options o = new BitmapFactory.Options();
//                            o.inJustDecodeBounds = true;
//                            BitmapFactory.decodeFile(getAbsolutePath(uri), o);
//                            int imageHeight = o.outHeight;
//                            int imageWidth = o.outWidth;
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                            Bitmap bitmap = DecodeBitmap.decodeSampleBitmapFromUri(uri, 70, 70, this);
//                            Log.e("asd", "Bitmap before decode : " + bitmaps.getRowBytes() * bitmaps.getHeight());
//                            Log.e("asd", "Bitmap after decode : " + bitmap.getRowBytes() * bitmap.getHeight());
                            imagesEncodedList.add(bitmap);
                        }
                    }
                }
                recyclerImageNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                postImageAdapter.setBitmapList(imagesEncodedList);
                postImageAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Log.e("asd", e.getMessage());
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
                if (null != this.promoNews
                        || null != this.information
                        || null != this.history) {
                    checkingDataBeforeSend();
                } else {
                    ((TextView) findViewById(R.id.bs_tv_title_choose_image)).setText(getString(R.string.choose_category));
                    bsCategory.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.bs_btn_update_choose_image:
                checkingDataBeforeSend();
                break;
        }
    }

    @Override
    public void onItemCategoryChoose(Forum.Category category) {
        this.category = category;
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
        if (null != customLoading && null != customLoading.getTag()) {
            customLoading.dismiss();
        }
        showSnackBar(msg);
    }

    @Override
    public void onSuccessPost() {
        if (null != customLoading && null != customLoading.getTag()) {
            customLoading.dismiss();
        }
        setResult(0);
        finish();
    }

    private void checkingDataBeforeSend() {
        HashMap<String, Object> createPostMap = new HashMap<>();
        String content = etContent.getText().toString().trim();
        String value;
        if (content.isEmpty()) {
            showSnackBar("Mohon isi konten postingan");
        } else if (null == this.privacy) {
            showSnackBar("Mohon pilih kepada siapa Anda akan membagikan postingan");
        } else if (null == this.category
                && null != this.promoNews
                && (null != this.information || null != this.history)) {
            showSnackBar("Mohon pilih kategori postingan");
        } else {
            createPostMap.put("post_id_source", "");
            createPostMap.put("profile_id", prefConfig.getProfileID());
            createPostMap.put("post_privacy", this.privacy.getName());
            createPostMap.put("post_text", content);
            createPostMap.put("news_id", "");

            createPostMap.put("post_attachment", this.imagesEncodedList);

            if (null != this.category)
                createPostMap.put("post_category_id", this.category.getCategoryID());

            createPostMap.put("repost_from", "");
            createPostMap.put("visible_to_id", new ArrayList<String>());
            createPostMap.put("reksa_dana_id", "");
            createPostMap.put("transaction_type", "");
            createPostMap.put("share_trade_type", "");

            switch (type) {
                case NEW_STANDARD_POST:
                    break;
                case SHARE_NEWS:
                    createPostMap.put("news_id", this.promoNews.getNewsID());
                    createPostMap.put("post_category_id", "4");
                    break;
                case SHARE_TRADE_INFORMATION:
                    createPostMap.put("reksa_dana_id", this.information.getReksadanaID());
                    createPostMap.put("post_category_id", "3");
                    if (this.information.getRaise() > 0) {
                        value = getString(R.string.up);
                    } else if (this.information.getRaise() < 0) {
                        value = getString(R.string.down);
                    } else {
                        value = getString(R.string.stay);
                    }
                    createPostMap.put("transaction_type", value);
                    createPostMap.put("share_trade_type", "information");

                    break;
                case SHARE_TRADE_HISTORY:
                    createPostMap.put("post_category_id", "3");
                    createPostMap.put("reksa_dana_id", this.history.getReksaDanaID());
                    if (this.history.getTransactionType().equalsIgnoreCase("Pembelian")) {
                        value = getString(R.string.buy);
                    } else {
                        value = getString(R.string.sell);
                    }
                    createPostMap.put("transaction_type", value);
                    createPostMap.put("share_trade_type", "history");
                    break;
                case EDIT_POST:
                    createPostMap.put("post_id", this.post.getPostID());
                    break;
            }

            if (this.privacy.getName().equalsIgnoreCase("direct")) {
                Intent intent = new Intent(this, DirectShareActivity.class);

                List<String> imageEncodedList = new ArrayList<>();

                for (Bitmap bitmap : (List<Bitmap>) createPostMap.get("post_attachment")) {
                    imageEncodedList.add(Utils.encodeBitmap(bitmap));
                }

                createPostMap.put("post_attachment", imageEncodedList);

                intent.putExtra(DirectShareActivity.DATA, createPostMap);
                startActivityForResult(intent, 0);
            } else {
                customLoading.show(getSupportFragmentManager(), "");
//                Log.e("asd", createPostMap.toString());
//                Log.e("asd", type);
                if (type.equalsIgnoreCase(EDIT_POST) && null != this.post) {
                    viewModel.editPost(prefConfig.getTokenUser(), this.post.getPostID(), createPostMap);
                } else {
                    viewModel.sendNewPost(prefConfig.getTokenUser(), createPostMap);
                }
            }
//                    viewModel.sendData(imagesEncodedList);
        }
    }


}