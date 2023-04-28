package ibf2022.assessment.paf.batch3.repositories;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Order;

@Repository
public class OrderRepository {

    @Autowired
    private MongoTemplate mongo;

    public String insertOrder(Order order) {
        // Generate the order ID
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);
        
        // Insert the order into the MongoDB
        mongo.insert(order);

        return orderId;

    }

	
}
