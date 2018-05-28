package com.yash.moviebookingsystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.daoimpl.ScreenDAOImpl;
import com.yash.moviebookingsystem.model.Movie;
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

	public MovieBookingSystemStartUpUtil() {
		this.screenDAO = new ScreenDAOImpl();
		this.movieService = new MovieServiceImpl();
		this.sittingArrangementService = new SittingArrangementServiceImpl();
		this.screenService = new ScreenServiceImpl(screenDAO, sittingArrangementService, movieService);
	}

	public static void startApplication() {
		OperatorMenuUtil.getOperatorMenu(OPERATOR_MENU_FILE_NAME);
		Scanner userInput = new Scanner(System.in);
		String cont = null;
		do {
			int choice = userInput.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Screen Name:");
				int screenId = userInput.nextInt();
				String screenName = userInput.next();
				Screen screen = new Screen(screenId, screenName);
				screenService.addScreen(screen);
				break;

			case 2:
				System.out.println("Select Screen:");
				List<Screen> screens = screenService.getScreens();
				Screen selectedScreen = selectScreen(userInput, screens);
				System.out.println("Enter Movie ID");
				int movieId = userInput.nextInt();
				System.out.println("Enter Movie Title");
				String movieTitle = userInput.nextLine();
				System.out.println("Enter Movie Production");
				String movieProduction = userInput.nextLine();
				System.out.println("Enter number of actors in movie:");
				List<String> movieActors = setMovieActors(userInput);
				List<Show> shows = setMovieShows(userInput);
				Movie movie = new Movie(movieId, movieTitle, movieProduction, movieActors, shows);
				selectedScreen.setMovie(movie);
				break;

			case 3:
				System.out.println("Select category-\n1.Gold\t2.Silver\t3.Platinum");
				String category = null;
				int categoryChoice = userInput.nextInt();
				if (categoryChoice == 1)
					category = SittingArrangementService.CATEGORY_GOLD;
				if (categoryChoice == 2)
					category = SittingArrangementService.CATEGORY_SILVER;
				if (categoryChoice == 3)
					category = SittingArrangementService.CATEGORY_PLATINUM;
				System.out.println("Enter number of rows:");
				int numberOfRowsInCategory = userInput.nextInt();
				System.out.println("Enter number seats in first row:");
				int numberOfSeatsInFirstRow = userInput.nextInt();
				sittingArrangementService.createSittingArrangementForCategory(category, numberOfRowsInCategory,
						numberOfSeatsInFirstRow);
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
		System.out.println("Enter shows in hh:mm AM/PM format:");
		if (numOfShows > 0) {
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
		for (String movieActor : movieActors) {
			movieActors.add(movieActor);
		}
		return movieActors;
	}

	private static Screen selectScreen(Scanner userInput, List<Screen> screens) {
		for (Screen screenRetrieved : screens) {
			System.out.println(screenRetrieved.getId() + ". " + screenRetrieved.getName());
		}
		int selectedScreenId = userInput.nextInt();
		Screen selectedScreen = null;
		for (Screen screenRetrieved : screens) {
			if (screenRetrieved.getId() == selectedScreenId)
				selectedScreen = screenRetrieved;
			else
				System.out.println("Invalid Screen");
		}
		return selectedScreen;
	}

}
