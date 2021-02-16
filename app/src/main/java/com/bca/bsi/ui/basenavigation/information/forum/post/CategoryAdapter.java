package com.bca.bsi.ui.basenavigation.information.forum.post;

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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private onCategoryClick onReportClick;
    private List<Forum.Category> categoryList;
    private int lastPosition;

    public interface onCategoryClick {
        void onItemCategoryChoose(Forum.Category report);
    }

    public CategoryAdapter(CategoryAdapter.onCategoryClick onCategoryClick) {
        this.onReportClick = onCategoryClick;
        this.categoryList = new ArrayList<>();
        lastPosition = -1;
    }

    public void setReportList(List<Forum.Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Forum.Category getCategoryDetail(String categoryName) {
        for (int i = 0; i < this.categoryList.size(); i++) {
            Forum.Category category = this.categoryList.get(i);
            if (category.getCategoryName().equalsIgnoreCase(categoryName)) {
                lastPosition = i;
                categoryList.get(lastPosition).setChoose(true);
                notifyDataSetChanged();
                return category;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_report, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Category category = categoryList.get(position);
        int drawable = category.isChoose() ? R.drawable.rectangle_rounded_stroke_ziggurat : R.drawable.rectangle_rounded_stroke_white_welma;

        holder.tvReport.setText(category.getCategoryName());
        holder.tvReport.setBackgroundResource(drawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPosition != -1) {
                    categoryList.get(lastPosition).setChoose(false);
                }

                lastPosition = position;
                categoryList.get(lastPosition).setChoose(true);
                notifyDataSetChanged();
                onReportClick.onItemCategoryChoose(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvReport;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvReport = itemView.findViewById(R.id.recycler_tv_report);
        }
    }
}
