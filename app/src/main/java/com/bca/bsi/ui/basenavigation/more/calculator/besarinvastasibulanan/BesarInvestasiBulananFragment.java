package com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan;

import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.ProductsPopUpActivity;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.IProductsCalculatorCallback;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.ProductsPopUpDialog;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.google.gson.Gson;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BesarInvestasiBulananFragment extends BaseFragment implements View.OnClickListener, IBesarInvestasiBulananCallback, ProductsPopUpDialog.onDataRetrieved {

    private BesarInvestasiBulananViewModel viewModel;
    private Button kalkulasi;
    private TextView BIBLabel;
    private TextView hasilBIB;
    private EditText ETBIBTargetHasilInvestasi;
    private EditText ETBIBModalAwal;
    private EditText ETBIBROR;
    private NestedScrollView nestedScrollView;
    private Spinner spinnerDurasiTahunBIB;
    private Spinner spinnerDurasiBulanBIB;
    private int numbOfTabs;
    private TextView tvRoR;
    private TextView tvRoRPertahun;
    private TextView tvRoRPersen;
    private TextView produkReksadanaLabel;
    private CardView cardViewSelectedReksadana;
    private TextView selectedNamaReksadanaTV;
    private TextView selectedTanggalReksadanaTV;
    private TextView selectedKinerjaSatuBulanReksadanaTV;
    private TextView selectedNABUnitReksadanaTV;
    private TextView selectedTipeReksadana;
    private Product.DetailReksaDana selectedDetailReksadana;
    private Product.DetailReksaDana detailReksaDana;
    private String value;
    private ProductsPopUpDialog productsPopUpDialog;
    private String rorValue;
    private ImageView chevronArrow;

    public BesarInvestasiBulananFragment(int numbOfTabs, Product.DetailReksaDana selectedDetailReksadana) {
        this.numbOfTabs = numbOfTabs;
        this.selectedDetailReksadana = selectedDetailReksadana;
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

        /*
        Bundle bundle = getArguments();

        if(bundle != null){
            value = bundle.getString("selectedProductReksadana");
        }

        if(value != null){
            Gson gson = new Gson();
            detailReksaDana = gson.fromJson(value, Product.DetailReksaDana.class);
        }else {
            System.out.println("VALUE DARI VALUE DETAIL REKSADANA NULL");
        }

        if (detailReksaDana != null){
            selectedDetailReksadana = detailReksaDana;
        }
        */

        switch (numbOfTabs) {
            case 3:
                ETBIBROR.setVisibility(View.GONE);
                tvRoR.setVisibility(View.GONE);
                tvRoRPertahun.setVisibility(View.GONE);
                tvRoRPersen.setVisibility(View.GONE);
                //produkReksadanaLabel.setVisibility(View.VISIBLE);
                produkReksadanaLabel.setVisibility(View.VISIBLE);
                cardViewSelectedReksadana.setVisibility(View.VISIBLE);
                break;
            case 4:
                ETBIBROR.setVisibility(View.VISIBLE);
                tvRoR.setVisibility(View.VISIBLE);
                tvRoRPertahun.setVisibility(View.VISIBLE);
                tvRoRPersen.setVisibility(View.VISIBLE);
                produkReksadanaLabel.setVisibility(View.GONE);
                cardViewSelectedReksadana.setVisibility(View.GONE);
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

        if(numbOfTabs == 3){
            selectedNamaReksadanaTV.setText(selectedDetailReksadana.getName());
            selectedTipeReksadana.setText(selectedDetailReksadana.getProductCategory());
            selectedNABUnitReksadanaTV.setText("Rp."+selectedDetailReksadana.getNabPerUnit());
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
        }

        /*
        try {
            String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, (selectedDetailReksadana.getReleaseDate()));
            selectedTanggalReksadanaTV.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */

        ETBIBModalAwal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if (ETBIBModalAwal.getText().length() == 2 && ETBIBModalAwal.getText().charAt(0) == zero) {
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
                if (ETBIBTargetHasilInvestasi.getText().length() == 2 && ETBIBTargetHasilInvestasi.getText().charAt(0) == zero) {
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
                if (ETBIBROR.getText().length() > 1 && ETBIBROR.getText().charAt(0) == zero) {
                    if (ETBIBROR.getText().length() == 2 && !ETBIBROR.getText().toString().equals("0.")) {
                        ETBIBROR.setText("");
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

        BIBLabel.setVisibility(View.GONE);
        hasilBIB.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_bib_kalkulasi:
                if (kalkulasi.getText().equals(getString(R.string.calculator_kalkulasi_label))) {

                    ETBIBModalAwal.setEnabled(false);
                    ETBIBTargetHasilInvestasi.setEnabled(false);
                    ETBIBROR.setEnabled(false);
                    spinnerDurasiTahunBIB.setEnabled(false);
                    spinnerDurasiBulanBIB.setEnabled(false);

                    if (ETBIBModalAwal.getText().toString().isEmpty()) {
                        ETBIBModalAwal.setText("0");
                    }

                    if (ETBIBTargetHasilInvestasi.getText().toString().equals("")) {
                        ETBIBTargetHasilInvestasi.setText("0");
                    }

                    if (ETBIBROR.getText().toString().equals("")) {
                        ETBIBROR.setText("0");
                    }

                    switch (numbOfTabs) {
                        case 3:
                            viewModel.kalkulasi(ETBIBModalAwal.getText().toString(), ETBIBTargetHasilInvestasi.getText().toString(),
                                    rorValue, spinnerDurasiBulanBIB.getSelectedItem().toString(), spinnerDurasiTahunBIB.getSelectedItem().toString());
                            break;
                        case 4:
                            viewModel.kalkulasi(ETBIBModalAwal.getText().toString(), ETBIBTargetHasilInvestasi.getText().toString(),
                                    ETBIBROR.getText().toString(), spinnerDurasiBulanBIB.getSelectedItem().toString(), spinnerDurasiTahunBIB.getSelectedItem().toString());
                            break;
                    }

                    BIBLabel.setVisibility(View.VISIBLE);
                    //hasilBIB.setVisibility(View.VISIBLE);
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
                    ETBIBModalAwal.setEnabled(true);
                    ETBIBTargetHasilInvestasi.setEnabled(true);
                    ETBIBROR.setEnabled(true);
                    spinnerDurasiTahunBIB.setEnabled(true);
                    spinnerDurasiBulanBIB.setEnabled(true);
                    cardViewSelectedReksadana.setEnabled(true);
                    chevronArrow.setVisibility(View.VISIBLE);

                    BIBLabel.setVisibility(View.GONE);
                    hasilBIB.setVisibility(View.GONE);
                    kalkulasi.setText(getString(R.string.calculator_kalkulasi_label));

                    /*
                    ETBIBModalAwal.setText("");
                    ETBIBROR.setText("");
                    ETBIBTargetHasilInvestasi.setText("");
                    spinnerDurasiTahunBIB.setSelection(0);
                    spinnerDurasiBulanBIB.setSelection(0);
                    */

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

        if(Integer.parseInt(ETBIBTargetHasilInvestasi.getText().toString()) <= Integer.parseInt(ETBIBModalAwal.getText().toString())){
            /*
            rpLabel.setText("Target Hasil Investasi tidak lebih besar dari Modal Awal");
            hasilBIB.setVisibility(View.INVISIBLE);
            */
            hasilBIB.setVisibility(View.VISIBLE);
            hasilBIB.setText("Target Hasil Investasi tidak lebih besar dari Modal Awal");
        }else{
            hasilBIB.setText("Rp "+formatHasil);
            switch (numbOfTabs) {
                case 3:
                    if (spinnerDurasiBulanBIB.getSelectedItemPosition() == 0 && spinnerDurasiTahunBIB.getSelectedItemPosition() == 0) {
                        /*
                        rpLabel.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                        hasilBIB.setVisibility(View.INVISIBLE);
                        */
                        hasilBIB.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                        hasilBIB.setVisibility(View.VISIBLE);
                    } else {
                        //rpLabel.setText(getString(R.string.rp));
                        hasilBIB.setVisibility(View.VISIBLE);
                    }
                    break;

                case 4:
                    if (spinnerDurasiBulanBIB.getSelectedItemPosition() == 0 && spinnerDurasiTahunBIB.getSelectedItemPosition() == 0
                            || Double.parseDouble(ETBIBROR.getText().toString()) <= 0) {
                        /*
                        rpLabel.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                        hasilBIB.setVisibility(View.INVISIBLE);
                        */
                        hasilBIB.setVisibility(View.VISIBLE);
                        hasilBIB.setText(getString(R.string.durasi_tidak_boleh_kosong_dan_ror_tidak_boleh_bernilai_nol));
                    } else {
                        //rpLabel.setText(getString(R.string.rp));
                        hasilBIB.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }

    @Override
    public void resultOf(List<Product.ReksaDana> reksaDanaList) {

        /*
        List<String> reksadanaList = new ArrayList<String>();

        for (int i = 0; i < reksaDanaList.size(); i++) {
            reksadanaList.add(reksaDanaList.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_dropdown_item_1line, reksadanaList);
        Integer position = adapter.getPosition(selectedProdukReksadana);
        rorValue = reksaDanaList.get(position).getKinerja();
        selectedNamaReksadanaTV.setText(reksaDanaList.get(position).getName());

        try {
            String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, (reksaDanaList.get(position).getDate()));
            selectedTanggalReksadanaTV.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        selectedTipeReksadana.setText(reksaDanaList.get(position).getType());
        selectedNABUnitReksadanaTV.setText(reksaDanaList.get(position).getNab());
        */

    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onSessionExpired() {
        sessionExpired();
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