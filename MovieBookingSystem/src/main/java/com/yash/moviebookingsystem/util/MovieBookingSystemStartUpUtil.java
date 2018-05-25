package com.yash.moviebookingsystem.util;

public class MovieBookingSystemStartUpUtil {

	private static final String OPERATOR_MENU_FILE_NAME = "OperatorMenuFile.txt";

	public static void startApplication() {
		OperatorMenuUtil.getOperatorMenu(OPERATOR_MENU_FILE_NAME);
	}

}
