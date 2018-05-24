package com.yash.moviebookingsystem.model;

public class Screen {

	private int id;
	private String name;
	private Movie movie;

	public Screen(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Screen(int id, String name, Movie movie) {
		super();
		this.id = id;
		this.name = name;
		this.movie = movie;
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

	@Override
	public String toString() {
		return "Screen [id=" + id + ", name=" + name + ", movie=" + movie + "]";
	}

}
