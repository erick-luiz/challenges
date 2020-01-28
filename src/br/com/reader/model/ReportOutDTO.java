package br.com.reader.model;

public class ReportOutDTO {
	
	private int clientsQuantity;
	private int salesmenQuantity;
	private String moreExpensiveSaleId;
	private String worseSale;
	
	public static ReportOutDTO from(Report report) {

		ReportOutDTO reportOutDTO = new ReportOutDTO();
		reportOutDTO.setClientsQuantity(report.getClientsQtd());
		reportOutDTO.setSalesmenQuantity(report.getSalesmenQtd());

		Sale sale = report.getMoreExpensiveSale();
		if(sale != null) {
			reportOutDTO.setMoreExpensiveSaleId(sale.getSalesId());
		}
		
		reportOutDTO.setWorseSale(report.getWorseSale());

		return reportOutDTO;
	}

	private void setClientsQuantity(int clientsQuantity) {
		this.clientsQuantity = clientsQuantity;
	}

	private void setSalesmenQuantity(int salesmenQuantity) {
		this.salesmenQuantity = salesmenQuantity;
	}

	private void setMoreExpensiveSaleId(String moreExpensiveSaleId) {
		this.moreExpensiveSaleId = moreExpensiveSaleId;
	}

	private void setWorseSale(String worseSale) {
		this.worseSale = worseSale;
	}

	@Override
	public String toString() {
		
		return "Numero de clientes: " + clientsQuantity + "\nNumero de vendedores: " + salesmenQuantity
				+ "\nVenda mais Cara: " + moreExpensiveSaleId + "\nVendedor a receber orientação: " + worseSale;
	}
}
