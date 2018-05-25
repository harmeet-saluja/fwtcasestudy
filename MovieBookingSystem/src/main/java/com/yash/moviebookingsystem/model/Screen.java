package com.yash.moviebookingsystem.model;

import java.util.List;

public class Screen {

	private int id;
	private String name;
	private Movie movie;
	private List<Row> rows;

	public Screen(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Screen(int id, String name, Movie movie) {
		this(id, name);
		this.movie = movie;
	}

	public Screen(int id, String name, Movie movie, List<Row> rows) {
		this(id, name, movie);
		this.rows = rows;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public List<Row> getRows() {
		return rows;
	}

	@Override
	public String toString() {
		return "Screen [id=" + id + ", name=" + name + ", movie=" + movie + ", rows=\n" + rows + "]";
	}

}
