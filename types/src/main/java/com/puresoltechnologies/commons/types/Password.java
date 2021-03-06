package com.puresoltechnologies.commons.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a single password. This class basically keeps a simple
 * {@link String}, but this class was introduced to point developers to the
 * fact, that the object handled here, is a password to enforce some care in
 * handling it. Additionally, the toString method which is used for debugging
 * and in some monitoring software does not provide the password automatically.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class Password {

    private final String password;

    /**
     * This default constructor is only convenience for JSON serialization.
     */
    public Password() {
	password = null;
    }

    @JsonCreator
    public Password(@JsonProperty("password") String password) {
	this.password = password;
    }

    public String getPassword() {
	return password;
    }

    @Override
    public String toString() {
	return "XXXXXXXX (password)";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
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
	Password other = (Password) obj;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	return true;
    }

    @Override
    protected Password clone() {
	return new Password(password);
    }
}
