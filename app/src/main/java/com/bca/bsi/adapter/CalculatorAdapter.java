package com.bca.bsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bca.bsi.R;

public class CalculatorAdapter extends PagerAdapter {

    private int numberOfTabs;
    private LayoutInflater layoutInflater;
    private Context context;

    public int getNumberOfTabs() {
        return numberOfTabs;
    }

    public void setNumberOfTabs(int numberOfTabs) {
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    public CalculatorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.recycler_calculator,container,false);

        TextView desc = view.findViewById(R.id.tv_desc);
        TextView tvTargetHasilInvestasi = view.findViewById(R.id.tv_target_hasil_investasi);
        //EditText

        /*
        switch (position){
            case 0:
                tvHadir.setText("tabe Hadir, tab ke-"+(position+1));
                tvNihil.setVisibility(View.GONE);
                break;
            case 1:
                tvHadir.setText("tabe Hadir, tab ke-"+(position+1));
                tvNihil.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvHadir.setText("tabe Hadir, tab ke-"+(position+1));
                tvNihil.setVisibility(View.GONE);
                break;
            case 3:
                tvHadir.setText("tabe Hadir, tab ke-"+(position+1));
                tvNihil.setVisibility(View.VISIBLE);
                break;
        }
        */

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
