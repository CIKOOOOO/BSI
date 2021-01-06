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

import java.util.Arrays;
import java.util.List;

public class QuizAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private onItemClick onItemClick;
    private JawabanKuis jawabanKuis = new JawabanKuis();
    private List<String> kunciKuis = Arrays.asList("a","b","c","b","a");
    private int correctCounter = 0;

    public interface onItemClick{
        void onClick();
    }

    

    @Override
    public int getCount() {
        return 6;
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

        jawabanKuis.setKunJaw(kunciKuis);

        final TextView noSoal;
        final TextView pertanyaan;
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

        if(position < 5) {
            System.out.println("POSESEEEEEH DEPAN: "+position);
            pertanyaan.setText("Menurut Anda apa itu Reksa Dana?");
            pilganA.setVisibility(View.VISIBLE);
            pilganB.setVisibility(View.VISIBLE);
            pilganC.setVisibility(View.VISIBLE);
            noSoal.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
            penjelasan.setVisibility(View.INVISIBLE);
            penjelasan.setText(R.string.lihat_penjelasan);
            next.setText(R.string.soal_berikutnya);
            pertanyaan.setTextColor(Color.BLACK);
            if(!jawabanKuis.getJawabanUserList().isEmpty()){
                if(jawabanKuis.getJawabanUserList().size() > position) {
                    switch (jawabanKuis.getJawabanUser(position)) {
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

                    switch (jawabanKuis.getKunJawList(position)){
                        case "a":
                            pilganA.setBackgroundColor(ContextCompat.getColor(context, R.color.light_tosca_palette));
                            pilganB.setBackgroundColor(Color.WHITE);
                            pilganC.setBackgroundColor(Color.WHITE);
                            break;
                        case "b":
                            pilganA.setBackgroundColor(Color.WHITE);
                            pilganB.setBackgroundColor(ContextCompat.getColor(context, R.color.light_tosca_palette));
                            pilganC.setBackgroundColor(Color.WHITE);
                            break;
                        case "c":
                            pilganA.setBackgroundColor(Color.WHITE);
                            pilganB.setBackgroundColor(Color.WHITE);
                            pilganC.setBackgroundColor(ContextCompat.getColor(context, R.color.light_tosca_palette));
                            break;
                    }

                    if(jawabanKuis.getJawabanUser(position).equals(jawabanKuis.getKunJawList(position))){
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                        noSoal.setTextColor(Color.WHITE);
                        noSoal.setText(R.string.symbol_centang);
                    }else{
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                        noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                        noSoal.setText(R.string.symbol_cross);
                    }
                }
            }

            if(position==4){
                next.setText(R.string.selesai);
            }

        }else {
            System.out.println("INNNNNNNNNNEEEEEH DI HALAMAN POSITION == 5");
            System.out.println("POSESEEEEEH: "+position);
            pertanyaan.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
            if(correctCounter < 3){
                pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(0)+"\n\nNilai Kamu: "+correctCounter);
                next.setVisibility(View.GONE);
            }else if(correctCounter == 3){
                pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(1)+"\n\nNilai Kamu: "+correctCounter);
            }else {
                pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(2)+"\n\nNilai Kamu: "+correctCounter);
            }
            pilganA.setVisibility(View.GONE);
            pilganB.setVisibility(View.GONE);
            pilganC.setVisibility(View.GONE);
            noSoal.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            penjelasan.setVisibility(View.VISIBLE);
            penjelasan.setText(R.string.ayo_belajar_lagi);
            next.setText(R.string.mulai_investasi);
        }


        pilganAOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jawabanKuis.getJawabanUserList().isEmpty()) {
                    if(jawabanKuis.getJawabanUserList().size() == position) {
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
                        jawabanKuis.storeJawabanUser("a");
                        if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                            correctCounter+=1;
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                            noSoal.setTextColor(Color.WHITE);
                            noSoal.setText(R.string.symbol_centang);
                        }else{
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                            noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                            noSoal.setText(R.string.symbol_cross);
                        }
                    }
                }else{
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
                    jawabanKuis.storeJawabanUser("a");
                    if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                        correctCounter+=1;
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                        noSoal.setTextColor(Color.WHITE);
                        noSoal.setText(R.string.symbol_centang);
                    }else{
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                        noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                        noSoal.setText(R.string.symbol_cross);
                    }
                }

                switch (jawabanKuis.getKunJawList(position)){
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

                System.out.println("PRINTINGGGGG: "+jawabanKuis.getJawabanUserList().toString());
                System.out.println("JAWABAN BENERRRRR: "+correctCounter);
            }
        });

        pilganBOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jawabanKuis.getJawabanUserList().isEmpty()) {
                    if(jawabanKuis.getJawabanUserList().size() == position) {
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
                        jawabanKuis.storeJawabanUser("b");
                        if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                            correctCounter+=1;
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                            noSoal.setTextColor(Color.WHITE);
                            noSoal.setText(R.string.symbol_centang);
                        }else{
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                            noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                            noSoal.setText(R.string.symbol_cross);
                        }
                    }
                }else{
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
                    jawabanKuis.storeJawabanUser("b");
                    if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                        correctCounter+=1;
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                        noSoal.setTextColor(Color.WHITE);
                        noSoal.setText(R.string.symbol_centang);
                    }else{
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                        noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                        noSoal.setText(R.string.symbol_cross);
                    }
                }

                switch (jawabanKuis.getKunJawList(position)){
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

                System.out.println("PRINTINGGGGG: "+jawabanKuis.getJawabanUserList().toString());
                System.out.println("JAWABAN BENERRRRR: "+correctCounter);
            }
        });


        pilganCOpsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jawabanKuis.getJawabanUserList().isEmpty()) {
                    if(jawabanKuis.getJawabanUserList().size() == position) {
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
                        jawabanKuis.storeJawabanUser("c");
                        if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                            correctCounter+=1;
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                            noSoal.setTextColor(Color.WHITE);
                            noSoal.setText(R.string.symbol_centang);
                        }else{
                            noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                            noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                            noSoal.setText(R.string.symbol_cross);
                        }
                    }
                }else{
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
                    jawabanKuis.storeJawabanUser("c");
                    if(jawabanKuis.getKunJawList(position).equals(jawabanKuis.getJawabanUser(position))){
                        correctCounter+=1;
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark_filled));
                        noSoal.setTextColor(Color.WHITE);
                        noSoal.setText(R.string.symbol_centang);
                    }else{
                        noSoal.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_stroke_tosca_dark));
                        noSoal.setTextColor(ContextCompat.getColor(context,R.color.deep_cerulean_palette));
                        noSoal.setText(R.string.symbol_cross);
                    }
                }

                switch (jawabanKuis.getKunJawList(position)){
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

                System.out.println("PRINTINGGGGG: "+jawabanKuis.getJawabanUserList().toString());
                System.out.println("JAWABAN BENERRRRR: "+correctCounter);
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
                        pertanyaan.setText("Menurut Anda apa itu Reksa Dana?");
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
                        pertanyaan.setText("Menurut Anda apa itu Reksa Dana?");
                        next.setText(R.string.selesai);
                    }
                }else {
                    System.out.println("PINDAHHH KE HALAMAN PRODUK REKSADANA");
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
                }else {
                    System.out.println("BUKA LEARNINGGGGGG");
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
