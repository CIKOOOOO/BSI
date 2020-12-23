package com.bca.bsi.ui.basenavigation.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.ProductTypeAdapter;
import com.bca.bsi.ui.basenavigation.products.detail.DetailProductActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;

public class ProductsFragment extends BaseFragment implements ProductTypeAdapter.objectOnClick {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_product_fragment);

        ProductTypeAdapter adapter = new ProductTypeAdapter(Constant.getProductTypeList(), Type.PRODUCT, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProductClick(int pos) {
        Intent intent = new Intent(mActivity, DetailProductActivity.class);
        intent.putExtra(DetailProductActivity.PRODUCT_TYPE, pos);
        startActivity(intent);
    }

    @Override
    public void onBtnSecondOnClick() {

    }
}