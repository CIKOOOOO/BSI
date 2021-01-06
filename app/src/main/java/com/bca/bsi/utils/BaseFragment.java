package com.bca.bsi.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bca.bsi.R;
import com.google.android.material.snackbar.Snackbar;

public class BaseFragment extends Fragment {

    public PrefConfig prefConfig;
    public Activity mActivity;
    public Context mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mContext = view.getContext();
        prefConfig = new PrefConfig(view.getContext());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showSnackBar(String msg) {
        View view = mActivity.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.black_palette))
                .setTextColor(getResources().getColor(R.color.white_palette))
                .setDuration(3000);
        snackbar.show();
    }
}
