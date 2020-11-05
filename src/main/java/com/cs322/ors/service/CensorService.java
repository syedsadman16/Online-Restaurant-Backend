package com.cs322.ors.service;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.TabooWord;


@Service
public class CensorService {
	@Autowired
	private TabooWordService tabooWordService;
	
	public String censor(String rawString) {
		 List<TabooWord> tabooWords = tabooWordService.getAllTabooWords();
		 List<String> censorPriorityList = tabooWords.stream().map(word -> word.getWord()).collect(Collectors.toList());
		 String newString = rawString;
		 for (String tabooWord : censorPriorityList) {
			 newString = RegExUtils.replaceAll(newString, "(?i)\\b" + tabooWord + "\\b", "***");
		 };		  
		return newString;
	}

}
