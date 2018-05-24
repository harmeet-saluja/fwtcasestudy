package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Row;

public interface SittingArrangementService {

	public final String CATEGORY_GOLD = "GOLD";
	public final String CATEGORY_SILVER = "SILVER";
	public final String CATEGORY_PLATINUM = "PLATINUM";

	public List<Row> createArrangement(String category, Integer numberOfRowsInCategory, Integer numberOfSeatsInFirstRow);

}
