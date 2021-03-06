package com.puresoltechnologies.commons.misc.hash;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides some utilities for the analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashUtilities {

    private static final HashAlgorithm DEFAULT_MESSAGE_DIGEST_ALGORITHM = HashAlgorithm.SHA256;

    public static HashAlgorithm getDefaultMessageDigestAlgorithm() {
	return DEFAULT_MESSAGE_DIGEST_ALGORITHM;
    }

    public static MessageDigest getDefaultMessageDigest() {
	try {
	    return MessageDigest.getInstance(DEFAULT_MESSAGE_DIGEST_ALGORITHM
		    .getAlgorithmName());
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException("Default message digest algorithm '"
		    + DEFAULT_MESSAGE_DIGEST_ALGORITHM.getAlgorithmName()
		    + "' is not available.");
	}
    }

    /**
     * This method creates a {@link HashId} from a {@link File}.
     * 
     * @param file
     *            the file to be read to calculate the {@link HashId}.
     * @return A {@link HashId} object is returned.
     * @throws IOException
     *             is thrown in case of an IO issue.
     */
    public static HashId createHashId(File file) throws IOException {
	try (FileInputStream stream = new FileInputStream(file)) {
	    return createHashId(stream);
	}
    }

    /**
     * This method creates a {@link HashId} from a {@link String}.
     * 
     * @param string
     *            is the string from which the {@link HashId} is to be created.
     * @return A {@link HashId} is returned.
     * @throws IOException
     *             is thrown in cases of IO issues.
     */
    public static HashId createHashId(String string) throws IOException {
	try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
		string.getBytes(Charset.defaultCharset()))) {
	    return createHashId(byteArrayInputStream);
	}
    }

    private static HashId createHashId(InputStream stream) throws IOException {
	try (DigestInputStream digestInputStream = new DigestInputStream(
		stream, HashUtilities.getDefaultMessageDigest())) {
	    byte buffer[] = new byte[256];
	    while (digestInputStream.read(buffer) >= 0)
		;
	    byte[] hashBytes = digestInputStream.getMessageDigest().digest();
	    String hashString = convertByteArrayToString(hashBytes);
	    return new HashId(HashUtilities.getDefaultMessageDigestAlgorithm(),
		    hashString);
	}
    }

    /**
     * This method converts a byte array into a string converting each byte into
     * a 2-digit hex representation and appending them all together.
     * 
     * @param byteArray
     *            is the array of bytes to be converted.
     * @return A {@link String} is returned representing the byte array.
     */
    private static String convertByteArrayToString(byte[] byteArray) {
	if (byteArray == null) {
	    throw new IllegalArgumentException("Byte array must not be null!");
	}
	StringBuffer hexString = new StringBuffer();
	for (int i = 0; i < byteArray.length; i++) {
	    int digit = 0xFF & byteArray[i];
	    String hexDigits = Integer.toHexString(digit);
	    if (hexDigits.length() < 2) {
		hexString.append("0");
	    }
	    hexString.append(hexDigits);
	}
	return hexString.toString();
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private HashUtilities() {
    }
}
