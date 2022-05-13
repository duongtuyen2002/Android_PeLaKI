package com.example.pelaki;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pelaki.Fragment.FoodFragment;
import com.example.pelaki.Fragment.HomeFragment;
import com.example.pelaki.Fragment.NotificationFragment;
import com.example.pelaki.Fragment.ProfileFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        BadgeDrawable badgeDrawable = bottomNav.getOrCreateBadge(R.id.nav_thongbao);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(10);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment  = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_food:
                    selectedFragment = new FoodFragment();
                    break;
                case R.id.nav_thongbao:
                    selectedFragment = new NotificationFragment();
                    break;
                case R.id.nav_frofile:
                    selectedFragment = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };
}