package com.bca.bsi.ui.basenavigation.more.learningmenu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.LearningTopicAdapter;
import com.bca.bsi.ui.basenavigation.more.learningmenu.materi.MateriAsuransiActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.materi.MateriObligasiActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.materi.MateriReksaDanaActivity;
import com.bca.bsi.utils.BaseActivity;

public class TopicListActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private LearningTopicAdapter.RecyclerViewClickListener listener;
    private RecyclerView.Adapter learningTopicAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton backBtn;
    private TextView titlePage;

    String[] learningTopic = {"Reksa Dana", "Obligasi", "Asuransi"};
    String[] topicDesc = {"Investasi Dana Terjangkau", "Investasi Berpendapatan Tetap", "Perlindungan Jiwa, Kesehatan, dan Harta Benda"};
    int[] topicImage = {R.drawable.img_asset_reksa_dana, R.drawable.img_asset_obligasi, R.drawable.img_asset_asuransi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);

        titlePage.setText(getString(R.string.learning));

        backBtn.setOnClickListener(this);

        setOnClickListener();

        recyclerView = findViewById(R.id.rv_learning_topic);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        learningTopicAdapter = new LearningTopicAdapter(this,learningTopic,topicDesc,topicImage, listener);
        recyclerView.setAdapter(learningTopicAdapter);


    }

    private void setOnClickListener() {
        listener = new LearningTopicAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), MateriReksaDanaActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), MateriObligasiActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), MateriAsuransiActivity.class);
                        startActivity(intent);
                        break;
                }

            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }
}