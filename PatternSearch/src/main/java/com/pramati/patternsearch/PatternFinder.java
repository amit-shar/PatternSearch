package com.pramati.patternsearch;

import com.pramati.patternsearch.services.PatternSearchService;
import com.pramati.patternsearch.services.PatternSearchServiceImpl;

public class PatternFinder {
	
	
	
	public static void main(String args[])
	{
		String firstFile= args[0];
		String secondFile= args[1];
		
		PatternSearchService patternSearchService= new PatternSearchServiceImpl();
		
		
		long startTime = System.currentTimeMillis();
		
		if(firstFile!=null && secondFile!=null)
		{
			patternSearchService.findMatcher(firstFile, secondFile);
		}
		
		else
		{
			System.out.println("Please enter absolute file path");
		}
		
        long endTime = System.currentTimeMillis();

		System.out.println("Time taken" +(endTime - startTime));  
			
			
	}

}
