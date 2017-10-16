package com.thenosyman.stockman.stockman;

import android.support.v4.app.Fragment;

import com.thenosyman.stockman.stockman.activities.SingleFragment;
import com.thenosyman.stockman.stockman.fragments.SecuritySummaryFragment;

public class MainActivity extends SingleFragment {

    private final static String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        return SecuritySummaryFragment.newInstance();
    }

    @Override
    protected int getFragmentId() {
        return R.id.security_summary;
    }

}
