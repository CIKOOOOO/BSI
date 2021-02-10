package com.bca.bsi.ui.basenavigation.more.calculator.durasiinvestasi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.ProductsPopUpDialog;
import com.bca.bsi.utils.BaseFragment;

import java.util.List;

public class DurasiInvestasiFragment extends BaseFragment implements View.OnClickListener, IDurasiInvestasiCallback, ProductsPopUpDialog.onDataRetrieved {

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
    private Product.DetailReksaDana selectedDetailReksadana;
    private CardView cardViewSelectedReksadana;
    private TextView produkReksadanaLabel;
    private TextView selectedNamaReksadanaTV;
    private TextView selectedTanggalReksadanaTV;
    private TextView selectedKinerjaSatuBulanReksadanaTV;
    private TextView selectedNABUnitReksadanaTV;
    private TextView selectedTipeReksadana;
    private ProductsPopUpDialog productsPopUpDialog;
    private ImageView chevronArrow;

    public DurasiInvestasiFragment(int numbOfTabs, Product.DetailReksaDana selectedDetailReksadana) {
        this.numbOfTabs = numbOfTabs;
        this.selectedDetailReksadana = selectedDetailReksadana;
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
        produkReksadanaLabel = view.findViewById(R.id.tv_produk_reksadana_label);
        cardViewSelectedReksadana = view.findViewById(R.id.card_view);
        selectedNamaReksadanaTV = view.findViewById(R.id.tv_selected_nama_reksadana);
        selectedTanggalReksadanaTV = view.findViewById(R.id.tv_selected_tanggal_reksadana);
        selectedKinerjaSatuBulanReksadanaTV = view.findViewById(R.id.tv_kinerja_1_bulan);
        selectedNABUnitReksadanaTV = view.findViewById(R.id.tv_nab_unit);
        selectedTipeReksadana = view.findViewById(R.id.tv_selected_tipe_reksadana);
        chevronArrow = view.findViewById(R.id.chevron_arrow);

        productsPopUpDialog = new ProductsPopUpDialog();
        productsPopUpDialog.setOnDataRetrieved(this);

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

        selectedNamaReksadanaTV.setText(selectedDetailReksadana.getName());
        selectedTipeReksadana.setText(selectedDetailReksadana.getProductCategory());
        selectedNABUnitReksadanaTV.setText("Rp."+ selectedDetailReksadana.getNabPerUnit());
        selectedKinerjaSatuBulanReksadanaTV.setText(selectedDetailReksadana.getKinerja1Bulan());
        rorValue = selectedDetailReksadana.getKinerja1Tahun();
        try {
            Double kinerja1Bulan = Double.parseDouble(selectedDetailReksadana.getKinerja1Bulan());
            if (kinerja1Bulan < 0) {
                selectedKinerjaSatuBulanReksadanaTV.setTextColor(getResources().getColor(R.color.red_palette));
            }else{
                selectedKinerjaSatuBulanReksadanaTV.setTextColor(getResources().getColor(R.color.green_base_palette));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectedTanggalReksadanaTV.setText(selectedDetailReksadana.getUpdateDate());


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

        cardViewSelectedReksadana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent popUpWindow = new Intent(mActivity, ProductsPopUpActivity.class);
//                startActivity(popaaUpWindow);
                productsPopUpDialog.show(getChildFragmentManager(), "");
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
                    cardViewSelectedReksadana.setEnabled(false);
                    chevronArrow.setVisibility(View.INVISIBLE);
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

                    cardViewSelectedReksadana.setEnabled(true);
                    chevronArrow.setVisibility(View.VISIBLE);

                    /*
                    ETDIInvestasiBulanan.setText("");
                    ETDIModalAwal.setText("");
                    ETDIROR.setText("");
                    ETDITargetHasilInvestasi.setText("");
                    */

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

        if(numbOfTabs==4){
            if(Double.parseDouble(ETDIROR.getText().toString()) <= 0) {
                hasilDI.setText(getString(R.string.ror_tidak_boleh_bernilai_nol));
            }
        }

    }

    @Override
    public void onDataArrived(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances) {
        selectedNamaReksadanaTV.setText(detailReksaDana.getName());
        selectedTipeReksadana.setText(detailReksaDana.getProductCategory());
        selectedNABUnitReksadanaTV.setText("Rp."+detailReksaDana.getNabPerUnit());
        selectedKinerjaSatuBulanReksadanaTV.setText(detailReksaDana.getKinerja1Bulan());
        rorValue = detailReksaDana.getKinerja1Tahun();
        try {
            Double kinerja1Bulan = Double.parseDouble(detailReksaDana.getKinerja1Bulan());
            if (kinerja1Bulan < 0) {
                selectedKinerjaSatuBulanReksadanaTV.setTextColor(getResources().getColor(R.color.red_palette));
            }else{
                selectedKinerjaSatuBulanReksadanaTV.setTextColor(getResources().getColor(R.color.green_base_palette));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectedTanggalReksadanaTV.setText(detailReksaDana.getUpdateDate());
        productsPopUpDialog.dismiss();
    }
}