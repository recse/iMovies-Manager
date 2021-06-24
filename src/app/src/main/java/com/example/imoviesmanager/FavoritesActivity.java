package com.example.imoviesmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class FavoritesActivity extends AppCompatActivity {

    ViewPager vpFavoritesActivity;
    TabLayout tabLayoutFavoritesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        vpFavoritesActivity = findViewById(R.id.view_pager_favorites_activity);
        FavoritesViewPagerAdapter favoritesViewPagerAdapter = new FavoritesViewPagerAdapter(this, getSupportFragmentManager());
        vpFavoritesActivity.setAdapter(favoritesViewPagerAdapter);

        tabLayoutFavoritesActivity = findViewById(R.id.tabs_favorites_activity);
        tabLayoutFavoritesActivity.setupWithViewPager(vpFavoritesActivity);

    }
}
