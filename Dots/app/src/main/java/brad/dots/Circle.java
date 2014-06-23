package brad.dots;

import android.opengl.GLES20;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Brad on 6/14/2014.
 */
public class Circle {

    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            // the matrix must be included as a modifier of gl_Position
            // Note that the uMVPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            "  gl_Position = uMVPMatrix * vPosition;" + //took out uMVPMatrix part, adding it back in makes the circle unrenderable
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";


    private FloatBuffer vertexBuffer;
    private float[] circleCoords = new float[364*3];
    public final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    private final int vertexCount = circleCoords.length / 3;
    //private int outerVertexCount = vertexCount - 1;



    //float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
    float color[] = {0.0f, 0.0f, 0.0f, 1.0f};

    public Circle(){

        //hopefully it's alright initializing the coordinates here
        //yep works, just places oval (not yet circle, need projection views I think) in center
        //this is the center that the triangle is fanned around
        //interesting to note that (0,0,0) is the center of the screen
        //circleCoords[0] = (float)0.1;
        //circleCoords[1] = (float)0.2;
        //circleCoords[2] = (float)0;     //changing this doesn't seem to change circle

        //should fetch two numbers in the acceptable range
        //apparently 0.9 doesn't work..
        //edit: it does work, I just had to scale the width since my tablet screen isn't a square
        circleCoords[0] = (float) -0.85*10/16 + (float)(Math.random() * (0.75*10/16 + 0.85*10/16));
        circleCoords[1] = (float) -0.85 + (float)(Math.random() * (0.75 + 0.85));
        circleCoords[2] = 0;


        //set up values for all coordinates (2-D)
        for (int i = 1; i < vertexCount; i++){
            circleCoords[(i*3)+0] = (float) (0.1*Math.cos((3.1416/180) * (float) i) + circleCoords[0]); //these are necessary for being able to move
            circleCoords[(i*3)+1] = (float) (0.1*Math.sin((3.1416/180) * (float) i) + circleCoords[1]);  //the circle around on the screen
            circleCoords[(i*3)+2] = 0 + circleCoords[2];
            //the coefficients in front of the math functions are what change radius size
        }


        //allocate number of coords * 4 bytes per coord
        //changing this from allocate to allocateDirect fixed the rendering issue about using native order buffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(circleCoords.length * 4);

        //use device hardware's native byteorder
        byteBuffer.order(ByteOrder.nativeOrder());

        //create floating point buffer from the ByteBuffer
        vertexBuffer = byteBuffer.asFloatBuffer();

        //add coordinates to float buffer
        vertexBuffer.put(circleCoords);

        //set buffer to read from first position
        vertexBuffer.position(0);

        int vertexShader = CircleRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = CircleRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);



    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param mvpMatrix - The Model View Project matrix in which to draw
     * this shape.
     */

    public void draw(float[] mvpMatrix){
        //add program to opengles environment
        GLES20.glUseProgram(mProgram);
        //GLES20.glLinkProgram(mProgram);

        //get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        //enable a handle to the circle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        //prepare the circle's coordinate data
        GLES20.glVertexAttribPointer(
                mPositionHandle, 3,         //coords per vertex is 3
                GLES20.GL_FLOAT, false,
                12, vertexBuffer);          //colors per coord * coords per vertex

        //was recommended to place the enable vertex attrib array after vertex attrib pointer..didn't solve anything though
        //enable a handle to the circle vertices
        //GLES20.glEnableVertexAttribArray(mPositionHandle);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //the next two calls aren't working
        //seems like the program isn't properly linked
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        CircleRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        CircleRenderer.checkGlError("glUniformMatrix4fv");

        //draw circle contours
        //don't think I need this line
        //doesn't seem to change anything
        //GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 2, outerVertexCount);

        // Draw the circle as filled shape
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);



        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }


}
