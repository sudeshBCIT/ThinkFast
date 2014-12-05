package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

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
        dataBaseAdapter = DataBaseAdapter.getOneInstance(this);
        //dataBaseAdapter = dataBaseAdapter.open();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userName = bundle.getString("USERNAME");
        score = bundle.getInt("score");

        // Display current score
        TextView tscore = (TextView) findViewById(R.id.editscore);
        tscore.setText(String.valueOf(score));

        // Get scores array from database
        scores = dataBaseAdapter.getScores(userName);

        // Save the current score in database if one of 3 highest scores
        int index = getLowScoreIndex(scores);
        dataBaseAdapter.updateScore(userName, scores , score, index);
        scores = dataBaseAdapter.getScores(userName);

        // Sort array (lowest to highest)
        Arrays.sort(scores);

        // Display the previous top three scores
        TextView textScore1 = (TextView) findViewById(R.id.first_place);
        int score1 = scores[2];
        textScore1.setText(String.valueOf(score1));

        TextView textScore2 = (TextView) findViewById(R.id.second_place);
        int score2 = scores[1];
        textScore2.setText(String.valueOf(score2));

        TextView textScore3 = (TextView) findViewById(R.id.third_place);
        int score3 = scores[0];
        textScore3.setText(String.valueOf(score3));

        //dataBaseAdapter.close();
    }

    public int getLowScoreIndex(int[] scores) {
        int lowScore = 0;
        int index = 0;

        lowScore = scores[0];
        if (scores[1] < lowScore) {
            lowScore = scores[1];
            index = 1;
        }
        if (scores[2]< lowScore) {
            index = 2;
        }
            return index;
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
