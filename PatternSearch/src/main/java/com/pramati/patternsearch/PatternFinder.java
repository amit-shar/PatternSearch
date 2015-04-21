package com.pramati.patternsearch;

import com.pramati.patternsearch.services.PatternSearchService;
import com.pramati.patternsearch.services.PatternSearchServiceImpl;

public class PatternFinder {
	
	
	
	public static void main(String args[])
	{
		String firstFile= "/home/amits/CrawlerController.java" ;//args[0];
		String secondFile= "/home/amits/CrawlerController.java" ;//args[1];
		
		PatternSearchService patternSearchService= new PatternSearchServiceImpl();
		
		patternSearchService.findMatcher(firstFile, secondFile);
		
		
		
	}

}
