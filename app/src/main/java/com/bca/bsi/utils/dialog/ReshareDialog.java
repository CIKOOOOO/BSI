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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bca.bsi.R;

public class ReshareDialog extends DialogFragment implements View.OnClickListener {

    private String info;
    private boolean isReshare;
    private onReshare onReshare;
    private String postID;


    public ReshareDialog(String info, boolean isReshare, ReshareDialog.onReshare onReshare, String postID) {
        this.info = info;
        this.isReshare = isReshare;
        this.onReshare = onReshare;
        this.postID = postID;
    }

    public interface onReshare{
        void onResharePost(String postID);
        void onUndoResharePost(String postID);
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
        return inflater.inflate(R.layout.custom_reshare_post, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvShareInfo = view.findViewById(R.id.tv_info_reshare_post);
        TextView tvYesShareInfo = view.findViewById(R.id.tv_yes_reshare_post);

        tvShareInfo.setText(info);
        tvYesShareInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_yes_reshare_post:
                if(!isReshare){
                    onReshare.onResharePost(postID);
                }else{
                    onReshare.onUndoResharePost(postID);
                }
                break;
        }
    }
}
