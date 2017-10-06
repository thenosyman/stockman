package com.thenosyman.stockman.stockman.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thenosyman.stockman.stockman.R;

public class SecuritySummaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_security_summary, container, false);
    }

    public static Fragment newInstance(){
        return new SecuritySummaryFragment();
    }
}
