package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.constant.Type;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ChildMainForumAdapter extends RecyclerView.Adapter<ChildMainForumAdapter.Holder> {

    private static final int TRENDING = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, TIMELINE = 5;

    private String type;
    private List<Forum> forumList;




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child_main_forum, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        switch (getItemViewType(position)) {
            case ChildMainForumAdapter.TRENDING:

                break;
            case ChildMainForumAdapter.STRATEGY:

                break;
            case ChildMainForumAdapter.SHARE_TRADE:

                break;
            case ChildMainForumAdapter.NEWS:

                break;
            case ChildMainForumAdapter.TIMELINE:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        switch (type) {
            case Type.TRENDING:
                viewType = ChildMainForumAdapter.TRENDING;
                break;
            case Type.STRATEGI:
                viewType = ChildMainForumAdapter.STRATEGY;
                break;
            case Type.SHARE_TRADE:
                viewType = ChildMainForumAdapter.SHARE_TRADE;
                break;
            case Type.NEWS:
                viewType = ChildMainForumAdapter.NEWS;
                break;
            case Type.TIMELINE:
                viewType = ChildMainForumAdapter.TIMELINE;
                break;
        }
        return viewType;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private RoundedImageView roundedImageView;
        private TextView tvName, tvDate, tvContent, tvType, tvMore, tvLike, tvComment, tvShare;
        private ImageView imgContent;

        public Holder(@NonNull View itemView) {
            super(itemView);

        }

    }

}
