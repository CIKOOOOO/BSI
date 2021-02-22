package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.model.User;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DetailProductTransactionActivity extends BaseActivity implements IDetailProductTransactionCallback, View.OnClickListener {

    public static final String PRODUCT_TYPE = "type";
    public static final String SALES_TYPE = "sales_type";
    public static final String DATA = "data";
    public static final String NOMINAL_PEMBELIAN = "nominal_pembelian";

    private Product.ReksaDana reksaDana;
    private TextView tvSaldo;
    private DetailProductTransactionViewModel viewModel;
    private EditText etNominalPembelian;
    private Product.DetailReksaDana detailReksaDana;
    private CheckBox cbPaymentType;
    private ProductNameDetailTransactionAdapter productNameDetailTransactionAdapter;
    private User.BCAUser.Rekening rekening;
    private List<ProductRekomen> productRekomenList;

    private String productType, salesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_transaction);
        initVar();
    }

    private void initVar() {
        TextView tvAccountNumber = findViewById(R.id.tv_nomor_rekening_detail_transaction);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        Button btnNext = findViewById(R.id.btn_next_detail_transaction);
        TextView toc = findViewById(R.id.ketentuan_pembelian);
        RecyclerView recycler_product_name = findViewById(R.id.recycler_product_name_detail_product_transaction);

        cbPaymentType = findViewById(R.id.cb_berkala_detail_transaction);

        toc.setOnClickListener(this);

        tvSaldo = findViewById(R.id.tv_saldo_detail_transaction);
        etNominalPembelian = findViewById(R.id.et_nominal_pembelian_detail_transaction);

        Gson gson = new Gson();

        productNameDetailTransactionAdapter = new ProductNameDetailTransactionAdapter();
        productRekomenList = new ArrayList<>();

        recycler_product_name.setLayoutManager(new LinearLayoutManager(this));
        recycler_product_name.setAdapter(productNameDetailTransactionAdapter);

        viewModel = new ViewModelProvider(this).get(DetailProductTransactionViewModel.class);
        viewModel.setCallback(this);

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PRODUCT_TYPE) && intent.hasExtra(SALES_TYPE)
                && intent.hasExtra(DATA)) {
            productType = intent.getStringExtra(PRODUCT_TYPE);
            salesType = intent.getStringExtra(SALES_TYPE);
            String product = intent.getStringExtra(DATA);

            tvAccountNumber.setText(prefConfig.getAccountNumber());

            if (productType.equals(Type.REKSA_DANA)) {
//                Product.ProductTransaction productTransaction = new Product.ProductTransaction(this.reksaDana.getName(), this.reksaDana.getDate(), this.reksaDana.getNab());
//
//                List<Product.ProductTransaction> productTransactionList = new ArrayList<>();
//                productTransactionList.add(productTransaction);
//
//                Log.e("asd",this.reksaDana.getName());
//
//                productNameDetailTransactionAdapter.setProductTransactions(productTransactionList);
//                productNameDetailTransactionAdapter.notifyDataSetChanged();

                Log.e("asd", product);

                viewModel.loadDetailTransaksi(prefConfig.getTokenUser(), prefConfig.getAccountNumber(), product);
            } else if (productType.equals(Type.PURCHASING_WITH_SMARTBOT)) {
                // Here to retrieve data from smartbot
                // Data : berapa persen pembagian setiap produk + list of product
                String nominalPembelian = intent.getStringExtra(NOMINAL_PEMBELIAN);
                productRekomenList = intent.getParcelableArrayListExtra(DATA);
                if (productRekomenList != null) {
                    List<Product.ProductTransaction> productTransactions = new ArrayList<>();
                    for (ProductRekomen productRekomen : productRekomenList) {
                        String updateDate = null;
                        try {
                            updateDate = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, productRekomen.getLastDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Product.ProductTransaction productTransaction = new Product.ProductTransaction(productRekomen.getProductName(), updateDate, productRekomen.getNab());
                        productTransactions.add(productTransaction);
                    }
                    productNameDetailTransactionAdapter.setProductTransactions(productTransactions);
                    productNameDetailTransactionAdapter.notifyDataSetChanged();
                    viewModel.loadDetailTransaksi(prefConfig.getTokenUser(), prefConfig.getAccountNumber(), productRekomenList.get(0).getReksaId());

                    etNominalPembelian.setText(Utils.formatUang3(Double.parseDouble(nominalPembelian)));
                    etNominalPembelian.setEnabled(false);
                } else {
                    onBackPressed();
                }
            }

            if (salesType.equals(Type.PURCHASING)) {
                tvTitleToolbar.setText(getString(R.string.purchasing));
            }
        }

        imgBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_next_detail_transaction:
                String nominal = etNominalPembelian.getText().toString().trim();
                Intent intent2 = getIntent();
                if (productType.equals(Type.PURCHASING_WITH_SMARTBOT) && null != intent2)
                    nominal = intent2.getStringExtra(NOMINAL_PEMBELIAN);
                if (nominal.isEmpty()) {
                    showSnackBar("Mohon isi nominal pembelian");
                } else if (this.detailReksaDana == null) {
                    onResume();
                } else if (this.rekening == null) {
                    onResume();
                } else if (this.rekening.getStatusPembelianPertama().equals("TRUE") && Double.parseDouble(this.detailReksaDana.getMinimumPembelianPertama()) > Double.parseDouble(nominal)) {
                    showSnackBar("Jumlah nominal pembelian lebih kecil daripada minimum pembelian pertama");
                } else if (this.rekening.getStatusPembelianPertama().equals("FALSE") && Double.parseDouble(this.detailReksaDana.getMinimumPembelianBerikut()) > Double.parseDouble(nominal)) {
                    showSnackBar("Jumlah nominal pembelian lebih kecil daripada minimum pembelian");
                } else {
                    String type = cbPaymentType.isChecked() ? Type.PEMBELIAN_BERKALA : Type.PEMBELIAN_SEKALI_BAYAR;
                    if (productType.equals(Type.REKSA_DANA)) {
                        String biayaProdukPembelian = detailReksaDana.getBiayaPembelian().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPembelian() : String.valueOf(Double.parseDouble(detailReksaDana.getBiayaPembelian()));
                        double biayaPembelian = (Double.parseDouble(biayaProdukPembelian) / 100) * Double.parseDouble(nominal);
                        double reksaDanaUnit = Double.parseDouble(nominal) / Double.parseDouble(this.detailReksaDana.getNabPerUnit());

                        Transaction.Purchasing purchasing = new Transaction.Purchasing();
                        purchasing.setTransactionType(Type.PURCHASING);
                        purchasing.setAmount(nominal);
                        purchasing.setPaymentType(type);
                        purchasing.setNominalBiayaPembelian(String.format("%.2f", biayaPembelian));
                        purchasing.setReksaDanaID(this.detailReksaDana.getReksadanaID());
                        purchasing.setReksaDanaUnit(String.valueOf(reksaDanaUnit));
                        purchasing.setNab(String.valueOf(this.detailReksaDana.getNabPerUnit()));

                        Intent intent = new Intent(this, ConfirmationTransactionActivity.class);
                        intent.putExtra(ConfirmationTransactionActivity.DATA_TRANSACTION, Utils.toJSON(purchasing));
                        intent.putExtra(ConfirmationTransactionActivity.DATA_REKSA_DANA, Utils.toJSON(this.detailReksaDana));
                        intent.putExtra(ConfirmationTransactionActivity.CONFIRMATION_TYPE, "");
                        startActivity(intent);
                    } else if (productType.equals(Type.PURCHASING_WITH_SMARTBOT)) {
                        List<Transaction.Purchasing> purchasingList = new ArrayList<>();

                        for (ProductRekomen productRekomen : this.productRekomenList) {
                            productRekomen.setBiayaPembelian("1");
//                            Log.e("asd", productRekomen.getPercentage() + "");
                            double nominalProduk = Double.parseDouble(nominal) * Double.parseDouble(productRekomen.getPercentage()) / 100;
                            String biayaProdukPembelian = productRekomen.getBiayaPembelian().substring(0, 1).equals(".") ? "0" + productRekomen.getBiayaPembelian() : String.valueOf(Double.parseDouble(productRekomen.getBiayaPembelian()));
                            double biayaPembelian = (Double.parseDouble(biayaProdukPembelian) / 100) * nominalProduk;
                            double reksaDanaUnit = nominalProduk / Double.parseDouble(productRekomen.getNab());

                            Transaction.Purchasing purchasing = new Transaction.Purchasing();
                            purchasing.setTransactionType(Type.PURCHASING);
                            purchasing.setAmount(String.valueOf(nominalProduk));
                            purchasing.setPaymentType(type);
                            purchasing.setNominalBiayaPembelian(String.format("%.2f", biayaPembelian));
                            purchasing.setReksaDanaID(productRekomen.getReksaId());
                            purchasing.setReksaDanaUnit(String.valueOf(reksaDanaUnit));
                            purchasing.setNab(String.valueOf(productRekomen.getNab()));

                            purchasingList.add(purchasing);
                        }

                        Intent intent = new Intent(this, ConfirmationTransactionActivity.class);
                        intent.putParcelableArrayListExtra(ConfirmationTransactionActivity.DATA_TRANSACTION, (ArrayList<? extends Parcelable>) purchasingList);
                        intent.putParcelableArrayListExtra(ConfirmationTransactionActivity.DATA_REKSA_DANA, (ArrayList<? extends Parcelable>) this.productRekomenList);
                        intent.putExtra(ConfirmationTransactionActivity.CONFIRMATION_TYPE, Type.PURCHASING_WITH_SMARTBOT);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.ketentuan_pembelian:

                break;
        }
    }

    @Override
    public void loadSaldo(User.BCAUser.Rekening bcaUser, Product.DetailReksaDana detailReksaDana) {
        this.detailReksaDana = detailReksaDana;
        this.rekening = bcaUser;
        Log.e("asd", detailReksaDana.getName() + " " + productType);

        if (!productType.equals(Type.PURCHASING_WITH_SMARTBOT)) {
            String updateDate = null;
            try {
                updateDate = Utils.formatDateFromDateString(Constant.DATE_FORMAT_FROM_DB, Constant.DATE_FORMAT_2, this.detailReksaDana.getUpdateDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.e("asd", updateDate);

            List<Product.ProductTransaction> productTransactions = new ArrayList<>();
            productTransactions.add(new Product.ProductTransaction(detailReksaDana.getName(), updateDate, detailReksaDana.getNabPerUnit()));

            productNameDetailTransactionAdapter.setProductTransactions(productTransactions);
            productNameDetailTransactionAdapter.notifyDataSetChanged();
        }

        tvSaldo.setText("Rp " + Utils.priceFormat(Double.parseDouble(bcaUser.getSaldo())));
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}