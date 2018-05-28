package com.yash.moviebookingsystem.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yash.moviebookingsystem.exception.NullFieldException;
import com.yash.moviebookingsystem.model.Screen;

public class JSONUtil {

	private static final File screenFile = new File("src/main/resources/files/jsonFiles/screenJson.json");

	public static File saveObject(Object object) {
		BufferedWriter objectToFileWriter;
		File file = null;
		checkIfObjectIsNull(object);
		try {
			if (checkIfObjectIsInstanceOfScreen(object)) {
				file = screenFile;
				List<Screen> screens = getListOfAlreadyPresentScreens();
				Screen screen = (Screen) object;
				String screensString = convertScreenObjectToJson(screens, screen);
				saveConvertedScreenObjectToFile(file, screensString);
				return file;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private static void saveConvertedScreenObjectToFile(File file, String screensString) throws IOException {
		BufferedWriter objectToFileWriter;
		objectToFileWriter = new BufferedWriter(new FileWriter(file));
		objectToFileWriter.write(screensString);
		objectToFileWriter.flush();
		objectToFileWriter.close();
	}

	private static String convertScreenObjectToJson(List<Screen> screens, Screen screen) {
		screens.add(screen);
		Gson screenGson = new Gson();
		String screensString = screenGson.toJson(screens);
		return screensString;
	}

	private static List<Screen> getListOfAlreadyPresentScreens() {
		List<Screen> screens = new ArrayList<Screen>();
		if (retrieveListOfScreen() != null)
			screens = retrieveListOfScreen();
		return screens;
	}

	private static boolean checkIfObjectIsInstanceOfScreen(Object object) {
		return object instanceof Screen;
	}

	private static void checkIfObjectIsNull(Object object) {
		if (object == null)
			throw new NullFieldException("Given object is null.");
	}

	public static List<Screen> retrieveListOfScreen() {
		List<Screen> screens = null;
		try {
			BufferedReader screenJsonBufferedReader = new BufferedReader(new FileReader(screenFile));
			try {
				String screensJsonList = retrieveScreensFromJsonFile(screenJsonBufferedReader);
				screens = convertJsonScreensToListOfScreens(screensJsonList);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return screens;

	}

	private static List<Screen> convertJsonScreensToListOfScreens(String screensJsonList) {
		List<Screen> screens;
		Gson screensGson = new Gson();
		Type screenType = new TypeToken<ArrayList<Screen>>() {
		}.getType();
		screens = screensGson.fromJson(screensJsonList, screenType);
		return screens;
	}

	private static String retrieveScreensFromJsonFile(BufferedReader screenJsonBufferedReader) throws IOException {
		String line = screenJsonBufferedReader.readLine();
		String screensJsonList = null;
		while (line != null) {
			screensJsonList = line;
			line = screenJsonBufferedReader.readLine();
		}
		return screensJsonList;
	}

	public static void updateScreen(List<Screen> screens) {
		Gson gson = new Gson();
		try {
			saveConvertedScreenObjectToFile(screenFile, gson.toJson(screens));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
