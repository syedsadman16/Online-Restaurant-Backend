package com.cs322.ors.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.TabooWord;
import com.cs322.ors.service.TabooWordService;

@RestController
@RequestMapping("/api/tabooword")
public class TabooWordController {

	@Autowired
	TabooWordService tabooWordService;

	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public List<TabooWord> listTabooWords() {
		return tabooWordService.getAllTabooWords();
	}

	@PostMapping
	@PreAuthorize("hasRole('MANAGER')")
	public TabooWord createTabooWord(@Valid @RequestBody TabooWord newTabooWord) {
		return tabooWordService.createTabooWord(newTabooWord);
	}

	
	@PatchMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')")
	public TabooWord updateTabooWord(@PathVariable long id, @RequestBody Map<String, String> updatedTabooWord){
		return tabooWordService.patchTabooWord(id, updatedTabooWord.get("word"));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')") 
	public void deleteTabooWord(@PathVariable long id) {
		tabooWordService.deleteTabooWord(id);
	}
}
