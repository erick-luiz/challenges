package br.com.reader.datatype;

import org.junit.Assert;
import org.junit.Test;

import br.com.reader.constants.DataType;
import br.com.reader.model.Report;

public class DataTypeTest {

	private static final String SALESMAN_DATA_TYPE_CODE = "001";
	private static final String CLIENT_DATA_TYPE_CODE = "002";
	private static final String SALE_DATA_TYPE_CODE = "003";

	private static final String[] SALESMAN_DATA = new String[] { SALESMAN_DATA_TYPE_CODE, "1234567891234", "Pedro",
			"50000" };

	private static final String[] CLIENT_DATA = new String[] { CLIENT_DATA_TYPE_CODE, "2345675434544345",
			"Jose da Silva", "32.2" };

	private static final String[] SALE_DATA = new String[] { SALE_DATA_TYPE_CODE, "10",
			"[1-10-100,2-30-2.50,3-40-3.10]", "Paulo" };

	@Test
	public void dataTypeSalesmanTest() {
		Assert.assertEquals(DataType.getByCode(SALESMAN_DATA_TYPE_CODE), DataType.SALESMAN);
	}

	@Test
	public void dataTypeClientTest() {
		Assert.assertEquals(DataType.getByCode(CLIENT_DATA_TYPE_CODE), DataType.CLIENT);
	}

	@Test
	public void dataTypeSale() {
		Assert.assertEquals(DataType.getByCode(SALE_DATA_TYPE_CODE), DataType.SALE);
	}

	@Test
	public void dataTypeWithNullTest() {
		Assert.assertNull(DataType.getByCode(null));
	}

	@Test
	public void dataTypeWithAnotherType() {
		Assert.assertNull(DataType.getByCode("004"));
	}

	@Test
	public void dataTypeFillReportSalesmanData() {
		Report report = new Report();
		DataType.SALESMAN.fillReportData(report, SALESMAN_DATA);
		Assert.assertEquals(1, report.getSalesmenQtd());
	}

	@Test
	public void dataTypeFillReportClientData() {
		Report report = new Report();
		DataType.CLIENT.fillReportData(report, CLIENT_DATA);
		Assert.assertEquals(1, report.getClientsQtd());
	}

}
