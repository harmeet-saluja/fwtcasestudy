package com.yash.moviebookingsystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.daoimpl.ScreenDAOImpl;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.MovieService;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SittingArrangementService;
import com.yash.moviebookingsystem.serviceimpl.MovieServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.ScreenServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.SittingArrangementServiceImpl;

public class MovieBookingSystemStartUpUtil {

	private static final String OPERATOR_MENU_FILE_NAME = "OperatorMenuFile.txt";
	private static ScreenService screenService;
	private static MovieService movieService;
	private static SittingArrangementService sittingArrangementService;
	private static ScreenDAO screenDAO;

	static {
		screenDAO = new ScreenDAOImpl();
		movieService = new MovieServiceImpl();
		sittingArrangementService = new SittingArrangementServiceImpl();
		screenService = new ScreenServiceImpl(screenDAO, sittingArrangementService, movieService);
	}

	public static void startApplication() {
		Scanner userInput = new Scanner(System.in);
		String cont = null;
		do {
			OperatorMenuUtil.getOperatorMenu(OPERATOR_MENU_FILE_NAME);
			int choice = userInput.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Screen Id:");
				int screenId = userInput.nextInt();
				System.out.println("Enter Screen Name:");
				userInput.nextLine();
				String screenName = userInput.nextLine();
				Screen screen = new Screen(screenId, screenName);
				System.out.println(screen);
				screenService.addScreen(screen);
				break;

			case 2:
				System.out.println("Select Screen(Enter id of screen):");
				List<Screen> screens = screenService.getScreens();
				Screen selectedScreenToAddMovie = selectScreen(userInput, screens);
				System.out.println("Enter Movie ID");
				int movieId = userInput.nextInt();
				System.out.println("Enter Movie Title");
				userInput.nextLine();
				String movieTitle = userInput.nextLine();
				System.out.println("Enter Movie Production");
				String movieProduction = userInput.nextLine();
				System.out.println("Enter number of actors in movie:");
				List<String> movieActors = setMovieActors(userInput);
				List<Show> shows = setMovieShows(userInput);
				Movie movie = new Movie(movieId, movieTitle, movieProduction, movieActors, shows);
				screenService.addMovieToScreen(selectedScreenToAddMovie, movie);
				break;

			case 3:
				System.out.println("Select Screen(Enter id of screen):");
				List<Screen> screensAvailable = screenService.getScreens();
				Screen selectedScreenToAddSittingArrangement = selectScreen(userInput, screensAvailable);
				System.out.println("Enter number of rows in gold category:");
				int numberOfRowsInCategory = userInput.nextInt();
				System.out.println("Enter number seats in first row in gold category:");
				int numberOfSeatsInFirstRow = userInput.nextInt();
				List<Row> rows = sittingArrangementService.createSittingArrangementForCategory(
						SittingArrangementService.CATEGORY_GOLD, numberOfRowsInCategory, numberOfSeatsInFirstRow);
				screenService.addRowsToScreen(selectedScreenToAddSittingArrangement, rows);
				System.out.println("Enter number of rows in silver category:");
				numberOfRowsInCategory = userInput.nextInt();
				System.out.println("Enter number seats in first row in silver category:");
				numberOfSeatsInFirstRow = userInput.nextInt();
				rows = sittingArrangementService.createSittingArrangementForCategory(
						SittingArrangementService.CATEGORY_SILVER, numberOfRowsInCategory, numberOfSeatsInFirstRow);
				screenService.addRowsToScreen(selectedScreenToAddSittingArrangement, rows);
				System.out.println("Enter number of rows in platinum category:");
				numberOfRowsInCategory = userInput.nextInt();
				System.out.println("Enter number seats in first row in platinum category:");
				numberOfSeatsInFirstRow = userInput.nextInt();
				rows = sittingArrangementService.createSittingArrangementForCategory(
						SittingArrangementService.CATEGORY_PLATINUM, numberOfRowsInCategory, numberOfSeatsInFirstRow);
				screenService.addRowsToScreen(selectedScreenToAddSittingArrangement, rows);
				System.out.println("Sitting arrangement created");
				screenService.displaySittingArrangement(selectedScreenToAddSittingArrangement);
				break;

			case 4:
				List<Movie> moviesAvailable = new ArrayList<Movie>();
				Screen movieScreen = null;
				for (Screen screenRetrieved : screenService.getScreens()) {
					if (screenRetrieved.getMovie() != null) {
						movieScreen = screenRetrieved;
						Movie movieRetrieved = screenRetrieved.getMovie();
						moviesAvailable.add(movieRetrieved);
					}
				}
				System.out.println("Available movies-");
				for (Movie movieAvailable : moviesAvailable) {
					System.out.println(movieAvailable.getId() + ". " + movieAvailable.getTitle());
				}

				System.out.println("Enter id of movie to add shows:");
				int movieIdToAddShow = userInput.nextInt();
				List<Show> showsToBeAdded = setMovieShows(userInput);
				for (Movie movieToBeUpdated : moviesAvailable) {
					if (movieToBeUpdated.getId() == movieIdToAddShow) {
						movieToBeUpdated.setShows(showsToBeAdded);
						movieScreen.setMovie(movieToBeUpdated);
						screenService.addMovieToScreen(movieScreen, movieToBeUpdated);
					}
				}
				break;

			case 8:
				System.exit(0);
			default:
				System.out.println("Invalid choice..");
			}
			System.out.println("Press Y to continue....");
			cont = userInput.next();
		} while (cont.equalsIgnoreCase("y"));
	}

	private static List<Show> setMovieShows(Scanner userInput) {
		System.out.println("Enter Number of Movie shows");
		int numOfShows = userInput.nextInt();
		List<Show> shows = new ArrayList<Show>();
		userInput.nextLine();
		if (numOfShows > 0) {
			System.out.println("Enter shows in hh:mm AM/PM format-");
			for (int i = 0; i < numOfShows; i++) {
				System.out.println("Enter start time");
				String startTime = userInput.nextLine();
				System.out.println("Enter end time");
				String endTime = userInput.nextLine();
				Show show = new Show(i + 1, startTime, endTime);
				shows.add(show);
			}
		}
		return shows;
	}

	private static List<String> setMovieActors(Scanner userInput) {
		int numOfActors = userInput.nextInt();
		List<String> movieActors = new ArrayList<String>();
		for (int i = 0; i < numOfActors; i++) {
			System.out.println("Enter actor name:");
			userInput.nextLine();
			String actorName = userInput.nextLine();
			movieActors.add(actorName);

		}
		return movieActors;
	}

	private static Screen selectScreen(Scanner userInput, List<Screen> screens) {
		for (Screen screenRetrieved : screens) {
			System.out.println(screenRetrieved.getId() + ". " + screenRetrieved.getName());
		}
		int selectedScreenId = userInput.nextInt();
		Screen selectedScreen = null;
		int screenCount = 0;
		for (Screen screenRetrieved : screens) {
			if (screenRetrieved.getId() == selectedScreenId)
				selectedScreen = screenRetrieved;
			else {
				screenCount = screenCount + 1;
			}
			if (screenCount == screens.size()) {
				System.out.println("Invalid choice");
				System.exit(0);
			}
		}
		return selectedScreen;
	}

}
