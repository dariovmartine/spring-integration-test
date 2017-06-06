package hello;

public class Item {

	long itemId;
	
	int quantity;
	
	private static final java.util.Random rand = new java.util.Random();

	public static Item getItem() {
		Item item = new Item();
		item.itemId = rand.nextInt(1000);
		item.quantity = rand.nextInt(10) + 1;
		return item;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", quantity=" + quantity + "]";
	}	
}
