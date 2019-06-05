package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.hu.v1wac.firstapp.model.*;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {

	private ArrayList<Country> findCountries(String query, Object... params) {

		ArrayList<Country> list = new ArrayList<>();
		try (Connection conn = super.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			System.out.println(query);

			// stmt.executeUpdate(strQuery);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("test");

			while (rs.next()) {
				Country c = new Country();
				c.setCode(rs.getString("code"));
				c.setName(rs.getString("name"));
				c.setContinent(rs.getString("continent"));
				c.setRegion(rs.getString("region"));
				c.setGovernment(rs.getString("governmentform"));
				c.setPopulation(rs.getInt("population"));
				c.setSurface(rs.getDouble("surfacearea"));
				c.setCapital(rs.getString("capital"));
				c.setLatitude(rs.getDouble("latitude"));
				c.setLongitude(rs.getDouble("longitude"));
				c.setIso3(rs.getString("iso3"));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list;
	}

	private Country findCountry(String query, Object... params) {
		Country c = new Country();
		try (Connection conn = super.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				c.setCode(rs.getString("code"));
				c.setName(rs.getString("name"));
				c.setContinent(rs.getString("continent"));
				c.setRegion(rs.getString("region"));
				c.setGovernment(rs.getString("governmentform"));
				c.setPopulation(rs.getInt("population"));
				c.setSurface(rs.getDouble("surfacearea"));
				c.setCapital(rs.getString("capital"));
				c.setLatitude(rs.getDouble("latitude"));
				c.setLongitude(rs.getDouble("longitude"));
				c.setIso3(rs.getString("iso3"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	@Override
	public Country save(Country c) {
		ArrayList<Country> list = null;
		boolean bestaat = findByCode(c.getCode()) != null;
		System.out.println("save: boolean");
		System.out.println(bestaat);
		if (bestaat) {
			System.out.println("save: if statement");
			return findCountry(
					"INSERT INTO country(code, name, capital, region, population, surfacearea) " + "VALUES (?, ?, ?, ?, ?, ?)",
					c.getCode(), c.getName(), c.getCapital(), c.getRegion(), c.getPopulation(), c.getSurface());

		} else {
			return c;
		}

	}

	@Override
	public ArrayList<Country> findAll() {
		return findCountries("SELECT * FROM country");
	}

	@Override
	public Country findByCode(String code) {
		System.out.println(code);
		return findCountry("SELECT * FROM country WHERE code = ?", code);

	}

	@Override
	public ArrayList<Country> find10LargestPopulations() {
		return findCountries("SELECT * FROM country ORDER BY population DESC FETCH FIRST 10 ROWS ONLY");

	}

	@Override
	public ArrayList<Country> find10LargestSurfaces() {
		return findCountries("SELECT * FROM country ORDER BY surfacearea DESC FETCH FIRST 10 ROWS ONLY");
	}

	@Override
	public Country update(Country c) {

		try (Connection conn = super.getConnection()) {
			
			String query = "UPDATE country SET (code = ?, name = ?, continent = ?, region = ?, governmentform = ?, population = ?, surfacearea = ?) WHERE code = ?";
			
			
					
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(0, c.getCode());
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getContinent());
			pstmt.setString(3, c.getRegion());
			pstmt.setString(4, c.getGovernment());
			pstmt.setInt(5, c.getPopulation());
			pstmt.setDouble(6, c.getSurface());
			pstmt.setString(7,  c.getCode());
			

			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				c.setCode(rs.getString("code"));
				c.setName(rs.getString("name"));
				c.setContinent(rs.getString("continent"));
				c.setRegion(rs.getString("region"));
				c.setGovernment(rs.getString("governmentform"));
				c.setPopulation(rs.getInt("population"));
				c.setSurface(rs.getDouble("surfacearea"));
				c.setCapital(rs.getString("capital"));
				c.setLatitude(rs.getDouble("latitude"));
				c.setLongitude(rs.getDouble("longitude"));
				c.setIso3(rs.getString("iso3"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;


	}

	@Override
	public boolean delete(Country c) {
		boolean bestaat = findByCode(c.getCode()) != null;

		if (bestaat) {
			findCountries("DELETE FROM country WHERE code = " + c.getCode());

			return true;
		} else {
			return false;
		}
	}

}
