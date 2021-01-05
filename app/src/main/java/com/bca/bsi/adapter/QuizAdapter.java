package com.bca.bsi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bca.bsi.R;
import com.bca.bsi.model.JawabanKuis;

public class QuizAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private onItemClick onItemClick;
    private JawabanKuis jawabanKuis = new JawabanKuis();

    public interface onItemClick{
        void onClick();
    }

    @Override
    public int getCount() {
        return 5;
    }

    public QuizAdapter(Context context, onItemClick onItemClick) {
        this.onItemClick = onItemClick;
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

        TextView noSoal;
        final TextView pertanyaan;
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
        final Button next;

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

        noSoal.setText(Integer.toString(position+1));
        penjelasan.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        String[] jawabanUserList = {jawabanKuis.getJawabanUserSoal1(), jawabanKuis.getJawabanUserSoal2(), jawabanKuis.getJawabanUserSoal3(),jawabanKuis.getJawabanUserSoal4(), jawabanKuis.getJawabanUserSoal5()};

        switch (jawabanUserList[position]) {
            case "a":
                pilganAOpsi.setTextColor(Color.WHITE);
                pilganAOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark_filled));
                pilganBOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganBOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganCOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganCOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                break;

            case "b":
                pilganBOpsi.setTextColor(Color.WHITE);
                pilganBOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark_filled));
                pilganAOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganAOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganCOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganCOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                break;

            case "c":
                pilganCOpsi.setTextColor(Color.WHITE);
                pilganCOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark_filled));
                pilganBOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganBOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganAOpsi.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
                pilganAOpsi.setBackground(ContextCompat.getDrawable(context, R.drawable.square_stroke_tosca_dark));
                pilganAOpsi.setClickable(false);
                pilganAOpsi.setLinksClickable(false);
                pilganBOpsi.setClickable(false);
                pilganBOpsi.setLinksClickable(false);
                pilganCOpsi.setClickable(false);
                pilganCOpsi.setLinksClickable(false);
                next.setVisibility(View.VISIBLE);
                penjelasan.setVisibility(View.VISIBLE);
                break;
        }

        pilganAOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilganA.setBackgroundColor(Color.BLACK);
                switch (position){
                    case 0:
                        if(jawabanKuis.getJawabanUserSoal1().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal1("a");
                        }
                        break;
                    case 1:
                        if(jawabanKuis.getJawabanUserSoal2().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal2("a");
                        }
                        break;
                    case 2:
                        if(jawabanKuis.getJawabanUserSoal3().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal3("a");
                        }
                        break;
                    case 3:
                        if(jawabanKuis.getJawabanUserSoal4().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal4("a");
                        }
                        break;
                    case 4:
                        if(jawabanKuis.getJawabanUserSoal5().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal5("a");
                        }
                        break;
                }
            }
        });

        pilganBOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        if(jawabanKuis.getJawabanUserSoal1().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal1("b");
                        }
                        break;
                    case 1:
                        if(jawabanKuis.getJawabanUserSoal2().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal2("b");
                        }
                        break;
                    case 2:
                        if(jawabanKuis.getJawabanUserSoal3().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal3("b");
                        }
                        break;
                    case 3:
                        if(jawabanKuis.getJawabanUserSoal4().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal4("b");
                        }
                        break;
                    case 4:
                        if(jawabanKuis.getJawabanUserSoal5().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal5("b");
                        }
                        break;
                }
            }
        });

        pilganCOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        if(jawabanKuis.getJawabanUserSoal1().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal1("c");
                        }
                        break;
                    case 1:
                        if(jawabanKuis.getJawabanUserSoal2().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal2("c");
                        }
                        break;
                    case 2:
                        if(jawabanKuis.getJawabanUserSoal3().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal3("c");
                        }
                        break;
                    case 3:
                        if(jawabanKuis.getJawabanUserSoal4().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal4("c");
                        }
                        break;
                    case 4:
                        if(jawabanKuis.getJawabanUserSoal5().equals("-")){
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
                            jawabanKuis.setJawabanUserSoal5("c");
                        }
                        break;
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(next.getText().toString().equals("Soal Berikutnya")) {
                    onItemClick.onClick();
                }else {
                    pilganA.setVisibility(View.VISIBLE);
                    pilganB.setVisibility(View.VISIBLE);
                    pilganC.setVisibility(View.VISIBLE);
                    penjelasan.setVisibility(View.VISIBLE);
                    pertanyaan.setTextColor(Color.BLACK);
                    pertanyaan.setText("Menurut Anda apa itu Reksa Dana?");
                    next.setText("Soal Berikutnya");
                }

            }
        });

        penjelasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilganA.setVisibility(View.INVISIBLE);
                pilganB.setVisibility(View.INVISIBLE);
                pilganC.setVisibility(View.INVISIBLE);
                penjelasan.setVisibility(View.INVISIBLE);
                pertanyaan.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                next.setText("Kembali");
                switch (position){
                    case 0:
                        pertanyaan.setText("YOOYOYOYOYOYOYOYOYOYOOOOOOOOOOOOOOOOYOYOYOYOYOYOYOYOYOYOYOYOO");
                        break;
                    case 1:
                        pertanyaan.setText("MOOYOYOYOYOYOYOYOYOYOOOOOOOOOOOOOOOOYOYOYOYOYOYOYOYOYOYOYOYOO");
                        break;
                    case 2:
                        pertanyaan.setText("POOYOYOYOYOYOYOYOYOYOOOOOOOOOOOOOOOOYOYOYOYOYOYOYOYOYOYOYOYOO");
                        break;
                    case 3:
                        pertanyaan.setText("BOOYOYOYOYOYOYOYOYOYOOOOOOOOOOOOOOOOYOYOYOYOYOYOYOYOYOYOYOYOO");
                        break;
                    case 4:
                        pertanyaan.setText("COOYOYOYOYOYOYOYOYOYOOOOOOOOOOOOOOOOYOYOYOYOYOYOYOYOYOYOYOYOO");
                        break;
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
