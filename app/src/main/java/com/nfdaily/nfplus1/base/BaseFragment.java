package com.nfdaily.nfplus1.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by xilinch on 17-12-22.
 */

public class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
