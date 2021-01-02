package com.bca.bsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bca.bsi.R;
import com.bca.bsi.model.LearningChapter;

import java.util.List;

public class LearningMateriAdapter extends PagerAdapter {

    private List<LearningChapter> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public LearningMateriAdapter(List<LearningChapter> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_learning_materi,container,false);

        ImageView imageView;
        TextView title, explanation;
        final Button button;
        LinearLayout scoring;

        imageView = view.findViewById(R.id.image_materi);
        title = view.findViewById(R.id.tv_title_materi);
        explanation = view.findViewById(R.id.tv_explanation_materi);
        button = view.findViewById(R.id.quiz_button);
        scoring = view.findViewById(R.id.scoring_quiz);

        if(position == models.size() - 1) {
            button.setVisibility(View.VISIBLE);
            scoring.setVisibility(View.VISIBLE);
            button.setText(R.string.ayo_ikuti_kuis);
            button.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
            button.setAllCaps(false);
        }else if (position == models.size() - 2){
            button.setVisibility(View.VISIBLE);
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_rounded_orange_light_5dp));
            button.setText(R.string.klik_disini);
            button.setTextColor(Color.BLACK);
            button.setAllCaps(true);
            scoring.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.GONE);
            scoring.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == models.size() - 2) {
                    //button.setTextColor(Color.WHITE);
                }else if (position == models.size() - 1) {
                    button.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_stroke_tosca_dark_filled));
                    button.setTextColor(Color.WHITE);
                }
            }
        });

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        explanation.setText(models.get(position).getExplanation());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
