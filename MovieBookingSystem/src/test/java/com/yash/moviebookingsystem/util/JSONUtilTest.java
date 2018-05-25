package com.yash.moviebookingsystem.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;

public class JSONUtilTest {

	private final File screenFile = new File("src/main/resources/files/jsonFiles/screenJson.json");

	@Test(expected = NullFieldException.class)
	public void saveObject_ObjectIsNull_ThrowNullFieldException() {
		Object object = null;
		JSONUtil.saveObject(object);
	}

	@Test
	public void saveObject_ObjectGiven_ShouldReturnFile() {
		List<Row> rows = Arrays.asList(new Row(1, "GOLD"), new Row(2, "SILVER"));
		Screen screen = new Screen(1, "Audi 1", new Movie(101, "PK", "Aamir", Arrays.asList("Anushka")), rows);
		File file = JSONUtil.saveObject(screen);
		assertEquals(0, screenFile.compareTo(file));
	}

	@Test
	public void retrieveListOfScreen_ShouldReturnListOfScreens() {
		List<Screen> screens = JSONUtil.retrieveListOfScreen();
		assertEquals(6, screens.size());
	}

}
