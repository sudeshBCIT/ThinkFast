package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MyGame extends Activity {

    Button no_b;

    TextView number_text, vowel_text, timer_text;

    private static final String FORMAT = "%02d:%02d";

    int score = 0, seconds, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        no_b = (Button) findViewById(R.id.no_button);
        number_text = (TextView) findViewById(R.id.number_view);
        vowel_text  = (TextView) findViewById(R.id.vowel_view);
        timer_text  = (TextView) findViewById(R.id.blank1_view);

        //set our button listener for the NO button
        no_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random_number = new Random();
                Random random_for_edittext = new Random();
                //numbers generated only from 0-9
                int random = random_number.nextInt(10);
                int randomforedittext = random_for_edittext.nextInt();
                //assign a letter from A-Z to a char
                char letter = randomLetter();

                if(randomforedittext <= 0.5){
                   number_text.setText(String.valueOf(random)+ letter);
                   vowel_text.setText("");
               }else

                {
                   vowel_text.setText(String.valueOf(random) + letter);
                   number_text.setText("");
                }

                if(number_text.getText().toString().equals("")){
                   boolean isVowel =  checkVowel(vowel_text.getText().toString());
                    if(isVowel == false)
                    {
                       score ++;
                    }
                }else
                {
                    boolean isEven =  checkEvenNumber(number_text.getText().toString());
                    if(isEven == false)
                    {
                        score ++;
                    }
                }

               /*  on 16/10/2014 commented
                //sudesh 08/10/2014
                switch(random)
                {
                    case 1:
                        number_text.setText(String.valueOf(random)+ letter);
                        vowel_text.setText("");
                        break;
                    case 2:
                        vowel_text.setText(String.valueOf(random)+ letter);
                        number_text.setText("");
                        break;
                    case 3:
                        vowel_text.setText(String.valueOf(random)+ letter);
                        number_text.setText("");
                        break;
                    case 4:
                        number_text.setText(String.valueOf(random)+ letter);
                        vowel_text.setText("");
                        break;
                }
              */
                //set our first text view to the random number and random letter
                //number_text.setText(String.valueOf(random)+ letter);

                //set our second text view to the random number and random letter
               // vowel_text.setText(String.valueOf(random)+ letter);
            }
        });

        //timer
        //Referenced this site: http://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android
        new CountDownTimer(45000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                timer_text.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                //timer_text.setText("done!");
                Toast.makeText(getApplicationContext(),"Your score is = " + score,Toast.LENGTH_LONG).show();
                startActivity(new Intent(MyGame.this, Continue1.class));
            }
        }.start();
    }

    //method to generate our random letter
    public char randomLetter(){
        Random random_char = new Random();
        int random = random_char.nextInt(27);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char random_letter = alphabet.charAt(random);
        return random_letter;
    }

    //  sudesh yadav 06/10/2014
    //method to check if the number is even
    //always checks the number_text view
    //for the NO and YES button
    public boolean checkEvenNumber(String str) {
       //check if the value of number_text field is an even number
       //remember the even number can be in the first or second position
       String num = str.replaceAll("[^0-9]", "");
        int num1 = Integer.parseInt(num);
        if ((num1 /2) == 0 ) {
            return true;
        }
        else {
            return false;
             }
        }
    // sudesh 06/10/2014


    // sudesh 06/10/2014
    //method to check if the letter is a vowel
    public boolean checkVowel(String str) {
        String vow = str.replaceAll("[0-9]","");

        return "AEIOUaeiou".indexOf(vow) != -1;
   }
   // sudesh 06/10/2014





}
