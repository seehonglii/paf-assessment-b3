package ibf2022.assessment.paf.batch3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository oRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(String breweryId, Map<String, Integer> orderQuantities) {
		// TODO: Task 5 
		Order order = new Order();
        order.setBreweryId(breweryId);
        order.setOrderDate(new Date());

        List<OrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : orderQuantities.entrySet()) {
            String beerId = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > 0) {
                OrderItem orderItem = new OrderItem();
                orderItem.setBeerId(beerId);
                orderItem.setQuantity(quantity);
                orderItems.add(orderItem);
            }
        }

        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("No order items found");
        }

        order.setOrders(orderItems);

        // Insert the order into the MongoDB
        String orderId = oRepo.insertOrder(order);

        return orderId;
    
    }
	
}
