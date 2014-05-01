package com.sinaapp.sanrenxing.util;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class SignatureUtil {

	public static boolean checkSignature(String signature, String timestamp,
			String nonce, String token) {

		// dictionary sort
		String[] dataStrings = new String[] { token, timestamp, nonce };
		Arrays.sort(dataStrings);
		// construct the three string
		String data = dataStrings[0] + dataStrings[1] + dataStrings[2];
		// sha1
		String flag = DigestUtils.shaHex(data);
		// check
		if (flag.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}
}
