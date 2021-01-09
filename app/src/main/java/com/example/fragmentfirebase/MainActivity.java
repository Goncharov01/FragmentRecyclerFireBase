package com.example.fragmentfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fragmentfirebase.databinding.ActivityMainBinding;
import com.example.fragmentfirebase.homeFragment.HomeFragment;
import com.example.fragmentfirebase.messageFragment.MessageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                    switch (item.getItemId()) {

                        case R.id.messageMenu:
                            fragment = new MessageFragment();
                            loadFragment(fragment);
                            return true;

                        case R.id.homeMenu:
                            fragment = new HomeFragment();
                            loadFragment(fragment);
                            return true;

                        case R.id.dashboardMenu:
                            fragment = new RecyclerFragment();
                            loadFragment(fragment);
                            return true;
                    }

                    return false;
                }
            };

    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

}