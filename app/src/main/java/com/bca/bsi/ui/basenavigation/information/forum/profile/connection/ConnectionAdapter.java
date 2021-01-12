package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.Holder> {

    private onPeopleClick onPeopleClick;

    public interface onPeopleClick {
        void onFollow();

        void onUnfollow();

        void onDetailPeople();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_people, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageView;
        private TextView tvName, tvType;

        public Holder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.recycler_img_profile_people);
            tvName = itemView.findViewById(R.id.recycler_tv_name_people);
            tvType = itemView.findViewById(R.id.recycler_tv_type_people);
        }
    }
}
