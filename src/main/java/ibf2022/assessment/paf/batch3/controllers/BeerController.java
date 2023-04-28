package ibf2022.assessment.paf.batch3.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	private BeerRepository repo; 

	@Autowired
	private BeerService beerSvc;

	//TODO Task 2 - view 0
	@GetMapping("/")
	public String goToLandingPage(Model model){
		List<Style> Styles = repo.getStyles();

        model.addAttribute("styles", Styles);
        return "view0";
	}	
	
	//TODO Task 3 - view 1
	@GetMapping("/beer/style/{id}")
	public String getDetailsByBeer(@PathVariable int id, @RequestParam String styleName, Model model) {
		List<Beer> beers = repo.getBreweriesByBeer(id);
		
		model.addAttribute("beers", beers);
		model.addAttribute("styleName", styleName);

		return "view1";
	}	

	//TODO Task 4 - view 2
	@GetMapping("/beer/brewery/{id}")
	public String getBreweryDetails(@PathVariable int id, @RequestParam String styleName, Model model) {
		Optional<Brewery> brewery = repo.getBeersFromBrewery(id);
		List<Beer> beers = repo.getBreweriesByBeer(id);

		model.addAttribute("brewery", brewery);
		model.addAttribute("beers", beers);
		
		return "view2";
	}
	
	//TODO Task 5 - view 2, place order
	@PostMapping(path = "/brewery/{breweryId}/order", consumes = "application/x-www-form-urlencoded")
	public String BeerOrdering(@RequestParam String breweryId, @RequestParam MultiValueMap<String, String> orderQuantities, Model model) {
		try {
			Map<String, Integer> quantities = new HashMap<>();
			for (Map.Entry<String, List<String>> entry : orderQuantities.entrySet()) {
				String beerId = entry.getKey();
				int quantity = Integer.parseInt(entry.getValue().get(0));
				quantities.put(beerId, quantity);
			}
			String orderId = beerSvc.placeOrder(breweryId, quantities);
			model.addAttribute("orderId", orderId);
			return "view3";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "view0";
		}
	}
}
