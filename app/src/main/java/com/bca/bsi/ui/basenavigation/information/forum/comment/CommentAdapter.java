package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {

    private List<Forum.Comment> commentList;
    private Context mContext;
    private onReport onReport;

    public CommentAdapter(CommentAdapter.onReport onReport) {
        this.onReport = onReport;
        commentList = new ArrayList<>();
    }

    public void setCommentList(List<Forum.Comment> commentList) {
        this.commentList = commentList;
    }

    public interface onReport {
        void onReportClick(Forum.Comment comment);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_comment, parent, false);
        mContext = parent.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Forum.Comment comment = commentList.get(position);
        if (comment != null) {
            holder.tvName.setText(comment.getName());
            holder.tvContent.setText(comment.getContent());
            Picasso.get()
                    .load(comment.getImage())
                    .into(holder.roundedImageView);
            holder.tvDate.setText(comment.getDate());
        }

        holder.imgBtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.imgBtnMore);
                popup.getMenuInflater().inflate(R.menu.menu_comment, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        onReport.onReportClick(comment);
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDate, tvContent;
        private RoundedImageView roundedImageView;
        private ImageButton imgBtnMore;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.recycler_tv_name_comment);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_comment);
            tvContent = itemView.findViewById(R.id.recycler_tv_content_comment);
            imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_comment);
            roundedImageView = itemView.findViewById(R.id.recycler_img_profile_comment);
        }
    }
}
