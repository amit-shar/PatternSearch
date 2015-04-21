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

	public void findMatcher(String firstPath, String secondPath) {

		List<String> wordList= new ArrayList<String>();
		

		String firstFileContent=convertFileTextToString(firstPath);
		String secondFileContent=convertFileTextToString(secondPath);
        
	
		String textStr[] = firstFileContent.split("\\r?\\n");
		

		for(int i=0;i<textStr.length;i++)
		{
			String [] wordsInLine=textStr[i].split("\\s+");
	    	wordList.add(wordsInLine[0].toLowerCase()+wordsInLine[wordsInLine.length-1].toLowerCase());	

		}

		List<String> commonString=findCommonString(secondFileContent,wordList);

		display(commonString);

	} 

	private void display(List<String> commonString) {

		if(commonString!=null)
		{
			System.out.println(commonString.size());
			Iterator<String> it = commonString.iterator();

			while(it.hasNext())
				System.out.println(it.next());
		}

		else
			System.out.println("No common string exists");

	}

	private List<String> findCommonString(String secondFileContent, List<String> wordList) {


		List<String> commonString= new ArrayList<String>();
		if(wordList!=null && secondFileContent!=null){

			String word;


			String textStr[] = secondFileContent.split("\\r?\\n");
			

			for(int i=0;i<textStr.length;i++)
			{   
				word=textStr[i];
		    	String [] wordsInLine=textStr[i].split("\\s+");;
		    	
		    	if(wordList.contains(wordsInLine[0].toLowerCase()+wordsInLine[wordsInLine.length-1].toLowerCase())
		    			|| wordList.contains(wordsInLine[0].toLowerCase())) 
					commonString.add(word);

			}
			
		}
		return commonString;	

	}

	@SuppressWarnings("resource")
	private String convertFileTextToString(String path)
	{

		String fileContent="";
		//InputStream is = null;

		BufferedReader br=null;

		try {
			br = new BufferedReader(new FileReader(path));

			Writer out = new StringWriter();
			for(int i=br.read();i!=-1;i=br.read()){
				out.write(i);
			}

			fileContent=out.toString();

		}catch (IOException e) {
             e.printStackTrace();

		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ioe) {

			}

		}

		return fileContent;

	}

}
