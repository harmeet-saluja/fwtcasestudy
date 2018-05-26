package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Show;

public interface MovieService {

	public final String AM = "AM";
	public final String PM = "PM";

	public boolean addShowsForMovie(Movie movie, List<Show> shows);

}
