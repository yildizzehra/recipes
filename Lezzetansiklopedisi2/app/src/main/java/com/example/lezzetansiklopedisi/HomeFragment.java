package com.example.lezzetansiklopedisi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private ImageButton btnBesamelliTavuk;
    private ImageButton btnChurros;
    private ImageButton btnCilekliLimonata;
    private SearchView searchView;
    private static final int REQUEST_CODE = 100;
    private Map<String, Class<? extends Fragment>> recipeFragmentMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize buttons
        btnBesamelliTavuk = view.findViewById(R.id.btn_besamelli_tavuk);
        btnChurros = view.findViewById(R.id.btn_churros);
        btnCilekliLimonata = view.findViewById(R.id.btn_cilekli_limonata);
        searchView = view.findViewById(R.id.search_view);

        // Initialize recipeFragmentMap
        initializeRecipeFragmentMap();

        checkPermission();

        // Set click listeners
        btnBesamelliTavuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(BesamelliTavukFragment.class);
            }
        });

        btnChurros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(ChurrosFragment.class);
            }
        });

        btnCilekliLimonata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(CileklilimonataFragment.class);
            }
        });

        // Set up SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search action
                navigateToFragmentBasedOnQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optionally handle text changes for live filtering
                return true;
            }
        });

        return view;
    }
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                // Kamera işlemlerini başlatabilirsiniz
            } else {
                // Permission denied
                Toast.makeText(getActivity(), "Uygulamanın Doğru Çalışması İçin Bu izinler Gerekli!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeRecipeFragmentMap() {
        recipeFragmentMap = new HashMap<>();
        recipeFragmentMap.put("Besamel Soslu Tavuk", BesamelliTavukFragment.class);
        recipeFragmentMap.put("Churros", ChurrosFragment.class);
        recipeFragmentMap.put("Cilekli Limonata", CileklilimonataFragment.class);
        recipeFragmentMap.put("Cookie", CookieFragment.class);
        recipeFragmentMap.put("Coollime", CoollimeFragment.class);
        recipeFragmentMap.put("Creme Caramel", CremeCaramelFragment.class);
        recipeFragmentMap.put("Domates Corbasi", DomatesFragment.class);
        recipeFragmentMap.put("Domates Salatasi", DomatesSalatasiFragment.class);
        recipeFragmentMap.put("Donut", DonutFragment.class);
        recipeFragmentMap.put("Ezogelin Corbasi", EzogelinFragment.class);
        recipeFragmentMap.put("Firinda Top Kofte", FirindaKofteFragment.class);
        recipeFragmentMap.put("Firinda Hamsi", HamsiFragment.class);
        recipeFragmentMap.put("Humus", HumusFragment.class);
        recipeFragmentMap.put("Karadut Serbeti", KaradutFragment.class);
        recipeFragmentMap.put("Korili Tavuk", KoriliTavukFragment.class);
        recipeFragmentMap.put("Kuru Cacik", KuruCacikFragment.class);
        recipeFragmentMap.put("Kuru Fasulye", KurufasulyeFragment.class);
        recipeFragmentMap.put("Macaron", MacaronFragment.class);
        recipeFragmentMap.put("Mantar Corbasi", MantarFragment.class);
        recipeFragmentMap.put("Mercimek Corbasi", MercimekFragment.class);
        recipeFragmentMap.put("Mucver", MucverFragment.class);
        recipeFragmentMap.put("Muzlu Sut", MuzluSutFragment.class);
        recipeFragmentMap.put("Patates Dolgulu Patlican", PatatesPatlicanFragment.class);
        recipeFragmentMap.put("Patates Salatasi", PatatesSalatasiFragment.class);
        recipeFragmentMap.put("Saksuka", SaksukaFragment.class);
        recipeFragmentMap.put("Kajun Baharatli Somon", SomonFragment.class);
        recipeFragmentMap.put("Somon Sandvic", SomonSandvicFragment.class);
        recipeFragmentMap.put("Taco", TacoFragment.class);
        recipeFragmentMap.put("Tavuk Corbasi", TavukFragment.class);
        recipeFragmentMap.put("Tiryaki Soslu Durum", TiryakiDurumFragment.class);
        recipeFragmentMap.put("Visne Serbeti", VisneFragment.class);
        recipeFragmentMap.put("Waffle", WaffleFragment.class);
        recipeFragmentMap.put("Zencefilli Gazoz", ZencefilliGazozFragment.class);
    }

    private void navigateToFragment(Class<? extends Fragment> fragmentClass) {
        try {
            Fragment fragment = fragmentClass.getDeclaredConstructor().newInstance();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Bir hata oluştu", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToFragmentBasedOnQuery(String query) {
        Class<? extends Fragment> fragmentClass = recipeFragmentMap.get(query);
        if (fragmentClass != null) {
            navigateToFragment(fragmentClass);
        } else {
            Toast.makeText(getActivity(), "Sonuç bulunamadı", Toast.LENGTH_SHORT).show();
        }
    }
}
