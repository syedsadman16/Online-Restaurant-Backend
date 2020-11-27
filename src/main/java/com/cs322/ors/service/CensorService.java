package com.cs322.ors.service;


import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cs322.ors.model.TabooWord;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserWarning;


@Service
public class CensorService {
	@Autowired
	private TabooWordService tabooWordService;
	
	@Autowired
	private UserWarningService userWarningService;
	
	public String censor(String rawString, User currentUser) {
		 List<TabooWord> tabooWords = tabooWordService.getAllTabooWords();
		 List<String> censorPriorityList = tabooWords.stream().map(word -> word.getWord()).collect(Collectors.toList());
		 String newString = rawString;
		 int count = 0;
		 for (String tabooWord : censorPriorityList) {
			 Pattern PATTERN = Pattern.compile("(?i)\\b" + tabooWord + "\\b");
			 count += PATTERN.matcher(newString).results().count();			 
			 newString = RegExUtils.replaceAll(newString, "(?i)\\b" + tabooWord + "\\b", "***");
			 
		 }
		 if(count > 0) {
			 userWarningService.createWarning(new UserWarning(currentUser, "Bad word used"));
		 }
		 if(count >= 3) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Can not order special dishes");
		 }
		 
		return newString;
	}

}
