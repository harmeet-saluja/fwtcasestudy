package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;

import com.yash.moviebookingsystem.exception.FieldLimitExceededException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class SittingArrangementServiceImpl implements SittingArrangementService {

	public List<Row> createArrangement(String category, Integer numberOfRowsInCategory,
			Integer numberOfSeatsInFirstRow) {
		if (category == null || numberOfRowsInCategory == null || numberOfSeatsInFirstRow == null)
			throw new NullFieldException("Provide all fields.");
		if (numberOfRowsInCategory > 10 || numberOfSeatsInFirstRow > 30)
			throw new FieldLimitExceededException("Number of rows should not be more than 10.");
		return null;

	}

}
