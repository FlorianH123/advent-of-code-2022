package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static String substringAfter(final String str, final String separator) {
		final Pattern pattern = Pattern.compile(separator + "(.*)");
		final Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		return str;
	}
}
