package br.com.reader;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.reader.model.Report;
import br.com.reader.model.ReportOutDTO;
import br.com.reader.utils.ReaderUtil;

public class Main {

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			int exec = 1;

			while (exec != 0) {
				showMenu();
				int option = scan.nextInt();

				switch (option) {
					case 1:
						proccessReport();
						break;
					case 0:
						exec = 0;
						break;
					default:
						System.exit(-1);
					}
			}
			System.exit(0);
		} catch (Exception e) {
			System.exit(-1);
		}
	}

	private static void proccessReport() {
		try {
			for (String path : ReaderUtil.getFileList()) {
				Optional<Report> optRead = ReaderUtil.readFile(path);
				if (optRead.isPresent()) {
					ReaderUtil.createFile(ReportOutDTO.from(optRead.get()), path);
				}
			}
		} catch (IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE,
					String.format("Erro no processameno do relatório, tente novamente mais tarde!%n %s %n", e.getMessage()));
		}
	}

	private static void showMenu() {
		System.out.println("Selecione a opção \n1- Gerar relatório\n0- Fechar programa");
	}
}
