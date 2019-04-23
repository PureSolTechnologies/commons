package com.puresoltechnologies.commons.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puresoltechnologies.commons.misc.io.PathResolutionException;
import com.puresoltechnologies.commons.misc.io.PathUtils;

public class PathUtilsTest {

	private static final String os = System.getProperty("os.name");

	@BeforeEach
	public void test() {
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println(key + "=" + properties.getProperty(key.toString()));
		}
	}

	@Test
	public void testClassToRelativePackagePath() {
		File relativePath = PathUtils.classToRelativePackagePath(PathUtilsTest.class);
		assertTrue(new File("src/test/java", relativePath.getPath()).exists());
	}

	@Test
	public void testNormalizePathLinux() {
		assumeTrue(os.equals("Linux"));
		assertEquals("destination/to.txt",
				PathUtils.normalizePath(new File("redundant/../destination/to.txt")).getPath());
		assertEquals("/destination/to.txt",
				PathUtils.normalizePath(new File("/redundant/../destination/to.txt")).getPath());
		assertEquals("/root/destination/to.txt",
				PathUtils.normalizePath(new File("/root/redundant/../destination/to.txt")).getPath());
		assertEquals("/destination/to.txt",
				PathUtils.normalizePath(new File("/root/redundant/../../destination/to.txt")).getPath());
		assertEquals("/destination/to.txt",
				PathUtils.normalizePath(new File("/root//redundant/../../destination//to.txt")).getPath());
		assertEquals("/a/b/c/d/e", PathUtils.normalizePath(new File("/a/b/././c/d/e/.")).getPath());
	}

	@Test
	public void testNormalizePathWindows() {
		assumeTrue(os.equals("Windows"));
		assertEquals("destination\\to.txt",
				PathUtils.normalizePath(new File("redundant/../destination/to.txt")).getPath());
		assertEquals("\\destination\\to.txt",
				PathUtils.normalizePath(new File("/redundant/../destination/to.txt")).getPath());
		assertEquals("\\root\\destination\\to.txt",
				PathUtils.normalizePath(new File("/root/redundant/../destination/to.txt")).getPath());
		assertEquals("\\destination\\to.txt",
				PathUtils.normalizePath(new File("/root/redundant/../../destination/to.txt")).getPath());
		assertEquals("\\destination\\to.txt",
				PathUtils.normalizePath(new File("/root//redundant/../../destination//to.txt")).getPath());
		assertEquals("\\a\\b\\c\\d\\e", PathUtils.normalizePath(new File("/a/b/././c/d/e/.")).getPath());
	}

	@Test
	public void testGetRelativePathsUnix() throws Exception {
		assertEquals("stuff/xyz.dat", PathUtils.getRelativePath("/var/data/", "/var/data/stuff/xyz.dat", "/"));
		assertEquals("../../b/c", PathUtils.getRelativePath("/a/x/y/", "/a/b/c", "/"));
		assertEquals("../../b/c", PathUtils.getRelativePath("/m/n/o/a/x/y/", "/m/n/o/a/b/c", "/"));
	}

	@Test
	public void testGetRelativePathFileToFile() throws Exception {
		String to = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String from = "C:\\Windows\\Speech\\Common\\sapisvr.exe";

		String relPath = PathUtils.getRelativePath(from, to, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToFile() throws Exception {
		String to = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String from = "C:\\Windows\\Speech\\Common\\";

		String relPath = PathUtils.getRelativePath(from, to, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathFileToDirectory() throws Exception {
		String to = "C:\\Windows\\Boot\\Fonts";
		String from = "C:\\Windows\\Speech\\Common\\foo.txt";

		String relPath = PathUtils.getRelativePath(from, to, "\\");
		assertEquals("..\\..\\Boot\\Fonts", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToDirectory() throws Exception {
		String to = "C:\\Windows\\Boot\\";
		String from = "C:\\Windows\\Speech\\Common\\";
		String expected = "..\\..\\Boot\\";

		String relPath = PathUtils.getRelativePath(from, to, "\\");
		assertEquals(expected, relPath);
	}

	@Test
	public void testGetRelativePathWithEqualsToAndFrom() throws Exception {
		String to = "/a/b/c";
		String from = "/a/b/c";
		String expected = "";

		String relPath = PathUtils.getRelativePath(from, to, "\\");
		assertEquals(expected, relPath);
	}

	@Test
	public void testGetRelativePathDifferentDriveLetters() throws Exception {
		String to = "D:\\sources\\recovery\\RecEnv.exe";
		String from = "C:\\Java\\workspace\\AcceptanceTests\\Standard test data\\geo\\";
		assertThrows(PathResolutionException.class, () -> {
			PathUtils.getRelativePath(from, to, "\\");
		});
	}

}
