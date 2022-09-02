import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class TimeElapsedRun {
    // yk how some compilers iE most java compilers have time elapsed
    //thought it would be cool to have it here
    //doesnt work soo
    int timerInSecs = 0;
    int timerInMins = 0;
    String TheTime12 = "";

    Timer timerC = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (timerInSecs > 60) {
                timerInSecs = 0;
                timerInMins++;
            }
            if (timerInSecs < 10) {
                TheTime12 = "Timer: " + timerInMins + ":0" + Integer.toString(timerInSecs);

            } else {
                TheTime12 = "Timer: " + timerInMins + ":0" + Integer.toString(timerInSecs);
            }
            System.out.println(timerInSecs);
            timerInSecs++;

        }
    });

    public Timer getTime(){
        return timerC;
    }
    public String getTimeElapsed(){
        return TheTime12;
    }

}
