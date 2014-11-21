package com.example.a00807017.thinkfast;

/**
 * Created by A00807017 on 11/6/2014.
 */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutFour extends Fragment {

    public static Fragment newInstance(Context context) {
        LayoutFour f = new LayoutFour();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_four, null);
        return root;
    }

}
