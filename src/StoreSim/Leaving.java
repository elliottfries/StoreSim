
package StoreSim;

import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 *
 * @author Elliott
 */
public class Leaving extends StoreEvent {
    
    CheckoutLane lane;
    
    public Leaving(CheckoutLane lane, Customer cust, double time){
        setLane(lane);
        setCust(cust);
        setTime(time);
        System.out.println("LeavingTime: " + time);
    }

    public CheckoutLane getLane() {
        return lane;
    }

    public void setLane(CheckoutLane lane) {
        this.lane = lane;
    }

    @Override
    public Customer getCust() {
        return cust;
    }

    @Override
    public void setCust(Customer cust) {
        this.cust = cust;
    }
    
    //displays relevant information amount the customer(Customer number, arrival time, when they got done shopping, ect.)
    @Override
    public void process(SimClock sc, PriorityQueue<StoreEvent> pq, PriorityQueue<StoreEvent> ar, PriorityQueue<CheckoutLane> coRegular, PriorityQueue<CheckoutLane> coExpress, PrintWriter writer){
        lane.removeCustomer(cust);
        writer.println("Customer Number: " + cust.getCustNum() + ", Arrival Time: " + cust.getArrivalTime() + ", End Shopping Time: " + cust.getEndShopTime() + ", End Wait Time: " + cust.getEndWaitTime() + ", End Checkout Time: " + cust.getEndCheckoutTime());
        System.out.println("\nCustomer Number: " + cust.getCustNum() + "\n, Arrival Time: " + cust.getArrivalTime() + ",\n End Shopping Time: " + cust.getEndShopTime() + ",\n End Wait Time: " + cust.getEndWaitTime() + ",\n End Checkout Time: " + cust.getEndCheckoutTime());
    }
}