package ar.com.directv.test.spring.integration.si;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items = new ArrayList<>();
    
    private long clientId;
            
    private static final java.util.Random rand = new java.util.Random();
    
    public static Order getOrder() {
    	Order order = new Order();
    	order.clientId = rand.nextInt(1000000);
    	int orderItemsSize = rand.nextInt(10);
    	for (int i=0; i < orderItemsSize; i++) {
    		order.items.add(Item.getItem());
    	}
    	return order;
    }

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "Order [items=" + items + ", clientId=" + clientId + "]";
	}
	
	
}
