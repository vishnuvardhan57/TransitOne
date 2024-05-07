package com.example.transitone;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.transitone.databinding.ActivityDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Enable edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle bottom navigation item selection
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    replaceFragment(new home());
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    replaceFragment(new profile());
                    return true;
                } else {
                    return false;
                }
            }
        });

        binding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_route) {
                    replaceFragment(new route());
                    return true;
                } else if (item.getItemId() == R.id.nav_fav) {
                    replaceFragment(new favorites());
                } else if (item.getItemId() == R.id.nav_notify) {
                    replaceFragment(new notification());
                } else if (item.getItemId() == R.id.nav_feed) {
                    replaceFragment(new feedback());
                } else if (item.getItemId() == R.id.nav_about) {
                    replaceFragment(new about());
                } else {
                    return false;
                }
            return true;
            }
        });


        // Initially display the home fragment
        replaceFragment(new home());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}


