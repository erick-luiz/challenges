package br.com.reader.model;

public class Item {

	private static final int EXPECTED_ARRAY_LENGTH = 3;
	private static final int ID_INDEX = 0;
	private static final int QTD_INDEX = 1;
	private static final int PRICE_INDEX = 2;
	
	private int quantity;
	private Double price;
	
	public Item(String id, int quantity, Double price) {
		this.quantity = quantity;
		this.price = price;
	} 

	public static Item from(String[] data) {

		if(data == null || data.length < EXPECTED_ARRAY_LENGTH) {
			throw new IllegalArgumentException("Item.from - Invalid data.");
		}
		
		return new Item(data[ID_INDEX], Integer.valueOf(data[QTD_INDEX]), Double.valueOf(data[PRICE_INDEX]));
	}

	public double getTotalPrice() {
		return this.price * this.quantity;
	}
	
}
