package com.bca.bsi.ui.basenavigation.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.more.calculatormore.CalculatorMoreActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.TopicListActivity;
import com.bca.bsi.utils.BaseFragment;

public class MoreFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton btnKalkulator;
    private ImageButton btnLearning;

    private Boolean fromMore;

    public Boolean getFromMore() {
        return fromMore;
    }

    public void setFromMore(Boolean fromMore) {
        this.fromMore = fromMore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnKalkulator = view.findViewById(R.id.imageButtonKalkulator);
        btnLearning = view.findViewById(R.id.imageButtonLearning);

        btnKalkulator.setOnClickListener(this);
        btnLearning.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonKalkulator:
                openCalculator();
                break;

            case R.id.imageButtonLearning:
                openLearning();
                break;
        }
    }

    public void openCalculator() {
        Intent intent = new Intent(mActivity, CalculatorMoreActivity.class);
        startActivity(intent);
    }

    public void openLearning() {
        Intent intent = new Intent(mActivity, TopicListActivity.class);
        startActivity(intent);
    }
}
