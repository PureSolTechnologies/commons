package com.puresoltechnologies.commons.misc;

import java.io.Serializable;
import java.util.IllegalFormatException;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashId implements Serializable, Comparable<HashId> {

    private static final long serialVersionUID = 1219606473615058203L;

    public static final HashId valueOf(String hashIdString)
	    throws IllegalFormatException {
	String[] splits = hashIdString.split(":");
	if (splits.length != 2) {
	    throw new IllegalArgumentException(
		    "Could not convert string '"
			    + hashIdString
			    + "' into a valid hash id. There should be a colon ':' separator.");
	}
	String algorithmName = splits[0];
	String hash = splits[1];
	HashAlgorithm algorithm = HashAlgorithm
		.fromAlgorithmName(algorithmName);
	return new HashId(algorithm, hash);
    }

    /**
     * This is the name of the algorithm used to calculate the hash.
     */
    private final HashAlgorithm algorithm;

    /**
     * This is the actual hash which is used as id.
     */
    private final String hash;

    /**
     * This is the initial value constructor for this hash id.
     * 
     * @param algorithm
     *            is the identifier of the algorithm used.
     * @param hash
     *            is the hash.
     */
    public HashId(@JsonProperty("algorithm") HashAlgorithm algorithm,
	    @JsonProperty("hash") String hash) {
	super();
	if ((hash == null) || (hash.isEmpty())) {
	    throw new IllegalArgumentException(
		    "hash must not be null or empty.");
	}
	if (algorithm == null) {
	    throw new IllegalArgumentException(
		    "The algorithm must not be null.");
	}
	this.algorithm = algorithm;
	this.hash = hash;
    }

    public HashAlgorithm getAlgorithm() {
	return algorithm;
    }

    public String getHash() {
	return hash;
    }

    @Override
    public final String toString() {
	return algorithm.getAlgorithmName() + ":" + hash;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((algorithm == null) ? 0 : algorithm.hashCode());
	result = prime * result + ((hash == null) ? 0 : hash.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	HashId other = (HashId) obj;
	if (algorithm != other.algorithm)
	    return false;
	if (hash == null) {
	    if (other.hash != null)
		return false;
	} else if (!hash.equals(other.hash))
	    return false;
	return true;
    }

    @Override
    public int compareTo(HashId other) {
	return toString().compareTo(other.toString());
    }

}