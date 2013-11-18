package io.rscnt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	public static String stringClearSpaces(String spacedString) {
		return spacedString.replaceAll("\\s+", "").trim();
	}

	public static String encodeToSHA512(String value) {
		String out = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			md.update(value.getBytes());
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}

		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return out;

	}

}
