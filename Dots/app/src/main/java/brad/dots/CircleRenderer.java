package brad.dots;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Brad on 6/14/2014.
 */
public class CircleRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "CircleRenderer";
    private Circle mCircle;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];


    public void onSurfaceCreated(GL10 unused, EGLConfig config){

        //the final 1.0f isn't changing anything when white color is used..probably only affects other colors
        //hard to tell any difference between using alpha and not using it
        //set background frame color
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        mCircle = new Circle();

    }

    public void onDrawFrame(GL10 unused){

        //float[] scratch = new float[16];

        //not sure why we are using it this time
        //unused.glPushMatrix();

        //draw background frame color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //unused.glMatrixMode(GL10.GL_PROJECTION);
        //unused.glLoadIdentity();
        //GLU.gluLookAt(unused, 0,0,5, 0f,0f,0f,0f,1.0f, 0.0f);

        //no idea what this is doing..
        //going to comment out the three matrix lines
        //set camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        //again, no idea what's going on...reminder to look at dev tools for opengl
        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        //Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // Draw circle
        mCircle.draw(mMVPMatrix);


        //took these out
        //unused.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 40);
        //unused.glPopMatrix();



    }

    public void onSurfaceChanged(GL10 unused, int width, int height){

        GLES20.glViewport(0, 0, width, height);

        //took out the line below and the matrix.frustum line to try and simplify
        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        //not sure what this does..
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

        //unused.glMatrixMode(GL10.GL_PROJECTION);
        //unused.glLoadIdentity();
        //unused.glFrustumf(-ratio, ratio, -1, 1, 3, 7);



    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

}

