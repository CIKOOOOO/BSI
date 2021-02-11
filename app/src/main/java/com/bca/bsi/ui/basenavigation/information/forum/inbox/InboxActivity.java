package com.bca.bsi.ui.basenavigation.information.forum.inbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.comment.CommentActivity;
import com.bca.bsi.utils.BaseActivity;

import java.util.List;

public class InboxActivity extends BaseActivity implements View.OnClickListener, InboxAdapter.onInboxClick, IInboxCallback {

    private InboxAdapter inboxAdapter;
    private InboxViewModel viewModel;
    private TextView tvAmountInbox;

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
        tvAmountInbox = findViewById(R.id.tv_amount_inbox);
        viewModel = new ViewModelProvider(this).get(InboxViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.inbox));
        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(inboxAdapter);

        viewModel.loadData(prefConfig.getTokenUser(), prefConfig.getProfileID());

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
        tvAmountInbox.setText(inboxes.size() + " of 1000");
        inboxAdapter.setInboxList(inboxes);
        inboxAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onDetailInbox(Forum.Inbox inbox) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(CommentActivity.DATA, inbox.getPostID());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        setResult(5);
        super.onBackPressed();
    }
}