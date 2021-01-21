package com.bca.bsi.ui.basenavigation.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseFragment;

public class ProfileFragment extends BaseFragment {

    ImageButton settingsButton;
    TextView tvProfilNama, tvProfilRisiko, tvBcaId, tvEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // menuju setting
        settingsButton = view.findViewById(R.id.img_btn_setting);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        // profil user
        tvProfilNama = view.findViewById(R.id.tv_profil_nama);
        tvProfilRisiko = view.findViewById(R.id.tv_profil_risiko);
        tvBcaId = view.findViewById(R.id.tv_bca_id);
        tvEmail = view.findViewById(R.id.tv_email);
        tvProfilNama.setText(prefConfig.getName());
        tvProfilRisiko.setText(prefConfig.getProfileRisiko());
        tvBcaId.setText(prefConfig.getBCAID());
        tvEmail.setText(prefConfig.getEmail());
    }
}