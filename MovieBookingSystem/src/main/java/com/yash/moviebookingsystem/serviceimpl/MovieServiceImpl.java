package com.yash.moviebookingsystem.serviceimpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.exception.InvalidShowTimingException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.exception.ShowsOverlapException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.MovieService;

public class MovieServiceImpl implements MovieService {

	public boolean addShowsForMovie(Movie movie, List<Show> shows) {
		checkForNullShows(shows);
		checkForShowStarAndEndTime(shows);

		boolean areMoviesAdded;
		checkForOverlappingShows(shows);
		movie.setShows(new ArrayList<Show>());
		areMoviesAdded = movie.getShows().addAll(shows);
		return areMoviesAdded;
	}

	private void checkForOverlappingShows(List<Show> shows) {
		Show[] showsArray = (Show[]) shows.toArray();
		compareTimingOfAllShows(showsArray);
	}

	private void compareTimingOfAllShows(Show[] showsArray) {
		for (int i = 0; i < showsArray.length; i++) {
			LocalTime firstShowStartTime = LocalTime.parse(showsArray[i].getStartTime(),
					DateTimeFormatter.ofPattern("hh:mm a"));
			LocalTime firstShowEndTime = LocalTime.parse(showsArray[i].getEndTime(),
					DateTimeFormatter.ofPattern("hh:mm a"));
			for (int j = i + 1; j < showsArray.length; j++) {
				LocalTime secondShowStartTime = LocalTime.parse(showsArray[j].getStartTime(),
						DateTimeFormatter.ofPattern("hh:mm a"));
				LocalTime secondShowEndTime = LocalTime.parse(showsArray[j].getEndTime(),
						DateTimeFormatter.ofPattern("hh:mm a"));
				System.out.println(secondShowStartTime + " " + secondShowEndTime);
				if ((firstShowStartTime.compareTo(secondShowStartTime) < 0
						&& firstShowEndTime.compareTo(secondShowStartTime) > 0)) {
					System.out.println(showsArray[i] + " and " + showsArray[j] + "Overlap");
					throw new ShowsOverlapException(showsArray[i] + " and " + showsArray[j] + " Overlap");
				}
			}
		}
	}

	private void checkForShowStarAndEndTime(List<Show> shows) {
		for (Show show : shows) {
			LocalTime showStartTime = LocalTime.parse(show.getStartTime(), DateTimeFormatter.ofPattern("hh:mm a"));
			LocalTime showEndTime = LocalTime.parse(show.getEndTime(), DateTimeFormatter.ofPattern("hh:mm a"));
			if (showEndTime.compareTo(LocalTime.parse("00:00")) > 0)
				if (showStartTime.compareTo(showEndTime) > 0)
					throw new InvalidShowTimingException("Show start time should be small than show end time.");
		}
	}

	private void checkForNullShows(List<Show> shows) {
		if (shows == null)
			throw new NullFieldException("Shows cannot be null");
	}

}
