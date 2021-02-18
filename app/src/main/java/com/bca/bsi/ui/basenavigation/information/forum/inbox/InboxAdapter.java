package com.bca.bsi.ui.basenavigation.information.forum.inbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.Holder> {

    private List<Forum.Inbox> inboxList;
    private onInboxClick onInboxClick;

    public interface onInboxClick {
        void onDetailInbox(Forum.Inbox inbox);
    }

    public InboxAdapter(InboxAdapter.onInboxClick onInboxClick) {
        this.onInboxClick = onInboxClick;
        this.inboxList = new ArrayList<>();
    }

    public void setInboxList(List<Forum.Inbox> inboxList) {
        this.inboxList = inboxList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_inbox, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Inbox inbox = inboxList.get(position);
        holder.tvUsername.setText(inbox.getUsername());
        holder.tvDate.setText(inbox.getDate());
        holder.tvContent.setText(inbox.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInboxClick.onDetailInbox(inbox);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inboxList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvUsername, tvDate, tvContent;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.recycler_tv_username_inbox);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_inbox);
            tvContent = itemView.findViewById(R.id.recycler_tv_content_inbox);
        }
    }

}
