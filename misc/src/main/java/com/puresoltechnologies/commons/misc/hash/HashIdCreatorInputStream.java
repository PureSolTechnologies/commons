package com.puresoltechnologies.commons.misc.hash;

import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.puresoltechnologies.commons.types.StringUtils;

public class HashIdCreatorInputStream extends DigestInputStream {

    public static final HashAlgorithm DEFAULT_HASH_ALGORITHM = HashAlgorithm.SHA256;
    public static final MessageDigest DEFAULT_HASH;

    static {
	try {
	    DEFAULT_HASH = MessageDigest.getInstance(DEFAULT_HASH_ALGORITHM.getAlgorithmName());
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException("Could not initialize default hash algorithm for analysis store.", e);
	}
    }

    public HashIdCreatorInputStream(InputStream stream) {
	super(stream, DEFAULT_HASH);
    }

    public HashId getHashId() {
	byte[] hashBytes = getMessageDigest().digest();
	String hashString = StringUtils.convertByteArrayToString(hashBytes);
	return new HashId(HashUtilities.getDefaultMessageDigestAlgorithm(), hashString);
    }
}
