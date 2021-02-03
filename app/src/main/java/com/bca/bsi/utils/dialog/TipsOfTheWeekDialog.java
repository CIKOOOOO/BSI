package com.bca.bsi.utils.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bca.bsi.R;
import com.bca.bsi.model.TipsOfTheWeek;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class TipsOfTheWeekDialog extends DialogFragment implements View.OnClickListener {

    private TipsOfTheWeek tipsOfTheWeek;
    private onItemClick onItemClick;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_clear3:
                onItemClick.onCloseDialog();
                break;
        }
    }

    public interface onItemClick {
        void onCloseDialog();

        void dontAskTipsOfTheWeek(boolean isShow);
    }


    public TipsOfTheWeekDialog(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setTipsOfTheWeek(TipsOfTheWeek tipsOfTheWeek) {
        this.tipsOfTheWeek = tipsOfTheWeek;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popup_tips_of_the_week, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoundedImageView imageView = view.findViewById(R.id.content_image);
        TextView tvTitle = view.findViewById(R.id.tv_content_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        ImageButton imgBtnClose = view.findViewById(R.id.ib_clear3);
        CheckBox checkBox = view.findViewById(R.id.cb_jangan_tampilkan_lagi);

        Log.e("asd", tipsOfTheWeek.getImgURL() + ": URL");

        if (!tipsOfTheWeek.getImgURL().isEmpty())
            Picasso.get()
                    .load(Utils.imageURL(tipsOfTheWeek.getImgURL()))
                    .into(imageView);

        tvTitle.setText(tipsOfTheWeek.getTitle());
        tvContent.setText(tipsOfTheWeek.getContent());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onItemClick.dontAskTipsOfTheWeek(isChecked);
            }
        });

        imgBtnClose.setOnClickListener(this);
    }
}
