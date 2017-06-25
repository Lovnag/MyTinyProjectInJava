import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

import java.util.Scanner;

/**
 * Created by thy humble Lovnag on 23.04.2017.
 */
public class Main {

    public final static double nanoScale = 1000000000.0;
    public final static double deltaLimit = 0.1;

    final static int k = 10;


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        //System.out.println("What kind of bodies would you like to observe? Press 0 for real-ish boides, and 1 for spheres and cubes:");
        //int sphc = reader.nextInt();
        boolean SpheresAndCubes = true;
        //if (sphc == 0) {
        //    SpheresAndCubes = false;
        //} else {
        //    SpheresAndCubes = true;
        //}

        AnalyticalInformation info = new AnalyticalInformation(SpheresAndCubes);
        double time = 0;
        double prevtime = 0;
        double sttime = System.nanoTime();
        double stopTime = 10000;
        info.generate(10);


        JFrame window = new JFrame("Insanity");
        GLCapabilities caps = new GLCapabilities(null);
        Drawing panel = new Drawing(caps, info.generateDrawingInformation());
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50, 50);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();

        while (time < stopTime) {
            prevtime = time;
            time = (System.nanoTime() - sttime) / nanoScale;
            info.elementalRunning(time, prevtime, sttime);
            panel.run(info.generateDrawingInformation());

        }

    }
}