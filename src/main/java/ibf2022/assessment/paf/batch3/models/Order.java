package ibf2022.assessment.paf.batch3.models;

import java.util.Date;
import java.util.List;

import ibf2022.assessment.paf.batch3.services.OrderItem;

public class Order {
    private String id;
    private String orderId;
    private String breweryId;
    private Date orderDate;
    private List<OrderItem> orders;
    
    public Order(String id, String orderId, String breweryId, Date orderDate, List<OrderItem> orders) {
        this.id = id;
        this.orderId = orderId;
        this.breweryId = breweryId;
        this.orderDate = orderDate;
        this.orders = orders;
    }
    public Order() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(String breweryId) {
        this.breweryId = breweryId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public List<OrderItem> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
    
}
