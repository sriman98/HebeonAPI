package in.l4g.hebeon.commons.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils
{
	private static final Random RANDOM = new SecureRandom();
	// private static final String ALPHABET =
	// "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static final int LENGTH = 8;

	public static String getRandomString()
	{
		StringBuilder sb = new StringBuilder(LENGTH);
		for (int i = 0; i < LENGTH; i++)
		{
			sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return sb.toString();
	}
}
