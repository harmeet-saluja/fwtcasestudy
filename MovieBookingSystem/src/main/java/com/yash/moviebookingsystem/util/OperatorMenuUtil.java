package com.yash.moviebookingsystem.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.yash.moviebookingsystem.exception.FileNameIsEmptyException;
import com.yash.moviebookingsystem.exception.FileNameIsNullException;

public class OperatorMenuUtil {

	private OperatorMenuUtil() {

	}

	public static void getOperatorMenu(String fileName) {
		checkForNullFileName(fileName);
		checkForEmptyFileName(fileName);

		File operatorMenuFile = new File(OperatorMenuUtil.class.getResource("/files/" + fileName).getFile());
		printOperatorMenu(operatorMenuFile);

	}

	private static void printOperatorMenu(File operatorMenuFile) {
		BufferedReader readOperatorMenu = null;

		try {
			readOperatorMenu = new BufferedReader(new FileReader(operatorMenuFile));
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		readOperatorMenuFile(readOperatorMenu);
	}

	private static void readOperatorMenuFile(BufferedReader readOperatorMenu) {
		String line;
		try {
			line = readOperatorMenu.readLine();
			while (line != null) {
				System.out.println(line);
				line = readOperatorMenu.readLine();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void checkForEmptyFileName(String fileName) {
		if (fileName.trim().equals(""))
			throw new FileNameIsEmptyException("File Name should not be empty");
	}

	private static void checkForNullFileName(String fileName) {
		if (fileName == null)
			throw new FileNameIsNullException("File Name should not be null");
	}

}
