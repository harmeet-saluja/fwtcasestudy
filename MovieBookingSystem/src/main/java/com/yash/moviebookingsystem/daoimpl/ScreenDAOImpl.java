package com.yash.moviebookingsystem.daoimpl;

import java.util.Arrays;
import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.util.JSONUtil;

public class ScreenDAOImpl implements ScreenDAO {

	public int save(Screen screen) {
		if (screen == null)
			throw new NullFieldException("Screen cannot be null");
		int rowsAffected = 0;
		JSONUtil.saveObject(screen);
		rowsAffected = 1;
		return rowsAffected;
	}

	public int update(Screen screen) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Screen> retrieve() {
		return JSONUtil.retrieveListOfScreen();
	}

}
