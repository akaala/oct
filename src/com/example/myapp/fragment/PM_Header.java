package com.example.myapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapp.R;

public class PM_Header extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** * Inflate the layout for this fragment */
        return inflater.inflate(R.layout.frag_header, container, false);
    }
}
