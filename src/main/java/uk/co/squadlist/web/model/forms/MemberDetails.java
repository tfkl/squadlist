package uk.co.squadlist.web.model.forms;

import org.hibernate.validator.constraints.NotBlank;

import uk.co.squadlist.web.model.Squad;

public class MemberDetails {

	@NotBlank
	private String firstName, lastName, emailAddress;
	
	private String contactNumber, rowingPoints, scullingPoints, registrationNumber;
	
	private Squad[] squads;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getRowingPoints() {
		return rowingPoints;
	}

	public void setRowingPoints(String rowingPoints) {
		this.rowingPoints = rowingPoints;
	}

	public String getScullingPoints() {
		return scullingPoints;
	}

	public void setScullingPoints(String scullingPoints) {
		this.scullingPoints = scullingPoints;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Squad[] getSquads() {
		return squads;
	}

	public void setSquads(Squad[] squads) {
		this.squads = squads;
	}

	@Override
	public String toString() {
		return "MemberDetails [contactNumber=" + contactNumber
				+ ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", registrationNumber="
				+ registrationNumber + ", rowingPoints=" + rowingPoints
				+ ", scullingPoints=" + scullingPoints + ", squads=" + squads
				+ "]";
	}
	
}
