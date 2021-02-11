package com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.ui.basenavigation.information.forum.fragment.OnPostClick;

public class NoDataViewHolder extends RecyclerView.ViewHolder {
    private OnPostClick onPostClick;

    public NoDataViewHolder(@NonNull View itemView, OnPostClick onPostClick) {
        super(itemView);
        this.onPostClick = onPostClick;
    }
}
