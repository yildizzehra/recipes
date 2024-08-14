package com.example.lezzetansiklopedisi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MezesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mezes, container, false);

        ImageButton btnSaksuka = view.findViewById(R.id.btn_saksuka);
        ImageButton btnKuruCacik = view.findViewById(R.id.btn_kurucacik);
        ImageButton btnHumus = view.findViewById(R.id.btn_humus);
        ImageButton btnMucver = view.findViewById(R.id.btn_mucver);
        ImageButton btnPatatesSalatasi = view.findViewById(R.id.btn_patates_salatasi);
        ImageButton btnDomatesSalatasi = view.findViewById(R.id.btn_domates_salatasi);




        btnSaksuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new SaksukaFragment());
            }
        });

        btnKuruCacik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new KuruCacikFragment());
            }
        });

        btnHumus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new HumusFragment());
            }
        });

        btnMucver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new MucverFragment());
            }
        });

        btnPatatesSalatasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new PatatesSalatasiFragment());
            }
        });

        btnDomatesSalatasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMezesDetailFragment(new DomatesSalatasiFragment());
            }
        });

        return view;
    }

    private void openMezesDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
