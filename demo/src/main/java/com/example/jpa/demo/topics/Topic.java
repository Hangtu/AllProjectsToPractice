package com.example.jpa.demo.topics;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Topic
 */

@Entity
public class Topic {
    
    @Id
    private int id;
    private String name;

    public Topic (){

    }

    public Topic (int id, String name){

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