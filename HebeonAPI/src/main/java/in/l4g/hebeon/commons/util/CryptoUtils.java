package in.l4g.hebeon.commons.util;


import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CryptoUtils
{
	private static PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param password
	 * @return
	 */
	public static String getMD5Hash(String source)
	{
		String hash = null;

		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] digest = md.digest();
			hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		}
		catch (NoSuchAlgorithmException e)
		{
			hash = source;
		}

		return hash;
	}

	public static String getSha1(String input)
	{
		String hash = null;

		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			hash = String.format("%040x", new BigInteger(1, digest.digest()));
		}
		catch (Exception e)
		{
			// Do nothing
		}

		return hash;
	}

	public static KeyPair generateKeys() throws Exception
	{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		return kpg.generateKeyPair();
	}

	public static String getBCryptHash(String source)
	{
		if (passwordEncoder == null)
		{
			passwordEncoder = new BCryptPasswordEncoder();
		}

		return passwordEncoder.encode(source);
	}

	public static boolean matchBCryptHash(String password, String encodedPassword)
	{
		if (passwordEncoder == null)
		{
			passwordEncoder = new BCryptPasswordEncoder();
		}

		return passwordEncoder.matches(password, encodedPassword);
	}
}
