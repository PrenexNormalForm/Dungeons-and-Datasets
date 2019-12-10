package model.utilities;
/*
Last updated November 5, 2019

This tool generates UUIDs.

Contributors:
Eva Moniz
 */

import java.util.Random;

/**
 * This tool generates UUIDs.
 *
 * @author Eva Moniz
 */
public final class UUID {

    private static final String DEFAULT_PREFIX = "";
    private static final int DEFAULT_LENGTH = 20;
    private static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static Random random = new Random();

    /**
     * Generate a simple UUID with default settings. Chance of collision of two
     * UUIDs with these settings is less than 1 in 7E35.
     *
     * @return a new UUID
     */
    public static String generateUUID() {
        return UUID.generateUUID(
                UUID.DEFAULT_ALPHABET,
                UUID.DEFAULT_PREFIX,
                UUID.DEFAULT_LENGTH);
    }

    /**
     * Generate a UUID from the given alphabet of characters with the given
     * prefix and length.
     *
     * @param _alphabet A string from which characters of the UUID will be
     * selected at random. If the alphabet contains repeated characters, the
     * random selection will not have a flat distribution.
     * @param _prefix A prefix to append to the generated UUID.
     * @param _length The length of the random portion of the UUID (not counting
     * the prefix length).
     * @return A new UUID, whose length is _length + the length of the prefix
     */
    public static String generateUUID(String _alphabet, String _prefix, int _length) {
        StringBuilder stringBuilder = new StringBuilder(_prefix);
        for (int i = 0; i < _length; i++) {
            int selection = UUID.random.nextInt(_alphabet.length());
            stringBuilder.append(_alphabet.charAt(selection));
        }
        return stringBuilder.toString();
    }

    private UUID() {
        //unreachable constructor disallows instantiation
    }
}
