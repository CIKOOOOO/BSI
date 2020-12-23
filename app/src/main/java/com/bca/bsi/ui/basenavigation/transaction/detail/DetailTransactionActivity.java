package com.bca.bsi.ui.basenavigation.transaction.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;

public class DetailTransactionActivity extends BaseActivity {

    public static final String TYPE = "type";
    public static final String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);
    }
}