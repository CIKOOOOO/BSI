package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana.DetailReksaDanaActivity;
import com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction.DetailProductTransactionActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;

import java.util.List;


public class ReksadanaProductFragment extends BaseFragment implements IReksaDanaProductCallback, ReksadanaProductAdapter.onItemClick {

    private ReksadanaProductViewModel viewModel;
    private ReksadanaProductAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reksadana_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etSearchProduct = view.findViewById(R.id.et_input_search);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_product_reksa_dana);

        adapter = new ReksadanaProductAdapter(this);

        viewModel = new ViewModelProvider(this).get(ReksadanaProductViewModel.class);

        viewModel.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getReksaDanaList(prefConfig.getTokenUser(), prefConfig.getProfileRisiko());

        etSearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void resultOf(List<Product.ReksaDana> reksaDanaList) {
        adapter.setReksaDanaList(reksaDanaList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onReksaDanaClick(Product.ReksaDana reksaDana) {
        Intent intent = new Intent(mActivity, DetailProductTransactionActivity.class);
        intent.putExtra(DetailProductTransactionActivity.PRODUCT_TYPE, Type.REKSA_DANA);
        intent.putExtra(DetailProductTransactionActivity.SALES_TYPE, Type.PURCHASING);
        intent.putExtra(DetailProductTransactionActivity.DATA, Utils.toJSON(reksaDana));
        startActivity(intent);
    }

    @Override
    public void onDetail(Product.ReksaDana reksaDana) {
        Intent intent = new Intent(mActivity, DetailReksaDanaActivity.class);
        intent.putExtra(DetailReksaDanaActivity.REKSA_DANA_ID, reksaDana.getReksadanaID());
        startActivity(intent);
    }
}