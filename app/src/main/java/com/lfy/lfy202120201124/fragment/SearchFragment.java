package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.VideoAdapter;
import com.lfy.lfy202120201124.db.VideoDbHelper;
import com.lfy.lfy202120201124.entity.Video;

import java.util.List;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private RecyclerView recyclerView;
    private VideoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch video data from database
        VideoDbHelper dbHelper = new VideoDbHelper(getContext());
        List<Video> videoList = dbHelper.getAllVideos();

        if (videoList.isEmpty()) {
            Log.e(TAG, "No videos found in database");
        } else {
            Log.d(TAG, "Videos loaded from database: " + videoList.size());
        }

        // Set up adapter
        adapter = new VideoAdapter(getContext(), videoList, recyclerView);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Release adapter resources
        if (adapter != null) {
            adapter.releasePlayer();
        }
        // Release RecyclerView reference
        recyclerView = null;
    }

    public void switchToNextVideo() {
        if (adapter != null) {
            int nextPosition = (adapter.getCurrentPlayingPosition() + 1) % adapter.getItemCount();
            adapter.playVideo(nextPosition);
        }
    }

    public void switchToPreviousVideo() {
        if (adapter != null) {
            int previousPosition = adapter.getCurrentPlayingPosition() - 1;
            if (previousPosition < 0) {
                previousPosition = adapter.getItemCount() - 1;
            }
            adapter.playVideo(previousPosition);
        }
    }

    public void loadData() {
        // Fetch video data from database
        VideoDbHelper dbHelper = new VideoDbHelper(getContext());
        List<Video> videoList = dbHelper.getAllVideos();

        if (videoList.isEmpty()) {
            Log.e(TAG, "No videos found in database");
        } else {
            Log.d(TAG, "Videos loaded from database: " + videoList.size());
        }

        // Update adapter with new data
        if (adapter != null) {
            adapter.setVideoList(videoList); // Assuming VideoAdapter has a method setVideoList(List<Video>)
            adapter.notifyDataSetChanged();
        }
    }
}
