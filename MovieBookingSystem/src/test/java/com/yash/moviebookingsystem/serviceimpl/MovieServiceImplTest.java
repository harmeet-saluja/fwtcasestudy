package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.exception.InvalidShowTimingException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.exception.ShowsOverlapException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.MovieService;

public class MovieServiceImplTest {

	MovieService movieService = null;
	Movie movie = null;

	@Before
	public void setUp() throws Exception {
		this.movieService = new MovieServiceImpl();
		movie = new Movie(1, "PK", "Aamir", Arrays.asList("Aamir"));
	}

	@Test(expected = NullFieldException.class)
	public void addShowsForMovie_ShowsIsNull_ThrowNullFieldException() {
		List<Show> shows = null;
		movieService.addShowsForMovie(movie, shows);
	}

	@Test(expected = InvalidShowTimingException.class)
	public void addShowsForMovie_AnyShowHasStartTimeValueAfterEndTime_ThrowInvalidShowTimingException() {
		List<Show> shows = Arrays.asList(new Show(1, "12:00 PM", "03:00 PM"), new Show(2, "09:00 PM", "06:00 PM"));
		movieService.addShowsForMovie(movie, shows);
	}

	@Test(expected = ShowsOverlapException.class)
	public void addShowsForMovie_TwoOrMoreShowsOverlap_ThrowShowsOverlapException() {
		List<Show> shows = Arrays.asList(new Show(1, "09:00 AM", "11:30 AM"), new Show(2, "12:00 PM", "03:00 PM"),
				new Show(2, "03:00 PM", "06:00 PM"), new Show(2, "06:00 PM", "09:30 PM"),
				new Show(5, "09:00 PM", "11:30 PM"));
		movieService.addShowsForMovie(movie, shows);
	}

	@Test
	public void addShowsForMovie_AllShowsGiven_ShouldReturnTrueWhenShowsAddedForMovie() {
		List<Show> shows = Arrays.asList(new Show(1, "09:00 AM", "11:30 AM"), new Show(2, "12:00 PM", "03:00 PM"),
				new Show(2, "03:00 PM", "06:00 PM"), new Show(2, "06:00 PM", "09:00 PM"),
				new Show(5, "09:00 PM", "12:00 AM"));
		boolean areShowsAdded = movieService.addShowsForMovie(movie, shows);
		assertTrue(areShowsAdded);
	}
}
