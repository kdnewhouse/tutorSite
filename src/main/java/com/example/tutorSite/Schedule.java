package com.example.tutorSite;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
	
	@Id
    @Column(name = "id", nullable = false, length = 6)
    private int id;
    
    @Column(name = "days", nullable = false, length = 100)
    private String days;
    
    public void newSchedule(int id, String days) {
    	this.setId(id);
    	this.setDays(days);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
    
}

