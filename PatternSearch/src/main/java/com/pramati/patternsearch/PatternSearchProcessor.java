package com.pramati.patternsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class PatternSearchProcessor {

	public void findMatcher(String firstPath, String secondPath) {
		Set<String> firstFileContent = getFileContent(firstPath);
		Set<String> secondFileContent = getFileContent(secondPath);
		Set<Tuple> matchingTupleList = new HashSet<Tuple>();
		Tuple tupleObj;
		for (String firstFileRow : firstFileContent) {

			for (String secondFileRow : secondFileContent) {
				if (isSimilar(firstFileRow, secondFileRow)
						&& getSimilarity(firstFileRow, secondFileRow) >= 0.6F) {
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
		String firstKey = getPhonetiPattern(firstTuple.toLowerCase());
		String secondKey = getPhonetiPattern(secondTuple.toLowerCase());
		return firstKey.compareTo(secondKey) == 0 ? true : false;

	}

	protected int editDistance(String firstWord, String secondWord) {
		int firstWordLen = firstWord.length();
		int secondWordLen = secondWord.length();
		int replace;
		int insert;
		int delete;
		int min;
		char firstToken;
		char secondToken;

		// firstWordLen+1, secondWordLen+1, because finally return
		// minCost[firstWordLen][secondWordLen]
		int[][] minCost = new int[firstWordLen + 1][secondWordLen + 1];
		for (int i = 0; i <= firstWordLen; i++) {
			minCost[i][0] = i;
		}
		for (int j = 0; j <= secondWordLen; j++) {
			minCost[0][j] = j;
		}
		// iterate though, and check last char
		for (int i = 0; i < firstWordLen; i++) {
			firstToken = firstWord.charAt(i);
			for (int j = 0; j < secondWordLen; j++) {
				secondToken = secondWord.charAt(j);
				// if last two chars equal
				if (firstToken == secondToken) {
					// update minCost value for +1 length
					minCost[i + 1][j + 1] = minCost[i][j];
				} else {
					replace = minCost[i][j] + 1;
					insert = minCost[i][j + 1] + 1;
					delete = minCost[i + 1][j] + 1;
					min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					minCost[i + 1][j + 1] = min;
				}
			}
		}
		return minCost[firstWordLen][secondWordLen];
	}

	public float getSimilarity(String firstWord, String secondWord) {
		float dis = editDistance(firstWord, secondWord);
		float maxLen = firstWord.length();
		if (maxLen < secondWord.length())
			maxLen = secondWord.length();
		if (maxLen == 0.0F)
			return 1.0F;
		else
			return 1.0F - dis / maxLen;
	}

	protected String getPhonetiPattern(String line) {
		StringBuilder code = new StringBuilder();
		StringBuilder pattern = new StringBuilder();
		String result = "";
		char prev = 0;
		for (int i = 1; i < line.length(); i++) {
			if (line.charAt(i) == 'a' || line.charAt(i) == 'i'
					|| line.charAt(i) == 'e' || line.charAt(i) == 'o'
					|| line.charAt(i) == 'u' || line.charAt(i) == 'y'
					|| line.charAt(i) == 'h' || line.charAt(i) == 'w') {
				code.append('0');
			} else if (line.charAt(i) == 'c' || line.charAt(i) == 'g'
					|| line.charAt(i) == 'j' || line.charAt(i) == 'k'
					|| line.charAt(i) == 'q' || line.charAt(i) == 's'
					|| line.charAt(i) == 'x' || line.charAt(i) == 'z') {
				code.append('2');
			} else if (line.charAt(i) == 'b' || line.charAt(i) == 'p'
					|| line.charAt(i) == 'f' || line.charAt(i) == 'v') {
				code.append('1');
			} else if (line.charAt(i) == 'd' || line.charAt(i) == 't') {
				code.append('3');
			} else if (line.charAt(i) == 'm' || line.charAt(i) == 'n') {
				code.append('5');
			} else if (line.charAt(i) == 'l') {
				code.append('4');
			} else if (line.charAt(i) == 'r') {
				code.append('6');
			}
		}
		// remove adjacent duplicates
		for (int i = 0; i < code.length() - 1; i++) {
			if (prev != code.charAt(i)) {
				if (code.charAt(i) == code.charAt(i + 1)) {
					pattern.append(code.charAt(i));
					prev = code.charAt(i);
					i++;
				} else {
					pattern.append(code.charAt(i));
					prev = code.charAt(i);
				}
			}
		}
		// remove all zeros
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) != '0') {
				result += pattern.charAt(i);
			}
		}
		if (result.length() >= 3)
			return line.charAt(0) + result.substring(0, 3);
		else if (result.length() == 2)
			return line.charAt(0) + result + '0';
		else if (result.length() == 1)
			return line.charAt(0) + result + "00";
		else
			return line.charAt(0) + "000";
	}

	private void display(Set<Tuple> matchingList) {
		if (matchingList != null && matchingList.size() > 0) {
			System.out.println("Size of common string map is : "
					+ matchingList.size());
			for (Tuple value : matchingList) {
				System.out.println(value.getFirstTuple() + "  matches with "
						+ value.getSecondTuple());
			}
		} else {
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
