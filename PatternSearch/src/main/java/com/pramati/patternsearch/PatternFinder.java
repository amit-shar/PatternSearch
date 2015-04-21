package com.pramati.patternsearch;

import com.pramati.patternsearch.services.PatternSearchService;
import com.pramati.patternsearch.services.PatternSearchServiceImpl;

public class PatternFinder {
	
	
	
	public static void main(String args[])
	{
		String firstFile= args[0];
		String secondFile= args[1];
		
		System.out.println(secondFile);
		PatternSearchService patternSearchService= new PatternSearchServiceImpl();
		
		patternSearchService.findMatcher(firstFile, secondFile);
		
		
		
	}

}
