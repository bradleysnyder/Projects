package brad.dots;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changed them to final..think it means they cannot be changed during execution
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
                //play.playSoundEffect(0);

            }
        });


    }


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
}
