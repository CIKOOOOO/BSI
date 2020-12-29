package com.bca.bsi.ui.basenavigation.more.CalculatorMore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;

public class DurasiInvestasiFragment extends BaseFragment implements View.OnClickListener {


    private Button kalkulasi;
    private TextView DILabel;
    private TextView hasilDI;
    private EditText ETDITargetHasilInvestasi;
    private EditText ETDIModalAwal;
    private EditText ETDIInvestasiBulanan;
    private EditText ETDIROR;


    public DurasiInvestasiFragment() {
        // Required empty public constructor
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


        kalkulasi = view.findViewById(R.id.btn_di_kalkulasi);
        kalkulasi.setOnClickListener(this);

        DILabel = view.findViewById(R.id.label_durasi_investasi);
        hasilDI = view.findViewById(R.id.tv_di_hasil);
        ETDIInvestasiBulanan = view.findViewById(R.id.et_di_investasi_bulanan);
        ETDIModalAwal = view.findViewById(R.id.et_di_modal_awal);
        ETDIROR = view.findViewById(R.id.et_di_ror);
        ETDITargetHasilInvestasi = view.findViewById(R.id.et_di_target_hasil_investasi);

        DILabel.setVisibility(View.INVISIBLE);
        hasilDI.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_di_kalkulasi:
                if (kalkulasi.getText().equals("KALKULASI")) {
                    DILabel.setVisibility(View.VISIBLE);
                    hasilDI.setVisibility(View.VISIBLE);
                    kalkulasi.setText("RESET");
                } else {
                    DILabel.setVisibility(View.INVISIBLE);
                    hasilDI.setVisibility(View.INVISIBLE);

                    ETDIInvestasiBulanan.setText("");
                    ETDIModalAwal.setText("");
                    ETDIROR.setText("");
                    ETDITargetHasilInvestasi.setText("");

                    kalkulasi.setText("KALKULASI");
                }
                break;

        }
    }
}