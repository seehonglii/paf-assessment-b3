package ibf2022.assessment.paf.batch3.models;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import ibf2022.assessment.paf.batch3.repositories.BeerRepository;

import java.util.LinkedList;

// DO NOT MODIFY THIS FILE.

public class Brewery {

	private int breweryId;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String phone;
	private String website;
	private String description;
	private List<Beer> beers = new LinkedList<>();
	
	public Brewery(int breweryId, String name, String address1, String address2, String city, String phone,
			String website, String description, List<Beer> beers) {
		this.breweryId = breweryId;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.phone = phone;
		this.website = website;
		this.description = description;
		this.beers = beers;
	}
	public int getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Beer> getBeers() {
		return beers;
	}
	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}
	public void addBeer(Beer beer) {
		this.beers.add(beer);
	}

	@Override
	public String toString() {
		return "Brewery [breweryId=" + breweryId + ", name=" + name + ", address1=" + address1 + ", address2="
				+ address2 + ", city=" + city + ", phone=" + phone + ", website=" + website + ", description="
				+ description + ", beers=" + beers + "]";
	}
	
    public static Brewery create(SqlRowSet rs, BeerRepository beerRepo) {
		int breweryId = rs.getInt("breweryId");
		String name = rs.getString("name");
		String address1 = rs.getString("address1");
		String address2 = rs.getString("address2");
		String city = rs.getString("city");
		String phone = rs.getString("phone");
		String website = rs.getString("website");
		String description = rs.getString("description");
		List<Beer> beers = beerRepo.getBreweriesByBeer(breweryId);
	
		Brewery brewery = new Brewery(breweryId, name, address1, address2, city, phone, website, description, beers);
		return brewery;
	}
	
}


