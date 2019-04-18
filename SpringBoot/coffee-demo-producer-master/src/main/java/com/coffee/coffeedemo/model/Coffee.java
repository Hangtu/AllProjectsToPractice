package com.coffee.coffeedemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coffee {

	@Id
	private Integer id;

	private String name;

	private String type;

	private Double price;

	public Coffee() {
	}

	public Coffee(Integer id, String name, String type, Double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coffee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
