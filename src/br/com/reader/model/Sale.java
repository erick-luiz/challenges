package br.com.reader.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	private static final int EXPECTED_ARRAY_LENGTH = 4;
	private static final int SALE_ID_INDEX = 1;
	private static final int ITEMS_INDEX = 2;
	private static final int SALESMAN_NAME_INDEX = 3;
	private static final String SALES_DELIMITER = ",";
	private static final String SALES_ITEMS_DELIMITER = "-";

	private String salesId; 
	private List<Item> items;
	private String salesmanName;
	private double total;
	
	public Sale(String saleId, List<Item> items, String salesmanName) {
		this.salesId = saleId;
		this.items = List.copyOf(items);
		this.salesmanName = salesmanName;
		this.total = fillTotal(this.items);
	}

	public static Sale from(String[] data) {

		if(data == null || data.length < EXPECTED_ARRAY_LENGTH) {
			throw new IllegalArgumentException("Sale.from - Invalid data.");
		}
		
		return new Sale(data[SALE_ID_INDEX], getItems(data[ITEMS_INDEX]), data[SALESMAN_NAME_INDEX]);
	}
	
	private static List<Item> getItems(String strItems){
		
		List<Item> items = new ArrayList<>();
		String[] split = strItems.substring(1, strItems.length() - 1).split(SALES_DELIMITER);
		
		for (String strItem : split) {
			Item item = Item.from(strItem.split(SALES_ITEMS_DELIMITER));
			items.add(item);
		}
		
		return items;
	}

	private static double fillTotal(List<Item> items){
		return items.stream().mapToDouble(Item::getTotalPrice).sum();
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public String getSalesId() {
		return this.salesId;
	}
	
	public String getSalesmanName() {
		return this.salesmanName;
	}

}
