package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate template; 

	public final static String STYLE_NAME_SORT_BEER_COUNT = "SELECT st.id, st.style_name, COUNT(b.style_id) AS beer_count FROM styles AS st INNER JOIN beers AS b ON st.id = b.style_id GROUP BY st.id, st.style_name ORDER BY beer_count DESC, st.style_name ASC";
	public final static String BREWERIES_BY_BEER = "SELECT b.name AS beer_name, b.descript AS beer_description, br.name AS brewery_name FROM beers AS b INNER JOIN breweries AS br ON b.brewery_id = br.id INNER JOIN styles AS st ON b.style_id = st.id WHERE st.style_name = '?' ORDER BY beer_name ASC";
	public final static String BEERS_FROM_BREWERY = "SELECT br.name AS brewery_name, br.descript AS brewery_description, CONCAT(br.address1, ', ', IFNULL(br.address2, ''), ', ', br.city) AS brewery_address, br.phone AS brewery_phone, br.website AS brewery_website, b.id AS beer_id, b.name AS beer_name, b.descript AS beer_description FROM breweries AS br INNER JOIN beers AS b ON br.id = b.brewery_id WHERE br.id = ? ORDER BY b.name ASC";

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		SqlRowSet rs = template.queryForRowSet(STYLE_NAME_SORT_BEER_COUNT);
        List<Style> styles = new ArrayList<>();

        while(rs.next()) {
            styles.add(Style.create(rs));
        }
		return this.getStyles();
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(/* You can add any number parameters here */Integer id) {
		// TODO: Task 3
		SqlRowSet rs = template.queryForRowSet(BREWERIES_BY_BEER, id);
        List<Beer> beers = new ArrayList<>();

        while(rs.next()) {
            beers.add(Beer.create(rs));
        }	

		return this.getBreweriesByBeer(id);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */Integer id) {
		// TODO: Task 4
		SqlRowSet rs = template.queryForRowSet(BEERS_FROM_BREWERY, id);
        List<Brewery> breweries = new ArrayList<>();

        while(rs.next()) {
            breweries.add(Brewery.create(rs, null));			
        }

		if (!breweries.isEmpty()) {
			return Optional.of(breweries.get(0));
		} else {
			return Optional.empty();
		}		
	}

}
