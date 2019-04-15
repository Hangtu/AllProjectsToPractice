package com.example.jpa.demo.courses;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Course
 */

@Entity
public class Course {
    
    @Id
    private int id;
    private String name;

    public Course (){

    }

    public Course (int id, String name){

    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}