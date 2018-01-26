package com.nfdaily.nfplus1.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfdaily.nfplus1.R;
import com.nfdaily.nfplus1.base.BaseFragment;

/**
 * Created by xilinch on 17-12-22.
 */

public class HomeMainFragment extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_homemain,container);
        }
        return view;

    }
}
