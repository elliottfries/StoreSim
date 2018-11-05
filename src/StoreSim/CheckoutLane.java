
package StoreSim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

/**
 *
 * @author Elliott
 */
public class CheckoutLane implements Comparable<CheckoutLane> {
    
    private int laneNum;
    private double totalCustomerWaitTime;
    private double itemTime;
    private double payTime;
    private boolean isExpress;
    private Queue<Customer> custQueue;
    private int maxQueueLength;
    private int customersServed;
    private int totalCustomersWaited;
 
    public CheckoutLane(int laneNum, double itemTime, double payTime, boolean isExpress) {
        this.laneNum = laneNum;
        this.itemTime = itemTime;
        this.payTime = payTime;
        this.isExpress = isExpress;
        this.custQueue = new LinkedList();
        this.customersServed = 0;
        this.maxQueueLength = 0;
        this.totalCustomersWaited = 0;
    }
    
    public int getLaneNum(){
        return this.laneNum;
    }
    
    public int getQueueLength(){
        return this.custQueue.size();
    }
    
    public int getMaxQueueLength(){
        return maxQueueLength;
    }
    
    public int getCustomersServed(){
        return customersServed;
    }
    
    public double getAverageWaitTime(){
        return (totalCustomerWaitTime / customersServed);
    }
    
    public double getAverageQueueSize(){
        return ((double)totalCustomersWaited / (double)customersServed);
    }
    
    //method determines pay time for each customer checkout and pay time
    private double processTime(Customer c){
        int custInQueue = this.custQueue.size();
        totalCustomersWaited += custInQueue;
        int i = 0;
        double totalTime = 0.0;
        double custWaitTime = 0.0;
        Queue<Customer> tempQueue = new LinkedList();
        while(i < custInQueue){
            Customer tempCust = this.custQueue.poll();
            tempQueue.offer(tempCust);
            
            totalTime += (tempCust.getNumItems() * this.itemTime);
            totalTime += this.payTime;
            
            //this statement determines if there is a customer in the line and
            //keeps track of the wait time accordingly
            if(i < (custInQueue - 1)){
                custWaitTime += (tempCust.getNumItems() * this.itemTime);
                custWaitTime += this.payTime;
            }
            
            i++;
        }
        
        this.custQueue = tempQueue;
        this.totalCustomerWaitTime += custWaitTime;
        c.setEndWaitTime(c.getArrivalTime() + c.getTotalShopTime() + custWaitTime);
        
        return totalTime;
    }

    //adding a customer to the queue for the line and incremints the total customers
    public void addCustomer(SimClock sc, Customer c, PriorityQueue<StoreEvent> pq){
        customersServed++;
        this.custQueue.add(c);
        
        int queueLength = this.getQueueLength();
        if(queueLength > this.maxQueueLength){
            this.maxQueueLength = queueLength;  
        }
        
        double custProcessTime = processTime(c);
        double time = c.getArrivalTime() + c.getTotalShopTime() + custProcessTime;
        c.setEndCheckoutTime(time);
        Leaving l = new Leaving(this, c, time);
        pq.offer(l);
    }
    
    //remove customer from queue
    public void removeCustomer(Customer cust){
        this.custQueue.remove(cust);
    }
    
    
    @Override
    public int compareTo(CheckoutLane cl) {
        if(this.custQueue.size() == cl.custQueue.size()){
            return 0;
        }
        else if(this.custQueue.size() < cl.custQueue.size()){
            return -1;
        }
        else{
            return 1;
        }
    }

     
}
