package com.cs322.ors.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.DishKeyWord;
import com.cs322.ors.service.DishKeyWordService;

@RestController
@RequestMapping("/api/keyword")
public class DishKeyWordController {
	@Autowired
	public DishKeyWordService dishKeyWordService;
	
	@GetMapping    //no preauth required as even people without accounts should be able to see category/keyword of dishes
	public List<DishKeyWord> getKeyWord(){
		return dishKeyWordService.getAllKeyWords();
	}
	
	@GetMapping("/{dishId}")   //find all keywords associated with a particular dish
	public List<DishKeyWord> getDishKeyWord(@PathVariable long dishId){
		return dishKeyWordService.getDishKeyWord(dishId);
	}
	
//	@GetMapping("/{keyWord}")	//find all dishes associated with a keyword
//	public List<DishKeyWord> getDishByWord(@PathVariable String keyWord){
//		return dishKeyWordService.getDishByWord(keyWord);	
//	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('CHEF','MANAGER')")
	public void createKeyWord(@Valid @RequestBody DishKeyWord keyWord) {
		dishKeyWordService.createWord(keyWord);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('CHEF','MANAGER')")
	public void updateKeyWord() {
		
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('CHEF','MANAGER')")
	public void deleteSKeyWord() {
		
	}

}
