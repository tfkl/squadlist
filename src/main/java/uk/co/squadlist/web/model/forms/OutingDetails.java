package uk.co.squadlist.web.model.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

public class OutingDetails {

	private int year, month, day, hour, minute;
	@NotBlank
	private String squad;
	private String notes;
	private Boolean repeats;
	
	public OutingDetails() {
	}
	
	public OutingDetails(LocalDateTime defaultOutingDateTime) {
    	this.setYear(defaultOutingDateTime.getYear());
    	this.setMonth(defaultOutingDateTime.getMonthOfYear());
    	this.setDay(defaultOutingDateTime.getDayOfMonth());
    	this.setHour(defaultOutingDateTime.getHourOfDay());
    	this.setMinute(defaultOutingDateTime.getMinuteOfHour());
	}
	
	public LocalDateTime toLocalTime() {
		return new LocalDateTime(year, month, day, hour, minute);	// TODO AM PM
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public String getSquad() {
		return squad;
	}

	public void setSquad(String squad) {
		this.squad = squad;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Boolean getRepeats() {
		return repeats;
	}

	public void setRepeats(Boolean repeats) {
		this.repeats = repeats;
	}

	@Override
	public String toString() {
		return "OutingDetails [day=" + day + ", hour=" + hour + ", minute="
				+ minute + ", month=" + month + ", notes=" + notes
				+ ", repeats=" + repeats + ", squad=" + squad + ", year="
				+ year + "]";
	}
	
}