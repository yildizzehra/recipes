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

public class MainDishesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_dishes, container, false);

        ImageButton btnPatatesPatlican = view.findViewById(R.id.btn_patates_patlican);
        ImageButton btnFirindaKofte = view.findViewById(R.id.btn_firinda_kofte);
        ImageButton btnKurufasulye = view.findViewById(R.id.btn_kurufasulye);
        ImageButton btnTaco = view.findViewById(R.id.btn_taco);
        ImageButton btnSomon = view.findViewById(R.id.btn_somon);
        ImageButton btnHamsi = view.findViewById(R.id.btn_hamsi);
        ImageButton btnSomonSandvic = view.findViewById(R.id.btn_somon_sandvic);
        ImageButton btnTiryakiDurum = view.findViewById(R.id.btn_tiryaki_durum);
        ImageButton btnKoriliTavuk = view.findViewById(R.id.btn_korili_tavuk);
        ImageButton btnBesamelliTavuk = view.findViewById(R.id.btn_besamelli_tavuk);



        btnPatatesPatlican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new PatatesPatlicanFragment());
            }
        });

        btnFirindaKofte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new FirindaKofteFragment());
            }
        });

        btnKurufasulye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new KurufasulyeFragment());
            }
        });

        btnTaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ openMainDishDetailFragment(new TacoFragment());
            }
        });

        btnSomon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new SomonFragment());
            }
        });

        btnHamsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new HamsiFragment());
            }
        });

        btnSomonSandvic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new SomonSandvicFragment());
            }
        });

        btnTiryakiDurum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new TiryakiDurumFragment());
            }
        });

        btnKoriliTavuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new KoriliTavukFragment());
            }
        });

        btnBesamelliTavuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDishDetailFragment(new BesamelliTavukFragment());
            }
        });

        return view;
    }

    private void openMainDishDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
