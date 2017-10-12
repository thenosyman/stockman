package com.thenosyman.stockman.stockman.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.thenosyman.stockman.stockman.R;

public abstract class SingleFragment extends AppCompatActivity {

    protected abstract Fragment createFragment();

    protected abstract int getFragmentId();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment_container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(getFragmentId());
            if (fragment == null) {
                fragment = createFragment();
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }
    }
}
