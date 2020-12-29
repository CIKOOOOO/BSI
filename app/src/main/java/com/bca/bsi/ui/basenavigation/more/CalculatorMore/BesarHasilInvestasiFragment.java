package com.bca.bsi.ui.basenavigation.more.CalculatorMore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class BesarHasilInvestasiFragment extends Fragment implements View.OnClickListener {

    private Spinner spinnerDurasiTahunBHI;
    private Spinner spinnerDurasiBulanBHI;
    private Button kalkulasi;
    private TextView BHILabel;
    private TextView rpLabel;
    private TextView hasilBHI;
    private EditText ETBHIModalAwal;
    private EditText ETBHIIvestasiBulanan;
    private EditText ETBHIROR;

    public BesarHasilInvestasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besar_hasil_investasi, container, false);
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

        kalkulasi = view.findViewById(R.id.btn_bhi_kalkulasi);
        kalkulasi.setOnClickListener(this);

        spinnerDurasiTahunBHI = view.findViewById(R.id.bhi_durasi_tahun);
        spinnerDurasiBulanBHI = view.findViewById(R.id.bhi_durasi_bulan);

        BHILabel = view.findViewById(R.id.label_besar_hasil_investasi);
        rpLabel = view.findViewById(R.id.label_bhi_rp);
        hasilBHI = view.findViewById(R.id.tv_bhi_hasil);
        ETBHIIvestasiBulanan = view.findViewById(R.id.et_bhi_investasi_bulanan);
        ETBHIModalAwal = view.findViewById(R.id.et_bhi_modal_awal);
        ETBHIROR = view.findViewById(R.id.et_bhi_ror);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiTahun);
        spinnerDurasiTahunBHI.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBHI.setAdapter(adapterBulan);


        BHILabel.setVisibility(View.INVISIBLE);
        rpLabel.setVisibility(View.INVISIBLE);
        hasilBHI.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bhi_kalkulasi:
                if (kalkulasi.getText().equals("KALKULASI")) {
                    BHILabel.setVisibility(View.VISIBLE);
                    rpLabel.setVisibility(View.VISIBLE);
                    hasilBHI.setVisibility(View.VISIBLE);
                    kalkulasi.setText("RESET");
                } else {


                    BHILabel.setVisibility(View.INVISIBLE);
                    rpLabel.setVisibility(View.INVISIBLE);
                    hasilBHI.setVisibility(View.INVISIBLE);

                    ETBHIIvestasiBulanan.setText("");
                    ETBHIModalAwal.setText("");
                    ETBHIROR.setText("");
                    spinnerDurasiTahunBHI.setSelection(0);
                    spinnerDurasiBulanBHI.setSelection(0);

                    kalkulasi.setText("KALKULASI");
                }
                break;
        }
    }
}