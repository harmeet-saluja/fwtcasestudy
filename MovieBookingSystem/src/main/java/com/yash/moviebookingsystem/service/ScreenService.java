package com.yash.moviebookingsystem.service;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	public int addScreen(Screen screen);

	public int addMovieToScreen(Screen screen, Movie movie);

}
