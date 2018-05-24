package com.yash.moviebookingsystem.util;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.FileNameIsEmptyException;
import com.yash.moviebookingsystem.exception.FileNameIsNullException;

public class OperatorMenuUtilTest {

	@Test(expected = FileNameIsNullException.class)
	public void getOperatorMenu_FileNameIsNull_ThrowFileNameIsNullException() {
		String fileName = null;
		OperatorMenuUtil.getOperatorMenu(fileName);
	}

	@Test(expected = FileNameIsEmptyException.class)
	public void getOperatorMenu_FileNameIsEmpty_ThrowFileNameIsEmptyException() {
		String fileName = " ";
		OperatorMenuUtil.getOperatorMenu(fileName);
	}

	@Test(expected = NullPointerException.class)
	public void getOperatorMenu_FileNameIsWrong_ThrowNullPointerException() {
		String fileName = "OperatorFile.txt";
		OperatorMenuUtil.getOperatorMenu(fileName);
	}

	@Test()
	public void getOperatorMenu_FileNameIsCorrect_ShouldPrintMenu() {
		String fileName = "OperatorMenuFile.txt";
		OperatorMenuUtil.getOperatorMenu(fileName);
	}

}
