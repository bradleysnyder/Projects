package brad.dots;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import brad.dots.R;

public class GameActivity extends ActionBarActivity {

    private boolean gameOver = false;
    private GLSurfaceView circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        //if (savedInstanceState == null) {
        //getSupportFragmentManager().beginTransaction()
        //.add(R.id.container, new PlaceholderFragment())
        //.commit();
        //}

        //call CircleGLSurfaceView (I think)

        circle = new CircleGLSurfaceView(this);
        setContentView(circle);

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
    private final CircleRenderer circleRend;

    public CircleGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        //added line below and set context above

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
    //@Override
    //public boolean onTouchEvent(MotionEvent e){

    //float x = e.getX();
    //float y = e.getY();



    //return true;
    //}


}