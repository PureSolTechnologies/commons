package com.puresoltechnologies.commons.misc.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourceUtilities {

    public static void copy(Class<?> clazz, String resource, OutputStream outputStream) throws IOException {
	try (InputStream inputStream = clazz.getResourceAsStream(resource)) {
	    FileUtilities.copy(inputStream, outputStream);
	}
    }

    public static void copy(Class<?> clazz, String resource, File target) throws IOException {
	File targetFile = target;
	if ((target.exists()) && (target.isDirectory())) {
	    targetFile = new File(targetFile, new File(resource).getName());
	}
	try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
	    copy(clazz, resource, outputStream);
	}
    }

}
