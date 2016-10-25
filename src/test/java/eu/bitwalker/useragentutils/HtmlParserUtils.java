package eu.bitwalker.useragentutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HtmlParserUtils {
	private static final Pattern patternHtmlTag = Pattern.compile("<[^>]*>");
	public static class Pair<A, B> {
		final A a;
		final B b;

		public static <X, Y> Pair<X, Y> create(X a, Y b) {
			return new Pair<X, Y>(a, b);
		}

		public Pair(A a, B b) {
			this.a = a;
			this.b = b;
		}

		public A getA() {
			return this.a;
		}

		public B getB() {
			return this.b;
		}

		@Override
		public String toString() {
			return (getA() == null ? "[null]" : getA().toString()) + " " + //
					(getB() == null ? "[null]" : getB().toString());
		}
	}

	public static String extractArea(String html, Pattern begin, Pattern end, String name) throws Exception {
		Matcher m = begin.matcher(html);
		if (!m.find())
			throw new Exception("Begin of " + name + " area is not found");
		html = html.substring(m.end());
		m = end.matcher(html);
		if (!m.find())
			throw new Exception("End of " + name + " area is not found");
		html = html.substring(0, m.start());
		return html;
	}

	// returns Pair<startPos, endPos>
	// returns null if tag is not found
	private static Pair<Integer, Integer> findTagInnerHtml(String html, String beginTag, String endTag, int pos) throws Exception {
		pos = html.indexOf(beginTag, pos);
		if (pos < 0)
			return null;
		pos = html.indexOf('>', (pos + beginTag.length()) - 1);
		if (pos < 0)
			throw new Exception("Could not find end of open tag " + beginTag);
		++pos;
		int pos2 = html.indexOf(endTag, pos);
		if (pos2 < 0)
			throw new Exception("Could not find close tag " + endTag);
		return new Pair<Integer, Integer>(pos, pos2);
	}

	public static List<String> parseTagsInnerHtml(String html, String beginTag, String endTag) throws Exception {
		List<String> res = new ArrayList<String>();
		int pos = 0;
		while (true) {
			Pair<Integer, Integer> poses = findTagInnerHtml(html, beginTag, endTag, pos);
			if (poses == null) {
				break;
			}
			String s = poses.getA().equals(poses.getB()) ? "" : html.substring(poses.getA(), poses.getB());
			res.add(s);
			pos = poses.getB() + endTag.length();
		}
		return res;
	}

	public static List<String> parseTagsInnerHtml(String html, String tag) throws Exception {
		final String beginTag = "<" + tag;
		final String endTag = "</" + tag + ">";
		return parseTagsInnerHtml(html, beginTag, endTag);
	}

	public static String getInnerText(String html) {
		Matcher m = patternHtmlTag.matcher(html);
		return m.replaceAll("");
	}
}
