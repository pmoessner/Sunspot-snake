/*
 * StartApplication.java
 *
 * Created on Nov 29, 2011 12:17:20 PM;
 */

package org.sunspotworld;

import com.sun.spot.peripheral.Spot;
import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ISwitch;
import com.sun.spot.sensorboard.peripheral.ITriColorLED;
import com.sun.spot.peripheral.radio.RadioFactory;
import com.sun.spot.peripheral.radio.IRadioPolicyManager;
import com.sun.spot.io.j2me.radiostream.*;
import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.peripheral.NoRouteException;
import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import com.sun.spot.util.*;

import java.io.*;
import javax.microedition.io.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * The startApp method of this class is called by the VM to start the
 * application.
 * 
 * The manifest specifies this class as MIDlet-1, which means it will
 * be selected for execution.
 */
public class StartApplication extends MIDlet {   
 
    protected void startApp() throws MIDletStateChangeException {
 
        IRadioPolicyManager rpm = Spot.getInstance().getRadioPolicyManager();
 
        IEEEAddress myAddr = new IEEEAddress(rpm.getIEEEAddress());
 
        System.out.println("Hi! my address = " + myAddr.asDottedHex()); 
 
        IEEEAddress bsAddress = new IEEEAddress(new String("0014.4F01.0000.6CE5"));        
 
        EDemoBoard demoboard = EDemoBoard.getInstance();
 
        IAccelerometer3D acc = demoboard.getAccelerometer();      
 
        while(true)
 
        {
 
            try{
 
  //Basestation Address
 
                RadiogramConnection conn = (RadiogramConnection)Connector.open("radiogram://0014.4F01.0000.6CE5:10");
 
                Radiogram rdg = (Radiogram)conn.newDatagram(conn.getMaximumLength());
 
                try
 
                {
 
                    rdg.writeUTF(""+demoboard.getAccelerometer().getAccelX()+","+demoboard.getAccelerometer().getAccelY()+","+demoboard.getAccelerometer().getAccelZ());
 
                    conn.send(rdg);
 
                    Utils.sleep(100);
 
                }
 
                catch (NoRouteException e)
 
                {
 
                }
 
                finally {
 
                    conn.close();
 
                }
 
            }
 
            catch(IOException e){
 
            }
 
        }
 
    }
 
    protected void pauseApp() {
 
    }    
 
    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
 
    }
 
}