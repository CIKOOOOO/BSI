package com.bca.bsi.ui.basenavigation.information;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;

public class InformationFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvStart, tvMid, tvEnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTitleToolbar = view.findViewById(R.id.tv_title_toolbar_back_image);
        ImageButton imgToolbar = view.findViewById(R.id.img_btn_action_toolbar_back_image);
        tvStart = view.findViewById(R.id.tv_start_information_fragment);
        tvMid = view.findViewById(R.id.tv_mid_information_fragment);
        tvEnd = view.findViewById(R.id.tv_end_information_fragment);

        tvTitleToolbar.setText(view.getContext().getString(R.string.information));

        tvStart.setTextColor(view.getContext().getResources().getColor(R.color.white_palette));
        tvMid.setTextColor(view.getContext().getResources().getColor(R.color.black_palette));
        tvEnd.setTextColor(view.getContext().getResources().getColor(R.color.black_palette));
        tvStart.setBackground(view.getContext().getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
        tvMid.setBackground(view.getContext().getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
        tvEnd.setBackground(view.getContext().getDrawable(R.drawable.rectangle_rounded_sherpa_blue));

        imgToolbar.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvMid.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_action_toolbar_back_image:

                break;
            case R.id.tv_start_information_fragment:
                switchButton(1, view.getContext());
                break;
            case R.id.tv_mid_information_fragment:
                switchButton(2, view.getContext());
                break;
            case R.id.tv_end_information_fragment:
                switchButton(3, view.getContext());
                break;
        }
    }

    private void switchButton(int pos, Context context) {
        switch (pos) {
            case 1:
                tvStart.setTextColor(context.getResources().getColor(R.color.white_palette));
                tvMid.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvEnd.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvStart.setBackground(context.getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvMid.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                tvEnd.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                break;
            case 2:
                tvMid.setTextColor(context.getResources().getColor(R.color.white_palette));
                tvStart.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvEnd.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvMid.setBackground(context.getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvStart.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                tvEnd.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                break;
            case 3:
                tvEnd.setTextColor(context.getResources().getColor(R.color.white_palette));
                tvMid.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvStart.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvEnd.setBackground(context.getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvMid.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                tvStart.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                break;
        }
    }
}