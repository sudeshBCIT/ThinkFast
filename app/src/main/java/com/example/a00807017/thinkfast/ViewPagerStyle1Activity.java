package com.example.a00807017.thinkfast;

/**
 * Created by A00807017 on 11/6/2014.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;

public class ViewPagerStyle1Activity extends FragmentActivity {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;

    //added this
    Button play_b;
    Button exit_b;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpView();
        setTab();

        //added this
        play_b = (Button) findViewById(R.id.play_button);

        play_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerStyle1Activity.this, MyGame.class);
                startActivity(intent);
                finish();
            }

        });
    }


    private void setUpView(){
        _mViewPager = (ViewPager) findViewById(R.id.viewPager);
        _adapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
    }
    private void setTab(){
        _mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                switch(position){
                    case 0:
                        findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.third_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fourth_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fifth_tab).setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.third_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fourth_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fifth_tab).setVisibility(View.INVISIBLE);
                        break;

                    case 2:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.third_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.fourth_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fifth_tab).setVisibility(View.INVISIBLE);
                        break;

                    case 3:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.third_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fourth_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.fifth_tab).setVisibility(View.INVISIBLE);
                        break;

                    case 4:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.third_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fourth_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fifth_tab).setVisibility(View.VISIBLE);
                        break;

                }
            }

        });

    }
}