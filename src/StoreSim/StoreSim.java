package StoreSim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *@author Elliott Fries
 */
public class StoreSim {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        //creates queues, arraylists, and simclock
        int totalCustomers;
        ArrayList arlist = new ArrayList<Customer>();
        ArrayList colist = new ArrayList();
        PriorityQueue<StoreEvent> ar = new PriorityQueue();
        PriorityQueue<CheckoutLane> coRegular = new PriorityQueue();
        PriorityQueue<CheckoutLane> coExpress = new PriorityQueue();
        SimClock sc = new SimClock();
        PriorityQueue<StoreEvent> pq = new PriorityQueue<>();
        
        PrintWriter writer = new PrintWriter("log.txt");
        writer.println("SIMULATION RESULTS");
        writer.println("____________________");
        writer.println();
        
        //calls methods to get arrival and checkoutfiles
        arrivalFile(arlist);
        checkoutFile(coRegular, coExpress);
        
        totalCustomers = arlist.size();
        
        //removes from customer adds arrival
        while(!arlist.isEmpty()){
            Customer c = (Customer) arlist.remove(0);
            Arrival a = new Arrival(c);
            ar.offer(a);
        }
        
        pq.offer(ar.poll());
        
        //start sim
        while(!pq.isEmpty()) {
            StoreEvent se = pq.poll();
            sc.time(se.getTime());
            se.process(sc, pq, ar, coRegular, coExpress, writer);
        }
        
        writer.println();
        writer.println("Total Customers Served: " + totalCustomers);
        writer.println();
        System.out.println("Total Customers Served: " + totalCustomers);
        
        writer.println("QUEUE STATISTICS");
        writer.println("__________________");
        writer.println();
        
        //writes the regular lane queue stats to a file
        while(!coRegular.isEmpty()){
            CheckoutLane colane = coRegular.poll();
            writer.println("Regular Lane #" + colane.getLaneNum() + ": Customers Served: " + colane.getCustomersServed() + ", Max Queue Length: " + colane.getMaxQueueLength() + ", Average Wait Time: " + colane.getAverageWaitTime() + ", Average Queue Size: " + colane.getAverageQueueSize());
            System.out.println("Regular Lane #" + colane.getLaneNum() + ": Customers Served: " + colane.getCustomersServed() + ", Max Queue Length: " + colane.getMaxQueueLength() + ", Average Wait Time: " + colane.getAverageWaitTime() + ", Average Queue Size: " + colane.getAverageQueueSize());
        }
        
        //writes the express lane queue stats to a file
        while(!coExpress.isEmpty()){
            CheckoutLane colane = coExpress.poll();
            writer.println("Express Lane #" + colane.getLaneNum() + ": Customers Served: " + colane.getCustomersServed() + ", Max Queue Length: " + colane.getMaxQueueLength() + ", Average Wait Time: " + colane.getAverageWaitTime() + ", Average Queue Size: " + colane.getAverageQueueSize());
            System.out.println("Express Lane #" + colane.getLaneNum() + ": Customers Served: " + colane.getCustomersServed() + ", Max Queue Length: " + colane.getMaxQueueLength() + ", Average Wait Time: " + colane.getAverageWaitTime() + ", Average Queue Size: " + colane.getAverageQueueSize());
        }
        
        writer.close();
    }
    //reads in a file for customer data
    public static void arrivalFile(ArrayList arlist) throws FileNotFoundException {
        File arrival = new File("arrival.txt");
        try (Scanner in = new Scanner(arrival)) {
            if (!arrival.exists()) {
                System.err.println("That file does not exist");
                return;
            }
            
            while (in.hasNext()) {
                
                double arrive = in.nextDouble();
                int items = in.nextInt();
                double shop = in.nextDouble();
                
                Customer c = new Customer(arrive, items, shop);
                arlist.add(c);
                
                 
            }
        }
    }
    //reads in a file for checkout lane data
    public static void checkoutFile(PriorityQueue<CheckoutLane> coRegular, PriorityQueue<CheckoutLane> coExpress) throws FileNotFoundException {
        File checkout = new File("checkout.txt");
        try (Scanner in = new Scanner(checkout)) {
            if (!checkout.exists()) {
                System.err.println("That file does not exist");
                return;
            }
            
            int numReg = in.nextInt();
            
            for (int i = 0; i < numReg; i++) {
                double itemTime = in.nextDouble();
                double payTime = in.nextDouble();
                CheckoutLane cl = new CheckoutLane(i+1, itemTime, payTime, false);
                coRegular.offer(cl);
            }
            int numExp = in.nextInt();
            
            for (int i = 0; i < numExp; i++) {
                double itemTime = in.nextDouble();
                double payTime = in.nextDouble();
                CheckoutLane cl = new CheckoutLane(i+1, itemTime, payTime, true);
                coExpress.offer(cl);
            }
        }  
    } 
}