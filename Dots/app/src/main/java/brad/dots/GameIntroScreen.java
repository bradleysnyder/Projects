package brad.dots;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import brad.dots.R;

public class GameIntroScreen extends Activity {

        private static long game_intro_timer = 1000;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_intro_screen);

            //if (savedInstanceState == null) {
            //getSupportFragmentManager().beginTransaction()
            //.add(R.id.container, new PlaceholderFragment())
            //.commit();
            //}

            //have screen stay up for a second before transitioning to game
            //the problem (NullPointerException) was fixed by calling the id of the image from the xml file
            ImageView iv = (ImageView) findViewById(R.id.screen);
            //ImageView iv = (ImageView) findViewById(this);
            //bottom is causing the crash
            //this was fixed by calling the imageview id by name
            //now this works as planned
            iv.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    //iv = (ImageView) findViewById(R.layout.activity_game_intro_screen);
                    new Handler().postDelayed(new Runnable(){

                        @Override
                        public void run(){
                            Intent i = new Intent(GameIntroScreen.this, GameActivity.class);
                            startActivity(i);

                            //stop activity
                            finish();
                        }
                    }, game_intro_timer);
                }
            });


        }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.game_intro_screen, menu);
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
