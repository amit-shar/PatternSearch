package com.pramati.patternsearch.services;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class PatternSearchServiceImpl implements PatternSearchService {

	private List<String> wordList;

	private static final String NEWLINE_PATTERN="\\r?\\n";
	private static final String WHITESPACE_PATTERN="\\s+";


	public void findMatcher(String firstPath, String secondPath) {

		wordList= new ArrayList<String>();


		String firstFileContent=convertFileTextToString(firstPath);
		String secondFileContent=convertFileTextToString(secondPath);

		if(firstFileContent!=null && firstFileContent!=""){

			String textStr[] = firstFileContent.split(NEWLINE_PATTERN);
			String line;

			for(int i=0;i<textStr.length;i++)
			{
				line=textStr[i];

				if(line!="" || line!=" "){

					String [] wordsInLine=line.split(WHITESPACE_PATTERN);
					wordList.add((wordsInLine[0]+" "+wordsInLine[wordsInLine.length-1]).toLowerCase());	
				}

			}

			List<String> commonString=findCommonString(secondFileContent,wordList);

			display(commonString);

		}

		else
		{
			System.out.println("First file is empty");
		}

	} 

	private void display(List<String> commonString) {

		if(commonString!=null && commonString.size()>0)
		{
			System.out.println("Size of common string list is : "+commonString.size());

			Iterator<String> it = commonString.iterator();

			while(it.hasNext())
			{
				System.out.println(it.next());
			}
		}

		else
			{
			System.out.println("No common String exists");
			}

	}

	private List<String> findCommonString(String secondFileContent, List<String> wordList) {


		List<String> commonString= new ArrayList<String>();



		if(wordList!=null && secondFileContent!=null && secondFileContent!=""){

			String word;
			String textStr[] = secondFileContent.split(NEWLINE_PATTERN);

			for(int i=0;i<textStr.length;i++)
			{   
				word=textStr[i];

				if(word!=" " || word!=""){
					String [] wordsInLine=textStr[i].split(WHITESPACE_PATTERN);;

					if(wordList.contains((wordsInLine[0]+" "+wordsInLine[wordsInLine.length-1]).toLowerCase()))
						//|| wordList.contains(wordsInLine[0].toLowerCase())) 
					{

						commonString.add(word);

					}

				}

			}
		}
		return commonString;	

	}


	private String convertFileTextToString(String path)
	{
		String fileContent="";

		BufferedReader br=null;

		try {
			br = new BufferedReader(new FileReader(path));
			Writer out = new StringWriter();

			for(int i=br.read();i!=-1;i=br.read()){
				out.write(i);
			}

			fileContent=out.toString();

		}catch (IOException e) {
			System.out.println("In PatternSearchServiceImpl : convertFileTextToString(). File IO exception");

		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ioe) {
				System.out.println("In PatternSearchServiceImpl : convertFileTextToString(). Exception occurred while closing the file");
			}

		}

		return fileContent;

	}

}
