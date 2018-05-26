package com.yash.moviebookingsystem.dao;

import java.util.List;

import com.yash.moviebookingsystem.model.Screen;

public interface ScreenDAO {

	public int save(Screen screen);

	public int update(Screen screen);

	public List<Screen> retrieve();

}
