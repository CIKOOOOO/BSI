package com.bca.bsi.ui.basenavigation.more.calculator.popup;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan.BesarInvestasiBulananFragment;
import com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan.IBesarInvestasiBulananCallback;
import com.bca.bsi.utils.PrefConfig;
import com.bca.bsi.utils.Utils;

import java.util.List;

public class ProductsPopUpDialog extends DialogFragment implements IProductsCalculatorCallback, ProductsCalculatorAdapter.onItemClick {

    private TextView tvToolbarSubtitle;
    private TextView tvToolbarTitle;
    private ImageButton backButton;
    private RecyclerView recyclerView;
    private ProductsCalculatorViewModel viewModel;
    private ProductsCalculatorAdapter adapter;
    private PrefConfig prefConfig;
    private onDataRetrieved onDataRetrieved;

    public interface onDataRetrieved{
        void onDataArrived(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances);
    }

    public void setOnDataRetrieved(ProductsPopUpDialog.onDataRetrieved onDataRetrieved) {
        this.onDataRetrieved = onDataRetrieved;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_products_pop_up, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvToolbarSubtitle = view.findViewById(R.id.tv_child_toolbar_back);
        tvToolbarTitle = view.findViewById(R.id.tv_title_toolbar_back);
        backButton = view.findViewById(R.id.img_btn_back_toolbar);
        recyclerView = view.findViewById(R.id.recycler_produk_choice_main);

        prefConfig = new PrefConfig(view.getContext());

        backButton.setVisibility(View.GONE);
        tvToolbarTitle.setText("PILIH PRODUK REKSA DANA");
        tvToolbarSubtitle.setText("PILIH PRODUK REKSA DANA");
        tvToolbarSubtitle.setVisibility(View.GONE);


        viewModel = new ViewModelProvider(this).get(ProductsCalculatorViewModel.class);
        viewModel.setCallbackProduct(this);
        //viewModel.setCallbackBesarInvestasi(this);
        viewModel.loadProducts(prefConfig.getProfileRisiko(), prefConfig.getTokenUser());


        adapter = new ProductsCalculatorAdapter((ProductsCalculatorAdapter.onItemClick) this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    @Override
    public void onLoadData(List<Product.ReksaDana> products) {
        System.out.println("SIZE: " + products.size());
        System.out.println("INI MASUK ONLOAD DATA: "+products.get(1).getName());
        adapter.setProductsCalculatorAdapter(products);
        recyclerView.setAdapter(adapter);
        System.out.println("SIZE DALAM ADAPTER DI DIALOG: " + adapter.getProducts().size());
    }


    @Override
    public void onReksaDanaClick(Product.ReksaDana reksaDana) {
        System.out.println("INI YANG DIPILIHHHHHHH : "+reksaDana.toString());
        viewModel.loadDetailReksaDana(prefConfig.getTokenUser(), Integer.parseInt(reksaDana.getReksadanaID()));
        //viewModel.retrieveDetailReksaDana(prefConfig.getTokenUser(), Integer.parseInt(reksaDana.getReksadanaID()));
    }

    @Override
    public void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances) {
        /*
        Bundle args = new Bundle();
        args.putString("selectedProductReksadanaDetail", Utils.toJSON(detailReksaDana));
        BesarInvestasiBulananFragment newFragment = new BesarInvestasiBulananFragment (3,detailReksaDana);
        newFragment.setArguments(args);
        */
        onDataRetrieved.onDataArrived(detailReksaDana, performances);
        System.out.println("SELECTED REKSADANA DETAIL ROR VALUE : "+ detailReksaDana.getKinerja1Tahun()+ " "+detailReksaDana.getName());
//        this.dismiss();
    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onFail(String msg) {
        System.out.println("INI ERROR ONFAIL : "+msg);
    }
}
