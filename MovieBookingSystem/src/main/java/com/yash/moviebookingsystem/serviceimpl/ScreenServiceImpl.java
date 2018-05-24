package com.yash.moviebookingsystem.serviceimpl;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDAO) {
		this.screenDAO = screenDAO;
	}

	public int addScreen(Screen screen) {
		checkForNullScreen(screen);
		int rowsAffected = screenDAO.save(screen);
		return rowsAffected;
	}

	private void checkForNullScreen(Screen screen) {
		if (screen == null)
			throw new NullFieldException("Screen cannot be null");
	}

	public int addMovieToScreen(Screen screen, Movie movie) {
		checkForNullmovie(movie);
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

}
