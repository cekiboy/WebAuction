/**
 * 
 */
package com.levi9.app.webAuction.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.levi9.app.webAuction.model.Auction;
import com.levi9.app.webAuction.model.AuctionBid;
import com.levi9.app.webAuction.model.Image;
import com.levi9.app.webAuction.model.User;
import com.levi9.app.webAuction.service.AuctionBidService;
import com.levi9.app.webAuction.service.AuctionService;
import com.levi9.app.webAuction.service.MailService;
import com.levi9.app.webAuction.service.UserService;
import com.levi9.app.webAuction.web.dto.AuctionFilesDTO;

/**
 * Controller for CRUD operations on auctions.
 * 
 * @author Ceki
 *
 */
@Controller
@RequestMapping("/auctions")
public class AuctionController {
	
	
	/**
	 * Auction service
	 */
	@Autowired
	private AuctionService auctionService;
	
	/**
	 * User service
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * AuctionBid service
	 */
	@Autowired
	private AuctionBidService auctionBidService;
	
	/**
	 * Mail service
	 */
	@Autowired
	private MailService mailService;
	
	
	
	
	
	private void deactivateExpiredAuctionsAndSendMail(){
		List<Auction> expiredAuctions = auctionService.deactivateExpiredAuctions();
		for (Auction auction : expiredAuctions){
			if (!auction.getAuctionBids().isEmpty()){
				mailService.sendBiggestBidSuccessMail(auction);
			}
		}
	}
	
	
	
	/**
	 * Retrieves all auctions and returns them as model attribute <code>auctions</code>.
	 * 
	 * @return list of all auctions, as model attribute <code>auctions</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model){
		deactivateExpiredAuctionsAndSendMail();
		List<Auction> auctions = auctionService.findByActiveTrue();
		model.addAttribute("auctions", auctions);
		return "auctions";
	}
	
	/**
	 * Retrieves all auctions posted by currently logged user and returns them as model attribute <code>auctions</code>.
	 * 
	 * @return list of all auctions posted by currently logged user, as model attribute <code>auctions</code>
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/myAuctions",method = RequestMethod.GET)
	public String getUserAuctions(Model model, Principal principal){
		String username = principal.getName();
		deactivateExpiredAuctionsAndSendMail();
		List<Auction> auctions = auctionService.findByActiveTrueAndUserUsername(username);
		model.addAttribute("auctions", auctions);
		return "auctions";
	}
	
	
	/**
	 * Retrieves all auctions that currently logged user bidded on, and returns them as model attribute <code>auctions</code>.
	 * 
	 * @return list of all auctions that currently logged user bidded on, as model attribute <code>auctions</code>
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/myBids",method = RequestMethod.GET)
	public String getUserBiddedAuctions(Model model, Principal principal){
		String username = principal.getName();
		Long userId = userService.findByUsername(username).getId();
		deactivateExpiredAuctionsAndSendMail();
		
		List<Auction> auctions = auctionService.findByBidderId(userId);
		model.addAttribute("auctions", auctions);
		return "auctions";
	}
	
	
	/**
	 * Returns the view for adding new auction. Adds empty auction as model attribute <code>auction</code>.
	 * 
	 * @param model the model object map
	 * @return the name of the view for adding/editing a auction
	 * 
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew (Model model){
		model.addAttribute("auctionFilesDTO", new AuctionFilesDTO());
		return "addAuction";
	}
	
	
	
	/**
	 * Uploader for images passed through auctionFilesDTO object.
	 * 
	 * @param files the list of image files
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private List<Image> uploadFiles (List<MultipartFile> files){
		List<Image> images = new ArrayList<Image>();
		String saveDirectory = "D:/levi/";
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
            	Image image = new Image();
                String fileName = multipartFile.getOriginalFilename();
                
                try {
					multipartFile.transferTo(new File(saveDirectory + fileName));
					image.setFileName(fileName);
	                images.add(image);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
            }
        }
		return images;
	}
	
	
	/**
	 * Persists the passed auction object.
	 * 
	 * @param auction the auction to persist
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the redirect view name, which redirects to auctions list
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping (params = "save")
	public String post (@Valid AuctionFilesDTO auctionFilesDTO, BindingResult bindingResult, 
			Model model, Principal principal, @RequestParam(value = "duration") String duration) 
					throws IllegalStateException, IOException{
		String viewName;
		Auction auction = auctionFilesDTO.getAuction();
		List<MultipartFile> files = auctionFilesDTO.getFiles();
		if (files.size()==1 && files.get(0).getContentType().equals("application/octet-stream")){
			bindingResult.rejectValue("files", "error.photoNotUploaded");
			return "addAuction";
		}
		for (MultipartFile mf : files){
			if (!mf.getContentType().equals("image/png") && !mf.getContentType().equals(
					"application/octet-stream")){
				bindingResult.rejectValue("files", "error.wrongFileFormat");
				return "addAuction";
			}
		}
		if (!bindingResult.hasErrors()){
			Date now = new Date();
			auction.setStartTime(now);
			Calendar c = Calendar.getInstance(); 
			c.setTime(now); 
			c.add(Calendar.DATE, Integer.parseInt(duration));
			Date endTime = c.getTime();
			auction.setEndTime(endTime);
			User user = userService.findByUsername(principal.getName());
			auction.setUser(user);
			auction.setActive(true);
			auction.setImages(uploadFiles(files));
			auctionService.save(auction);
			viewName = "redirect:auctions"; 
		}
		else{
			model.addAttribute("auctionFilesDTO", auctionFilesDTO);
			viewName = "addAuction";
		}
		return viewName;
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:auctions";
	}
	
	/**
	 * Returns the detail view of a auction. Adds auction, found by passed id, as model attribute
	 * <code>auction</code>.
	 * 
	 * @param id the id of the auction
	 * @param model the model object map
	 * @return the name of the view for detail view of a auction and/or adding a bid
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String details(@PathVariable Long id, Model model) throws  UnsupportedEncodingException {
		Auction auction = auctionService.findOne(id);
		if (auction==null || auction.isActive()==false){
			return "redirect:..";
		}
		model.addAttribute("auction", auctionService.findOne(id));
		model.addAttribute("auctionBid", new AuctionBid());
		return "auctionDetails";
	}
	
	
	/**
	 * Adds new bid to auction. 
	 * 
	 * @param auctionId the auction to add bid to
	 * @param model the model object map
	 * @return the name of the view for detail view of auction
	 */
	@RequestMapping(params = "auctionId", method = RequestMethod.POST)
	public String addBid(@Valid AuctionBid auctionBid, BindingResult bindingResult, Model model, Principal principal,
			@RequestParam(value = "auctionId") Long auctionId) {
		Auction auction = auctionService.findOne(auctionId);
		System.out.println(auction.getImages().size()+"prva");
		double currentPrice = 0;
		if (!bindingResult.hasErrors()){
			if (auction.getAuctionBids().isEmpty()){
				currentPrice = auction.getStartPrice();
			}
			else {
				currentPrice= auction.getAuctionBids().get(0).getBidValue();
			}
			if (Double.compare(auctionBid.getBidValue(), currentPrice) != 1 ){
				model.addAttribute("auctionBid", auctionBid);
				model.addAttribute("auction", auction);
				model.addAttribute("smallBidError", "Bid must be higher than current price");
				return "auctionDetails";
			}
			else{
				auctionBid.setUser(userService.findByUsername(principal.getName()));
				auctionBid.setBidDate(new Date());
				auction.getAuctionBids().add(auctionBid);
				auctionService.save(auction);
				System.out.println(auction.getImages().size()+"aaaaaaaaaaaaaaaa");
				model.addAttribute("auction", auction);
				
				return "redirect:auctions/details/"+auction.getId();
			}
		}
		else{
			model.addAttribute("auctionBid", auctionBid);
			model.addAttribute("auction", auction);
			return "auctionDetails";
			
		}
			
	}
	@RequestMapping(value = "/buyNow/{id}")
	public String buyOut(@PathVariable Long id, Principal principal) {
		Auction auction = auctionService.findOne(id);
		auction.setActive(false);
		auctionService.save(auction);
		User user = userService.findByUsername(principal.getName());
		mailService.sendBuyOutSuccesMail(user, auction);
		return "redirect:..";
	}
	
}
