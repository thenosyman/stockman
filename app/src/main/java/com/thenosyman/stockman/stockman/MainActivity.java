package com.thenosyman.stockman.stockman;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thenosyman.stockman.stockman.activities.SingleFragment;
import com.thenosyman.stockman.stockman.fragments.SecuritySummaryFragment;

public class MainActivity extends SingleFragment {

    @Override
    protected Fragment createFragment() {
        return SecuritySummaryFragment.newInstance();
    }

    @Override
    protected int getFragmentId() {
        return R.id.security_summary;
    }
}
