package br.com.reader.model;

public class Salesman {

	private static final int EXPECTED_ARRAY_LENGTH = 4;
	private static final int ID_INDEX = 0;
	private static final int CPF_INDEX = 1;
	private static final int NAME_INDEX = 2;

	private String id;
	private String cpf;
	private String name;

	public Salesman(String id, String cpf, String name) {
		this.id = id;
		this.cpf = cpf;
		this.name = name;
	}
	
	public static Salesman from(String[] data) {

		if(data == null || data.length < EXPECTED_ARRAY_LENGTH) {
			throw new IllegalArgumentException("Salesman.from - Invalid data.");
		}

		return new Salesman(data[ID_INDEX], data[CPF_INDEX], data[NAME_INDEX]);
	}
	
	@Override
	public String toString() {
		return String.format("id: %s, CPF: %s, nome: %s", this.id, this.cpf, this.name);
	}
}
