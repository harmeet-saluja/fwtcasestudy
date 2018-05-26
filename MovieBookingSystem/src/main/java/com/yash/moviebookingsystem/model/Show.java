package com.yash.moviebookingsystem.model;

import java.time.LocalTime;

public class Show {

	private int id;
	private String startTime;
	private String endTime;

	public Show(int id, String startTime, String endTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
