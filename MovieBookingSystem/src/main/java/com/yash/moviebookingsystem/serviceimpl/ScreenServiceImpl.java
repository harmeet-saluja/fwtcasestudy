package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.DataAlreadyExistsException;
import com.yash.moviebookingsystem.exception.ListSizeExceededException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class ScreenServiceImpl implements ScreenService {

	ScreenDAO screenDAO;
	SittingArrangementService sittingArrangementService;

	public ScreenServiceImpl(ScreenDAO screenDAO, SittingArrangementService sittingArrangementService) {
		this.screenDAO = screenDAO;
		this.sittingArrangementService = sittingArrangementService;
	}

	public int addScreen(Screen screen) {
		checkForNullScreen(screen);
		List<Screen> screens = screenDAO.retrieve();
		checkForTotalScreensNotMoreThanThree(screens);
		checkForDuplicateScreenName(screen, screens);
		int rowsAffected = screenDAO.save(screen);
		return rowsAffected;
	}

	private void checkForDuplicateScreenName(Screen screen, List<Screen> screens) {
		for (Screen screenPresentInFile : screens) {
			if (screen.getName().equals(screenPresentInFile.getName()))
				throw new DataAlreadyExistsException("Screen with given name already exists");
		}
	}

	private void checkForTotalScreensNotMoreThanThree(List<Screen> screens) {
		if (screens.size() > 2)
			throw new ListSizeExceededException("There cannot be more than three screens");
	}

	private void checkForNullScreen(Screen screen) {
		if (screen == null)
			throw new NullFieldException("Screen cannot be null");
	}

	public int addMovieToScreen(Screen screen, Movie movie) {
		checkForNullmovie(movie);
		checkForNullScreen(screen);
		int rowsAffected = 0;
		screen.setMovie(movie);
		int result = screenDAO.update(screen);
		if (result > 0)
			rowsAffected = result;
		return rowsAffected;
	}

	private void checkForNullmovie(Movie movie) {
		if (movie == null)
			throw new NullFieldException("Movie cannot be null");
	}

	public boolean addRowsToScreen(Screen screen, List<Row> rows) {
		checkForNullRows(rows);
		boolean areRowsAdded = false;
		screen.setRows(rows);
		if (screen.getRows().size() == rows.size())
			areRowsAdded = true;
		return areRowsAdded;
	}

	private void checkForNullRows(List<Row> rows) {
		if (rows == null)
			throw new NullFieldException("Rows cannot be null");
	}

}
