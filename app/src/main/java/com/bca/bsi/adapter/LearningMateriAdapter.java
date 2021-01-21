package com.bca.bsi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bca.bsi.R;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.ui.basenavigation.more.learningmenu.materi.IMateriQuizCallback;

import java.util.List;

public class LearningMateriAdapter extends PagerAdapter {

    private List<LearningChapter> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private onItemClick onItemClick;
    private KuisData.UserScore userScore;

    public interface onItemClick{
        void onClick();
    }

    public LearningMateriAdapter(List<LearningChapter> models, Context context, onItemClick onItemClick, KuisData.UserScore userScore) {
        this.models = models;
        this.onItemClick = onItemClick;
        this.context = context;
        this.userScore = userScore;
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
        ImageView scoreStar;
        TextView dateAttempt;
        TextView userScoreInt;

        imageView = view.findViewById(R.id.image_materi);
        title = view.findViewById(R.id.tv_title_materi);
        explanation = view.findViewById(R.id.tv_explanation_materi);
        button = view.findViewById(R.id.quiz_button);
        scoring = view.findViewById(R.id.scoring_quiz);
        scoreStar = view.findViewById(R.id.image_score_quiz);
        dateAttempt = view.findViewById(R.id.date_attempt);
        userScoreInt = view.findViewById(R.id.user_score);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        //title.setText(userScore.getScore());
        explanation.setText(models.get(position).getExplanation());

        if(position == models.size() - 1) {
            button.setVisibility(View.VISIBLE);

            if(userScore != null){
                scoring.setVisibility(View.VISIBLE);
                switch (userScore.getScore()){
                    case "0":
                        scoreStar.setImageResource(R.drawable.score_star_0);
                        break;

                    case "1":
                        scoreStar.setImageResource(R.drawable.score_star_1);
                        break;

                    case "2":
                        scoreStar.setImageResource(R.drawable.score_star_2);
                        break;

                    case "3":
                        scoreStar.setImageResource(R.drawable.score_star_3);
                        break;

                    case "4":
                        scoreStar.setImageResource(R.drawable.score_star_4);
                        break;

                    case "5":
                        scoreStar.setImageResource(R.drawable.score_star_5);
                        break;
                }

                //System.out.println("INIIII UDAH MASUK SINI. DATE " +userScore.getDateAttempt() );
                String[] dateFormated = userScore.getDateAttempt().split(" ");
                dateAttempt.setText(dateFormated[0]);
                userScoreInt.setText(userScore.getScore());
                button.setText(context.getString(R.string.ulangi_kuis));
            }else{
                scoring.setVisibility(View.GONE);
                button.setText(context.getString(R.string.ayo_ikuti_kuis));
            }

            /*
            if(explanation.getText().equals(context.getString(R.string.kuis_belum_ambil_asuransi)) || explanation.getText().equals(context.getString(R.string.kuis_belum_ambil_reksadana)) || explanation.getText().equals(context.getString(R.string.kuis_belum_ambil_obligasi))) {
                scoring.setVisibility(View.GONE);
                button.setText(context.getString(R.string.ayo_ikuti_kuis));
            }else {
                scoring.setVisibility(View.VISIBLE);
                button.setText(context.getString(R.string.ulangi_kuis));
            }
            */

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
            button.setVisibility(View.INVISIBLE);
            scoring.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClick.onClick();
                Log.e("asd","button adapter click");

                if(position == models.size() - 2) {
                    //button.setTextColor(Color.WHITE);
                }else if (position == models.size() - 1) {
                    /*
                    button.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_stroke_tosca_dark_filled));
                    button.setTextColor(Color.WHITE);
                    */
                }
            }
        });

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
