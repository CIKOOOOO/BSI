package com.bca.bsi.ui.basenavigation.more.calculator.durasiinvestasi;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;

public class DurasiInvestasiFragment extends BaseFragment implements View.OnClickListener, IDurasiInvestasiCallback {

    private DurasiInvestasiViewModel viewModel;
    private Button kalkulasi;
    private TextView DILabel;
    private TextView hasilDI;
    private EditText ETDITargetHasilInvestasi;
    private EditText ETDIModalAwal;
    private EditText ETDIInvestasiBulanan;
    private EditText ETDIROR;
    private NestedScrollView nestedScrollView;
    private int numbOfTabs;
    private String rorValue;
    private TextView tvRoR;
    private TextView tvRoRPertahun;
    private TextView tvRoRPersen;

    public DurasiInvestasiFragment(int numbOfTabs, String rorValue) {
        this.numbOfTabs = numbOfTabs;
        this.rorValue = rorValue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_durasi_investasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DILabel = view.findViewById(R.id.label_durasi_investasi);
        hasilDI = view.findViewById(R.id.tv_di_hasil);
        ETDIInvestasiBulanan = view.findViewById(R.id.et_di_investasi_bulanan);
        ETDIModalAwal = view.findViewById(R.id.et_di_modal_awal);
        ETDIROR = view.findViewById(R.id.et_di_ror);
        ETDITargetHasilInvestasi = view.findViewById(R.id.et_di_target_hasil_investasi);
        kalkulasi = view.findViewById(R.id.btn_di_kalkulasi);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        tvRoR = view.findViewById(R.id.tv_ror);
        tvRoRPertahun = view.findViewById(R.id.tv_ror_pertahun);
        tvRoRPersen = view.findViewById(R.id.tv_ror_persen);

        viewModel = new ViewModelProvider(this).get(DurasiInvestasiViewModel.class);
        viewModel.setCallback(this);
        kalkulasi.setOnClickListener(this);

        switch (numbOfTabs){
            case 3:
                ETDIROR.setVisibility(View.GONE);
                tvRoR.setVisibility(View.GONE);
                tvRoRPertahun.setVisibility(View.GONE);
                tvRoRPersen.setVisibility(View.GONE);
                break;
            case 4:
                ETDIROR.setVisibility(View.VISIBLE);
                tvRoR.setVisibility(View.VISIBLE);
                tvRoRPertahun.setVisibility(View.VISIBLE);
                tvRoRPersen.setVisibility(View.VISIBLE);
                break;
        }

        ETDITargetHasilInvestasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETDITargetHasilInvestasi.getText().length()==2 && ETDITargetHasilInvestasi.getText().charAt(0)==zero){
                    ETDITargetHasilInvestasi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETDIModalAwal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETDIModalAwal.getText().length()==2 && ETDIModalAwal.getText().charAt(0)==zero){
                    ETDIModalAwal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETDIInvestasiBulanan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETDIInvestasiBulanan.getText().length()==2 && ETDIInvestasiBulanan.getText().charAt(0)==zero){
                    ETDIInvestasiBulanan.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETDIROR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETDIROR.getText().length()>1 && ETDIROR.getText().charAt(0)==zero){
                    if(ETDIROR.getText().length()==2 && !ETDIROR.getText().toString().equals("0.")){
                        ETDIROR.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        DILabel.setVisibility(View.GONE);
        hasilDI.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_di_kalkulasi:
                if (kalkulasi.getText().equals(getString(R.string.calculator_kalkulasi_label))) {
                    ETDIModalAwal.setEnabled(false);
                    ETDIInvestasiBulanan.setEnabled(false);
                    ETDIROR.setEnabled(false);
                    ETDITargetHasilInvestasi.setEnabled(false);

                    if(ETDIModalAwal.getText().toString().equals("")){
                        ETDIModalAwal.setText("0");
                    }

                    if(ETDIInvestasiBulanan.getText().toString().equals("")){
                        ETDIInvestasiBulanan.setText("0");
                    }

                    if(ETDIROR.getText().toString().equals("")){
                        ETDIROR.setText("0");
                    }

                    if(ETDITargetHasilInvestasi.getText().toString().equals("")){
                        ETDITargetHasilInvestasi.setText("0");
                    }

                    switch (numbOfTabs){
                        case 3:
                            viewModel.kalkulasi(ETDIModalAwal.getText().toString(),ETDIInvestasiBulanan.getText().toString(),
                                    ETDITargetHasilInvestasi.getText().toString(),rorValue);
                            break;
                        case 4:
                            viewModel.kalkulasi(ETDIModalAwal.getText().toString(),ETDIInvestasiBulanan.getText().toString(),
                                    ETDITargetHasilInvestasi.getText().toString(),ETDIROR.getText().toString());
                            break;
                    }

                    DILabel.setVisibility(View.VISIBLE);
                    hasilDI.setVisibility(View.VISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_reset_label));

                    nestedScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                } else {
                    ETDIModalAwal.setEnabled(true);
                    ETDIInvestasiBulanan.setEnabled(true);
                    ETDIROR.setEnabled(true);
                    ETDITargetHasilInvestasi.setEnabled(true);

                    DILabel.setVisibility(View.GONE);
                    hasilDI.setVisibility(View.GONE);

                    ETDIInvestasiBulanan.setText("");
                    ETDIModalAwal.setText("");
                    ETDIROR.setText("");
                    ETDITargetHasilInvestasi.setText("");

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
    public void kalkulasiOutput(String hasilKalkulasi, String formatTargetHasil, String formatModalAwal, String formatInvestBulanan, String formatRoR) {
        ETDITargetHasilInvestasi.setText(formatTargetHasil);
        ETDIModalAwal.setText(formatModalAwal);
        ETDIInvestasiBulanan.setText(formatInvestBulanan);
        ETDIROR.setText(formatRoR);
        hasilDI.setText(hasilKalkulasi);
    }
}