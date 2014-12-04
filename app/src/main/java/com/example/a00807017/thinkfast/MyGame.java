package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MyGame extends Activity {
    public CountDownTimer ourTimer;
    public long time_remaining=0; //==
    public TextView number_text, vowel_text, timer_text;
    private int score =0;
    private int seconds, minutes;
    private static final String FORMAT = "%02d:%02d";
    public String userName;
    public boolean result;
    public int correct = 0;
    public int pause = 0;
    private boolean resumeHasRun = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_bar_game, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_pause:
//                Intent pauseGame = new Intent(this, MyGame.class);
//                onPause();
                if (pause == 0) {
                    this.ourTimer.cancel();
                    super.onPause();
                    pause = 1;
                   // Toast.makeText(this,time_remaining+ "", Toast.LENGTH_SHORT).show();
//                    time_remaining =
                }

                else {
                    ourTimer.start();
                    super.onResume();
                     pause=0;
                }

                return true;
            case R.id.menu_exit:
                Intent exitGame = new Intent(this, Options.class);
                startActivity(exitGame);
               // onStop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Intent intent = getIntent();
        userName = intent.getStringExtra("USERNAME");

        // Get references to views
        number_text = (TextView) findViewById(R.id.number_view);
        vowel_text = (TextView) findViewById(R.id.vowel_view);
        timer_text = (TextView) findViewById(R.id.timer_view);

        // Generate random text
        result = showRandomText();


        //timer
        //Referenced this site: http://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android
       ourTimer = new CountDownTimer(15000, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {

                        timer_text.setText("" + String.format(FORMAT,
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                        time_remaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                    }

                    public void onFinish() {
                        timer_text.setText("Time Out!");
                        Intent intent = new Intent (MyGame.this,Score.class);
                        Bundle extras = new Bundle();
                        extras.putInt("score", correct);
                        extras.putString("USERNAME", userName);
                        intent.putExtras(extras);
                        startActivity(intent);

                    }

                    public void onPause() {
                        onPause();
                    }

                }.start();
    }

    //method to generate random number and letter and place in a randomly selected view
    public boolean showRandomText() {
        int number, viewNumber;
        char letter;
        boolean result;
        Random random_number = new Random();

        //assign a random number from 1-9 to an int that is displayed
        number = random_number.nextInt(9) + 1;

        //assign a random letter from A-Z (except "Y") to a char that is displayed
        letter = randomLetter();

        //assign to a view based on a random number
        viewNumber = random_number.nextInt(2);


        if (viewNumber == 0) { //set text to top textview and check if number is even
            number_text.setText(String.valueOf(number) + letter);
            vowel_text.setText("");
            result = checkEvenNumber(number);
        } else { //set text to bottom textview and check if letter is a vowel
            number_text.setText("");
            vowel_text.setText(String.valueOf(number) + letter);
            result = checkVowel(letter);
        }
        return result;
    }

    //method to generate our random letter (excluding "Y")
    private char randomLetter() {
        Random random_char = new Random();
        int random = random_char.nextInt(25);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXZ";
        char random_letter = alphabet.charAt(random);
        return random_letter;
    }

    //method checks if random number is even
    private boolean checkEvenNumber(int num) {
        if ((num % 2) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //method checks if random letter is a vowel
    public boolean checkVowel(char let) {
        String vowels = "AEIOU";
        if (vowels.indexOf(let) != -1) {
            return true;
        } else {
            return false;
        }
    }

    //method to check if button clicked is correct
    public void checkAnswer(View view) {
        boolean answer = true;
        String right;

        switch (view.getId()) {
            case R.id.yes_button:       //"yes" button was pressed
                answer = true;
                break;
            case R.id.no_button:        //"no" button was pressed
                answer = false;
                break;
            default:
                break;
        }
        if (answer == result) {
            correct = correct + 1;
            right = "right";
        } else {
            right = "wrong";
        }
        final Toast toast = Toast.makeText(getApplicationContext(), right, Toast.LENGTH_SHORT);
        toast.show();
        // set toast to show for only 1 second
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);
        // get new random text
        result = showRandomText();
    }
}





