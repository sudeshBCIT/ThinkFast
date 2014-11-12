package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sudesh on 11/10/2014.
 */
public class Continue1 extends Activity {
      // int score = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        Intent intent = getIntent();
        String scr = intent.getStringExtra("score");
     //   Toast.makeText(this,scr,Toast.LENGTH_SHORT).show();
       // score = Integer.parseInt(scr);
        EditText tscore = (EditText) findViewById(R.id.editscore);
        tscore.setText(scr);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact(new Score(scr, "userid"));
    }
}
