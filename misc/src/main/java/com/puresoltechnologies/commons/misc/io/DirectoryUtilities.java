package com.puresoltechnologies.commons.misc.io;

import java.io.File;
import java.io.IOException;

public class DirectoryUtilities {

    public static void mkdirs(File directory) throws IOException {
	if (directory.exists()) {
	    if (!directory.isDirectory()) {
		throw new IllegalStateException("Directory '" + directory + "' exists, but is not a directory.");
	    }
	} else {
	    if (!directory.mkdirs()) {
		throw new IOException("Directory '" + directory + "' could not be created.");
	    }
	}
    }

}
