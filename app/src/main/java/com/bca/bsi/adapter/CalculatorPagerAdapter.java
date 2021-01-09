package com.bca.bsi.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bca.bsi.ui.basenavigation.more.calculatormore.besarhasilinvestasi.BesarHasilInvestasiFragment;
import com.bca.bsi.ui.basenavigation.more.calculatormore.besarinvastasibulanan.BesarInvestasiBulananFragment;
import com.bca.bsi.ui.basenavigation.more.calculatormore.besarror.BesarRoRFragment;
import com.bca.bsi.ui.basenavigation.more.calculatormore.durasiinvestasi.DurasiInvestasiFragment;

public class CalculatorPagerAdapter extends FragmentPagerAdapter {

    private int numbOfTabs;
    private String rorValue;

    public CalculatorPagerAdapter(FragmentManager fm, int numbOfTabs, String rorValue) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numbOfTabs = numbOfTabs;
        this.rorValue = rorValue;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BesarInvestasiBulananFragment(numbOfTabs, rorValue);
            case 1:
                return new BesarHasilInvestasiFragment(numbOfTabs, rorValue);
            case 2:
                return new DurasiInvestasiFragment(numbOfTabs, rorValue);
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
