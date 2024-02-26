package com.submarine.jacksparrowtask.model;

import org.springframework.stereotype.Component;

@Component
public class SubMarine {

	private int id;
	private String name;
	private boolean isHidden;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

}
