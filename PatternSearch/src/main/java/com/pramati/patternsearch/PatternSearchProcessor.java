package com.pramati.patternsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PatternSearchProcessor {

	private static final String WHITESPACE_PATTERN = "\\s+";

	public void findMatcher(String firstPath, String secondPath) {

		List<String> firstFileContent = getFileContent(firstPath);
		List<String> secondFileContent = getFileContent(secondPath);

		Map<String, String> keyNamePair = new Hashtable<String, String>();

		if (firstFileContent.size() > 0) {

			for (String name : firstFileContent) {
				String[] wordsInLine = name.split(WHITESPACE_PATTERN);

				if (wordsInLine.length > 0) {

					String key = (wordsInLine[0] + " " + wordsInLine[wordsInLine.length - 1])
							.toLowerCase();

					keyNamePair.put(key, name);
					System.out.println("abc");

				}
			}

		}

		Map<String, String> commonString = findCommonString(secondFileContent,
				keyNamePair);

		display(commonString);

	}

	@SuppressWarnings("rawtypes")
	private void display(Map<String, String> commonString) {

		if (commonString != null && commonString.size() > 0) {

			System.out.println("Size of common string map is : "
					+ commonString.size());

			Set<Entry<String, String>> set = commonString.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();

			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				System.out.println(entry.getKey() + "  matches with :  "
						+ entry.getValue());
			}

		}

		else {
			System.out.println("No common String exists");
		}

	}

	private Map<String, String> findCommonString(
			List<String> secondFileContent, Map<String, String> keyNamePair) {

		Map<String, String> commonString = new Hashtable<String, String>();

		if (secondFileContent.size() > 0) {

			for (String name : secondFileContent) {

				String[] wordsInLine = name.split(WHITESPACE_PATTERN);
				if (wordsInLine.length > 0) {

					String key = (wordsInLine[0] + " " + wordsInLine[wordsInLine.length - 1])
							.toLowerCase();
					if (keyNamePair.containsKey(key))

					{
						commonString.put(keyNamePair.get(key), name);

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
