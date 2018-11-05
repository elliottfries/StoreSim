
package StoreSim;

import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 *
 * @author Elliott
 */
public class FinishShopping extends StoreEvent {
   
    public FinishShopping(Customer cust){
        setCust(cust);
        setTime(cust.getArrivalTime() + processShopTime());
    }

    private double processShopTime(){
        return(cust.getShopTime() * cust.getNumItems());
    }
    
    @Override
    public Customer getCust() {
        return cust;
    }

    @Override
    public void setCust(Customer cust) {
        this.cust = cust;
    }
    
    //determines which lane a customer will enter
    @Override
    public void process(SimClock sc, PriorityQueue<StoreEvent> pq, PriorityQueue<StoreEvent> ar, PriorityQueue<CheckoutLane> coRegular, PriorityQueue<CheckoutLane> coExpress, PrintWriter writer){
        cust.setEndShopTime(sc.time());
        CheckoutLane regularLane = coRegular.poll();
        if(coExpress.isEmpty()){
            regularLane.addCustomer(sc, cust, pq);
            coRegular.offer(regularLane);
        }
        else{
            if(cust.getNumItems() <= 12){
                CheckoutLane expressLane = coExpress.poll();
                if(expressLane.getQueueLength() <= regularLane.getQueueLength()){
                    expressLane.addCustomer(sc, cust, pq);
                    coRegular.offer(regularLane);
                    coExpress.offer(expressLane);
                }
                else{
                    regularLane.addCustomer(sc, cust, pq);
                    coRegular.offer(regularLane);
                    coExpress.offer(expressLane);
                }
            }
            else{
                regularLane.addCustomer(sc, cust, pq);
                coRegular.offer(regularLane);
            }
        }
    }
    
    
    
}
