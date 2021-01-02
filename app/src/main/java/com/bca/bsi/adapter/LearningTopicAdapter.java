package com.bca.bsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;

public class LearningTopicAdapter extends RecyclerView.Adapter<LearningTopicAdapter.ViewHolder> {

    private Context context;
    private String[] learningTopic;
    private String[] learningTopicDecs;
    int[] images;
    private RecyclerViewClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rowName;
        TextView rowDescription;
        ImageView rowImage;
        TextView rowButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowName = itemView.findViewById(R.id.recycler_tv_title_learning_topic);
            rowDescription = itemView.findViewById(R.id.recycler_tv_description_learning_topic);
            rowImage = itemView.findViewById(R.id.recycler_img_learning_topic);
            rowButton = itemView.findViewById(R.id.recyler_btn_pelajari_lebih_lanjut);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView,getAdapterPosition());
        }
    }

    public LearningTopicAdapter(Context context, String[] learningTopic, String[] learningTopicDesc, int[] images, RecyclerViewClickListener listener) {
        this.context = context;
        this.learningTopic = learningTopic;
        this.learningTopicDecs = learningTopicDesc;
        this.images = images;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LearningTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recycler_learning_topic, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningTopicAdapter.ViewHolder holder, int position) {
        holder.rowName.setText(learningTopic[position]);
        holder.rowDescription.setText(learningTopicDecs[position]);
        holder.rowImage.setImageResource(images[position]);

        /*
        holder.rowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return learningTopic.length;
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
