package com.example.a00807017.thinkfast;

/**
 * Created by A00807017 on 11/6/2014.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ViewPagerStyle1Activity extends FragmentActivity {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    public String userName;

    //added this
    Button play_b;
    Button exit_b;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_exit:
                Intent exitGame = new Intent(this, Options.class);
                startActivity(exitGame);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpView();
        setTab();

        Intent intent = getIntent();
        userName = intent.getStringExtra("USERNAME");

        //added this
        play_b = (Button) findViewById(R.id.play_button);

        play_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerStyle1Activity.this, MyGame.class);
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
                finish();
            }

        });

        exit_b = (Button) findViewById(R.id.cancel_button);

        exit_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_exit = new Intent(ViewPagerStyle1Activity.this, Options.class);
                intent_exit.putExtra("USERNAME", userName);
                startActivity(intent_exit);
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