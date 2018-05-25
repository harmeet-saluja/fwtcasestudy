package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.exception.FieldLimitExceededException;
import com.yash.moviebookingsystem.exception.FieldSizeIsLessException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class SittingArrangementServiceImplTest {

	SittingArrangementService sittingArrangementService;

	@Before
	public void setUp() throws Exception {
		this.sittingArrangementService = new SittingArrangementServiceImpl();
	}

	@Test(expected = NullFieldException.class)
	public void createSittingArrangementForCategory_EitherOfThreeInputsIsNull_ThrowNullFieldException() {
		String category = null;
		Integer numberOfRowsInCategory = null;
		Integer numberOfSeatsInFirstRow = null;
		sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldLimitExceededException.class)
	public void createSittingArrangementForCategory_NumberOfRowsInGivenCategoryIsMoreThanTen_ThrowFieldLimitExceededException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 11;
		Integer numberOfSeatsInFirstRow = 25;
		sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldLimitExceededException.class)
	public void createSittingArrangementForCategory_NumberOfSeatsInFirstRowIsMoreThanThirty_ThrowFieldLimitExceededException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 9;
		Integer numberOfSeatsInFirstRow = 31;
		sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldSizeIsLessException.class)
	public void createSittingArrangementForCategory_NoOfRowsInGivenCategoryLessThanSix_ThrowFieldSizeIsLessException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 5;
		Integer numberOfSeatsInFirstRow = 12;
		sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);
	}

	@Test(expected = FieldSizeIsLessException.class)
	public void createSittingArrangementForCategory_NoOfSeatsInFirstRowLessThanTwelwe_ThrowFieldSizeIsLessException() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 6;
		Integer numberOfSeatsInFirstRow = 11;
		sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);
	}

	@Test
	public void createSittingArrangementForCategory_AllFieldsAreCorrect_ShouldReturnListOfRows() {
		String category = SittingArrangementService.CATEGORY_GOLD;
		Integer numberOfRowsInCategory = 6;
		Integer numberOfSeatsInFirstRow = 12;
		List<Row> rowsInCategory = sittingArrangementService.createSittingArrangementForCategory(category,
				numberOfRowsInCategory, numberOfSeatsInFirstRow);
		assertEquals(6, rowsInCategory.size());
	}

}
