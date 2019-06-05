package nl.hu.v1wac.firstapp.model;

public class Country {
	private String code;
	private String iso3;
	private String name;
	private String capital;
	private String continent;
	private String region;
	private double surface;
	private int population;
	private String government;
	private double latitude;
	private double longitude;
	
	public Country(String code, String iso3, String nm, String cap, String ct, String reg, double sur, int pop, String gov, double lat, double lng) {
		this.code = code; 
		this.iso3 = iso3;
		this.name = nm;
		this.capital = cap;
		this.continent = ct;
		this.region = reg;
		this.surface = sur;
		this.population = pop;
		this.government = gov;
		this.latitude = lat;
		this.longitude = lng;
	}
	
	public Country(String code, String name, String continent, String region, double surface, int population,
			String governmentForm) {
		super();
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.surface = surface;
		this.population = population;
		this.government = governmentForm;
	}
	
	public Country() {
		
	}
	public Country(String code2, String land, String cap, String regio, double opp, int pop) {
		this.code = code2;
		this.name = land;
		this.capital = cap;
		this.region = regio;
		this.surface = opp;
		this.population = pop;
	}

	public Country(String code2, String naam, String cap, String region2, double surface2, int population2,
			double latitude2, double longitude2) {
		this.code = code2;
		this.name = naam;
		this.capital = cap;
		this.region = region2;
		this.surface = surface2;
		this.population = population2;

	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCode() {
		return code;
	}
	
	public String getIso3() {
		return iso3;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCapital() {
		return capital;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public String getRegion() {
		return region;
	}
	
	public double getSurface() {
		return surface;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public String getGovernment() {
		return government;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", iso3=" + iso3 + ", name=" + name + ", capital=" + capital + ", continent="
				+ continent + ", region=" + region + ", surface=" + surface + ", population=" + population
				+ ", government=" + government + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
