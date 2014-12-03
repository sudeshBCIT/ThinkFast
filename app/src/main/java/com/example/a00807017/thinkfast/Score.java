package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sudesh on 11/10/2014.
 */
public class Score extends Activity {

    public String userName;
    public int score;
    private int[]scores;
    DataBaseAdapter dataBaseAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        // get Instance  of Database Adapter
        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userName = bundle.getString("USERNAME");
        score = bundle.getInt("score");
     //   Toast.makeText(this,scr,Toast.LENGTH_SHORT).show();
       // score = Integer.parseInt(scr);
        TextView tscore = (TextView) findViewById(R.id.editscore);
        tscore.setText(String.valueOf(score));

    }
    public void backtoActivity(View view)
    {
        //Calling the main activity again
        //Date 19/11/2014
        Intent intent = new Intent(Score.this,MyGame.class);
        intent.putExtra("USERNAME", userName);
        startActivity(intent);
    }

    public void backToOptions(View view)
    {
        //take us back to Options menu
        Intent options_intent = new Intent(Score.this,Options.class);
        options_intent.putExtra("USERNAME", userName);
        startActivity(options_intent);
    }


}
