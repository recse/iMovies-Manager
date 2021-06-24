package com.example.imoviesmanager;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FavoritesViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public FavoritesViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new PeliculasFavoritasFragmento();
        } else {
            return new SeriesFavoritasFragmento();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.peliculas);
        } else {
            return context.getString(R.string.series);
        }
    }
}
