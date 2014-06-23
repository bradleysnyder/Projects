package brad.dots;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;

import javax.microedition.khronos.egl.EGL;

import brad.dots.R;

public class GameActivity extends ActionBarActivity {

    private boolean gameOver = false;
    private CircleGLSurfaceView circle; //changing this allowed me yo call methods not available to GLSurfaceView
    private TextView score;
    private long playerScoreTotal = 0;
    public View scoreView;
    //private View gameView;

    private String scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);



        //setContentView(R.layout.activity_game);

        //if (savedInstanceState == null) {
        //getSupportFragmentManager().beginTransaction()
        //.add(R.id.container, new PlaceholderFragment())
        //.commit();
        //}

        //call CircleGLSurfaceView (I think)

        //gameView = new CircleGLSurfaceView(this);
        //score = (TextView) findViewById(R.id.scoreBox);

        //setContentView(gameView);
        //addContentView(score, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //trial code above
        //score = (TextView) findViewById(R.id.scoreBox);
        //score.setText("Score: ");
        //circle = (CircleGLSurfaceView) findViewById(R.id.circleView);
        //circle = new CircleGLSurfaceView(this, Bundle.);
        //setContentView(circle);
        //setContentView(R.layout.activity_game);

        //layoutinflater and the scoreview as well as the getWindow line solved the problem
        LayoutInflater inflater = getLayoutInflater();

        scoreView = inflater.inflate(R.layout.activity_game, null);
        //scoreView.setText((String) playerScoreTotal);


        circle = new CircleGLSurfaceView(this);
        setContentView(circle);
        //circle = new CircleGLSurfaceView(this);


        getWindow().addContentView(scoreView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //circle.requestRender();

        Timer circleTimer = new Timer();

        circleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (circle.TouchHappened()) {
                    final TextView scoreUpdater = (TextView) findViewById(R.id.playerScore);
                    //getParent();
                    //String scoreNumber = scoreUpdate.getText().toString();
                    //int oldScore = Integer.parseInt(scoreNumber);
                    //oldScore++;
                    //scoreUpdate.setText(oldScore);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scoreUpdater.setTextColor(Color.RED);
                            String scoreNumber = scoreUpdater.getText().toString();
                            //int oldScore = Integer.parseInt(scoreNumber);
                            //oldScore++;
                            int oldScore = 1234;
                            scoreUpdater.setText(oldScore+"");  //needed to add the +"" part to the end
                        }
                    });
                    //scoreUpdate.setTextColor(Color.RED);

                }
                //circle = new CircleGLSurfaceView(this);
            }
        }, 3000);

        //while (!gameOver) {
            //if (circle.TouchHappened()) {
              //  TextView scoreUpdate = (TextView) findViewById(R.id.playerScore);
               // scoreUpdate.setTextColor(Color.RED);
                //gameOver = true;

            //}

        //}



        //scoreB = "score: ";

        //need something to fix this and make an editable box next to the score
        //addContentView(score, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //addContentView(score);

        //example has onPause and onResume methods for memory management

        //while (!gameOver){



        //}


    }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.game, menu);
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

    @Override
    public void onPause(){
        super.onPause();
        circle.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        circle.onResume();
    }


}


class CircleGLSurfaceView extends GLSurfaceView {

    //setEGLContextClientVersion(2);
    private CircleRenderer circleRend;
    public boolean touchEvent;


    public CircleGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        //added line below and set context above

        touchEvent = false;
        circleRend = new CircleRenderer();
        setRenderer(circleRend);

        //render view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //set renderer for drawing
        //setRenderer(new CircleRenderer());

        //setEGLContextClientVersion(2);

    }

    //not sure if I need this..
    //also not sure how to use yet..
    //I think it will be used to check for something being touched by user
    @Override
    public boolean onTouchEvent(MotionEvent e){


        //float x = e.getX();
        //float y = e.getY();
        TextView sView;
        touchEvent = false;
        //not working :C
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                //circleRend.clearCircle();
                //requestRender();
                break;
            case MotionEvent.ACTION_UP:
                //GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                //circleRend.clearCircle();
                //requestRender();
                //this.clearFocus();
                //this.destroyDrawingCache();
                //requestRender();
                //circleRend.clearCircle();
                //can only call setRenderer once per instance, so it doesn't make sense to try and render a new circle
                //CircleRenderer newCircle;
                //newCircle = new CircleRenderer();
                //setRenderer(newCircle);
                //this.setBackgroundColor(0);
                //GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
                //this.setBackgroundColor(GLES20.GL_COLOR_BUFFER_BIT);
                //clearFocus();
                circleRend.clearCircle();
                //View scoreUpdate = findViewById(R.id.playerScore);
                sView = (TextView) findViewById(R.id.playerScore);

                //sView.setTextColor(Color.RED);
                //requestrender needed for changes to show since we have render mode set to render when dirty
                requestRender();
                touchEvent = true;

                //long circleRenderTimer = 300;

                //Circle newCircle;
                //new Handler().postDelayed(new Runnable(){

                    //@Override
                    //public void run(){
                        //doesn't work with this line
                        //Intent intent = new Intent(CircleGLSurfaceView.this, GameActivity.class);
                        //Intent intent = new Intent();
                        //intent.setClass(getContext(), GameActivity.class);
                        //startActivity(intent);

                    //}
                //},
                //circleRenderTimer);
                //circleRend = new CircleRenderer();
                //circleRend.mCircle = new Circle();
                //requestRender();
                break;

        }
    return true;
    }


    public boolean TouchHappened(){
        return touchEvent;
    }


}

/*class DotPress implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        }

        return true;

    }

} */