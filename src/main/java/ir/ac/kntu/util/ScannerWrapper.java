package ir.ac.kntu.util;

import java.util.Scanner;

public class ScannerWrapper {
	private static ScannerWrapper instance = new ScannerWrapper();

	private Scanner scanner;

	private ScannerWrapper() {
		scanner = new Scanner(System.in);
	}

	public static ScannerWrapper getInstance() {
		return instance;
	}

	public String next() {
		return scanner.next();
	}

	public Double nextDouble() {
		return scanner.nextDouble();
	}

	public Integer nextInt() {
		return scanner.nextInt();
	}

	public void close() {
		scanner.close();
	}

	public String nextLine() {
		return scanner.nextLine();
	}
}