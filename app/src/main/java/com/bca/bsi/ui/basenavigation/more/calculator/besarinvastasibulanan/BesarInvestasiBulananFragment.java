package com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan;

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

public class BesarInvestasiBulananFragment extends BaseFragment implements View.OnClickListener, IBesarInvestasiBulananCallback {

    private BesarInvestasiBulananViewModel viewModel;
    private Button kalkulasi;
    private TextView BIBLabel;
    private TextView rpLabel;
    private TextView hasilBIB;
    private EditText ETBIBTargetHasilInvestasi;
    private EditText ETBIBModalAwal;
    private EditText ETBIBROR;
    private NestedScrollView nestedScrollView;
    private Spinner spinnerDurasiTahunBIB;
    private Spinner spinnerDurasiBulanBIB;
    private int numbOfTabs;
    private String rorValue;
    private TextView tvRoR;
    private TextView tvRoRPertahun;
    private TextView tvRoRPersen;

    public BesarInvestasiBulananFragment(int numbOfTabs, String rorValue) {
        this.numbOfTabs = numbOfTabs;
        this.rorValue = rorValue;
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

        BIBLabel = view.findViewById(R.id.label_besar_setoran_investasi_bulanan);
        rpLabel = view.findViewById(R.id.label_bib_rp);
        hasilBIB = view.findViewById(R.id.tv_bib_hasil);
        ETBIBModalAwal = view.findViewById(R.id.et_bib_modal_awal);
        ETBIBTargetHasilInvestasi = view.findViewById(R.id.et_bib_target_hasil_investasi);
        ETBIBROR = view.findViewById(R.id.et_bib_ror);
        spinnerDurasiTahunBIB = view.findViewById(R.id.bib_durasi_tahun);
        spinnerDurasiBulanBIB = view.findViewById(R.id.bib_durasi_bulan);
        kalkulasi = view.findViewById(R.id.btn_bib_kalkulasi);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        tvRoR = view.findViewById(R.id.tv_ror);
        tvRoRPertahun = view.findViewById(R.id.tv_ror_pertahun);
        tvRoRPersen = view.findViewById(R.id.tv_ror_persen);

        switch (numbOfTabs){
            case 3:
                ETBIBROR.setVisibility(View.GONE);
                tvRoR.setVisibility(View.GONE);
                tvRoRPertahun.setVisibility(View.GONE);
                tvRoRPersen.setVisibility(View.GONE);
                break;
            case 4:
                ETBIBROR.setVisibility(View.VISIBLE);
                tvRoR.setVisibility(View.VISIBLE);
                tvRoRPertahun.setVisibility(View.VISIBLE);
                tvRoRPersen.setVisibility(View.VISIBLE);
                break;
        }

        viewModel = new ViewModelProvider(this).get(BesarInvestasiBulananViewModel.class);
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
        spinnerDurasiTahunBIB.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBIB.setAdapter(adapterBulan);

        ETBIBModalAwal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBIBModalAwal.getText().length()==2 && ETBIBModalAwal.getText().charAt(0)==zero){
                    ETBIBModalAwal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBIBTargetHasilInvestasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBIBTargetHasilInvestasi.getText().length()==2 && ETBIBTargetHasilInvestasi.getText().charAt(0)==zero){
                    ETBIBTargetHasilInvestasi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBIBROR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBIBROR.getText().length()>1 && ETBIBROR.getText().charAt(0)==zero){
                    if(ETBIBROR.getText().length()==2 && !ETBIBROR.getText().toString().equals("0.")){
                        ETBIBROR.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        BIBLabel.setVisibility(View.GONE);
        rpLabel.setVisibility(View.GONE);
        hasilBIB.setVisibility(View.GONE);
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

                    if(ETBIBModalAwal.getText().toString().isEmpty()){
                        ETBIBModalAwal.setText("0");
                    }

                    if(ETBIBTargetHasilInvestasi.getText().toString().equals("")){
                        ETBIBTargetHasilInvestasi.setText("0");
                    }

                    if(ETBIBROR.getText().toString().equals("")){
                        ETBIBROR.setText("0");
                    }

                    switch (numbOfTabs){
                        case 3:
                            viewModel.kalkulasi(ETBIBModalAwal.getText().toString(),ETBIBTargetHasilInvestasi.getText().toString(),
                                    rorValue,spinnerDurasiBulanBIB.getSelectedItem().toString(),spinnerDurasiTahunBIB.getSelectedItem().toString());
                            break;
                        case 4:
                            viewModel.kalkulasi(ETBIBModalAwal.getText().toString(),ETBIBTargetHasilInvestasi.getText().toString(),
                                    ETBIBROR.getText().toString(),spinnerDurasiBulanBIB.getSelectedItem().toString(),spinnerDurasiTahunBIB.getSelectedItem().toString());
                            break;
                    }

                    BIBLabel.setVisibility(View.VISIBLE);
                    rpLabel.setVisibility(View.VISIBLE);
                    hasilBIB.setVisibility(View.VISIBLE);

                    kalkulasi.setText(getString(R.string.calculator_reset_label));

                    nestedScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });

                }else {
                    ETBIBModalAwal.setEnabled(true);
                    ETBIBTargetHasilInvestasi.setEnabled(true);
                    ETBIBROR.setEnabled(true);
                    spinnerDurasiTahunBIB.setEnabled(true);
                    spinnerDurasiBulanBIB.setEnabled(true);

                    BIBLabel.setVisibility(View.GONE);
                    rpLabel.setVisibility(View.GONE);
                    hasilBIB.setVisibility(View.GONE);
                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));

                    ETBIBModalAwal.setText("");
                    ETBIBROR.setText("");
                    ETBIBTargetHasilInvestasi.setText("");
                    spinnerDurasiTahunBIB.setSelection(0);
                    spinnerDurasiBulanBIB.setSelection(0);

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
    public void kalkulasiOutput(String formatTargetHasilInvest, String formatModalAwal, String formatRoR, String formatHasil) {
        ETBIBTargetHasilInvestasi.setText(formatTargetHasilInvest);
        ETBIBModalAwal.setText(formatModalAwal);
        ETBIBROR.setText(formatRoR);
        hasilBIB.setText(formatHasil);
    }
}