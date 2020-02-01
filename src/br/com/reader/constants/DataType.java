package br.com.reader.constants;

import br.com.reader.model.Client;
import br.com.reader.model.Reportable;
import br.com.reader.model.Sale;
import br.com.reader.model.Salesman;

public enum DataType {

	SALESMAN("001") {
		@Override
		public void fillReportData(Reportable report, String[] data) {
			report.add(Salesman.from(data));
		}
	},
	CLIENT("002") {
		@Override
		public void fillReportData(Reportable report, String[] data) {
			report.add(Client.from(data));
		}
	},
	SALE("003") {
		@Override
		public void fillReportData(Reportable report, String[] data) {
			report.add(Sale.from(data));
		}
	};

	private final String code;

	DataType(String code) {
		this.code = code;
	}

	public static DataType getByCode(String code) {

		for (DataType dt : DataType.values()) {
			if (dt.code.equals(code)) {
				return dt;
			}
		}

		return null;
	}

	public abstract void fillReportData(Reportable report, String[] data);
}
