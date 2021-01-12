package com.bca.bsi.ui.basenavigation.information.forum.profile.inbox;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.BaseActivity;

import java.util.List;

public class InboxActivity extends BaseActivity implements View.OnClickListener, InboxAdapter.onInboxClick, IInboxCallback {

    private InboxAdapter inboxAdapter;
    private InboxViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        initVar();
    }

    private void initVar() {

        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler_inbox);

        inboxAdapter = new InboxAdapter(this);
        viewModel = new ViewModelProvider(this).get(InboxViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.inbox));
        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(inboxAdapter);

        viewModel.loadData();

        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onLoadInbox(List<Forum.Inbox> inboxes) {
        inboxAdapter.setInboxList(inboxes);
        inboxAdapter.notifyDataSetChanged();
    }
}