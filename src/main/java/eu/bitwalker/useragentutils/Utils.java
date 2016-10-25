package eu.bitwalker.useragentutils;

public class Utils {

	static String[] toLowerCase(String[] strArr) {
		if (strArr == null)
			return null;
		String[] res = new String[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			res[i] = toLowerCaseAscii(strArr[i]);
		}
		return res;
	}

	static String toLowerCaseAscii(String s) {
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if ((ch >= 'A' && ch <= 'Z'))
				ch += (char) 0x20;
			result[i] = ch;
		}
		return new String(result);
	}

	static boolean contains(String str, String[] strArr) {
		if (strArr == null)
			return false;
		for (String arrStr : strArr) {
			if (str.contains(arrStr)) {
				return true;
			}
		}
		return false;
	}

}
