package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenDAO screenDAO;
	private ScreenService screenService;

	@Before
	public void setUp() throws Exception {
		this.screenDAO = mock(ScreenDAO.class);
		this.screenService = new ScreenServiceImpl(this.screenDAO);
	}

	@Test(expected = NullFieldException.class)
	public void addScreen_ScreenObjectIsNull_ThrowNullFieldException() {
		Screen screen = null;
		screenService.addScreen(screen);
	}

	@Test
	public void addScreen_ScreenObjectGiven_ScreenAddedShouldReturnOne() {
		Screen screen = new Screen(1, "Audi 1");
		when(screenDAO.save(any(Screen.class))).thenReturn(1);
		int rowsAffected = screenService.addScreen(screen);
		assertEquals(1, rowsAffected);
	}

	@Test(expected = NullFieldException.class)
	public void addMovieToScreen_MovieObjectIsNull_ThrowNullFieldException() {
		Movie movie = null;
		screenService.addMovieToScreen(movie);
	}

}
