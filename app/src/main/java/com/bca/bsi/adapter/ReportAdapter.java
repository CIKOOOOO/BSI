package com.bca.bsi.adapter;

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

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.Holder> {

    private onReportClick onReportClick;
    private List<Forum.Report> reportList;
    private int lastPosition;

    public interface onReportClick {
        void onItemReportChoose(Forum.Report report);
    }

    public ReportAdapter(ReportAdapter.onReportClick onReportClick) {
        this.onReportClick = onReportClick;
        this.reportList = new ArrayList<>();
        lastPosition = -1;
    }

    public void setReportList(List<Forum.Report> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_report, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Report report = reportList.get(position);
        int drawable = report.isChoose() ? R.drawable.rectangle_rounded_stroke_ziggurat : R.drawable.rectangle_rounded_stroke_white_welma;

        holder.tvReport.setText(report.getValue());
        holder.tvReport.setBackgroundResource(drawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPosition != -1) {
                    reportList.get(lastPosition).setChoose(false);
                }

                lastPosition = position;
                reportList.get(lastPosition).setChoose(true);
                notifyDataSetChanged();
                onReportClick.onItemReportChoose(report);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvReport;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvReport = itemView.findViewById(R.id.recycler_tv_report);
        }
    }
}
