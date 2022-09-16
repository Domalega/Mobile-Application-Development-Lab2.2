package com.example.lab22;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {
    Cube cube;
    float p;

    public MyRenderer(){cube = new Cube();}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {}

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {}

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClearColor(4,4,4,4);

        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glOrthof(-3,3, -3,3,5,-5);
        GLU.gluLookAt(gl,0,1,-2,0,2,-1 ,0,1,0);

        gl.glTranslatef(0,3,0);
        gl.glPushMatrix();
        gl.glRotatef(p,0,1,0);
        cube.draw(gl);
        gl.glPopMatrix();

        gl.glEnable(GL10.GL_STENCIL_TEST);
        gl.glStencilFunc(GL10.GL_ALWAYS, 1, 0xFF); // Set any stencil to 1
        gl.glStencilOp(GL10.GL_ZERO, GL10.GL_ZERO, GL10.GL_REPLACE);
        gl.glStencilMask(0xff); // Write to stencil buffer
        gl.glDepthMask(false); // Don't write to depth buffer
        gl.glClearStencil(0);
        gl.glClear(GL10.GL_STENCIL_BUFFER_BIT); // Clear stencil buffer (0 by default)

        gl.glPushMatrix();
        gl.glTranslatef(0,-1,0);
        gl.glScalef(2,2,2);
        gl.glRotatef(p,0,1,0);
        gl.glRotatef(-90,1,0,0);
        gl.glColor4f(0,0,0,1); //!!!!
        gl.glPopMatrix();

        gl.glEnable(GL10.GL_STENCIL_TEST);
        gl.glStencilFunc(GL10.GL_EQUAL, 1, 0xFF);
        gl.glStencilMask(0x0);

        gl.glColorMask(true,true,true,true);
        gl.glDepthMask(true);

        gl.glPushMatrix();
        gl.glTranslatef(0,-2,0);
        gl.glRotatef(p,0,1,0);
        cube.draw(gl);
        gl.glPopMatrix();
        p = (p > 360) ? 0 : p + 1f;

        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glDisable(GL10.GL_STENCIL_TEST);
    }
}
