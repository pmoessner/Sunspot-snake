/*
 * SunSpotHostApplication.java
 *
 * Created on Nov 28, 2011 10:21:26 AM;
 */
package org.sunspotworld;

import com.sun.spot.peripheral.radio.RadioFactory;
import com.sun.spot.peripheral.radio.IRadioPolicyManager;
import com.sun.spot.io.j2me.radiostream.*;
import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.peripheral.NoRouteException;
import com.sun.spot.util.IEEEAddress;

import java.io.*;
import javax.microedition.io.*;
import javax.swing.JFrame;

/**
 * Sample Sun SPOT host application
 */
public class SunSpotHostApplication {

    public void runGame() {
        //snake snake2 = new snake();
        // engin engin2 = new engin();
        myPanel myPanel = new myPanel();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(myPanel);

        f.setSize(1040, 1040);
        f.setLocation(400, 400);
        f.setVisible(true);

        while (true) {
            try {
                RadiogramConnection conn = (RadiogramConnection) Connector.open("radiogram://:10");
                Radiogram rdg = (Radiogram) conn.newDatagram(conn.getMaximumLength());

                try {
                    conn.receive(rdg);
                    System.out.println(rdg.getAddress());
                    String s = rdg.readUTF();
                    System.out.println(s);
                    String accArr[] = s.split(",");

                    double x = Double.parseDouble(accArr[0]);
                    double y = Double.parseDouble(accArr[1]);
                    double z = Double.parseDouble(accArr[2]);


                    //System.out.println("xtilt: "+x+", ytilt: "+y);
                    System.out.println("xtilt: " + x + ", ytilt: " + y + ", ztilt: " + z);


                    int direction = 1;
                    if (y >0.5) {
                        direction = 1;//right
                    }
                    //myPanel.set_Direction(2);
                    if (y < -0.5) {
                        direction = 3;//left
                    }
                    // myPanel.set_Direction(4);
                    if (x >0.5 ) {
                        direction = 2;//up
                    }
                    //myPanel.set_Direction(3);
                    if (x <-0.5){
                    direction = 4;//down
                    }
                    //else direction=2;
                     // myPanel.set_Direction(1);
                    {
                        if (rdg.getAddress().equals("0014.4F01.0000.6AEF")) {
                            myPanel.set_Direction(direction);
                        }
                    }
                    //myPanel.repaint();
                    //snake2.check_game_over();
                } catch (NoRouteException e) {
                } finally {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        SunSpotHostApplication app = new SunSpotHostApplication();
        app.runGame();
    }
}