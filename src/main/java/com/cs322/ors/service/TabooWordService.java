package com.cs322.ors.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;


import com.cs322.ors.db.TabooWordRepository;
import com.cs322.ors.model.TabooWord;

@Service
public class TabooWordService {

	@Autowired
	private TabooWordRepository tabooWordRepository;

	public TabooWord createTabooWord(TabooWord tabooWord) {
		return tabooWordRepository.save(tabooWord);
	}

	public List<TabooWord> getAllTabooWords() {
		return tabooWordRepository.findAll();
	}

	public Optional<TabooWord> getTabooWordById(long id) {
		return tabooWordRepository.findById(id);

	}

	public TabooWord patchTabooWord(long id, String word) {
		TabooWord  tabooWord = tabooWordRepository.findById(id).get();
		if (tabooWord != null) {
		    tabooWord.setWord(word);		
		    return tabooWordRepository.save(tabooWord);
		}
		return tabooWord;
		
	}


	public void deleteTabooWord(long id) {
		Optional<TabooWord> tabooWordDb = this.tabooWordRepository.findById(id);
		if (tabooWordDb.isPresent()) {
			tabooWordRepository.delete(tabooWordDb.get());
		}

	}

}
