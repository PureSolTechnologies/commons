package com.puresoltechnologies.commons.misc.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * This class checks the FileSearch class.
 *
 * @author Rick-Rainer Ludwig
 *
 */
public class FileSearchTest {

	@Test
	public void testPattern() {
		String regExpString = FileSearch.wildcardsToRegExp("*");
		System.out.println(regExpString);
		Pattern pattern = Pattern.compile(regExpString);
		assertTrue(pattern.matcher("abc.xyz").matches());
		assertFalse(pattern.matcher(File.separator + "abc.xyz").matches());
	}

	@Test
	public void testPattern2() {
		String regExpString = FileSearch.wildcardsToRegExp(".*");
		System.out.println(regExpString);
		Pattern pattern = Pattern.compile(regExpString);
		assertTrue(pattern.matcher(".abc.xyz").matches());
		assertFalse(pattern.matcher("abc.xyz").matches());
	}
}
