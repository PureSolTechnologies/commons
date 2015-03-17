package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashId;

public class HashIdTest {

    @Test
    public void testInstantiation() {
	HashId hashId = new HashId(HashAlgorithm.SHA384, "1234567890");
	assertEquals(HashAlgorithm.SHA384, hashId.getAlgorithm());
	assertEquals("1234567890", hashId.getHash());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiationNullAlgorithm() {
	new HashId(null, "1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiationNullHash() {
	new HashId(HashAlgorithm.SHA256, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiationEmptyHash() {
	new HashId(HashAlgorithm.SHA256, "");
    }

    @Test
    public void testSerialization() throws IOException {
	HashId hashId = new HashId(HashAlgorithm.SHA384, "1234567890");
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	JsonFactory jsonFactory = new JsonFactory(new ObjectMapper());
	JsonGenerator generator = jsonFactory.createGenerator(outputStream);
	generator.writeObject(hashId);
	ByteArrayInputStream inputStream = new ByteArrayInputStream(
		outputStream.toByteArray());
	JsonParser parser = jsonFactory.createParser(inputStream);
	HashId deserialized = parser.readValueAs(HashId.class);
	assertEquals(hashId, deserialized);
    }
}
