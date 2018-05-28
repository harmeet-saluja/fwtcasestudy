package com.yash.moviebookingsystem.serviceimpl;

import java.util.Iterator;
import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.DataAlreadyExistsException;
import com.yash.moviebookingsystem.exception.ListSizeExceededException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.service.MovieService;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;
	private SittingArrangementService sittingArrangementService;
	private MovieService movieService;

	public ScreenServiceImpl(ScreenDAO screenDAO, SittingArrangementService sittingArrangementService,
			MovieService movieService) {
		this.screenDAO = screenDAO;
		this.sittingArrangementService = sittingArrangementService;
		this.movieService = movieService;
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
		if (screens != null) {
			for (Screen screenPresentInFile : screens) {
				if (screen.getName().equals(screenPresentInFile.getName()))
					throw new DataAlreadyExistsException("Screen with given name already exists");
			}
		}
	}

	private void checkForTotalScreensNotMoreThanThree(List<Screen> screens) {
		if (screens != null) {
			if (screens.size() > 2)
				throw new ListSizeExceededException("There cannot be more than three screens");
		}
	}

	private void checkForNullScreen(Screen screen) {
		if (screen == null)
			throw new NullFieldException("Screen cannot be null");
	}

	public int addMovieToScreen(Screen screen, Movie movie) {
		checkForNullmovie(movie);
		checkForNullScreen(screen);
		if (movie.getShows() == null)
			throw new NullFieldException("Shows in movies cannot be null");
		int rowsAffected = 0;
		screen.setMovie(movie);
		System.out.println(screen);
		System.out.println(movie);
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
		boolean areRowsAdded = false;
		if (screen.getRows() == null)
			screen.setRows(rows);
		else {
			screen.getRows().addAll(rows);
		}
		screenDAO.update(screen);
		if (screen.getRows().size() == rows.size())
			areRowsAdded = true;
		return areRowsAdded;
	}

	public List<Screen> getScreens() {
		return screenDAO.retrieve();
	}

	public void displaySittingArrangement(Screen screen) {
		System.out.println(
				"-----------------------------------------Screen This Way--------------------------------------------");
		for (int rowNum = 0; rowNum < screen.getRows().size(); rowNum++) {
			Row row = screen.getRows().get(rowNum);
			System.out.print(row.getCategory() + ":" + (rowNum + 1));
			for (Seat seat : row.getSeats()) {
				System.out.print(seat + "\t");
			}
			System.out.println();
		}
	}
}
