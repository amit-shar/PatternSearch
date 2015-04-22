package com.pramati.patternsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatternSearchProcessor {

	private List<String> wordList;

	private static final String WHITESPACE_PATTERN = "\\s+";

	public void findMatcher(String firstPath, String secondPath) {

		wordList = new ArrayList<String>();

		List<String> firstFileContent = getFileContent(firstPath);
		List<String> secondFileContent = getFileContent(secondPath);

		if (firstFileContent.size() > 0) {

			for (String name : firstFileContent) {
				String[] wordsInLine = name.split(WHITESPACE_PATTERN);

				if (wordsInLine.length > 0) {
					wordList.add((wordsInLine[0] + " " + wordsInLine[wordsInLine.length - 1])
							.toLowerCase());
				}
			}

		}

		List<String> commonString = findCommonString(secondFileContent,
				wordList);

		display(commonString);

	}

	private void display(List<String> commonString) {

		if (commonString != null && commonString.size() > 0) {
			System.out.println("Size of common string list is : "
					+ commonString.size());

			Iterator<String> it = commonString.iterator();

			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}

		else {
			System.out.println("No common String exists");
		}

	}

	private List<String> findCommonString(List<String> secondFileContent,
			List<String> wordList) {

		List<String> commonString = new ArrayList<String>();

		if (secondFileContent.size() > 0) {

			for (String name : secondFileContent) {

				String[] wordsInLine = name.split(WHITESPACE_PATTERN);
				if (wordsInLine.length > 0) {
					if (wordList
							.contains((wordsInLine[0] + " " + wordsInLine[wordsInLine.length - 1])
									.toLowerCase()))

					{
						commonString.add(name);

					}

				}
			}

		}
		return commonString;
	}

	private List<String> getFileContent(String path) {

		List<String> nameList = new ArrayList<String>();

		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {

				if (line != "") {
					nameList.add(line);
				}

			}

		} catch (IOException e) {
			System.err
					.println("In PatternSearchServiceImpl : convertFileTextToString(). File IO exception");

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ioe) {
				System.err
						.println("In PatternSearchServiceImpl : convertFileTextToString(). Exception occurred while closing the file");
			}

		}

		return nameList;

	}

}
