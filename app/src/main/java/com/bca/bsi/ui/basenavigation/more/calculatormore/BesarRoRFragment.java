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

public class BesarRoRFragment extends BaseFragment implements View.OnClickListener {

    private Spinner spinnerDurasiTahunBROR;
    private Spinner spinnerDurasiBulanBROR;

    private Button kalkulasi;
    private TextView BRORLabel;
    private TextView persenLabel;
    private TextView hasilBROR;
    private TextView pertahunLabel;
    private EditText ETBRORTargetHasilInvestasi;
    private EditText ETBRORModalAwal;
    private EditText ETBRORInvestasiBulanan;

    public BesarRoRFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besar_ro_r, container, false);
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

        kalkulasi = view.findViewById(R.id.btn_bror_kalkulasi);
        kalkulasi.setOnClickListener(this);

        spinnerDurasiTahunBROR = view.findViewById(R.id.bror_durasi_tahun);
        spinnerDurasiBulanBROR = view.findViewById(R.id.bror_durasi_bulan);

        BRORLabel = view.findViewById(R.id.label_bror_ror);
        persenLabel = view.findViewById(R.id.label_bror_persen);
        hasilBROR = view.findViewById(R.id.tv_bror_hasil);
        pertahunLabel = view.findViewById(R.id.label_bror_pertahun);
        ETBRORInvestasiBulanan = view.findViewById(R.id.et_bror_investasi_bulanan);
        ETBRORModalAwal = view.findViewById(R.id.et_bror_modal_awal);
        ETBRORTargetHasilInvestasi = view.findViewById(R.id.et_bror_target_hasil_investasi);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiTahun);
        spinnerDurasiTahunBROR.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBROR.setAdapter(adapterBulan);

        BRORLabel.setVisibility(View.INVISIBLE);
        persenLabel.setVisibility(View.INVISIBLE);
        hasilBROR.setVisibility(View.INVISIBLE);
        pertahunLabel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bror_kalkulasi:
                if (kalkulasi.getText().equals(getString(R.string.calculator_kalkulasi_label))) {

                    ETBRORModalAwal.setEnabled(false);
                    ETBRORInvestasiBulanan.setEnabled(false);
                    ETBRORTargetHasilInvestasi.setEnabled(false);
                    spinnerDurasiBulanBROR.setEnabled(false);
                    spinnerDurasiTahunBROR.setEnabled(false);

                    if(ETBRORModalAwal.getText().toString().equals("")){
                        ETBRORModalAwal.setText("0");
                    }

                    if(ETBRORInvestasiBulanan.getText().toString().equals("")){
                        ETBRORInvestasiBulanan.setText("0");
                    }

                    if(ETBRORTargetHasilInvestasi.getText().toString().equals("")){
                        ETBRORTargetHasilInvestasi.setText("0");
                    }

                    Utils utils = new Utils();
                    Double hasilKalkulasiRor;
                    Double ETBRORModalAwalDouble = Double.parseDouble(ETBRORModalAwal.getText().toString());
                    Double ETBRORInvestasiBulananDouble = Double.parseDouble(ETBRORInvestasiBulanan.getText().toString());
                    Double ETBRORTargetHasilInvestasiDouble = Double.parseDouble(ETBRORTargetHasilInvestasi.getText().toString());
                    Integer spinnerDurasiBulanBRORInt = Integer.parseInt(spinnerDurasiBulanBROR.getSelectedItem().toString());
                    Integer spinnerDurasiTahunBRORInt = Integer.parseInt(spinnerDurasiTahunBROR.getSelectedItem().toString());

                    hasilKalkulasiRor = utils.getRor(ETBRORModalAwalDouble,ETBRORInvestasiBulananDouble,ETBRORTargetHasilInvestasiDouble,spinnerDurasiBulanBRORInt,spinnerDurasiTahunBRORInt);

                    if (hasilKalkulasiRor == -1) {
                        hasilBROR.setText(getString(R.string.input_tidak_sesuai));
                    } else if (hasilKalkulasiRor == 123123) {
                        hasilBROR.setText(getString(R.string.proporsi_input_tidak_sesuai));
                    } else {
                        hasilKalkulasiRor*=100;
                        hasilKalkulasiRor = utils.roundDouble(hasilKalkulasiRor,4);
                        hasilBROR.setText(hasilKalkulasiRor.toString());
                        persenLabel.setVisibility(View.VISIBLE);
                    }

                    BRORLabel.setVisibility(View.VISIBLE);
                    hasilBROR.setVisibility(View.VISIBLE);
                    pertahunLabel.setVisibility(View.VISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_reset_label));
                } else {

                    ETBRORModalAwal.setEnabled(true);
                    ETBRORInvestasiBulanan.setEnabled(true);
                    ETBRORTargetHasilInvestasi.setEnabled(true);
                    spinnerDurasiBulanBROR.setEnabled(true);
                    spinnerDurasiTahunBROR.setEnabled(true);

                    BRORLabel.setVisibility(View.INVISIBLE);
                    persenLabel.setVisibility(View.INVISIBLE);
                    hasilBROR.setVisibility(View.INVISIBLE);
                    pertahunLabel.setVisibility(View.INVISIBLE);

                    ETBRORInvestasiBulanan.setText("");
                    ETBRORModalAwal.setText("");
                    ETBRORTargetHasilInvestasi.setText("");
                    spinnerDurasiTahunBROR.setSelection(0);
                    spinnerDurasiBulanBROR.setSelection(0);

                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));
                }
                break;
        }
    }
}