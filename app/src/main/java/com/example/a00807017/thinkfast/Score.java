package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
/**
 * This activity displays the current score and the user's top 3 high scores.
 *
 * @authors:    ThinkFast!
 *              Lynn Yuen, Sudesh Yadav, and Sandra Buchanan
 *              Fall Term 2014
 */

public class Score extends Activity {

    public String userName;
    public int score;           // Current score
    private int[]scores;        // Array containing top 3 scores
    DataBaseAdapter dataBaseAdapter;   // Database instance

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        // Get instance  of database adapter
        dataBaseAdapter = DataBaseAdapter.getOneInstance(this);

        // Retrieve user name and current score passed from previous activity
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

        // Sort scores array (lowest to highest)
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

    }

    // Method to retrieve the array index of the lowest score that was stored
    public int getLowScoreIndex(int[] scores) {
        int lowScore = 0;
        int index = 0;

        // Set low score to first score in array
        lowScore = scores[0];
        // Check all other scores against lowest score
        if (scores[1] < lowScore) {
            lowScore = scores[1];
            index = 1;
        }
        if (scores[2]< lowScore) {
            index = 2;
        }
            return index;
    }
    // Return to game activity based on button click
    public void backtoActivity(View view)
    {
        Intent intent = new Intent(Score.this,MyGame.class);
        intent.putExtra("USERNAME", userName);
        startActivity(intent);
    }
    // Return to option activity based on button click
    public void backToOptions(View view)
    {
        Intent options_intent = new Intent(Score.this,Options.class);
        options_intent.putExtra("USERNAME", userName);
        startActivity(options_intent);
    }
}
