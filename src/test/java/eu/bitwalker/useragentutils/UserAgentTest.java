/**
 *
 */
package eu.bitwalker.useragentutils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author harald
 *
 */
public class UserAgentTest {

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#parseUserAgentString(java.lang.String)}.
	 */
	@Test
	public void testParseUserAgentString() {
		UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		assertEquals(OperatingSystem.WINDOWS_XP, userAgent.getOperatingSystem());
		assertEquals(Browser.IE6, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.IE11, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.11 Safari/537.36 OPR/21.0.1432.5 (Edition Developer)");
		assertEquals(OperatingSystem.WINDOWS_XP, userAgent.getOperatingSystem());
		assertEquals(Browser.OPERA21, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36 OPR/24.0.1558.51 (Edition Next)");
		assertEquals(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
		assertEquals(Browser.OPERA24, userAgent.getBrowser());

		userAgent = UserAgent
				.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.3 Safari/537.36 OPR/28.0.1750.5");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.OPERA28, userAgent.getBrowser());

		userAgent = UserAgent
				.parseUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36 OPR/38.0.2220.31");
		assertEquals(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
		assertEquals(Browser.OPERA38, userAgent.getBrowser());
		
		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME37, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME39, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.94 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME40, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME41, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME42, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
		assertEquals(OperatingSystem.WINDOWS_10, userAgent.getOperatingSystem());
		assertEquals(Browser.CHROME51, userAgent.getBrowser());
		
		userAgent = UserAgent
				.parseUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A");
		assertEquals(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
		assertEquals(Browser.SAFARI7, userAgent.getBrowser());

		userAgent = UserAgent
				.parseUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Safari/600.1.4");
		assertEquals(OperatingSystem.iOS8_IPHONE, userAgent.getOperatingSystem());
		assertEquals(Browser.SAFARI8, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0");
		assertEquals(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
		assertEquals(Browser.FIREFOX32, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
		assertEquals(OperatingSystem.WINDOWS_81, userAgent.getOperatingSystem());
		assertEquals(Browser.FIREFOX36, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		assertEquals(OperatingSystem.WINDOWS_10, userAgent.getOperatingSystem());
		assertEquals(Browser.FIREFOX47, userAgent.getBrowser());
		
		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; HTC_DesireS_S510e Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile");
		assertEquals(OperatingSystem.ANDROID2, userAgent.getOperatingSystem());
		assertEquals(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Linux; U; Android 2.2.1; en-gb; HTC_DesireZ_A7272 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
		assertEquals(OperatingSystem.ANDROID2, userAgent.getOperatingSystem());
		assertEquals(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		assertEquals(OperatingSystem.ANDROID4, userAgent.getOperatingSystem());
		assertEquals(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

		userAgent = UserAgent.parseUserAgentString(
				"Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30");
		assertEquals(OperatingSystem.ANDROID4_TABLET, userAgent.getOperatingSystem());
		assertEquals(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());
	}

	@Test
	public void testBotUserAgentString() {
		String[] botUserAgents = {
				"Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
				"Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
				"msnbot/1.0 (+http://search.msn.com/msnbot.htm",
				"msnbot/2.0b (+http://search.msn.com/msnbot.htm)",
				"msnbot-media/1.1 (+http://search.msn.com/msnbot.htm)",
				"adidxbot/1.1 (+http://search.msn.com/msnbot.htm)",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko) BingPreview/1.0b",
				"Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko BingPreview/1.0b",
				"Mozilla/5.0 (compatible; MixrankBot; crawler@mixrank.com",
				"Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
				"AdsBot-Google (+http://www.google.com/adsbot.html)",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0; en-US) adbeat.com/policy like Gecko"
		};
		for (String botUserAgent : botUserAgents) {
			UserAgent userAgent = UserAgent.parseUserAgentString(botUserAgent);
			assertEquals("'" + botUserAgent + "' parsed as " + userAgent.getBrowser(), BrowserType.ROBOT, userAgent.getBrowser().getBrowserType());
			assertTrue("'" + botUserAgent + "' parsed as " + userAgent.getBrowser(),
					Browser.BOT.equals(userAgent.getBrowser()) || Browser.BOT.equals(userAgent.getBrowser().getGroup()));
		}
	}

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#parseUserAgentString(java.lang.String)} that checks for proper handling of a <code>null</code>
	 * userAgentString.
	 */
	@Test
	public void testParseUserAgentStringNull() {
		UserAgent userAgent = UserAgent.parseUserAgentString(null);
		assertEquals(OperatingSystem.UNKNOWN, userAgent.getOperatingSystem());
		assertEquals(Browser.UNKNOWN, userAgent.getBrowser());
		assertNull(userAgent.getBrowserVersion());
	}

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#toString()}.
	 */
	@Test
	public void testToString() {
		UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		assertEquals(OperatingSystem.WINDOWS_XP.toString() + "-" + Browser.IE6.toString(), userAgent.toString());
	}

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#valueOf(int)}.
	 */
	@Test
	public void testValueOf() {
		UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		UserAgent retrievedUserAgent = UserAgent.valueOf(userAgent.getId());
		assertEquals(userAgent, retrievedUserAgent);
	}

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#valueOf(String)}.
	 */
	@Test
	public void testValueOf2() {
		UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		UserAgent retrievedUserAgent = UserAgent.valueOf(userAgent.toString());
		assertEquals(userAgent, retrievedUserAgent);
	}
}
