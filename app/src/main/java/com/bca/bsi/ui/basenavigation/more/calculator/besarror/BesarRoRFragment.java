package com.bca.bsi.ui.basenavigation.more.calculator.besarror;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.List;

public class BesarRoRFragment extends BaseFragment implements View.OnClickListener, IBesarRoRCallback {

    private BesarRoRViewModel viewModel;
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
    private NestedScrollView nestedScrollView;

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

        spinnerDurasiTahunBROR = view.findViewById(R.id.bror_durasi_tahun);
        spinnerDurasiBulanBROR = view.findViewById(R.id.bror_durasi_bulan);
        BRORLabel = view.findViewById(R.id.label_bror_ror);
        persenLabel = view.findViewById(R.id.label_bror_persen);
        hasilBROR = view.findViewById(R.id.tv_bror_hasil);
        pertahunLabel = view.findViewById(R.id.label_bror_pertahun);
        ETBRORInvestasiBulanan = view.findViewById(R.id.et_bror_investasi_bulanan);
        ETBRORModalAwal = view.findViewById(R.id.et_bror_modal_awal);
        ETBRORTargetHasilInvestasi = view.findViewById(R.id.et_bror_target_hasil_investasi);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        kalkulasi = view.findViewById(R.id.btn_bror_kalkulasi);

        viewModel = new ViewModelProvider(this).get(BesarRoRViewModel.class);
        viewModel.setCallback(this);
        kalkulasi.setOnClickListener(this);

        List<Integer> durasiTahun = new ArrayList<Integer>();
        List<Integer> durasiBulan = new ArrayList<Integer>();

        for (int i = 0; i < 51; i++) {
            durasiTahun.add(i);
        }

        for (int j = 0; j < 12; j++) {
            durasiBulan.add(j);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiTahun);
        spinnerDurasiTahunBROR.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBROR.setAdapter(adapterBulan);

        ETBRORTargetHasilInvestasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBRORTargetHasilInvestasi.getText().length()==2 && ETBRORTargetHasilInvestasi.getText().charAt(0)==zero){
                    ETBRORTargetHasilInvestasi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBRORModalAwal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBRORModalAwal.getText().length()==2 && ETBRORModalAwal.getText().charAt(0)==zero){
                    ETBRORModalAwal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBRORInvestasiBulanan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBRORInvestasiBulanan.getText().length()==2 && ETBRORInvestasiBulanan.getText().charAt(0)==zero){
                    ETBRORInvestasiBulanan.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        BRORLabel.setVisibility(View.GONE);
        persenLabel.setVisibility(View.GONE);
        hasilBROR.setVisibility(View.GONE);
        pertahunLabel.setVisibility(View.GONE);
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

                    viewModel.kalkulasi(ETBRORModalAwal.getText().toString(),ETBRORInvestasiBulanan.getText().toString(),ETBRORTargetHasilInvestasi.getText().toString(),spinnerDurasiBulanBROR.getSelectedItem().toString(),spinnerDurasiTahunBROR.getSelectedItem().toString());

                    BRORLabel.setVisibility(View.VISIBLE);
                    hasilBROR.setVisibility(View.VISIBLE);
                    pertahunLabel.setVisibility(View.VISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_reset_label));

                    nestedScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });

                } else {

                    ETBRORModalAwal.setEnabled(true);
                    ETBRORInvestasiBulanan.setEnabled(true);
                    ETBRORTargetHasilInvestasi.setEnabled(true);
                    spinnerDurasiBulanBROR.setEnabled(true);
                    spinnerDurasiTahunBROR.setEnabled(true);

                    BRORLabel.setVisibility(View.GONE);
                    persenLabel.setVisibility(View.GONE);
                    hasilBROR.setVisibility(View.GONE);
                    pertahunLabel.setVisibility(View.GONE);

                    ETBRORInvestasiBulanan.setText("");
                    ETBRORModalAwal.setText("");
                    ETBRORTargetHasilInvestasi.setText("");
                    spinnerDurasiTahunBROR.setSelection(0);
                    spinnerDurasiBulanBROR.setSelection(0);

                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));

                    nestedScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(View.FOCUS_UP);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void kalkulasiOutput(String hasilKalkulasi, String formatTargetHasil, String formatModalAwal, String formatInvestasiBulanan) {
        if (!hasilKalkulasi.equals(getString(R.string.ror_bernilai_negatif))) {
            if(!hasilKalkulasi.equals(getString(R.string.ror_bernilai_lebih_dari_1000_persen))){
                persenLabel.setVisibility(View.VISIBLE);
            }
        } else {
            persenLabel.setVisibility(View.INVISIBLE);
        }

        hasilBROR.setText(hasilKalkulasi);
        ETBRORTargetHasilInvestasi.setText(formatTargetHasil);
        ETBRORModalAwal.setText(formatModalAwal);
        ETBRORInvestasiBulanan.setText(formatInvestasiBulanan);
    }
}