package com.vankhai.weather.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vankhai.weather.R;


public class LoadingShimmerFragment extends Fragment {

    public LoadingShimmerFragment() {
    }

    public static LoadingShimmerFragment newInstance() {
        LoadingShimmerFragment fragment = new LoadingShimmerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_shimmer, container, false);
    }
}