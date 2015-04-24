package com.pramati.patternsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatternSearchProcessor {

	private static final String WHITESPACE_PATTERN = "\\s+";

	public void findMatcher(String firstPath, String secondPath) {

		Set<String> firstFileContent = getFileContent(firstPath);
		Set<String> secondFileContent = getFileContent(secondPath);
		List<Tuple> matchingTupleList = new ArrayList<Tuple>();

		Tuple tupleObj;
		
		for (String firstFileRow : firstFileContent) {

			for (String secondFileRow : secondFileContent) {

				if (isSimilar(firstFileRow, secondFileRow)) {
					tupleObj = new Tuple();
					tupleObj.setFirstTuple(firstFileRow);
					tupleObj.setSecondTuple(secondFileRow);
					matchingTupleList.add(tupleObj);
					

				}

			}

		}

		display(matchingTupleList);

	}

	private boolean isSimilar(String firstTuple, String secondTuple) {

		String firstKey = getKey(firstTuple);
		String secondKey = getKey(secondTuple);

		return firstKey.equalsIgnoreCase(secondKey);

	}

	private String getKey(String value) {

		String[] wordsInLine = value.split(WHITESPACE_PATTERN);
		String key = "";

		if (wordsInLine.length > 0) {
			key = (wordsInLine[0] + " " + wordsInLine[wordsInLine.length - 1]);

		}

		return key;

	}

	private void display(List<Tuple> matchingList) {

		if (matchingList != null && matchingList.size() > 0) {

			System.out.println("Size of common string map is : "
					+ matchingList.size());

			for (Tuple value : matchingList) {
				System.out.println(value.getFirstTuple() + "  matches with "
						+ value.getSecondTuple());
			}

		}

		else {
			System.out.println("No common String exists");
		}

	}

	private Set<String> getFileContent(String path) {

		Set<String> nameSet = new HashSet<String>();

		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {

				if (line != "") {
					nameSet.add(line);
				}

			}

		} catch (IOException e) {
			System.err
					.println("In PatternSearchProcessor : getFileContent(). File IO exception");

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ioe) {
				System.err
						.println("In PatternSearchProcessor : getFileContent(). Exception occurred while closing the file");
			}

		}

		return nameSet;

	}

}
