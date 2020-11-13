package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.DishKeyWordRepository;
import com.cs322.ors.model.DishKeyWord;


@Service
public class DishKeyWordService {
	@Autowired
	DishKeyWordRepository dishKeyWordRepository;

	public List<DishKeyWord> getAllKeyWords() {
		return dishKeyWordRepository.findAll();
	}

	public List<DishKeyWord> getDishKeyWord(long dishId) {
		return dishKeyWordRepository.findByDish_Id(dishId);
		
	}
	
	public Optional<DishKeyWord> getDishByWordId(long keywordId) {
		return dishKeyWordRepository.findById(keywordId);
	
	}


	public List<DishKeyWord> getDishByWord(String keyWord) {
		return dishKeyWordRepository.findByKeyWord(keyWord);
	
	}

	public void createWord(DishKeyWord keyWord) {
		dishKeyWordRepository.save(keyWord);
	}

	public void deleteKeyWord(long keywordId) {
		dishKeyWordRepository.deleteById(keywordId);	
	}
	
	

}
