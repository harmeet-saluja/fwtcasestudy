package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.DataAlreadyExistsException;
import com.yash.moviebookingsystem.exception.ListSizeExceededException;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.MovieService;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SittingArrangementService;

public class ScreenServiceImplTest {

	private ScreenDAO screenDAO;
	private ScreenService screenService;
	private SittingArrangementService sittingArrangementService;
	private MovieService movieService;

	@Before
	public void setUp() throws Exception {
		this.screenDAO = mock(ScreenDAO.class);
		this.sittingArrangementService = new SittingArrangementServiceImpl();
		this.movieService = new MovieServiceImpl();
		this.screenService = new ScreenServiceImpl(this.screenDAO, this.sittingArrangementService, this.movieService);
	}

	@Test(expected = NullFieldException.class)
	public void addScreen_ScreenObjectIsNull_ThrowNullFieldException() {
		Screen screen = null;
		screenService.addScreen(screen);
	}

	@Test
	public void addScreen_ScreenObjectGiven_ScreenAddedShouldReturnOne() {
		Screen screen = new Screen(3, "Audi 3");
		when(screenDAO.save(any(Screen.class))).thenReturn(1);
		int rowsAffected = screenService.addScreen(screen);
		assertEquals(1, rowsAffected);
	}

	@Test(expected = ListSizeExceededException.class)
	public void addScreen_ScreenObjectGiven_ThrowListSizeExceededExceptionWhenThreeScreenAlreadyPresent() {
		List<Screen> screens = Arrays.asList(new Screen(1, "Audi 1"), new Screen(2, "Audi 2"), new Screen(3, "Audi 3"));
		when(screenDAO.retrieve()).thenReturn(screens);
		when(screenDAO.save(any(Screen.class))).thenThrow(ListSizeExceededException.class);
		screenService.addScreen(new Screen(4, "Audi 4"));
	}

	@Test(expected = DataAlreadyExistsException.class)
	public void addScreen_ScreenObjectGiven_ThrowDataAlreadyExistsExceptionWhenScreenAlreadyPresent() {
		List<Screen> screens = Arrays.asList(new Screen(1, "Audi 1"), new Screen(2, "Audi 2"));
		when(screenDAO.retrieve()).thenReturn(screens);
		when(screenDAO.save(any(Screen.class))).thenThrow(DataAlreadyExistsException.class);
		screenService.addScreen(new Screen(4, "Audi 1"));
	}

	@Test(expected = NullFieldException.class)
	public void addMovieToScreen_MovieObjectIsNull_ThrowNullFieldException() {
		Movie movie = null;
		Screen screen = new Screen(2, "Audi 2");
		screenService.addMovieToScreen(screen, movie);
	}

	@Test(expected = NullFieldException.class)
	public void addMovieToScreen_ShowsInMovieAreNull_ShouldReturnOneWhenMovieAdded() {
		Movie movie = new Movie(1, "PK", "Aamir", Arrays.asList("Aamir", "Anushka"));
		Screen screen = new Screen(2, "Audi 2");
		when(screenDAO.update(any(Screen.class))).thenReturn(1);
		int rowsAffected = screenService.addMovieToScreen(screen, movie);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void addMovieToScreen_MovieObjectIsGiven_ShouldReturnOneWhenMovieAdded() {
		Movie movie = new Movie(1, "PK", "Aamir", Arrays.asList("Aamir", "Anushka"));
		movie.setShows(Arrays.asList(new Show(1, "12:00 PM", "03:00 PM"), new Show(2, "09:00 PM", "06:00 PM")));
		Screen screen = new Screen(2, "Audi 2");
		when(screenDAO.update(any(Screen.class))).thenReturn(1);
		int rowsAffected = screenService.addMovieToScreen(screen, movie);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void addMovieToScreen_MovieObjectIsGiven_ShouldReturnZeroWhenMovieNotAdded() {
		Movie movie = new Movie(1, "PK", "Aamir", Arrays.asList("Aamir", "Anushka"));
		movie.setShows(Arrays.asList(new Show(1, "12:00 PM", "03:00 PM"), new Show(2, "09:00 PM", "06:00 PM")));
		Screen screen = new Screen(2, "Audi 2");
		when(screenDAO.update(any(Screen.class))).thenReturn(0);
		int rowsAffected = screenService.addMovieToScreen(screen, movie);
		assertEquals(0, rowsAffected);
	}

	@Test(expected = NullFieldException.class)
	public void addMovieToScreen_ScreenObjectIsNull_ThrowNullFieldException() {
		Movie movie = new Movie(1, "PK", "Aamir", Arrays.asList("Aamir", "Anushka"));
		Screen screen = null;
		when(screenDAO.update(any(Screen.class))).thenReturn(0);
		screenService.addMovieToScreen(screen, movie);
	}

	@Test
	public void addRowsToScreen_ListOfRowsGiven_ShouldReturnTrue() {
		Screen screen = new Screen(3, "Audi 3");
		List<Row> rows = sittingArrangementService
				.createSittingArrangementForCategory(SittingArrangementService.CATEGORY_GOLD, 6, 14);
		boolean areRowsAdded = screenService.addRowsToScreen(screen, rows);
		assertTrue(areRowsAdded);
	}

	@Test
	public void getScreens_ShouldReturnListOfScreens() {
		List<Screen> screens = Arrays.asList(new Screen(1, "Audi 1"), new Screen(2, "Audi 2"));
		when(screenDAO.retrieve()).thenReturn(screens);
		List<Screen> screensRetrieved = screenService.getScreens();
		assertEquals(screens.size(), screensRetrieved.size());
	}

}
