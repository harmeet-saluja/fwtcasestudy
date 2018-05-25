package com.yash.moviebookingsystem.model;

import java.util.List;

public class Row {

	private int id;
	private List<Seat> seats;
	private String category;

	public Row(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Row [id=" + id + ", seats=" + seats + ", category=" + category + "]";
	}

}
