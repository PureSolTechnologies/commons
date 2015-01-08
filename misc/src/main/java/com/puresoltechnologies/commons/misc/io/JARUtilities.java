package com.puresoltechnologies.commons.misc.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides some utilities for handling jar files and their content.
 * 
 * @author Rick-Rainer Ludwig
 */
public class JARUtilities {

    public static void copyResource(URL resource, File destination)
	    throws IOException {
	if (resource == null) {
	    throw new IllegalArgumentException("URL must not be null.");
	}
	if (destination == null) {
	    throw new IllegalArgumentException("URL must not be null.");
	}
	try (InputStream inStream = resource.openStream()) {
	    File parent = destination.getParentFile();
	    if ((parent != null) && (!parent.exists())) {
		if (!parent.mkdirs()) {
		    throw new IOException("Could not create target directory '"
			    + parent + "'.");
		}
	    }
	    try (FileOutputStream outStream = new FileOutputStream(destination)) {
		byte[] buffer = new byte[1024];
		int amount;
		while ((amount = inStream.read(buffer)) >= 0) {
		    outStream.write(buffer, 0, amount);
		}
	    }
	}
    }

    /**
     * This class reads a properties file and replaces constructs surrounded by
     * ${ and } by a system properties specified within the curly brackets. For
     * example, the construct ${user.home} is replaced by the used home
     * directory.
     * 
     * @param url
     *            is the url to be read.
     * @return Returns a Properties object containing the properties.
     * @throws IOException
     *             is thrown in cases of IO failures.
     */
    public static Properties readPropertyFile(URL url) throws IOException {
	if (url == null) {
	    throw new IllegalArgumentException("URL must not be null.");
	}
	try (InputStream inputStream = url.openStream()) {
	    Properties properties = new Properties();
	    properties.load(inputStream);
	    Pattern pattern = Pattern.compile("\\$\\{([\\w.]+)\\}");
	    for (Entry<Object, Object> entry : properties.entrySet()) {
		Object key = entry.getKey();
		String value = (String) entry.getValue();
		while (true) {
		    Matcher matcher = pattern.matcher(value);
		    if (!matcher.find()) {
			break;
		    }
		    String string = matcher.group(1);
		    String propertyValue = System.getProperty(string);
		    value = value.replaceAll("\\$\\{" + string + "\\}",
			    propertyValue);
		}
		properties.put(key, value);
	    }
	    return properties;
	}
    }

    public static String readResourceFileToString(URL url) throws IOException {
	if (url == null) {
	    throw new IllegalArgumentException("URL must not be null.");
	}
	try (InputStream inStream = url.openStream()) {
	    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
	    byte buffer[] = new byte[1024];
	    int length = inStream.read(buffer);
	    while (length > 0) {
		byteArray.write(buffer, 0, length);
		length = inStream.read(buffer);
	    }
	    return byteArray.toString(Charset.defaultCharset().name());
	}
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private JARUtilities() {
    }
}