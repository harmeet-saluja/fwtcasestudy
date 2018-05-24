package com.yash.moviebookingsystem;

import com.yash.moviebookingsystem.util.OperatorMenuUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static final String OPERATOR_MENU_FILE_NAME = "OperatorMenuFile.txt";

	public static void main(String[] args) {
		OperatorMenuUtil.getOperatorMenu(OPERATOR_MENU_FILE_NAME);
	}
}
