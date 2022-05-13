package com.example.pelaki.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerProfile extends FragmentStatePagerAdapter {
    public ViewPagerProfile(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Buy();
            case 1:
                return new Fragment_Post();
            default:
                return new Fragment_Buy();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Mua hàng";
                break;
            case 1:
                title = "Bài viết";
                break;
            default:
                title = "Mua hàng";
                break;

        }
        return title;
    }
}
