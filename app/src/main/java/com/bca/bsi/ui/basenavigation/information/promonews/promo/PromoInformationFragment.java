package com.bca.bsi.ui.basenavigation.information.promonews.promo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.promonews.PromoNewsAdapter;
import com.bca.bsi.ui.basenavigation.information.promonews.detail.DetailPromoNewsActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;

import java.util.List;

public class PromoInformationFragment extends BaseFragment implements IPromoInformationCallback, PromoNewsAdapter.onItemClick {

    private PromoInformationViewModel viewModel;
    private PromoNewsAdapter promoNewsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_promo_info);

        promoNewsAdapter = new PromoNewsAdapter(this);

        viewModel = new ViewModelProvider(this).get(PromoInformationViewModel.class);
        viewModel.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(promoNewsAdapter);

        viewModel.getPromoList();
    }

    @Override
    public void resultOf(List<PromoNews> promoNews) {
        this.promoNewsAdapter.setPromoNewsList(promoNews);
        this.promoNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onObjectClick(PromoNews promoNews) {
        String data = Utils.toJSON(promoNews);
        Intent intent = new Intent(mActivity, DetailPromoNewsActivity.class);
        intent.putExtra(DetailPromoNewsActivity.DATA, data);
//        mActivity.startActivity(intent);
    }
}