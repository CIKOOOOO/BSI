package com.bca.bsi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bca.bsi.ui.basenavigation.products.detail.reksadana.calculatorfragment.BesarHasilInvestasiCalProdFragment;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.calculatorfragment.BesarInvestasiBulananCalProdFragment;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.calculatorfragment.DurasiInvestasiCalProdFragment;

public class CalculatorProductPagerAdapter extends FragmentPagerAdapter {
    private  int numbOfTabs;

    public CalculatorProductPagerAdapter(FragmentManager fm, int numbOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BesarInvestasiBulananCalProdFragment();
            case 1:
                return new BesarHasilInvestasiCalProdFragment();
            case 2:
                return new DurasiInvestasiCalProdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
