package com.example.lezzetansiklopedisi;

import android.annotation.SuppressLint;
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

import android.widget.ImageView;
public class DessertsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desserts, container, false);

        ImageButton btnWaffle = view.findViewById(R.id.btn_waffle);
        ImageButton btnDonut = view.findViewById(R.id.btn_donut);
        ImageButton btnCookie = view.findViewById(R.id.btn_cookie);
        ImageButton btnChurros = view.findViewById(R.id.btn_churros);
        ImageButton btnCremeCaramel = view.findViewById(R.id.btn_creme_caramel);
        ImageButton btnMacaron = view.findViewById(R.id.btn_macaron);


        btnWaffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new WaffleFragment());
            }
        });

        btnDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new DonutFragment());
            }
        });

        btnCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new CookieFragment());
            }
        });

        btnChurros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new ChurrosFragment());
            }
        });

        btnCremeCaramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new CremeCaramelFragment());
            }
        });

        btnMacaron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDessertDetailFragment(new MacaronFragment());
            }
        });

        return view;
    }

    private void openDessertDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
