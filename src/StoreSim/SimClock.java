/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreSim;

/**
 *
 * @author Elliott
 */
public class SimClock {
    
    private static double now;
    
    /**
     * default constructor, initialises start time to 0
     */
    public SimClock()
    {
        now = 0.0;
    }
    /**
     * time method returns current time
     * @return
     */
    public double time() {
        return now;
    }
    /**
     * time method sets time = to parameter
     * @param tm
     */
    public void time(double tm) {
        now = tm;
    }
}

