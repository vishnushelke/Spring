package com.bridgelabz.hibernet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alien {

	@Id
	private int alienId;
	private AlienName alienName;
	private String colour;
	public int getAlienId() {
		return alienId;
	}
	public void setAlienId(int alienId) {
		this.alienId = alienId;
	}
	public AlienName getAlienName() {
		return alienName;
	}
	public void setAlienName(AlienName alienName) {
		this.alienName = alienName;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	@Override
	public String toString() {
		return "Alien [alienId=" + alienId + ", alienName=" + alienName + ", colour=" + colour + "]";
	}
	
	
}
