package nl.hu.v1wac.firstapp.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/countries")
public class WorldResource {

	private WorldService service = ServiceProvider.getWorldService();
	
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public Response createCountry(			
			@FormParam("invoerCode") String code,
			@FormParam("invoerland") String naam,
			@FormParam("invoerCap") String cap, 
			@FormParam("invoerRegio") String region,
			@FormParam("invoerlandOpp") double surface,
			@FormParam("invoerlandPop") int population, 
			@FormParam("latitude") double latitude, 
			@FormParam("longitude") double longitude){

		Country country = new Country(code, naam, cap, region, surface, population, latitude, longitude);
		System.out.println("POST: " + country);
		System.out.println(code);

		Country c = service.CreateCountry(country);
		return Response.ok(c).build();
	}


	@GET
	@Produces("application/json")
	public String getCountries() {

		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();

			job.add("Code", c.getCode());
			job.add("Name", c.getName());
			job.add("Capital", c.getCapital());
			job.add("Surface", c.getSurface());
			job.add("Goverment", c.getGovernment());
			job.add("Lat", c.getLatitude());
			job.add("Iso3", c.getIso3());
			job.add("Continent", c.getContinent());
			job.add("Region", c.getRegion());
			job.add("Population", c.getPopulation());
			job.add("Lng", c.getLongitude());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();

	}

	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getLargestPopulations() {

		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();

			job.add("Code", c.getCode());
			job.add("Name", c.getName());
			job.add("Capital", c.getCapital());
			job.add("Surface", c.getSurface());
			job.add("Goverment", c.getGovernment());
			job.add("Lat", c.getLatitude());
			job.add("Iso3", c.getIso3());
			job.add("Continent", c.getContinent());
			job.add("Region", c.getRegion());
			job.add("Population", c.getPopulation());
			job.add("Lng", c.getLongitude());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();

	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces() {

		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();

			job.add("Code", c.getCode());
			job.add("Name", c.getName());
			job.add("Capital", c.getCapital());
			job.add("Surface", c.getSurface());
			job.add("Goverment", c.getGovernment());
			job.add("Lat", c.getLatitude());
			job.add("Iso3", c.getIso3());
			job.add("Continent", c.getContinent());
			job.add("Region", c.getRegion());
			job.add("Population", c.getPopulation());
			job.add("Lng", c.getLongitude());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();

	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountry(@PathParam("code") String code) {

		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		Country c = service.getCountryByCode(code);
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("Code", c.getCode());
		job.add("Name", c.getName());
		job.add("Capital", c.getCapital());
		job.add("Surface", c.getSurface());
		job.add("Goverment", c.getGovernment());
		job.add("Lat", c.getLatitude());
		job.add("Iso3", c.getIso3());
		job.add("Continent", c.getContinent());
		job.add("Region", c.getRegion());
		job.add("Population", c.getPopulation());
		job.add("Lng", c.getLongitude());
		jab.add(job);

		JsonArray array = jab.build();
		return array.toString();
	}

	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response deleteCountry(@PathParam("code") String code) {
		System.out.println(code);

		if (!service.deleteCountry(code)) {
			return Response.status(404).build();

		}
		return Response.ok().build();

	}

	
	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response editCountry(
			@FormParam("invoerCode") String code,
			@FormParam("invoerland") String naam,
			@FormParam("invoerCap") String cap, 
			@FormParam("invoerRegio") String region,
			@FormParam("invoerlandOpp") double surface,
			@FormParam("invoerlandPop") int population, 
			@FormParam("latitude") double latitude, 
			@FormParam("longitude") double longitude) {
		System.out.println(naam);
//		System.out.println(code, naam, cap, cont, region, surface, population, gov, latitude, longitude);
		Country c = new Country(code, naam, cap, region, surface, population, latitude, longitude);
		System.out.println("Service: " + c);

		if (service.editCountry(c) == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("Error", "Country does not exist");
			return Response.status(409).entity(messages).build();
		}

		return Response.ok(c).build();

	}
	
}
