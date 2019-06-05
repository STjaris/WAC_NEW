package nl.hu.v1wac.firstapp.persistence;
import java.util.*;
import nl.hu.v1wac.firstapp.model.*;

public interface CountryDao {
	
	Country save(Country country);
	ArrayList<Country> findAll();
	Country findByCode(String code);
	ArrayList<Country> find10LargestPopulations();
	ArrayList<Country> find10LargestSurfaces();
	Country update(Country country);
	boolean delete(Country country);
	

}
