package com.example.jpa.demo.courses;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.jpa.demo.topics.Topic;

/**
 * Course
 */

@Entity
public class Course {

	@Id
	private Integer id;
	private String name;

	@ManyToOne
	private Topic topic;

	public Course() {

	}

	public Course(int id, String name, int topicID) {
		this.id = id;
		this.name = name;
		this.topic = new Topic(topicID,"");
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

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}