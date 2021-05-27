package com.hackerrank.practice.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamelCaseTest {
	
	private CamelCase  camelCase;
	
	@BeforeEach
	public void setUp() {
		camelCase = new CamelCase();
	}

	@Test
	void test() {
		String source = "saveChangesInTheEditor";
		assertEquals(5, camelCase.camelcase(source));
	}

}
