package br.com.reader.model;

public class Client {

	private static final int EXPECTED_ARRAY_LENGTH = 4;
	private static final int ID_INDEX = 0;
	private static final int CNPJ_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int BUSINESS_AREA_INDEX = 3;

	private String id;
	private String cnpj;
	private String name;
	private String businessArea;

	public Client(String id, String cnpj, String name, String businessArea) {
		this.id = id;
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public static Client from(String[] data) {

		if (data == null || data.length < EXPECTED_ARRAY_LENGTH) {
			throw new IllegalArgumentException("Client.from - Invalid data.");
		}

		return new Client(data[ID_INDEX], data[CNPJ_INDEX], data[NAME_INDEX], data[BUSINESS_AREA_INDEX]);
	}

	@Override
	public String toString() {
		return String.format("id: %s, cnpj: %s, nome: %s, area de negocio: %s", this.id, this.cnpj, this.name,
				this.businessArea);
	}

}
