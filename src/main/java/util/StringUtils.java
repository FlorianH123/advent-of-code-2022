package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static String substringAfter(final String str, final String separator) {
		final Pattern pattern = Pattern.compile(".*" + separator + "(.*)");
		final Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return null;
	}

	public static boolean isNumber(final String str) {
		final var pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

		if (str == null) {
			return false;
		}

		return pattern.matcher(str).matches();
	}
}
