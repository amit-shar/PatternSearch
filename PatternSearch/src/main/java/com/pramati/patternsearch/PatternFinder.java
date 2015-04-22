package com.pramati.patternsearch;



public class PatternFinder {

	public static void main(String args[]) {
		String firstFile = args[0];
		String secondFile = args[1];

		PatternSearchProcessor patternSearchService = new PatternSearchProcessor();

		System.out.println("First file path : " + firstFile);
		System.out.println("Second file path : " + secondFile);

		long startTime = System.currentTimeMillis();

		if (firstFile != null && secondFile != null) {
			patternSearchService.findMatcher(firstFile, secondFile);
		}

		else {
			System.out.println("Please enter valid file path");
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Time taken to find the common names :"
				+ (endTime - startTime) + "milli-seconds");

	}

}
