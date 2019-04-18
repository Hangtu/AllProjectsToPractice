package com.coffee.coffeedemo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coffee.coffeedemo.model.Coffee;
import com.coffee.coffeedemo.service.CoffeeService;

@Component
@Path("/coffee")
public class CoffeeResource  {
	
	@Autowired
	private CoffeeService coffeeService;
	
	/**
	 * 
	 * @param coffee
	 * @param uriInfo
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response addCoffee(Coffee coffee, @Context UriInfo uriInfo) {
		Coffee newCoffee = coffeeService.addCoffee(coffee);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(newCoffee.getId().toString());

		return Response.created(builder.build()).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Response getCoffee(@PathParam("id") Integer id) {
		return Response.ok(coffeeService.getCoffee(id)).build();
	}

	/**
	 * 
	 * @param coffee
	 * @return
	 * @throws Exception 
	 */
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateCoffee(Coffee coffee) {
		return Response.ok(coffeeService.updateCoffee(coffee)).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@DELETE
	@Path("/delete/{id}")
	public Response deleteCoffee(@PathParam("id") Integer id){
		coffeeService.deleteCoffee(id);
		return Response.noContent().build();
	}

	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/all")
	public Response getAllCoffees() {
		return Response.ok(coffeeService.getAllCoffees()).build();
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/name/{name}")
	public Response getCoffeByName(@PathParam("name") String name) {
		return Response.ok(coffeeService.getCoffeeByName(name)).build();
	}

	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/type/{type}")
	public Response getCoffeesByType(@PathParam("type") String type) {
		return Response.ok(coffeeService.getCoffeesByType(type)).build();
	}

	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/expensive")
	public Response mostExpensiveCoffee() throws Exception {
		return Response.ok(coffeeService.getCoffeeByPrice("Expensive")).build();
	}

	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Produces("application/json")
	@Path("/cheap")
	public Response cheapestCoffee() throws Exception {
		return Response.ok(coffeeService.getCoffeeByPrice("Cheap")).build();
	}

}
