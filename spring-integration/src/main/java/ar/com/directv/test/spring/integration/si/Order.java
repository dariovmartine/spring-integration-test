package ar.com.directv.test.spring.integration.si;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items = new ArrayList<>();
    
    private long clientId;
    
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
