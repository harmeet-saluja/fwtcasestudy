package com.yash.moviebookingsystem.model;

import java.util.List;

public class Movie {

	private int id;
	private String title;
	private String production;
	private List<String> actors;

	public Movie(int id, String title, String production, List<String> actors) {
		super();
		this.id = id;
		this.title = title;
		this.production = production;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", production=" + production + ", actors=" + actors + "]";
	}

}
