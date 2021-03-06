package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.otherprofile.OtherProfileActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Type;

import java.util.List;

public class ConnectionActivity extends BaseActivity implements View.OnClickListener, IConnectionCallback, ConnectionAdapter.onPeopleClick {

    public static final String DATA = "data";

    private TextView tvStart, tvEnd;
    private ConnectionViewModel viewModel;
    private ConnectionAdapter connectionAdapter;
    private String profileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        initVar();
    }

    private void initVar() {
        profileID = "";

        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler_connection);

        tvStart = findViewById(R.id.tv_start_connection);
        tvEnd = findViewById(R.id.tv_end_connection);

        connectionAdapter = new ConnectionAdapter(this);
        connectionAdapter.setSelfProfileID(prefConfig.getProfileID());

        viewModel = new ViewModelProvider(this).get(ConnectionViewModel.class);
        viewModel.setCallback(this);

        findViewById(R.id.img_btn_filter_search).setVisibility(View.GONE);
        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(connectionAdapter);

        tvTitle.setText(getString(R.string.connection));

        Intent intent = getIntent();
        if (null != intent && intent.hasExtra(DATA)) {
            profileID = intent.getStringExtra(DATA);
        }

        switchButton(1);

        imgBack.setOnClickListener(this);

        tvStart.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.tv_start_connection:
                switchButton(1);
                break;
            case R.id.tv_end_connection:
                switchButton(2);
                break;
        }
    }

    private void switchButton(int pos) {
        switch (pos) {
            case 1:
                tvStart.setTextColor(getResources().getColor(R.color.black_palette));
                tvEnd.setTextColor(getResources().getColor(R.color.white_palette));
                tvStart.setBackground(getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvEnd.setBackground(getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                viewModel.loadConnection(Type.FOLLOWING, prefConfig.getTokenUser(), prefConfig.getProfileID(), profileID);
                connectionAdapter.setType(Type.FOLLOWING);
                break;
            case 2:
                tvStart.setTextColor(getResources().getColor(R.color.white_palette));
                tvEnd.setTextColor(getResources().getColor(R.color.black_palette));
                tvStart.setBackground(getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                tvEnd.setBackground(getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                viewModel.loadConnection(Type.FOLLOWERS, prefConfig.getTokenUser(), prefConfig.getProfileID(), profileID);
                connectionAdapter.setType(Type.FOLLOWERS);
                break;
        }
        connectionAdapter.clearData();
    }

    @Override
    public void onLoadData(List<Forum.Connection> connectionList) {
//        Log.e("asd", connectionList.size() + "");
        connectionAdapter.setConnectionList(connectionList);
        connectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadData(Forum.User user) {
        connectionAdapter.changeStatus(user);
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onFollow(String profileID) {
        Log.e("asd", "Self profile : " + prefConfig.getProfileID() + " - Other Profile : " + profileID);
        viewModel.followUnFollowProfile(prefConfig.getTokenUser(), prefConfig.getProfileID(), profileID);
    }

    @Override
    public void onDetailPeople(String profileID) {
        Intent intent = new Intent(this, OtherProfileActivity.class);
        intent.putExtra(OtherProfileActivity.DATA, profileID);
        startActivity(intent);
    }
}