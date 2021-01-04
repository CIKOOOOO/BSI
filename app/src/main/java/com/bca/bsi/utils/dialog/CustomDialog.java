package com.bca.bsi.utils.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bca.bsi.R;
import com.bumptech.glide.Glide;

public class CustomDialog extends DialogFragment implements View.OnClickListener {

    private TextView tvInfo;
    private String info, btnTitle;
    private Drawable imageIcon;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close_custom_dialog:
                onClick.onDismissButton();
                break;
        }
    }

    public interface onClick {
        void onDismissButton();
    }

    private onClick onClick;

    public CustomDialog(String info, String btnTitle, Drawable imageIcon, CustomDialog.onClick onClick) {
        this.info = info;
        this.imageIcon = imageIcon;
        this.btnTitle = btnTitle;
        this.onClick = onClick;
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
        return inflater.inflate(R.layout.custom_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnClose = view.findViewById(R.id.btn_close_custom_dialog);
        ImageView image = view.findViewById(R.id.img_view_close_custom_dialog);

        tvInfo = view.findViewById(R.id.tv_info_custom_dialog);

        tvInfo.setText(info);
        btnClose.setText(btnTitle);

        Glide.with(view)
                .load(imageIcon)
                .into(image);

        btnClose.setOnClickListener(this);
    }
}
