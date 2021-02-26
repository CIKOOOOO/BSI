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
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class to show list of comment
 * and thing user can do with comment such as delete and report
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {

    private List<Forum.Comment> commentList;
    private Context mContext;
    private onCommentClick onCommentClick;
    private String profileID;

    public CommentAdapter(onCommentClick onCommentClick, String profileID) {
        this.onCommentClick = onCommentClick;
        this.profileID = profileID;
        commentList = new ArrayList<>();
    }

    public void setCommentList(List<Forum.Comment> commentList) {
        this.commentList = commentList;
    }

    public interface onCommentClick {
        void onReportClick(Forum.Comment comment);

        void onDeleteComment(Forum.Comment comment);

        void onDetailProfile(String profileID);
    }

    public void addComment(Forum.Comment comment) {
        this.commentList.add(comment);
        notifyDataSetChanged();
    }

    public void removeComment(String commentID) {
        for (int i = 0; i < this.commentList.size(); i++) {
            Forum.Comment comment = this.commentList.get(i);
            if (comment.getCommentID().equalsIgnoreCase(commentID)) {
                this.commentList.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
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
                    .load(Utils.imageURL(comment.getImage()))
                    .into(holder.roundedImageView);
            try {
                String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_6, Constant.DATE_FORMAT_5, comment.getDate());
                holder.tvDate.setText(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.imgBtnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(mContext, holder.imgBtnMore);

                    int menu = profileID.equalsIgnoreCase(comment.getProfileID()) ? R.menu.menu_repost : R.menu.menu_comment;

                    popup.getMenuInflater().inflate(menu, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_delete:
                                    onCommentClick.onDeleteComment(comment);
                                    break;
                                case R.id.menu_report:
                                    onCommentClick.onReportClick(comment);
                                    break;
                            }
                            return true;
                        }
                    });

                    popup.show();
                }
            });

            holder.roundedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCommentClick.onDetailProfile(comment.getProfileID());
                }
            });

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCommentClick.onDetailProfile(comment.getProfileID());
                }
            });
        }
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
