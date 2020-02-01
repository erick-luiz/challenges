package br.com.reader.datatype;

import org.junit.Assert;
import org.junit.Test;

import br.com.reader.model.MenuFunction;

public class MenuFunctionTest {

	private static final int REPORT_GENERATOR_CODE = 1;
	private static final int EXIT_CODE = 0;
	private static final int DEFAULT_CODE = -1;
	private static final int UNEXISTENT_CODE = -1;

	@Test
	public void getReportGeneratorTest() {
		Assert.assertEquals(MenuFunction.get(REPORT_GENERATOR_CODE), MenuFunction.REPORT_GENERATOR);
	}
	
	@Test
	public void exitSystemTest() {
		Assert.assertEquals(MenuFunction.get(EXIT_CODE), MenuFunction.EXIT);
	}
	
	@Test
	public void defaultOptionTest() {
		Assert.assertEquals(MenuFunction.get(DEFAULT_CODE), MenuFunction.DEFAULT);
		Assert.assertEquals(MenuFunction.get(UNEXISTENT_CODE), MenuFunction.DEFAULT);
	}
	
}
