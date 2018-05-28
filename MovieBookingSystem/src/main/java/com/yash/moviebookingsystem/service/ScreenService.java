package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	public int addScreen(Screen screen);

	public int addMovieToScreen(Screen screen, Movie movie);

	public boolean addRowsToScreen(Screen screen, List<Row> rows);

	public List<Screen> getScreens();

	public void displaySittingArrangement(Screen screen);

}
