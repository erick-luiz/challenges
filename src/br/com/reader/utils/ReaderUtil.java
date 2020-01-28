package br.com.reader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import br.com.reader.model.Client;
import br.com.reader.model.Report;
import br.com.reader.model.Sale;
import br.com.reader.model.Salesman;

public final class ReaderUtil {

	private static final String INPUT_DATA_TYPE = ".dat";
	private static final String OS_HOMEPATH_VAR_NAME = "HOMEPATH";
	private static final String OS_DIVISOR = "\\";

	private static final String DATA_DELIMITER = "ç";
	private static final String SALE_TYPE = "003";
	private static final String CLIENT_TYPE = "002";
	private static final String SALESMAN_TYPE = "001";

	private ReaderUtil() {
	}

	private static final String PATH_IN;
	private static final String PATH_OUT;

	static {
		PATH_IN = System.getenv(OS_HOMEPATH_VAR_NAME) + OS_DIVISOR + "data" + OS_DIVISOR + "in";
		PATH_OUT = System.getenv(OS_HOMEPATH_VAR_NAME) + OS_DIVISOR + "data" + OS_DIVISOR + "out";
	}

	public static List<String> getFileList() {

		File file = new File(PATH_IN);

		if (!file.exists()) {
			throw new RuntimeException("Folder dosn't exists.");
		}

		String[] paths = file.list();

		List<String> listPaths = Arrays.asList(paths);

		return listPaths.parallelStream().filter(getFileDat()).collect(Collectors.toList());

	}

	private static Predicate<? super String> getFileDat() {
		return p -> p.endsWith(INPUT_DATA_TYPE);
	}

	public static Optional<Report> readFile(String path) throws FileNotFoundException {

		Report report = new Report();

		try (Scanner reader = new Scanner(new File(PATH_IN + OS_DIVISOR + path))) {

			while (reader.hasNextLine()) {
				String[] data = reader.nextLine().split(DATA_DELIMITER);
				fillReportData(report, data);
			}
		}

		return Optional.of(report);

	}

	public static void createFile(Object data, String fileName) throws IOException {
		createFile(data.toString(), fileName);
	}

	public static void createFile(String data, String fileName) throws IOException {

		File file = new File(PATH_OUT + OS_DIVISOR + fileName);

		if(!file.exists() && !file.createNewFile()) {
			throw new IOException(String.format("Wasn't possible create the file's called %s", fileName));
		}

		try (FileWriter writer = new FileWriter(PATH_OUT + OS_DIVISOR + fileName)) {
			writer.write(data);
		}
	}

	private static void fillReportData(Report report, String[] data) {
		switch (data[0]) {
			case SALESMAN_TYPE:
				report.add(Salesman.from(data));
				break;
			case CLIENT_TYPE:
				report.add(Client.from(data));
				break;
			case SALE_TYPE:
				report.add(Sale.from(data));
				break;
			default:
				Logger.getAnonymousLogger().log(Level.SEVERE, 
						String.format("invalid data! %s", data[0]));
				break;
		}
	}
}
