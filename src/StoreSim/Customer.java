
package StoreSim;

/**
 *
 * @author Elliott
 */
public class Customer {
    
    private int custNum;
    private double arrivalTime;
    private int numItems;
    private double shopTime;
    private double endShopTime;
    private double endWaitTime;
    private double endCheckoutTime;

    @Override
    public String toString() {
        return "Customer{" + "arrivalTime=" + arrivalTime + ", numItems=" + numItems + ", shopTime=" + shopTime + '}';
    }

    
    public Customer(double arrivalTime, int numItems, double shopTime) {
        this.arrivalTime = arrivalTime;
        this.numItems = numItems;
        this.shopTime = shopTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }
    
    public int getCustNum() {
        return custNum;
    }
    
    public void setCustNum(int custNum) {
        this.custNum = custNum;
    }

    public int getNumItems() {
        return numItems;
    }

    public double getShopTime() {
        return shopTime;
    }
    
    public double getTotalShopTime() {
        return shopTime * numItems;
    }
    
    public void setEndShopTime(double endShopTime) {
        this.endShopTime = endShopTime;
    }
    
    public double getEndShopTime() {
        return endShopTime;
    }
    
    public void setEndWaitTime(double endWaitTime) {
        this.endWaitTime = endWaitTime;
    }
    
    public double getEndWaitTime() {
        return endWaitTime;
    }
    
    public void setEndCheckoutTime(double endCheckoutTime) {
        this.endCheckoutTime = endCheckoutTime;
    }
    
    public double getEndCheckoutTime() {
        return endCheckoutTime;
    }
    
    //does calculation to determine how long a customer will be shopping
    public double timeShopping() {
        double finished = numItems * shopTime;
        return finished;
    }

    
    
}
