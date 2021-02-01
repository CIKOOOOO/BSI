package com.bca.bsi.ui.basenavigation.more.calculator.besarhasilinvestasi;

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

public class BesarHasilInvestasiFragment extends BaseFragment implements View.OnClickListener, IBesarHasilInvestasiCallback {

    private BesarHasilInvestasiViewModel viewModel;
    private Spinner spinnerDurasiTahunBHI;
    private Spinner spinnerDurasiBulanBHI;
    private Button kalkulasi;
    private TextView BHILabel;
    private TextView rpLabel;
    private TextView hasilBHI;
    private EditText ETBHIModalAwal;
    private EditText ETBHIIvestasiBulanan;
    private EditText ETBHIROR;
    private NestedScrollView nestedScrollView;
    private int numbOfTabs;
    private String rorValue;
    private TextView tvRoR;
    private TextView tvRoRPertahun;
    private TextView tvRoRPersen;

    public BesarHasilInvestasiFragment(int numbOfTabs, String rorValue) {
        this.numbOfTabs = numbOfTabs;
        this.rorValue = rorValue;
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

        kalkulasi = view.findViewById(R.id.btn_kalkulasi);
        spinnerDurasiTahunBHI = view.findViewById(R.id.spinner_durasi_tahun);
        spinnerDurasiBulanBHI = view.findViewById(R.id.spinner_durasi_bulan);
        BHILabel = view.findViewById(R.id.main_tv_hasil);
        rpLabel = view.findViewById(R.id.tv_hasil_kalkulasi_rp);
        hasilBHI = view.findViewById(R.id.tv_hasil);
        ETBHIIvestasiBulanan = view.findViewById(R.id.et_modal_awal);
        ETBHIModalAwal = view.findViewById(R.id.et_target_hasil_investasi);
        ETBHIROR = view.findViewById(R.id.et_ror);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        tvRoR = view.findViewById(R.id.tv_ror);
        tvRoRPertahun = view.findViewById(R.id.tv_ror_pertahun);
        tvRoRPersen = view.findViewById(R.id.tv_ror_persen);

        viewModel = new ViewModelProvider(this).get(BesarHasilInvestasiViewModel.class);
        viewModel.setCallback(this);
        kalkulasi.setOnClickListener(this);

        switch (numbOfTabs){
            case 3:
                ETBHIROR.setVisibility(View.GONE);
                tvRoR.setVisibility(View.GONE);
                tvRoRPertahun.setVisibility(View.GONE);
                tvRoRPersen.setVisibility(View.GONE);
                break;
            case 4:
                ETBHIROR.setVisibility(View.VISIBLE);
                tvRoR.setVisibility(View.VISIBLE);
                tvRoRPertahun.setVisibility(View.VISIBLE);
                tvRoRPersen.setVisibility(View.VISIBLE);
                break;
        }

        List<Integer> durasiTahun = new ArrayList<Integer>();
        List<Integer> durasiBulan = new ArrayList<Integer>();

        for (int i = 0; i < 51; i++) {
            durasiTahun.add(i);
        }

        for (int j = 0; j < 12; j++) {
            durasiBulan.add(j);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiTahun);
        spinnerDurasiTahunBHI.setAdapter(adapter);

        ArrayAdapter<Integer> adapterBulan = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_dropdown_item_1line, durasiBulan);
        spinnerDurasiBulanBHI.setAdapter(adapterBulan);


        ETBHIModalAwal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBHIModalAwal.getText().length()==2 && ETBHIModalAwal.getText().charAt(0)==zero){
                    ETBHIModalAwal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBHIIvestasiBulanan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBHIIvestasiBulanan.getText().length()==2 && ETBHIIvestasiBulanan.getText().charAt(0)==zero){
                    ETBHIIvestasiBulanan.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETBHIROR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(ETBHIROR.getText().length()>1 && ETBHIROR.getText().charAt(0)==zero){
                    if(ETBHIROR.getText().length()==2 && !ETBHIROR.getText().toString().equals("0.")){
                        ETBHIROR.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        BHILabel.setVisibility(View.GONE);
        rpLabel.setVisibility(View.GONE);
        hasilBHI.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kalkulasi:
                if (kalkulasi.getText().equals(getString(R.string.calculator_kalkulasi_label))) {

                    ETBHIModalAwal.setEnabled(false);
                    ETBHIIvestasiBulanan.setEnabled(false);
                    ETBHIROR.setEnabled(false);
                    spinnerDurasiBulanBHI.setEnabled(false);
                    spinnerDurasiTahunBHI.setEnabled(false);

                    if(ETBHIModalAwal.getText().toString().equals("")){
                        ETBHIModalAwal.setText("0");
                    }

                    if(ETBHIIvestasiBulanan.getText().toString().equals("")){
                        ETBHIIvestasiBulanan.setText("0");
                    }

                    if(ETBHIROR.getText().toString().equals("")){
                        ETBHIROR.setText("0");
                    }

                    switch (numbOfTabs){
                        case 3:
                            viewModel.kalkulasi(ETBHIModalAwal.getText().toString(),ETBHIIvestasiBulanan.getText().toString(),
                                    rorValue,spinnerDurasiBulanBHI.getSelectedItem().toString(),spinnerDurasiTahunBHI.getSelectedItem().toString());
                            System.out.print("INIIIIII ROR VALUE DARI BESAR HASIL INVESTASI FRAGMENT: "+rorValue);
                            break;
                        case 4:
                            viewModel.kalkulasi(ETBHIModalAwal.getText().toString(),ETBHIIvestasiBulanan.getText().toString(),ETBHIROR.getText().toString(),
                                    spinnerDurasiBulanBHI.getSelectedItem().toString(),spinnerDurasiTahunBHI.getSelectedItem().toString());
                            break;
                    }

                    BHILabel.setVisibility(View.VISIBLE);
                    rpLabel.setVisibility(View.VISIBLE);
                    //hasilBHI.setVisibility(View.VISIBLE);
                    kalkulasi.setText(getString(R.string.calculator_reset_label));

                    nestedScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });

                } else {
                    ETBHIModalAwal.setEnabled(true);
                    ETBHIIvestasiBulanan.setEnabled(true);
                    ETBHIROR.setEnabled(true);
                    spinnerDurasiBulanBHI.setEnabled(true);
                    spinnerDurasiTahunBHI.setEnabled(true);

                    BHILabel.setVisibility(View.GONE);
                    rpLabel.setText(getString(R.string.rp));
                    rpLabel.setVisibility(View.GONE);
                    hasilBHI.setVisibility(View.GONE);

                    ETBHIIvestasiBulanan.setText("");
                    ETBHIModalAwal.setText("");
                    ETBHIROR.setText("");
                    spinnerDurasiTahunBHI.setSelection(0);
                    spinnerDurasiBulanBHI.setSelection(0);

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
    public void kalkulasiOutput(String hasilKalkulasi, String formatModalAwal, String formatInvestBulanan, String formatRoR) {
        ETBHIModalAwal.setText(formatModalAwal);
        ETBHIIvestasiBulanan.setText(formatInvestBulanan);
        ETBHIROR.setText(formatRoR);

        /*
        if(hasilKalkulasi.equals("NaN") || hasilKalkulasi.equals("-NaN") ){
            rpLabel.setText(getString(R.string.ror_tidak_boleh_bernilai_nol));
            hasilBHI.setVisibility(View.INVISIBLE);
        }else{
            rpLabel.setVisibility(View.VISIBLE);
            hasilBHI.setText(hasilKalkulasi);
            */

        hasilBHI.setText(hasilKalkulasi);

        switch (numbOfTabs){
            case 3:
                if(spinnerDurasiBulanBHI.getSelectedItemPosition()==0 && spinnerDurasiTahunBHI.getSelectedItemPosition()==0) {
                    rpLabel.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                    hasilBHI.setVisibility(View.INVISIBLE);
                }else{
                    rpLabel.setText(getString(R.string.rp));
                    hasilBHI.setVisibility(View.VISIBLE);
                }
                break;

            case 4:
                if(spinnerDurasiBulanBHI.getSelectedItemPosition()==0 && spinnerDurasiTahunBHI.getSelectedItemPosition()==0
                        || Double.parseDouble(ETBHIROR.getText().toString()) <= 0) {
                    rpLabel.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                    hasilBHI.setVisibility(View.INVISIBLE);
                }else{
                    rpLabel.setText(getString(R.string.rp));
                    hasilBHI.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}