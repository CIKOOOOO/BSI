package com.bca.bsi.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.utils.BaseActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginCallback {

    private EditText etBCAID;
    private TextInputLayout tilPassword;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiVar();
    }

    private void intiVar() {
        Button btnLogin = findViewById(R.id.btn_login);

        etBCAID = findViewById(R.id.et_bca_id);
        tilPassword = findViewById(R.id.til_password);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.setCallback(this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String bcaId = etBCAID.getText().toString().trim();
                String pass = tilPassword.getEditText().getText().toString().trim();

//                if (bcaId.isEmpty() || pass.isEmpty()) {
//                    Toast.makeText(this, "BCA ID atau password kosong", Toast.LENGTH_SHORT).show();
//                } else {
                viewModel.loginWith(bcaId, pass);
//                }
                break;
        }
    }

    /*
     * Condition when user success login
     * */
    @Override
    public void onSuccess() {
        finishAffinity();
        startActivity(new Intent(this, BaseNavigationActivity.class));
    }

    /*
     * Condition when user failed login
     * */
    @Override
    public void onFailed() {

    }
}