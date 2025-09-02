/*
 * Problem :
 * You are running an e-commerce website for which a customer can create multiple order buckets.
 * Customer can either order items independently, or add them in a a bucket and order them or order a
 * bunch of buckets or both buckets and items in a single order.
 * Write the code to implement this logic, ensure to keep it open for future if a bucket can also contain other buckets
 * Implement a function getOrderValue() to return the total order value which is the sum of all the individual order items
 * Solution : 
 * Since this forms a tree like structure using the composite design pattern will help. 
 * We can consider the leaf to be a single item, a bucket can be the composite which will contain either all 
 * items or bunch of buckets. 
 * */

import java.util.* ;

/*
NOTE : We could have done the same stuff with abstract class and not used interface
But that would violate the Open/Closed Principle because it will then be closed for extension in future
Consider the fact that we might need to create a DiscountedBucket or GiftWrap later

In details, this would have caused us to look at the type of object while calculation of order value;
which then requires all the new classes to be accomodated to the if-else block
 */

interface OrderComponent {
    int getOrderValue()  ;
}

class OrderItem implements OrderComponent {
	private int price ;
    private int quantity ;

    OrderItem(int price, int quantity) {
        this.price = price ;
        this.quantity = quantity ;
    }
    public int getOrderValue(){
        return price * quantity ;
    }
}

class OrderBucket implements OrderComponent {
    private List<OrderComponent> items ;
    private int orderValue ;
    public OrderBucket() {
        items = new ArrayList<>() ; // either do it here or at line 43, if not done will get NullPointerException
        orderValue = 0 ;
    }

    public void addItem(OrderComponent item) {
        items.add(item) ;
        orderValue += item.getOrderValue() ;
    }

    public void removeItem(OrderComponent item) {
        items.remove(item) ;
        orderValue -= item.getOrderValue() ;
    }

    public int getOrderValue() {
        return orderValue ;
    }
}

/*
NOTE : It might look like creating the below client is overkill as we could have just extended the OrderBucket
 and added the functionality of execute order on it but consider the back that bucket might have changes later on
 in which a coupon could be added which may or may not apply on the OrderClient.
 */
class OrderClient {
    private List<OrderComponent> items  = new ArrayList<>();
    private int orderValue ;
    OrderClient(List<OrderComponent> items) {
        for (OrderComponent item : items) {
            addItem(item) ;
        }
    }

    public void addItem(OrderComponent item) {
        items.add(item) ;
        orderValue += item.getOrderValue() ;
    }

    public void removeItem(OrderComponent item) {
        items.remove(item) ;
        orderValue -= item.getOrderValue() ;
    }

    // Recalculating final order value, we can add discounts here as well
    public int getOrderValue() {
        int value = 0 ;
        for (OrderComponent item : items) {
            value += item.getOrderValue();
        }
        return value ;
    }

    public void executeOrder(){
        // send the request of order value to Payment Gateway or Order confirmation service
        System.out.println("Processing your order..");
        int finalOrderValue = getOrderValue() ;
        System.out.println("Your final order value is : " + finalOrderValue);
    }
}


public class composite {
    public static void main(String[] args) {
        OrderItem pant = new OrderItem(1000, 1) ;
        OrderItem shirt = new OrderItem(350, 2) ;
        OrderBucket orderBucket = new OrderBucket() ;

        orderBucket.addItem(pant) ;
        orderBucket.addItem(shirt) ;

        OrderItem tv = new OrderItem(50000, 1) ;

        OrderClient client = new OrderClient(List.of(orderBucket, tv)) ;
        client.executeOrder();
    }
}