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
		if (screen == null)
			throw new NullFieldException("Screen cannot be null");
		int rowsAffected = screenDAO.save(screen);
		return rowsAffected;
	}

	public int addMovieToScreen(Movie movie) {
		if (movie == null)
			throw new NullFieldException("Movie cannot be null");
		return 0;
	}

}
