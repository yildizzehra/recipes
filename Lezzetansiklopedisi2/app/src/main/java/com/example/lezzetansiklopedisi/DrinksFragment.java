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


public class DrinksFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);

        ImageButton btnKaradut = view.findViewById(R.id.btn_karadut);
        ImageButton btnVisne = view.findViewById(R.id.btn_visne);
        ImageButton btnCilekliLimonata = view.findViewById(R.id.btn_cilekli_limonata);
        ImageButton btnCoollime = view.findViewById(R.id.btn_coollime);
        ImageButton btnMuzluSut = view.findViewById(R.id.btn_muzlusut);
        ImageButton btnZencefilliGazoz = view.findViewById(R.id.btn_zencefilli_gazoz);



        btnKaradut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new KaradutFragment());
            }
        });

        btnVisne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new VisneFragment());
            }
        });

        btnCilekliLimonata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new CileklilimonataFragment());
            }
        });

        btnCoollime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new CoollimeFragment());
            }
        });

        btnMuzluSut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new MuzluSutFragment());
            }
        });

        btnZencefilliGazoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrinkDetailFragment(new ZencefilliGazozFragment());
            }
        });

        return view;
    }

    private void openDrinkDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
