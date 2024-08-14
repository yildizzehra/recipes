package com.example.lezzetansiklopedisi;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Üstteki butonları tanımla
        ImageButton buttonSoups = findViewById(R.id.button_soups);
        ImageButton buttonMainDishes = findViewById(R.id.button_main_dishes);
        ImageButton buttonMezes = findViewById(R.id.button_mezes);
        ImageButton buttonDesserts = findViewById(R.id.button_desserts);
        ImageButton buttonDrinks = findViewById(R.id.button_drinks);

        // Buton tıklamalarını ayarla
        buttonSoups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SoupsFragment());
            }
        });

        buttonMainDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MainDishesFragment());
            }
        });

        buttonMezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MezesFragment());
            }
        });

        buttonDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new DessertsFragment());
            }
        });

        buttonDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new DrinksFragment());
            }
        });

        // İlk yüklenen fragment
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.navigation_list) {
                    selectedFragment = new ListFragment();
                } else if (itemId == R.id.navigation_person) {
                    selectedFragment = new PersonFragment();
                }
                return loadFragment(selectedFragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Bu ID'yi `activity_main.xml`'deki `FrameLayout` ile eşleştirmeniz gerekiyor.
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }
}
