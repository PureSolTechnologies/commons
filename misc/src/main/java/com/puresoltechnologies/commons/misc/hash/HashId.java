package com.puresoltechnologies.commons.misc.hash;

import java.io.Serializable;
import java.util.IllegalFormatException;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	private final int hashCode;

	/**
	 * This is the initial value constructor for this hash id.
	 * 
	 * @param algorithm
	 *            is the identifier of the algorithm used.
	 * @param hash
	 *            is the hash.
	 */
	public HashId(HashAlgorithm algorithm, String hash) {
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
		this.hashCode = Objects.hash(algorithm, hash);
	}

	/**
	 * This is the 2nd initial value constructor for this hash id. <b>This
	 * constructor is needed to provide the algorithm name,too. The algorithm
	 * name is implicitly provided by the algorithm, but is needed on WebUI
	 * site, too. To get it JSON marshalled and unmarshalled, this constructor
	 * was added.</b> In normal code use the other constructor.
	 * 
	 * @param algorithm
	 *            is the identifier of the algorithm used.
	 * @param algorithmName
	 *            is the name of the algorithm. This name is checked against
	 *            algorithm for consistency. An {@link IllegalArgumentException}
	 *            is thrown in case of mismatch.
	 * @param hash
	 *            is the hash.
	 */
	@JsonCreator
	public HashId(@JsonProperty("algorithm") HashAlgorithm algorithm,
			@JsonProperty("algorithmName") String algorithmName,
			@JsonProperty("hash") String hash) {
		this(algorithm, hash);
		if (!algorithmName.equals(algorithm.getAlgorithmName())) {
			throw new IllegalArgumentException("Provided algorithm name '"
					+ algorithmName + "' does not match to the algorithm '"
					+ algorithm.name() + "'.");
		}
	}

	public HashAlgorithm getAlgorithm() {
		return algorithm;
	}

	public String getAlgorithmName() {
		return algorithm.getAlgorithmName();
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
		return hashCode;
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
