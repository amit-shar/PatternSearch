package com.pramati.patternsearch.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternSearchServiceImpl implements PatternSearchService {

	public void findMatcher(String firstPath, String secondPath) {

		Set<String> wordSet= new HashSet<String>();
		//String patternCheck;

		String firstFileContent=convertFileTextToString(firstPath);
		String secondFileContent=convertFileTextToString(secondPath);

		/*if(firstFileContent.length()>secondFileContent.length())
		   patternCheck=secondFileContent;
		else
			patternCheck=secondFileContent;*/

		Pattern p = Pattern.compile("[\\w']+");
		Matcher m = p.matcher(firstFileContent);

		while(m.find()){

			wordSet.add(m.group());	

		}

		Set<String> commonString=findCommonString(secondFileContent,wordSet);

		display(commonString);




	} 

	private void display(Set<String> commonString) {

		if(commonString!=null)
		{
			Iterator<String> it = commonString.iterator();

			while(it.hasNext())
				System.out.println(it.next());
		}

		else
			System.out.println("No common string exists");

	}

	private Set<String> findCommonString(String secondFileContent, Set<String> wordSet) {


		Set<String> commonString= new HashSet<String>();
		if(wordSet!=null && secondFileContent!=null){

			Pattern p = Pattern.compile("[\\w']+");
			Matcher m = p.matcher(secondFileContent);
			String word;


			while(m.find()){
				word= m.group();

				if(wordSet.contains(word.toLowerCase()) || wordSet.contains(word.toUpperCase()) || wordSet.contains(word))
					commonString.add(word);

			}

		}
		return commonString;	

	}

	@SuppressWarnings("resource")
	private String convertFileTextToString(String path)
	{

		String fileContent="";
		InputStream is = null;

		BufferedReader br;



		try {
			br = new BufferedReader(new FileReader(path));

			Writer out = new StringWriter();
			for(int i=br.read();i!=-1;i=br.read()){
				out.write(i);
			}

			fileContent=out.toString();

		}catch (IOException e) {


		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException ioe) {

			}



		}

		return fileContent;

	}

}
