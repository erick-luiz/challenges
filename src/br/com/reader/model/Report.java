package br.com.reader.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Report implements Reportable{

	private List<Client> clients = new ArrayList<>();
	private List<Sale> sales = new ArrayList<>();
	private List<Salesman> salesmen = new ArrayList<>();

	public void add(Sale sale) {
		this.sales.add(sale);
	}

	public void add(Client client) {
		this.clients.add(client);
	}

	public void add(Salesman salesman) {
		this.salesmen.add(salesman);
	}

	public String getWorseSale() {
		
		Map<String, Double> salesmanResult = new HashMap<>();
		
		for(Sale sale: this.sales) {
			
			if(salesmanResult.containsKey(sale.getSalesmanName())) {
				salesmanResult.put(sale.getSalesmanName(), sale.getTotal() + salesmanResult.get(sale.getSalesmanName()));
			}else {
				salesmanResult.put(sale.getSalesmanName(), sale.getTotal());
			}
		}

		return getWorseSale(salesmanResult);

	}
	
	private static String getWorseSale(Map<String, Double> salesmanResult) {
		
		String worse = "";
		Double worseValue = Double.MAX_VALUE;

		for (Entry<String, Double> entry : salesmanResult.entrySet()) {
			if(entry.getValue() < worseValue) {
				worse = entry.getKey();
			}
		}
		
		return worse;
	}
	
	@Override
	public String toString() {
		return String.format("%s %n %s %n %s %n", this.clients.toString(), this.sales.toString(), this.salesmen.toString());
	}

	public int getClientsQtd() {
		return this.clients.size();
	}

	public int getSalesmenQtd() {
		return this.salesmen.size();
	}

	public Sale getMoreExpensiveSale() {
		return this.sales.parallelStream().max((s, s1) -> Double.compare(s.getTotal(), s1.getTotal())).orElse(null);
	}
}
