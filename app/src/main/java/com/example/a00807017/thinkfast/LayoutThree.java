package com.example.a00807017.thinkfast;

/**
 * This activity creates the third instruction page fragment
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutThree extends Fragment {

    public static Fragment newInstance(Context context) {
        LayoutThree f = new LayoutThree();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_three, null);
        return root;
    }

}
