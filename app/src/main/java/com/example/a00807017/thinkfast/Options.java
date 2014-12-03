package com.example.a00807017.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Options extends Activity {

    public String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        Intent intent = getIntent();
        userName = intent.getStringExtra("USERNAME");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void optionsOnClick(View v) {

        switch(v.getId()){

            case R.id.btn_play: /** Start the game **/
                Intent playIntent = new Intent(this, MyGame.class);
                playIntent.putExtra("USERNAME", userName);
                this.startActivity(playIntent);
                break;

            case R.id.btn_instruct: /** Start the instructions */
                Intent instructionsIntent = new Intent(this, ViewPagerStyle1Activity.class);
                instructionsIntent.putExtra("USERNAME", userName);
                this.startActivity(instructionsIntent);
                break;

            case R.id.btn_scores: /** Show the scores **/
                Intent scoresIntent = new Intent(this, Score.class);
                scoresIntent.putExtra("USERNAME", userName);
                this.startActivity(scoresIntent);
                break;
        }
    }
}
