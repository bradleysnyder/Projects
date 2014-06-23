package brad.dots;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class SplashScreen extends Activity {

    private static long splash_timer = 3000;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //Resources res = getResources();
        //Drawable dr = res.getDrawable(R.drawable.bosware);
        //image = (ImageView) res.getDrawable(R.drawable.bosware);
        //Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.bosware_png);

        //image = (ImageView)findViewById(R.id.bosware);
        ImageView img = (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.bosware);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                //testing out some activity transitions
                //I'll work on that later..using just integers caused it to crash
                //overridePendingTransition(1, 1);

                //stop activity
                finish();
                //overridePendingTransition(1, 1);

            }
        },
        splash_timer);


        //Thread timer = new Thread(){

        //@Override
        //public void run(){
        //try{
        //sleep(3000);
        //} catch (InterruptedException e){
        //e.printStackTrace();
        //} finally{
        //Intent i = new Intent (SplashScreen.this, MainActivity.class);
        //startActivity(i);

        //close activity
        //finish();
        //}
        //}

        //};
        //timer.start();

    }


}
