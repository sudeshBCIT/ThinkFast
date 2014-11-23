package com.example.a00807017.thinkfast;

/**
 * Created by A00807017 on 11/6/2014.
 */

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutFive extends Fragment {

    public static Fragment newInstance(Context context) {
        LayoutFive f = new LayoutFive();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_five, null);
        return root;
    }

}
