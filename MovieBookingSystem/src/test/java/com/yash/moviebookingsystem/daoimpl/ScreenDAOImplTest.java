package com.yash.moviebookingsystem.daoimpl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImplTest {

	ScreenDAO screenDAO;

	@Before
	public void setUp() throws Exception {
		screenDAO = new ScreenDAOImpl();
	}

	@Test(expected = NullFieldException.class)
	public void save_ScreenObjectIsNull_ShouldThrowNullFieldException() {
		Screen screen = null;
		screenDAO.save(screen);
	}

	@Test
	public void save_ScreenObjectGiven_ShouldReturnOne() {
		Screen screen = new Screen(3, "Audi 3");
		int rowsAffected = screenDAO.save(screen);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void retrieve_ShouldReturnListOfScreensPresent() {
		List<Screen> screens = screenDAO.retrieve();
		assertEquals(4, screens.size());
	}

}
