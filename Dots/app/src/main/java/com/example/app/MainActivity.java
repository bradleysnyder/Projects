package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    //maybe these should be declared inside the onCreate Method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play, settings, sync;


        //addListenerOnButton();
        play = (Button) findViewById(R.id.playButton);
        //play.setOnClickListener((View.OnClickListener) this);
        //play.setOnClickListener(playListener);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent gameIntroIntent = new Intent(MainActivity.this, GameIntroScreen.class);
                //gameIntroIntent.putExtra("Calling class", "MainActivity");
                //added to try and switch the content view??
                //the below line just duplicated the layout
                //setContentView(R.layout.activity_game_intro_screen);
                //MainActivity.this.startActivity(gameIntroIntent);
                //don't want MainActivity to finish since pressing back will result
                //in thee game completely exiting
                //finish();
                startActivity(gameIntroIntent);
            }
        });

    }

    //private OnClickListener playListener = new OnClickListener(){
        //public void onCLick (View v){
            //setContentView(R.layout.activity_game);
            //Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
            //MainActivity.this.startActivity(gameIntent);
        //}
    //};

    //this is what creates the clickable button in the upper right of the screen for settings and what not
    //I'll just do this on my own
    //called the action bar
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        //if (id == R.id.action_settings) {
            //return true;
        //}
        //return super.onOptionsItemSelected(item);
    //}

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    */
}
