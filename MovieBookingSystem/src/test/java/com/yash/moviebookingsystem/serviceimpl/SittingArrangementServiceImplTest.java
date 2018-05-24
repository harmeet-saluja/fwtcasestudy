package com.yash.moviebookingsystem.serviceimpl;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.FieldLimitExceededException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class SittingArrangementServiceImplTest {

	@Test(expected = NullFieldException.class)
	public void createArrangement_EitherOfThreeInputsIsNull_ThrowNullFieldException() {
		String category = null;
		Integer numberOfRowsInCategory = null;
		Integer numberOfSeatsInFirstRow = null;
		SittingArrangementService sittingArrangementService = new SittingArrangementServiceImpl();
		sittingArrangementService.createArrangement(category, numberOfRowsInCategory, numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldLimitExceededException.class)
	public void createArrangement_NumberOfRowsInGivenCategoryIsMoreThanTen_ThrowFieldLimitExceededException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 11;
		Integer numberOfSeatsInFirstRow = 25;
		SittingArrangementService sittingArrangementService = new SittingArrangementServiceImpl();
		sittingArrangementService.createArrangement(category, numberOfRowsInCategory, numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldLimitExceededException.class)
	public void createArrangement_NumberOfSeatsInFirstRowIsMoreThanThirty_ThrowFieldLimitExceededException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 9;
		Integer numberOfSeatsInFirstRow = 31;
		SittingArrangementService sittingArrangementService = new SittingArrangementServiceImpl();
		sittingArrangementService.createArrangement(category, numberOfRowsInCategory, numberOfSeatsInFirstRow);
	}

}
