
package StoreSim;

import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 *
 * @author Elliott
 */

//parent class for arrival, finishShopping, and leaving
public abstract class StoreEvent implements Comparable<StoreEvent> {
    
    private double time;
    Customer cust;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }
    
    public abstract void process(SimClock sc, PriorityQueue<StoreEvent> pq, PriorityQueue<StoreEvent> ar, PriorityQueue<CheckoutLane> coRegular, PriorityQueue<CheckoutLane> coExpress, PrintWriter writer);

    
    
    
    //take in time and customer

    @Override
    public int compareTo(StoreEvent t) {
        if(this.getTime() == t.getTime()){
            return 0;
        }
        else if(this.getTime() < t.getTime()){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}
