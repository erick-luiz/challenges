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

import br.com.reader.constants.DataType;
import br.com.reader.model.Report;
import br.com.reader.model.ReportOutDTO;

public final class ReaderUtil {

	private static final String INPUT_DATA_TYPE = ".dat";
	private static final String OS_HOMEPATH_VAR_NAME = "HOMEPATH";
	private static final String OS_DIVISOR = "\\";
	private static final String DATA_DELIMITER = "ç";
	private static final String PATH_IN = System.getenv(OS_HOMEPATH_VAR_NAME) + OS_DIVISOR + "data" + OS_DIVISOR + "in";
	private static final String PATH_OUT = System.getenv(OS_HOMEPATH_VAR_NAME) + OS_DIVISOR + "data" + OS_DIVISOR + "out";

	private ReaderUtil() {}
	

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

		if (!file.exists() && !file.createNewFile()) {
			throw new IOException(String.format("Wasn't possible create the file's called %s", fileName));
		}

		try (FileWriter writer = new FileWriter(PATH_OUT + OS_DIVISOR + fileName)) {
			writer.write(data);
		}
	}

	private static void fillReportData(Report report, String[] data) {

		DataType dataProcessorType = DataType.getByCode(data[0]);

		if (dataProcessorType != null) {
			dataProcessorType.fillReportData(report, data);
			return;
		}

		Logger.getAnonymousLogger().log(Level.SEVERE, String.format("invalid data! %s", data[0]));
	}

	public static void proccessReport() {
		try {
			for (String path : ReaderUtil.getFileList()) {
				Optional<Report> optRead = ReaderUtil.readFile(path);
				if (optRead.isPresent()) {
					ReaderUtil.createFile(ReportOutDTO.from(optRead.get()), path);
				}
			}
		} catch (IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, String
					.format("Erro no processameno do relatório, tente novamente mais tarde!%n %s %n", e.getMessage()));
		}
	}
}
