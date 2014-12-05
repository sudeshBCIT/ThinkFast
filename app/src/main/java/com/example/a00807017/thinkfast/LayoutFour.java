package com.example.a00807017.thinkfast;

/**
 * This activity creates the fourth instruction page fragment
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */

import android.support.v4.app.Fragment;
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
