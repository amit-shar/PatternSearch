package com.pramati.patternsearch;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatternSearchProcessorTest {
	
	private PatternSearchProcessor helper;

	@Before
	public void setUp() throws Exception {
		
		helper =new PatternSearchProcessor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEditDistance() {
		
		int actualCost=helper.editDistance("Himanshu", "Himansu");
		int expectedCost=1;
	   assertEquals(expectedCost, actualCost);
		
	}
	
public void testGetPhonetiPattern() {
		
		String actualPattern=helper.getPhonetiPattern("Himanshu");
		String expectedPattern="H552";
	   assertEquals(expectedPattern, actualPattern);
		
	}

}
