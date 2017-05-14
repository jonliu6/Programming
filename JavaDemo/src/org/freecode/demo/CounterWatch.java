package org.freecode.demo;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class CounterWatch implements Serializable {
    private Timer theTimer = null;
    private int theMilliSecs = 0;
    private boolean isRunning = false;

    public static void main(String[] args) {
        CounterWatch watch = new CounterWatch();
        // watch.setUp(0, 0, 6);
        // watch.countDown();
        // watch.countUp(6000);
    }

    public CounterWatch() {
        theTimer = new Timer();
    }

    public void setUp(int hrs, int mins, int secs) {
        theMilliSecs = 1000 * secs + 1000 * 60 * mins + 1000 * 60 * 60 * hrs;
    }

    public void countUp( final JLabel lbl ) {
        isRunning = true;
        theTimer.schedule(new TimerTask() {
            public void run() {
                while ( isRunning ) {
                    try {
                        Thread.sleep(1);
                        theMilliSecs++;
                        // System.out.print( theMilliSecs + " ");
                        lbl.setText( formatMilliSecs(theMilliSecs) );
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }, 0);
    }
    
    public void reset() {
        theMilliSecs = 0;
    }
    
    public void stop () {
        isRunning = false;
    }
    
    public void resume() {
        
    }

    public void countDown( final JLabel lbl ) {
        isRunning = true;
        theTimer.schedule(new TimerTask() {
            public void run() {
                while ( isRunning && theMilliSecs-- > 0) {
                    try {
                        Thread.sleep(1);
                        // System.out.print( theMilliSecs + " ");
                        lbl.setText(formatMilliSecs(theMilliSecs));
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }, 0);

    }

    public int getMilliSecs() {
        return theMilliSecs;
    }

    public void setMilliSecs(int milliSecs) {
        theMilliSecs = milliSecs;
    }
    
    public String formatMilliSecs(int milliSecs) {
        int hrs = 0;
        int mins = 0;
        int secs = 0;
        int reminder = 0;
        
        hrs = milliSecs / (60 * 60 * 1000);
        reminder = milliSecs % (60 * 60 * 1000);
        mins = reminder / (60 * 1000);
        reminder = reminder % (60 * 1000);
        secs = reminder / 1000;
        reminder = reminder % 1000;
        return String.format("%02d", hrs) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs) + "." + String.format("%03d", reminder); 
    }
}
