package com.bca.bsi.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.besarhasilinvestasi.BesarHasilInvestasiFragment;
import com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan.BesarInvestasiBulananFragment;
import com.bca.bsi.ui.basenavigation.more.calculator.besarror.BesarRoRFragment;
import com.bca.bsi.ui.basenavigation.more.calculator.durasiinvestasi.DurasiInvestasiFragment;

public class CalculatorPagerAdapter extends FragmentPagerAdapter {

    private int numbOfTabs;
    private String rorValue;
    private String selectedProdukReksadana;
    private Product.DetailReksaDana selectedDetailReksadana;

    public CalculatorPagerAdapter(FragmentManager fm, int numbOfTabs, String rorValue, String selectedProdukReksadana, Product.DetailReksaDana selectedDetailReksadana) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numbOfTabs = numbOfTabs;
        this.rorValue = rorValue;
        this.selectedProdukReksadana = selectedProdukReksadana;
        this.selectedDetailReksadana = selectedDetailReksadana;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BesarInvestasiBulananFragment(numbOfTabs, selectedDetailReksadana);
            case 1:
                return new BesarHasilInvestasiFragment(numbOfTabs, selectedDetailReksadana);
            case 2:
                return new DurasiInvestasiFragment(numbOfTabs, selectedDetailReksadana);
            case 3:
                return new BesarRoRFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
