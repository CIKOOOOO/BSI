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


    TextView pertanyaan;

    private LayoutInflater layoutInflater;
    private Context context;
    private onItemClick onItemClick;
    private JawabanKuis jawabanKuis;
    private List<String> kunciKuis = Arrays.asList("a","b","c","b","a");
    private List<String> pertanyaanKuis = Arrays.asList("RD soal 1","RD soal 2","RD soal 3","RD soal 4","RD soal 5");
    private List<String> opsiJawA = Arrays.asList("RD soal 1 opsi a","RD soal 2 opsi a","RD soal 3 opsi a","RD soal 4 opsi a","RD soal 5 opsi a");
    private List<String> opsiJawB = Arrays.asList("RD soal 1 opsi b","RD soal 2 opsi b","RD soal 3 opsi b","RD soal 4 opsi b","RD soal 5 opsi b");
    private List<String> opsiJawC = Arrays.asList("RD soal 1 opsi c","RD soal 2 opsi c","RD soal 3 opsi c","RD soal 4 opsi c","RD soal 5 opsi c");
    private List<String> explBenar = Arrays.asList("soal 1 benar","soal 2 benar","soal 3 benar","soal 4 benar","soal 5 benar");
    private List<String> explSalah = Arrays.asList("soal 1 salah","soal 2 salah","soal 3 salah","soal 4 salah","soal 5 salah");

    private List<String> kunciKuisOb = Arrays.asList("a","a","a","b","b");
    private List<String> pertanyaanKuisOb = Arrays.asList("OB soal 1","OB soal 2","OB soal 3","OB soal 4","OB soal 5");
    private List<String> opsiJawAOb = Arrays.asList("OB soal 1 opsi a","OB soal 2 opsi a","OB soal 3 opsi a","OB soal 4 opsi a","OB soal 5 opsi a");
    private List<String> opsiJawBOb = Arrays.asList("OB soal 1 opsi b","OB soal 2 opsi b","OB soal 3 opsi b","OB soal 4 opsi b","OB soal 5 opsi b");
    private List<String> opsiJawCOb = Arrays.asList("OB soal 1 opsi c","OB soal 2 opsi c","OB soal 3 opsi c","OB soal 4 opsi c","OB soal 5 opsi c");
    private List<String> explBenarOb = Arrays.asList("soal OB 1 benar","soal OB 2 benar","soal OB 3 benar","soal OB 4 benar","soal OB 5 benar");
    private List<String> explSalahOb = Arrays.asList("soal OB 1 salah","soal OB 2 salah","soal OB 3 salah","soal OB 4 salah","soal OB 5 salah");

    private List<String> kunciKuisAs = Arrays.asList("c","c","a","b","a");
    private List<String> pertanyaanKuisAs = Arrays.asList("AS soal 1","AS soal 2","AS soal 3","AS soal 4","AS soal 5");
    private List<String> opsiJawAAs = Arrays.asList("AS soal 1 opsi a","AS soal 2 opsi a","AS soal 3 opsi a","AS soal 4 opsi a","AS soal 5 opsi a");
    private List<String> opsiJawBAs = Arrays.asList("AS soal 1 opsi b","AS soal 2 opsi b","AS soal 3 opsi b","AS soal 4 opsi b","AS soal 5 opsi b");
    private List<String> opsiJawCAs = Arrays.asList("AS soal 1 opsi c","AS soal 2 opsi c","AS soal 3 opsi c","AS soal 4 opsi c","AS soal 5 opsi c");
    private List<String> explBenarAs = Arrays.asList("soal AS 1 benar","soal AS 2 benar","soal AS 3 benar","soal AS 4 benar","soal AS 5 benar");
    private List<String> explSalahAs = Arrays.asList("soal AS 1 salah","soal AS 2 salah","soal AS 3 salah","soal AS 4 salah","soal AS 5 salah");

    private int correctCounter = 0;

    public interface onItemClick{
        void onClick();
        void openPage();
    }

    public void setKuis(String topic){
        switch (topic){
            case "reksadana":
                jawabanKuis = new JawabanKuis();
                jawabanKuis.setKunJaw(kunciKuis);
                jawabanKuis.setPertanyaanList(pertanyaanKuis);
                jawabanKuis.setJawabanOpsiAList(opsiJawA);
                jawabanKuis.setJawabanOpsiBList(opsiJawB);
                jawabanKuis.setJawabanOpsiCList(opsiJawC);
                jawabanKuis.setCorretExplList(explBenar);
                jawabanKuis.setWrongExplList(explSalah);
                break;
            case "obligasi":
                jawabanKuis = new JawabanKuis();
                jawabanKuis.setKunJaw(kunciKuisOb);
                jawabanKuis.setPertanyaanList(pertanyaanKuisOb);
                jawabanKuis.setJawabanOpsiAList(opsiJawAOb);
                jawabanKuis.setJawabanOpsiBList(opsiJawBOb);
                jawabanKuis.setJawabanOpsiCList(opsiJawCOb);
                jawabanKuis.setCorretExplList(explBenarOb);
                jawabanKuis.setWrongExplList(explSalahOb);
                break;
            case "asuransi":
                jawabanKuis = new JawabanKuis();
                jawabanKuis.setKunJaw(kunciKuisAs);
                jawabanKuis.setPertanyaanList(pertanyaanKuisAs);
                jawabanKuis.setJawabanOpsiAList(opsiJawAAs);
                jawabanKuis.setJawabanOpsiBList(opsiJawBAs);
                jawabanKuis.setJawabanOpsiCList(opsiJawCAs);
                jawabanKuis.setCorretExplList(explBenarAs);
                jawabanKuis.setWrongExplList(explSalahAs);
                break;
        }
    }

    public void setNilai(){
        pertanyaan.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
        if(correctCounter < 3){
            pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(0)+"\n\nNilai Kamu: "+correctCounter);
        }else if(correctCounter == 3){
            pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(1)+"\n\nNilai Kamu: "+correctCounter);
        }else {
            pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(2)+"\n\nNilai Kamu: "+correctCounter);
        }
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

        noSoal.setText(Integer.toString(position+1));
        penjelasan.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        if(position < 5) {
            pertanyaan.setText(jawabanKuis.getPertanyaan(position));
            pilganAText.setText(jawabanKuis.getJawabanOpsiA(position));
            pilganBText.setText(jawabanKuis.getJawabanOpsiB(position));
            pilganCText.setText(jawabanKuis.getJawabanOpsiC(position));
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
            pertanyaan.setTextColor(ContextCompat.getColor(context, R.color.deep_cerulean_palette));
            if(correctCounter < 3){
                pertanyaan.setText("\n\n\n"+jawabanKuis.getPenilaianKuis(0)+"\n\nNilai Kamu: "+correctCounter);
                //next.setVisibility(View.GONE);
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
                    if(jawabanKuis.getJawabanUser(position).equals(jawabanKuis.getKunJawList(position))){
                        penjelasanText.setText(jawabanKuis.getCorretExpl(position));
                    }else{
                        penjelasanText.setText(jawabanKuis.getWrongExpl(position));
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
