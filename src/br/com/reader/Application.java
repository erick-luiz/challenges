package br.com.reader;

import java.util.Scanner;

import br.com.reader.model.MenuFunction;
import br.com.reader.utils.ReaderViewUtils;

public final class Application {

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {

			while (true) {
				ReaderViewUtils.showMenu();
				MenuFunction.get(scan.nextInt()).process();
			}

		} catch (Exception e) {
			System.exit(-1);
		}
	}



}
