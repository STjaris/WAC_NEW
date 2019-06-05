package nl.hu.v1wac.firstapp.model;

import java.util.List;


import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	private CountryPostgresDaoImpl dao = new CountryPostgresDaoImpl();
	
		
	public List<Country> getAllCountries() {

		return dao.findAll();
	}
	
	public List<Country> get10LargestPopulations() {
		return dao.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() {
		return dao.find10LargestSurfaces();
	}
	
	public Country getCountryByCode(String code) {
		return dao.findByCode(code);
	}

	public boolean deleteCountry(String code) {
		return dao.delete(dao.findByCode(code));
	}

	public Country editCountry(Country country) {

		return dao.update(country);
	}

	public Country CreateCountry(Country country) {
		return dao.save(country);
	}

}
