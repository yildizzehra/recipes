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


public class SoupsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soups, container, false);

        ImageButton btnMercimek = view.findViewById(R.id.btn_mercimek);
        ImageButton btnEzogelin = view.findViewById(R.id.btn_ezogelin1);
        ImageButton btnDomates = view.findViewById(R.id.btn_domates);
        ImageButton btnTavuk = view.findViewById(R.id.btn_tavuk);
        ImageButton btnMantar = view.findViewById(R.id.btn_mantar);



        btnMercimek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoupDetailFragment(new MercimekFragment());
            }
        });

        btnEzogelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoupDetailFragment(new EzogelinFragment());
            }
        });

        btnDomates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoupDetailFragment(new DomatesFragment());
            }
        });

        btnTavuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoupDetailFragment(new TavukFragment());
            }
        });

        btnMantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoupDetailFragment(new MantarFragment());
            }
        });

        return view;
    }

    private void openSoupDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
