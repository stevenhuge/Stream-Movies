package com.example.movieapi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movieapi.tab.tab1;
import com.example.movieapi.tab.tab2;
import com.example.movieapi.tab.tab3;
import com.example.movieapi.tab.tab4;

public class tabAdapter extends FragmentStatePagerAdapter {
    int numTab;
    public tabAdapter(@NonNull FragmentManager fm, int numTab) {
        super(fm);
        this.numTab=numTab;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
            case 3:
                return new tab4();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
