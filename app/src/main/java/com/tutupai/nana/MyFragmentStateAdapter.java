package com.tutupai.nana;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        notifyItemInserted(fragmentList.size() - 1);
    }

    public Fragment getFragment(int position) {
        return fragmentList.get(position);
    }
}
