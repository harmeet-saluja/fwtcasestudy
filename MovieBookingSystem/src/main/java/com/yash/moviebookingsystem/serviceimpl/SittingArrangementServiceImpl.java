package com.yash.moviebookingsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.exception.FieldLimitExceededException;
import com.yash.moviebookingsystem.exception.FieldSizeIsLessException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class SittingArrangementServiceImpl implements SittingArrangementService {

	public List<Row> createSittingArrangementForCategory(String category, Integer numberOfRowsInCategory,
			Integer numberOfSeatsInFirstRow) {
		checkForNullParameters(category, numberOfRowsInCategory, numberOfSeatsInFirstRow);
		checkForNumberOfRowsInCategoryNotLessThanSixAndNotMoreThanTen(numberOfRowsInCategory);
		checkForNumberOfSeatsInFirstRowNotLessThanTwelveNotMoreThanThirty(numberOfSeatsInFirstRow);

		List<Row> rowsInCategory = makeRowsAndAssignSeatsToEachRow(category, numberOfRowsInCategory,
				numberOfSeatsInFirstRow);

		return rowsInCategory;
	}

	private List<Row> makeRowsAndAssignSeatsToEachRow(String category, Integer numberOfRowsInCategory,
			Integer numberOfSeatsInFirstRow) {
		List<Row> rowsInCategory = new ArrayList<Row>();
		List<Seat> seats = null;
		for (int i = 0; i < numberOfRowsInCategory; i++) {
			rowsInCategory.add(new Row((i + 1), category));
			seats = new ArrayList<Seat>();
			rowsInCategory.get(i).setSeats(seats);
			rowsInCategory.get(i).setCategory(category);
			assignSeatsToEachRow(numberOfSeatsInFirstRow, seats);
			rowsInCategory.get(i).setSeats(seats);
			numberOfSeatsInFirstRow = numberOfSeatsInFirstRow - 2;
		}
		return rowsInCategory;
	}

	private void assignSeatsToEachRow(Integer numberOfSeatsInFirstRow, List<Seat> seats) {
		for (int j = 0; j < numberOfSeatsInFirstRow; j++) {
			Seat seat = new Seat(j + 1, false);
			seats.add(seat);
		}
	}

	private void checkForNumberOfSeatsInFirstRowNotLessThanTwelveNotMoreThanThirty(Integer numberOfSeatsInFirstRow) {
		if (numberOfSeatsInFirstRow > 30)
			throw new FieldLimitExceededException("Number of seats in first row should not be more than 30.");
		if (numberOfSeatsInFirstRow < 12)
			throw new FieldSizeIsLessException("Number of seats in first row cannot be less than 12");
	}

	private void checkForNumberOfRowsInCategoryNotLessThanSixAndNotMoreThanTen(Integer numberOfRowsInCategory) {
		if (numberOfRowsInCategory > 10)
			throw new FieldLimitExceededException("Number of rows should not be more than 10.");
		if (numberOfRowsInCategory < 6)
			throw new FieldSizeIsLessException("Number of rows cannot be less than 6");
	}

	private void checkForNullParameters(String category, Integer numberOfRowsInCategory,
			Integer numberOfSeatsInFirstRow) {
		if (category == null || numberOfRowsInCategory == null || numberOfSeatsInFirstRow == null)
			throw new NullFieldException("Provide all fields.");
	}

}
