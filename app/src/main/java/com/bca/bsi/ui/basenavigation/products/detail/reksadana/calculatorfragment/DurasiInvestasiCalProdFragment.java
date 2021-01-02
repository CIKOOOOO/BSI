package com.bca.bsi.ui.basenavigation.products.detail.reksadana.calculatorfragment;

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
import com.bca.bsi.utils.Utils;


public class DurasiInvestasiCalProdFragment extends BaseFragment implements View.OnClickListener {

    private Button kalkulasi;
    private TextView DILabel;
    private TextView hasilDI;
    private EditText ETDITargetHasilInvestasi;
    private EditText ETDIModalAwal;
    private EditText ETDIInvestasiBulanan;
    private EditText ETDIROR;
    private TextView rorLabel;
    private TextView rorPertahunLabel;
    private TextView rorPersenLabel;

    public DurasiInvestasiCalProdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_durasi_investasi_cal_prod, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        kalkulasi = view.findViewById(R.id.btn_di_kalkulasi_calprod);
        kalkulasi.setOnClickListener(this);

        DILabel = view.findViewById(R.id.label_durasi_investasi_calprod);
        hasilDI = view.findViewById(R.id.tv_di_hasil_calprod);
        ETDIInvestasiBulanan = view.findViewById(R.id.et_di_investasi_bulanan_calprod);
        ETDIModalAwal = view.findViewById(R.id.et_di_modal_awal_calprod);
        ETDIROR = view.findViewById(R.id.et_di_ror_calprod);
        ETDITargetHasilInvestasi = view.findViewById(R.id.et_di_target_hasil_investasi_calprod);

        rorLabel = view.findViewById(R.id.textView22_calprod);
        rorPersenLabel = view.findViewById(R.id.textView27_calprod);
        rorPertahunLabel = view.findViewById(R.id.textView23_calprod);

        DILabel.setVisibility(View.INVISIBLE);
        hasilDI.setVisibility(View.INVISIBLE);
        ETDIROR.setVisibility(View.GONE);
        ETDIROR.setText("2");
        rorLabel.setVisibility(View.GONE);
        rorPersenLabel.setVisibility(View.GONE);
        rorPertahunLabel.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_di_kalkulasi_calprod:
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

                    Utils utils = new Utils();
                    Double ETDIModalAwalDouble = Double.parseDouble(ETDIModalAwal.getText().toString());
                    Double ETDIInvestasiBulananDouble = Double.parseDouble(ETDIInvestasiBulanan.getText().toString());
                    Double ETDITargetHasilInvestasiDouble = Double.parseDouble(ETDITargetHasilInvestasi.getText().toString());
                    Double ETDIRORDouble = Double.parseDouble(ETDIROR.getText().toString())/100;

                    int[] hasilKalkulasiDI = utils.getDuration(ETDIModalAwalDouble,ETDIInvestasiBulananDouble,ETDITargetHasilInvestasiDouble,ETDIRORDouble);

                    hasilDI.setText(hasilKalkulasiDI[1]+" tahun "+hasilKalkulasiDI[0]+" bulan");

                    DILabel.setVisibility(View.VISIBLE);
                    hasilDI.setVisibility(View.VISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_reset_label));
                } else {
                    ETDIModalAwal.setEnabled(true);
                    ETDIInvestasiBulanan.setEnabled(true);
                    ETDIROR.setEnabled(true);
                    ETDITargetHasilInvestasi.setEnabled(true);

                    DILabel.setVisibility(View.INVISIBLE);
                    hasilDI.setVisibility(View.INVISIBLE);

                    ETDIInvestasiBulanan.setText("");
                    ETDIModalAwal.setText("");
                    ETDIROR.setText("");
                    ETDITargetHasilInvestasi.setText("");

                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));
                }
                break;

        }
    }
}