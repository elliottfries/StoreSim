
package StoreSim;

import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 *
 * @author Elliott
 */
public class Arrival extends StoreEvent {
    
    public static int custNum = 0;
    
    public Arrival(Customer cust){
        setTime(cust.getArrivalTime());
        setCust(cust);
    }

    @Override
    public Customer getCust() {
        return cust;
    }

    @Override
    public void setCust(Customer cust) {
        this.cust = cust;
    }
    
    @Override
    public void process(SimClock sc, PriorityQueue<StoreEvent> pq, PriorityQueue<StoreEvent> ar, PriorityQueue<CheckoutLane> coRegular, PriorityQueue<CheckoutLane> coExpress, PrintWriter writer){
        cust.setCustNum(++custNum);
        FinishShopping fs = new FinishShopping(getCust());
        pq.offer(fs);
        if(!ar.isEmpty()){
            pq.offer(ar.poll());
        }
    }

    
}
