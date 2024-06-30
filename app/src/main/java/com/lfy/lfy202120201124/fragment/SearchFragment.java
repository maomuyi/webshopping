package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.VideoPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private ViewPager2 viewPager;
    private VideoPagerAdapter videoPagerAdapter;
    private List<String> videoPaths;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        viewPager = view.findViewById(R.id.view_pager);

        // Add video paths
        videoPaths = Arrays.asList(
                "android.resource://" + getActivity().getPackageName() + "/" + R.raw.v1,
                "android.resource://" + getActivity().getPackageName() + "/" + R.raw.v2,
                "android.resource://" + getActivity().getPackageName() + "/" + R.raw.v3,
                "android.resource://" + getActivity().getPackageName() + "/" + R.raw.v4
        );

        videoPagerAdapter = new VideoPagerAdapter(this, videoPaths);
        viewPager.setAdapter(videoPagerAdapter);

        // Set current item to the middle to enable infinite scrolling
        viewPager.setCurrentItem(videoPagerAdapter.getInitialPosition(), false);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int realPosition = videoPagerAdapter.getRealPosition(position);
                if (position != realPosition) {
                    viewPager.setCurrentItem(realPosition, false);
                }
            }
        });

        return view;
    }
}
