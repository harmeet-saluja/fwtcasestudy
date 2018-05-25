package com.yash.moviebookingsystem.model;

public class Seat {

	private int id;
	private boolean bookedStatus;

	public Seat(int id, boolean bookedStatus) {
		super();
		this.id = id;
		this.bookedStatus = bookedStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBookedStatus() {
		return bookedStatus;
	}

	public void setBookedStatus(boolean bookedStatus) {
		this.bookedStatus = bookedStatus;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", bookedStatus=" + bookedStatus + "]";
	}

}
