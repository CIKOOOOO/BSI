package com.bca.bsi.ui.basenavigation.more.calculatormore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;


import java.util.ArrayList;
import java.util.List;

public class BesarInvestasiBulananFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {

    private Spinner spinnerDurasiTahunBIB;
    private Spinner spinnerDurasiBulanBIB;

    private Button kalkulasi;
    private TextView BIBLabel;
    private TextView rpLabel;
    private TextView hasilBIB;
    private EditText ETBIBTargetHasilInvestasi;
    private EditText ETBIBModalAwal;
    private EditText ETBIBROR;

    public BesarInvestasiBulananFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besar_investasi_bulanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Integer> durasiTahun = new ArrayList<Integer>();
        List<Integer> durasiBulan = new ArrayList<Integer>();

        for (int i = 0; i < 51; i++) {
            durasiTahun.add(i);
        }

        for (int j = 0; j < 12; j++) {
            durasiBulan.add(j);
        }


        kalkulasi = view.findViewById(R.id.btn_bib_kalkulasi);
        kalkulasi.setOnClickListener(this);


        spinnerDurasiTahunBIB = view.findViewById(R.id.bib_durasi_tahun);
        spinnerDurasiBulanBIB = view.findViewById(R.id.bib_durasi_bulan);


        BIBLabel = view.findViewById(R.id.label_besar_setoran_investasi_bulanan);
        rpLabel = view.findViewById(R.id.label_bib_rp);
        hasilBIB = view.findViewById(R.id.tv_bib_hasil);
        ETBIBModalAwal = view.findViewById(R.id.et_bib_modal_awal);
        ETBIBTargetHasilInvestasi = view.findViewById(R.id.et_bib_target_hasil_investasi);
        ETBIBROR = view.findViewById(R.id.et_bib_ror);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiTahun);
        spinnerDurasiTahunBIB.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBIB.setAdapter(adapterBulan);

        ETBIBModalAwal.setOnFocusChangeListener(this);


        BIBLabel.setVisibility(View.INVISIBLE);
        rpLabel.setVisibility(View.INVISIBLE);
        hasilBIB.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_bib_kalkulasi:
                if(kalkulasi.getText().equals(getString(R.string.calculator_kalkulasi_label))){

                    ETBIBModalAwal.setEnabled(false);
                    ETBIBTargetHasilInvestasi.setEnabled(false);
                    ETBIBROR.setEnabled(false);
                    spinnerDurasiTahunBIB.setEnabled(false);
                    spinnerDurasiBulanBIB.setEnabled(false);

                    if(ETBIBModalAwal.getText().toString().equals("")){
                        ETBIBModalAwal.setText("0");
                    }

                    if(ETBIBTargetHasilInvestasi.getText().toString().equals("")){
                        ETBIBTargetHasilInvestasi.setText("0");
                    }

                    if(ETBIBROR.getText().toString().equals("")){
                        ETBIBROR.setText("0");
                    }

                    Utils utils = new Utils();
                    Double hasilKalkulasiBIB;
                    Double ETBIBModalAwalDouble = Double.parseDouble(ETBIBModalAwal.getText().toString());
                    Double ETBIBTargetHasilInvestasiDouble = Double.parseDouble(ETBIBTargetHasilInvestasi.getText().toString());
                    Double ETBIBRORDouble = Double.parseDouble(ETBIBROR.getText().toString())/100;
                    Integer spinnerDurasiTahunBIBInt = Integer.parseInt(spinnerDurasiTahunBIB.getSelectedItem().toString());
                    Integer spinnerDurasiBulanBIBInt = Integer.parseInt(spinnerDurasiBulanBIB.getSelectedItem().toString());

                    hasilKalkulasiBIB = utils.getMonthlyCost(ETBIBModalAwalDouble,ETBIBTargetHasilInvestasiDouble,ETBIBRORDouble,spinnerDurasiBulanBIBInt,spinnerDurasiTahunBIBInt);

                    BIBLabel.setVisibility(View.VISIBLE);
                    rpLabel.setVisibility(View.VISIBLE);
                    hasilBIB.setVisibility(View.VISIBLE);

                    hasilBIB.setText(utils.priceFormat(hasilKalkulasiBIB));

                    kalkulasi.setText(getString(R.string.calculator_reset_label));
                }else {
                    ETBIBModalAwal.setEnabled(true);
                    ETBIBTargetHasilInvestasi.setEnabled(true);
                    ETBIBROR.setEnabled(true);
                    spinnerDurasiTahunBIB.setEnabled(true);
                    spinnerDurasiBulanBIB.setEnabled(true);

                    BIBLabel.setVisibility(View.INVISIBLE);
                    rpLabel.setVisibility(View.INVISIBLE);
                    hasilBIB.setVisibility(View.INVISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));

                    ETBIBModalAwal.setText("");
                    ETBIBROR.setText("");
                    ETBIBTargetHasilInvestasi.setText("");
                    spinnerDurasiTahunBIB.setSelection(0);
                    spinnerDurasiBulanBIB.setSelection(0);
                }

                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){



            //ETBIBModalAwal.setText("88888888888");

            /*
            switch (v.getId()) {
                case R.id.et_bib_modal_awal:
                    utils.cekFormatInputNumber(ETBIBModalAwal,ETBIBModalAwal.getText().toString());
                    //ETBIBModalAwal.setText("88888888888");
                    break;
            }
            */
        }
    }
}