package com.bca.bsi.ui.basenavigation.portfolio.filter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.ArrayList;

public class FilterSortActivity extends BaseActivity implements SortAdapter.onWholeClick {
    TextView toolbarTitle, resetButton, tvTerapkan;
    ImageButton closeButton;
    FilterAdapter filterAdapter;
    SortAdapter sortAdapter;

    private int chosenPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_sort);
        initVar();
    }

    private void initVar() {
        setToolbarTitle();
        setCloseButton();
        setFilterAdapter();
        setSortAdapter();
        setResetButton();
        setTvTerapkan();
    }

    private void setResetButton() {
        resetButton = findViewById(R.id.tv_reset_filter);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ubah Filter jadi Default
                filterAdapter = new FilterAdapter();
                filterAdapter.setFilterJenisReksaList(DummyData.getFilterJenisReksaListDefault());
                RecyclerView rec = findViewById(R.id.recycler_filter);
                rec.setLayoutManager(new LinearLayoutManager(FilterSortActivity.this));
                rec.setAdapter(filterAdapter);

                sortAdapter.resetButton();

                // Ubah Sort jadi Default
//                sortAdapter = new SortAdapter(FilterSortActivity.this);
//                sortAdapter.setSortJenisReksas(DummyData.getSortJenisReksaListDefault());
//                RecyclerView rec2 = findViewById(R.id.recycler_sort);
//                rec2.setLayoutManager(new LinearLayoutManager(FilterSortActivity.this));
//                rec2.setAdapter(sortAdapter);
            }
        });
    }

    private void setToolbarTitle() {
        toolbarTitle = findViewById(R.id.tv_title_toolbar_filter);
        toolbarTitle.setText("PENYARINGAN");
    }

    private void setCloseButton() {
        closeButton = findViewById(R.id.img_btn_close_toolbar);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFilterAdapter() {
        filterAdapter = new FilterAdapter();
        filterAdapter.setFilterJenisReksaList(DummyData.getFilterJenisReksaListDefault());

        RecyclerView rec = findViewById(R.id.recycler_filter);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(filterAdapter);
    }

    private void setSortAdapter() {
        Intent intent = getIntent();
        int sortPosition = intent.getIntExtra("sort_position",0);
        sortAdapter = new SortAdapter(this);
        sortAdapter.setSortJenisReksas(DummyData.getSortJenisReksaListFalse());
        sortAdapter.setCheck(sortPosition,DummyData.getSortJenisReksaListFalse().get(sortPosition));

        RecyclerView rec = findViewById(R.id.recycler_sort);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(sortAdapter);
    }

    @Override
    public void onItemClick(int position) {
        this.chosenPosition = position;
    }

    private void setTvTerapkan(){
        tvTerapkan = findViewById(R.id.tv_terapkan);
        tvTerapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("sort_data", chosenPosition);
                intent.putIntegerArrayListExtra("filter_data", (ArrayList<Integer>) filterAdapter.getFilterChosen());
                setResult(1, intent);
                finish();
            }
        });
    }
}