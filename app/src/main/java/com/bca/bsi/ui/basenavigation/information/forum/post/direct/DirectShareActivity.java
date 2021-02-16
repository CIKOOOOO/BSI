package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.User;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.CustomLoading;

import java.util.HashMap;
import java.util.List;

public class DirectShareActivity extends BaseActivity implements IDirectShareCallback, View.OnClickListener, DirectShareAdapter.onUserClick {

    public static final String DATA = "data";
    private DirectShareViewModel viewModel;
    private DirectShareAdapter directShareAdapter, chosenUserAdapter;
    private RecyclerView recyclerChosenUser;
    private CustomLoading customLoading;
    private HashMap<String, Object> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_share);
        initVar();
    }

    private void initVar() {
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        RecyclerView recyclerUser = findViewById(R.id.recycler_user_direct_share);
        EditText etSearch = findViewById(R.id.et_input_search);
        Button btnShare = findViewById(R.id.btn_share_direct);

        recyclerChosenUser = findViewById(R.id.recycler_chosen_user_direct_share);

        directShareAdapter = new DirectShareAdapter(this);
        chosenUserAdapter = new DirectShareAdapter(this);
        customLoading = new CustomLoading();

        viewModel = new ViewModelProvider(this).get(DirectShareViewModel.class);
        viewModel.setCallback(this);

        recyclerUser.setLayoutManager(new LinearLayoutManager(this));
        recyclerUser.setAdapter(directShareAdapter);

        recyclerChosenUser.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        chosenUserAdapter.setType(1);
        recyclerChosenUser.setAdapter(chosenUserAdapter);

        tvTitle.setText(getString(R.string.share_to));

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            this.hashMap = (HashMap<String, Object>) intent.getSerializableExtra(DATA);
            viewModel.loadUser(prefConfig.getTokenUser(), "", prefConfig.getProfileID());
        } else {
            onBackPressed();
        }

        etSearch.addTextChangedListener(textWatcher);

        imgBack.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            viewModel.clearForumList();
        }

        @Override
        public void afterTextChanged(Editable s) {
            viewModel.loadUser(prefConfig.getTokenUser(), s.toString(), prefConfig.getProfileID());
        }
    };

    @Override
    public void onLoadForumUser(List<User.ForumUser> forumUserList) {
        directShareAdapter.setForumUserList(forumUserList);
        directShareAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadChosenForumUser(List<User.ForumUser> forumUserList) {
        chosenUserAdapter.setForumUserList(forumUserList);
        chosenUserAdapter.notifyDataSetChanged();
        recyclerChosenUser.scrollToPosition(forumUserList.size() - 1);
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onSuccessPost() {
        if (null != customLoading && null != customLoading.getTag()) {
            customLoading.dismiss();
        }

        setResult(10);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_share_direct:
                if (viewModel.getChosenUserList().size() == 0) {
                    showSnackBar("Mohon pilih user untuk melanjutkan");
                } else {
                    customLoading.show(getSupportFragmentManager(), "");
                    this.hashMap.put("visible_to_id", viewModel.getChosenUserList());
                    viewModel.sendNewPost(prefConfig.getTokenUser(), this.hashMap);
                }
                break;
        }
    }

    @Override
    public void onUserChooseProfile(User.ForumUser forumUser) {
        viewModel.addChosenUser(forumUser);
    }

    @Override
    public void onDeleteProfile(User.ForumUser forumUser) {
        viewModel.removeChosenUser(forumUser);
    }
}