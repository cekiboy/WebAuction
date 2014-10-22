/**
 * 
 */
package com.levi9.app.webAuction.web.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.service.AuctionService;
import com.levi9.app.webAuction.service.UserService;
import com.levi9.app.webAuction.web.dto.SearchDTO;

/**
 * @author Ceki
 *
 */
@Controller
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	AuctionService auctionService;
	
	@Autowired
	UserService userService;
	
	
	private Double currentPrice (Auction auction){
		if (auction.getAuctionBids().isEmpty()){
			return auction.getStartPrice();
		}
		else {
			return auction.getAuctionBids().get(0).getBidValue();
		}
	}
	
	public LinkedHashMap<String, String> loadSearchOptions(){
		LinkedHashMap<String, String> searchTypes = new LinkedHashMap<String, String>();
		searchTypes.put("byUser",  "By auction owner");
		searchTypes.put("byName", "By auction name");
		searchTypes.put("priceLessThan", "Current price less than :");
		searchTypes.put("priceGreaterThan", "Current price greater than :");
		return searchTypes;
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(Model model) {
		SearchDTO searchDTO = new SearchDTO();
		LinkedHashMap<String, String> searchTypes = loadSearchOptions();
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("searchTypes", searchTypes);
        return "search";
	}
	
	@RequestMapping(params="search", method=RequestMethod.POST)
	public String search(@Valid SearchDTO searchDTO, BindingResult bindingResult, Model model) {
		String viewName = null;
		List<Auction> auctions = new ArrayList<>();
		
		if (!bindingResult.hasErrors()){
			String searchType = searchDTO.getSearchType();
			String searchValue = searchDTO.getSearchValue();
			if (searchType.equals("byUser")) {
				auctions = auctionService.findByUserUsernameContaining(searchValue);
				}
			else if(searchType.equals("byName")){
				auctions = auctionService.findByNameContaining(searchValue);
			}
			else {
				Double searchPrice = 0.0;
				try {
					searchPrice = Double.parseDouble(searchValue);
				} catch (NumberFormatException e) {
					LinkedHashMap<String, String> searchTypes = loadSearchOptions();
					model.addAttribute("searchTypes", searchTypes);
//					viewName = "search";
					bindingResult.rejectValue("searchValue", "search.parseDouble");
					return "search";
				}
				List<Auction>auctionsForSearch = auctionService.findByActiveTrue();
				if (searchType.equals("priceLessThan")){
					for (Auction auction : auctionsForSearch){
						if (Double.compare(currentPrice(auction), searchPrice) == -1 ){
							auctions.add(auction);
						}
					}
				}
				else if (searchType.equals("priceGreaterThan")){
					for (Auction auction : auctionsForSearch){
						if (Double.compare(currentPrice(auction), searchPrice) == 1 ){
							auctions.add(auction);
						}
					}
				}
			}
		
			if (auctions.isEmpty()){
				LinkedHashMap<String, String> searchTypes = loadSearchOptions();
				model.addAttribute("searchTypes", searchTypes);
				viewName = "search";
				bindingResult.rejectValue("searchValue", "search.noSearchResults");
			}
			else{
				model.addAttribute("auctions", auctions);
				viewName = "auctions";
			}
		
		}
		else{
			LinkedHashMap<String, String> searchTypes = loadSearchOptions();
			model.addAttribute("searchTypes", searchTypes);
			model.addAttribute("searchDTO", searchDTO);
			viewName = "search";
		}
		return viewName;
	}
}
