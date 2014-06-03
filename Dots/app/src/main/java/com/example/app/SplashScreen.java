package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
//needed the following for handlers
import android.os.Handler;
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

        //new Handler().postDelayed(new Runnable(){

          //  @Override
            //public void run(){
              //  Intent i = new Intent(SplashScreen.this, MainActivity.class);
                //startActivity(i);

                //stop activity
                //finish();
            //}
        //},
        //splash_timer);


        Thread timer = new Thread(){

            @Override
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally{
                    Intent i = new Intent (SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
            }

        };
        timer.start();

    }
}
