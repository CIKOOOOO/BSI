package com.bca.bsi.ui.basenavigation.more.CalculatorMore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bca.bsi.R;

public class DurasiInvestasiFragment extends Fragment {

    public DurasiInvestasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_durasi_investasi, container, false);
    }
}