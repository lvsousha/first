package com.stone.model;

public class Role {

	private int id;
	private User user;
	private String role;
	private String descriptin;
	private boolean available;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescriptin() {
		return descriptin;
	}
	public void setDescriptin(String descriptin) {
		this.descriptin = descriptin;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
