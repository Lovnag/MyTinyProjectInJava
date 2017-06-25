/**
 * Created by thy humble Lovnag on 29.04.2017.
 */


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import com.jogamp.opengl.*;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.JFrame;


public class Drawing extends GLJPanel implements GLEventListener, KeyListener {


    private float rotateX, rotateY, rotateZ;


    DrawingInformation info;

    public Drawing(GLCapabilities capabilities, DrawingInformation info) {
        super(capabilities);
        setPreferredSize(new Dimension(600, 600));
        addGLEventListener(this);
        addKeyListener(this);
        rotateX = 0;
        rotateY = 0;
        rotateZ = 0;
        this.info = info;
    }

    void drawBody(Integer[][] surfaces, Vector3[] dots, GL2 gl) {
        gl.glBegin(gl.GL_TRIANGLE_STRIP);
        for (int i = 0; i < surfaces.length; i++) {

            gl.glColor3d(0.0, 0.0, 0.5);
            gl.glNormal3d(dots[surfaces[i][0]].getX(), dots[surfaces[i][0]].getY(), dots[surfaces[i][0]].getZ());
            gl.glVertex3d(dots[surfaces[i][0]].getX(), dots[surfaces[i][0]].getY(), dots[surfaces[i][0]].getZ());
            gl.glNormal3d(dots[surfaces[i][1]].getX(), dots[surfaces[i][1]].getY(), dots[surfaces[i][1]].getZ());
            gl.glVertex3d(dots[surfaces[i][1]].getX(), dots[surfaces[i][1]].getY(), dots[surfaces[i][1]].getZ());
            gl.glColor3d(0.5, 0.5, 0.0);
            gl.glNormal3d(dots[surfaces[i][2]].getX(), dots[surfaces[i][2]].getY(), dots[surfaces[i][2]].getZ());
            gl.glVertex3d(dots[surfaces[i][2]].getX(), dots[surfaces[i][2]].getY(), dots[surfaces[i][2]].getZ());
        }
        gl.glEnd();
    }


    void drawSphere(double r, int latitude, int longitude, GL2 gl) {
        gl.glBegin(gl.GL_QUAD_STRIP);
        for (int i = 0; i <= latitude; i++) {
            double lat0 = Math.PI * (-0.5 + (double) (i - 1) / latitude);
            double z0 = Math.sin(lat0);
            double zr0 = Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) i / latitude);
            double z1 = Math.sin(lat1);
            double zr1 = Math.cos(lat1);


            for (int j = 0; j <= longitude; j++) {
                double lng = 2 * Math.PI * (double) (j - 1) / longitude;
                double x = Math.cos(lng);
                double y = Math.sin(lng);

                gl.glColor3d(0.0, 1.0, 1.0);
                gl.glNormal3d(r * x * zr0, r * y * zr0, r * z0);
                gl.glVertex3d(r * x * zr0, r * y * zr0, r * z0);
                gl.glColor3d(1.0, 0.0, 0.0);
                gl.glNormal3d(r * x * zr1, r * y * zr1, r * z1);
                gl.glVertex3d(r * x * zr1, r * y * zr1, r * z1);
            }
        }
        gl.glEnd();
    }

    void drawCuboid(double length, double width, double height, GL2 gl) {
        gl.glBegin(gl.GL_QUAD_STRIP);

        gl.glColor3d(0.0, 1.0, 0.0);

        gl.glNormal3d(length / 2, width / 2, height / 2);
        gl.glVertex3d(length / 2, width / 2, height / 2);
        gl.glNormal3d(length / 2, width / 2, -height / 2);
        gl.glVertex3d(length / 2, width / 2, -height / 2);
        gl.glNormal3d(length / 2, -width / 2, height / 2);
        gl.glVertex3d(length / 2, -width / 2, height / 2);
        gl.glNormal3d(length / 2, -width / 2, -height / 2);
        gl.glVertex3d(length / 2, -width / 2, -height / 2);

        gl.glColor3d(0.0, 0.0, 1.0);

        gl.glNormal3d(-length / 2, width / 2, height / 2);
        gl.glVertex3d(-length / 2, width / 2, height / 2);
        gl.glNormal3d(-length / 2, width / 2, -height / 2);
        gl.glVertex3d(-length / 2, width / 2, -height / 2);
        gl.glNormal3d(-length / 2, -width / 2, height / 2);
        gl.glVertex3d(-length / 2, -width / 2, height / 2);
        gl.glNormal3d(-length / 2, -width / 2, -height / 2);
        gl.glVertex3d(-length / 2, -width / 2, -height / 2);

        gl.glColor3d(0.0, 1.0, 0.0);

        gl.glNormal3d(length / 2, width / 2, height / 2);
        gl.glVertex3d(length / 2, width / 2, height / 2);
        gl.glNormal3d(length / 2, width / 2, -height / 2);
        gl.glVertex3d(length / 2, width / 2, -height / 2);
        gl.glNormal3d(-length / 2, width / 2, height / 2);
        gl.glVertex3d(-length / 2, width / 2, height / 2);
        gl.glNormal3d(-length / 2, width / 2, -height / 2);
        gl.glVertex3d(-length / 2, width / 2, -height / 2);

        gl.glColor3d(0.0, 0.0, 1.0);

        gl.glNormal3d(length / 2, -width / 2, height / 2);
        gl.glVertex3d(length / 2, -width / 2, height / 2);
        gl.glNormal3d(length / 2, -width / 2, -height / 2);
        gl.glVertex3d(length / 2, -width / 2, -height / 2);
        gl.glNormal3d(-length / 2, -width / 2, height / 2);
        gl.glVertex3d(-length / 2, -width / 2, height / 2);
        gl.glNormal3d(-length / 2, -width / 2, -height / 2);
        gl.glVertex3d(-length / 2, -width / 2, -height / 2);

        gl.glColor3d(0.0, 1.0, 0.0);

        gl.glNormal3d(length / 2, width / 2, height / 2);
        gl.glVertex3d(length / 2, width / 2, height / 2);
        gl.glNormal3d(-length / 2, width / 2, height / 2);
        gl.glVertex3d(-length / 2, width / 2, height / 2);
        gl.glNormal3d(length / 2, -width / 2, height / 2);
        gl.glVertex3d(length / 2, -width / 2, height / 2);
        gl.glNormal3d(-length / 2, -width / 2, height / 2);
        gl.glVertex3d(-length / 2, -width / 2, height / 2);

        gl.glColor3d(0.0, 0.0, 1.0);

        gl.glNormal3d(length / 2, width / 2, -height / 2);
        gl.glVertex3d(length / 2, width / 2, -height / 2);
        gl.glNormal3d(-length / 2, width / 2, -height / 2);
        gl.glVertex3d(-length / 2, width / 2, -height / 2);
        gl.glNormal3d(length / 2, -width / 2, -height / 2);
        gl.glVertex3d(length / 2, -width / 2, -height / 2);
        gl.glNormal3d(-length / 2, -width / 2, -height / 2);
        gl.glVertex3d(-length / 2, -width / 2, -height / 2);

        gl.glEnd();
    }

    void computeLocation(GL2 gl) {
        double x = 1000;     // my x-, y-, and z-coordinates
        double y = 1000;
        double z = 1000;
        double d = Math.sqrt(x * x + y * y + z * z); // distance to origin

        gl.glMatrixMode(gl.GL_PROJECTION);        // Set projection parameters.
        gl.glLoadIdentity();
        gl.glFrustum(-d * 5, d * 5, -d * 5, d * 5, d - 11, d + 11);
    }

    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0.02f, 0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glRotatef(rotateZ, 0, 0, 1);
        gl.glRotatef(rotateY, 0, 1, 0);
        gl.glRotatef(rotateX, 1, 0, 0);

        ByteBuffer temp = ByteBuffer.allocateDirect(16);
        temp.order(ByteOrder.nativeOrder());
        gl.glColor3d(0.3, 0.0, 0.0);
        float[] lightAmbient = {0.0f, 0.5f, 0.5f, 1.0f};  // Ambient Light Values
        float[] lightDiffuse = {0.7f, 0.7f, 0.7f, 1.0f};      // Diffuse Light Values
        float[] lightPosition = {-1.0f, -1.0f, 0.0f, 0.0f};
        float[] lightSpecular = {0.7f, 0.7f, 0.7f, 1.0f};
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, lightAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightDiffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, lightSpecular, 0);
        if (info.isSpheresAndCubes()) {
            for (int i = 0; i < info.getSphereCenter().length; i++) {
                gl.glTranslatef((float) info.getSphereCenter()[i].getX(), (float) info.getSphereCenter()[i].getY(), (float) info.getSphereCenter()[i].getZ());
                drawSphere(info.getSphereRadius()[i], 100, 100, gl);
                gl.glTranslatef(-(float) info.getSphereCenter()[i].getX(), -(float) info.getSphereCenter()[i].getY(), -(float) info.getSphereCenter()[i].getZ());
            }

            for (int i = 0; i < info.getCubeCenter().length; i++) {

                gl.glTranslatef((float) info.getCubeCenter()[i].getX(), (float) info.getCubeCenter()[i].getY(), (float) info.getCubeCenter()[i].getZ());
                drawCuboid(info.getCuboidDimensions()[i][0], info.getCuboidDimensions()[i][1], info.getCuboidDimensions()[i][2], gl);
                gl.glTranslatef(-(float) info.getCubeCenter()[i].getX(), -(float) info.getCubeCenter()[i].getY(), -(float) info.getCubeCenter()[i].getZ());
            }
        } else {
            for (int i = 0; i < info.getRealBodyCenter().length; i++) {

                gl.glTranslatef((float) info.getRealBodyCenter()[i].getX(), (float) info.getRealBodyCenter()[i].getY(), (float) info.getRealBodyCenter()[i].getZ());
                drawBody(info.getRealBodySurfaces()[i], info.getRealBodyDots()[i], gl);
                gl.glTranslatef(-(float) info.getRealBodyCenter()[i].getX(), -(float) info.getRealBodyCenter()[i].getY(), -(float) info.getRealBodyCenter()[i].getZ());

            }
        }


    }

    public void init(GLAutoDrawable drawable) {
        // called when the panel is created
        GL2 gl = drawable.getGL().getGL2();
        computeLocation(gl);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1.0);
        gl.glEnable(GL.GL_DEPTH_TEST);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    public void run(DrawingInformation newInfo) {
        info = newInfo;
        repaint();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            rotateY -= 15;
        else if (key == KeyEvent.VK_RIGHT)
            rotateY += 15;
        else if (key == KeyEvent.VK_DOWN)
            rotateX += 15;
        else if (key == KeyEvent.VK_UP)
            rotateX -= 15;
        else if (key == KeyEvent.VK_PAGE_UP)
            rotateZ += 15;
        else if (key == KeyEvent.VK_PAGE_DOWN)
            rotateZ -= 15;
        else if (key == KeyEvent.VK_HOME)
            rotateX = rotateY = rotateZ = 0;
        repaint();

    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {


    }


}