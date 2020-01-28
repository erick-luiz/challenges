package br.com.reader.model;

public class Salesman {

	private static final int EXPECTED_ARRAY_LENGTH = 4;
	private static final int ID_INDEX = 0;
	private static final int CPF_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int SALARY_INDEX = 3;

	private String id;
	private String cpf;
	private String name;
	private Double salary;

	public Salesman(String id, String cpf, String name, Double salary) {
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}
	
	public static Salesman from(String[] data) {

		if(data == null || data.length < EXPECTED_ARRAY_LENGTH) {
			throw new IllegalArgumentException("Salesman.from - Invalid data.");
		}

		return new Salesman(data[ID_INDEX], data[CPF_INDEX], data[NAME_INDEX], Double.valueOf(data[SALARY_INDEX]));
	}
	
	@Override
	public String toString() {
		return String.format("id: %s, CPF: %s, nome: %s", this.id, this.cpf, this.name);
	}
}
