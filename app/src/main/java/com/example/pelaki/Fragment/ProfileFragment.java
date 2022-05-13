package com.example.pelaki.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pelaki.Profile.ViewPagerProfile;
import com.example.pelaki.R;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);

        tabLayout = view.findViewById(R.id.tab_profile);
        viewPager = view.findViewById(R.id.viewpager_profile);

        ViewPagerProfile adapter = new ViewPagerProfile(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
