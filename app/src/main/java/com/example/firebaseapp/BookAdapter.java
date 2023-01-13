package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.AbstractList;

public class BookAdapter extends FragmentStateAdapter {

    AbstractList<Fragment> fragments;
    public BookAdapter(@NonNull FragmentActivity fragmentActivity, AbstractList<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments=fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
