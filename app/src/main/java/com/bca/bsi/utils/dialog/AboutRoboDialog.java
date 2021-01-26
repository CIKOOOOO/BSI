package com.bca.bsi.utils.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bca.bsi.R;

public class AboutRoboDialog extends DialogFragment implements View.OnClickListener {

    private  onCloseDialog onCloseDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_oke:
                onCloseDialog.onCloseAboutRoboDialog();
                break;
        }
    }

    public interface onCloseDialog{
        void onCloseAboutRoboDialog();
    }

    public AboutRoboDialog(AboutRoboDialog.onCloseDialog onCloseDialog) {
        this.onCloseDialog = onCloseDialog;
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
        return inflater.inflate(R.layout.popup_tentang_robo, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvOke = view.findViewById(R.id.tv_oke);

        tvOke.setOnClickListener(this);
    }
}
