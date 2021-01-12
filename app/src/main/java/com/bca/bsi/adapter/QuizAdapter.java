package com.bca.bsi.adapter;

import android.content.Context;
import android.graphics.Color;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuizAdapter extends PagerAdapter {


    TextView pertanyaan;
    LinearLayout scoringLayout;
    ImageView userStar;
    TextView userScore;
    TextView dateAttempt;

    private LayoutInflater layoutInflater;
    private Context context;
    private onItemClick onItemClick;
    private KuisData models;
    private List<String> jawabanUser = new ArrayList<>(5);
    private int correctCounter = 0;

    public interface onItemClick{
        void onClick();
        void openPage();
    }

    public void setNilai(){
        pertanyaan.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
        if(correctCounter < 3){
            pertanyaan.setText(models.getPenilaianKuisIndex(0)+"\n\nNilai Kamu: "+correctCounter);
        }else if(correctCounter == 3){
            pertanyaan.setText(models.getPenilaianKuisIndex(1)+"\n\nNilai Kamu: "+correctCounter);
        }else {
            pertanyaan.setText(models.getPenilaianKuisIndex(2)+"\n\nNilai Kamu: "+correctCounter);
        }
        scoringLayout.setVisibility(View.VISIBLE);
        userScore.setText(String.valueOf(correctCounter));
        Date currentDate = new Date();
        dateAttempt.setText(new SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(currentDate));


        switch (correctCounter){
            case 0:
                userStar.setImageResource(R.drawable.score_star_0);
                break;

            case 1:
                userStar.setImageResource(R.drawable.score_star_1);
                break;

            case 2:
                userStar.setImageResource(R.drawable.score_star_2);
                break;

            case 3:
                userStar.setImageResource(R.drawable.score_star_3);
                break;

            case 4:
                userStar.setImageResource(R.drawable.score_star_4);
                break;

            case 5:
                userStar.setImageResource(R.drawable.score_star_5);
                break;
        }

    }

    @Override
    public int getCount() {
        return 6;
    }

    public QuizAdapter(KuisData models, Context context, onItemClick onItemClick) {
        this.onItemClick = onItemClick;
        this.models = models;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.recycler_kuis_pertanyaan,container,false);

        //final TextView pertanyaan;
        final TextView noSoal;
        final Button next;
        final LinearLayout pilganA;
        final TextView pilganAOpsi;
        TextView pilganAText;
        final LinearLayout pilganB;
        final TextView pilganBOpsi;
        TextView pilganBText;
        final LinearLayout pilganC;
        final TextView pilganCOpsi;
        TextView pilganCText;
        final Button penjelasan;
        final TextView penjelasanText;

        noSoal = view.findViewById(R.id.question_number);
        pertanyaan = view.findViewById(R.id.pertanyaan);
        pilganA = view.findViewById(R.id.pilgan_a);
        pilganAOpsi = view.findViewById(R.id.pilgan_a_opsi);
        pilganAText = view.findViewById(R.id.pilgan_a_text);
        pilganB = view.findViewById(R.id.pilgan_b);
        pilganBOpsi = view.findViewById(R.id.pilgan_b_opsi);
        pilganBText = view.findViewById(R.id.pilgan_b_text);
        pilganC = view.findViewById(R.id.pilgan_c);
        pilganCOpsi = view.findViewById(R.id.pilgan_c_opsi);
        pilganCText = view.findViewById(R.id.pilgan_c_text);
        next = view.findViewById(R.id.btn_soal_berikutnya);
        penjelasan = view.findViewById(R.id.btn_ke_penjelasan);
        penjelasanText = view.findViewById(R.id.penjelasan_text);
        scoringLayout = view.findViewById(R.id.scoring_quiz);
        userStar = view.findViewById(R.id.image_score_quiz);
        userScore = view.findViewById(R.id.user_score);
        dateAttempt = view.findViewById(R.id.date_attempt);

        noSoal.setText(Integer.toString(position+1));
        penjelasan.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        if(position>4){
            pertanyaan.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
            if(correctCounter < 3){
                pertanyaan.setText(models.getPenilaianKuisIndex(0)+"\n\nNilai Kamu: "+correctCounter);
            }else if(correctCounter == 3){
                pertanyaan.setText(models.getPenilaianKuisIndex(1)+"\n\nNilai Kamu: "+correctCounter);
            }else {
                pertanyaan.setText(models.getPenilaianKuisIndex(2)+"\n\nNilai Kamu: "+correctCounter);
            }
            pilganA.setVisibility(View.GONE);
            pilganB.setVisibility(View.GONE);
            pilganC.setVisibility(View.GONE);
            noSoal.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            penjelasan.setVisibility(View.VISIBLE);
            penjelasan.setText(R.string.ayo_belajar_lagi);
            next.setText(R.string.mulai_investasi);
        }else{
            pertanyaan.setText(models.getQuizIndex(position).getQuestionText());
            pilganAText.setText(models.getQuizIndex(position).getDataJawabanIndex(0).getAnswerText());
            pilganBText.setText(models.getQuizIndex(position).getDataJawabanIndex(1).getAnswerText());
            pilganCText.setText(models.getQuizIndex(position).getDataJawabanIndex(2).getAnswerText());
        }

        if(position==4){
            next.setText(R.string.selesai);
        }


        pilganAOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilganAOpsi.setTextColor(Color.WHITE);
                pilganAOpsi.setBackground(ContextCompat.getDrawable(context,R.drawable.square_stroke_tosca_dark_filled));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                jawabanUser.add("a");
                if(models.getQuizIndex(position).getKunciJawaban().getAnswerOption().equalsIgnoreCase(jawabanUser.get(position))){
                    correctCounter+=1;
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                    noSoal.setTextColor(Color.WHITE);
                    noSoal.setText(R.string.symbol_centang);
                }else{
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                    noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                    noSoal.setText(R.string.symbol_cross);
                }
                switch (models.getQuizIndex(position).getKunciJawaban().getAnswerOption().toLowerCase()){
                    case "a":
                        pilganA.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "b":
                        pilganB.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "c":
                        pilganC.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                }
            }
        });

        pilganBOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilganBOpsi.setTextColor(Color.WHITE);
                pilganBOpsi.setBackground(ContextCompat.getDrawable(context,R.drawable.square_stroke_tosca_dark_filled));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                jawabanUser.add("b");
                if(models.getQuizIndex(position).getKunciJawaban().getAnswerOption().equalsIgnoreCase(jawabanUser.get(position))){
                    correctCounter+=1;
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                    noSoal.setTextColor(Color.WHITE);
                    noSoal.setText(R.string.symbol_centang);
                }else{
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                    noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                    noSoal.setText(R.string.symbol_cross);
                }
                switch (models.getQuizIndex(position).getKunciJawaban().getAnswerOption().toLowerCase()){
                    case "a":
                        pilganA.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "b":
                        pilganB.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "c":
                        pilganC.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                }
            }
        });


        pilganCOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilganCOpsi.setTextColor(Color.WHITE);
                pilganCOpsi.setBackground(ContextCompat.getDrawable(context,R.drawable.square_stroke_tosca_dark_filled));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                jawabanUser.add("c");
                if(models.getQuizIndex(position).getKunciJawaban().getAnswerOption().equalsIgnoreCase(jawabanUser.get(position))){
                    correctCounter+=1;
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                    noSoal.setTextColor(Color.WHITE);
                    noSoal.setText(R.string.symbol_centang);
                }else{
                    noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                    noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                    noSoal.setText(R.string.symbol_cross);
                }

                switch (models.getQuizIndex(position).getKunciJawaban().getAnswerOption().toLowerCase()){
                    case "a":
                        pilganA.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "b":
                        pilganB.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                    case "c":
                        pilganC.setBackgroundColor(ContextCompat.getColor(context,R.color.light_tosca_palette));
                        break;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<4){
                    if(next.getText().toString().equals(context.getString(R.string.soal_berikutnya))) {
                        onItemClick.onClick();
                    }else {
                        pilganA.setVisibility(View.VISIBLE);
                        pilganB.setVisibility(View.VISIBLE);
                        pilganC.setVisibility(View.VISIBLE);
                        penjelasan.setVisibility(View.VISIBLE);
                        pertanyaan.setTextColor(Color.BLACK);
                        penjelasanText.setVisibility(View.INVISIBLE);
                        next.setText(R.string.soal_berikutnya);
                    }
                }else if(position == 4){
                    if(next.getText().toString().equals(context.getString(R.string.selesai))) {
                        onItemClick.onClick();
                    }else {
                        pilganA.setVisibility(View.VISIBLE);
                        pilganB.setVisibility(View.VISIBLE);
                        pilganC.setVisibility(View.VISIBLE);
                        penjelasan.setVisibility(View.VISIBLE);
                        pertanyaan.setTextColor(Color.BLACK);
                        penjelasanText.setVisibility(View.INVISIBLE);
                        next.setText(R.string.selesai);
                    }
                }else {
                    onItemClick.onClick();
                }
            }
        });

        penjelasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<5){
                    pilganA.setVisibility(View.INVISIBLE);
                    pilganB.setVisibility(View.INVISIBLE);
                    pilganC.setVisibility(View.INVISIBLE);
                    penjelasan.setVisibility(View.INVISIBLE);
                    pertanyaan.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                    next.setText("Kembali");

                    penjelasanText.setVisibility(View.VISIBLE);
                    if(models.getQuizIndex(position).getKunciJawaban().getAnswerOption().equalsIgnoreCase(jawabanUser.get(position))){
                        penjelasanText.setText(models.getQuizIndex(position).getCorrectExplanation());
                    }else{
                        penjelasanText.setText(models.getQuizIndex(position).getFalseExplanation());
                    }

                }else {
                    onItemClick.openPage();
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
