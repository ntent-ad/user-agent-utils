/**
 * 
 */

using System;
using NUnit.Framework;

namespace eu.bitwalker.useragentutils
{


/**
 * @author harald
 *
 */

    public class UserAgentTest
    {

        /**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#parseUserAgentString(java.lang.String)}.
	 */

        [Test]
        public void testParseUserAgentString()
        {
            UserAgent userAgent =
                UserAgent.parseUserAgentString(
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
            Assert.AreEqual(OperatingSystem.WINDOWS_XP, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.IE6, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.IE11, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.11 Safari/537.36 OPR/21.0.1432.5 (Edition Developer)");
            Assert.AreEqual(OperatingSystem.WINDOWS_XP, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.OPERA21, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36 OPR/24.0.1558.51 (Edition Next)");
            Assert.AreEqual(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.OPERA24, userAgent.getBrowser());

            userAgent = UserAgent
                    .parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.3 Safari/537.36 OPR/28.0.1750.5");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.OPERA28, userAgent.getBrowser());

            userAgent = UserAgent
                    .parseUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36 OPR/38.0.2220.31");
            Assert.AreEqual(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.OPERA38, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME37, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME39, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.94 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME40, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME41, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME42, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            Assert.AreEqual(OperatingSystem.WINDOWS_10, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.CHROME51, userAgent.getBrowser());

            userAgent = UserAgent
                    .parseUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A");
            Assert.AreEqual(OperatingSystem.MAC_OS_X, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.SAFARI7, userAgent.getBrowser());

            userAgent = UserAgent
                    .parseUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 8_0_2 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Safari/600.1.4");
            Assert.AreEqual(OperatingSystem.iOS8_IPHONE, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.SAFARI8, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0");
            Assert.AreEqual(OperatingSystem.WINDOWS_7, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.FIREFOX32, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
            Assert.AreEqual(OperatingSystem.WINDOWS_81, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.FIREFOX36, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
            Assert.AreEqual(OperatingSystem.WINDOWS_10, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.FIREFOX47, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; HTC_DesireS_S510e Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile");
            Assert.AreEqual(OperatingSystem.ANDROID2, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Linux; U; Android 2.2.1; en-gb; HTC_DesireZ_A7272 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
            Assert.AreEqual(OperatingSystem.ANDROID2, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
            Assert.AreEqual(OperatingSystem.ANDROID4, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());

            userAgent = UserAgent.parseUserAgentString(
                    "Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30");
            Assert.AreEqual(OperatingSystem.ANDROID4_TABLET, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.ANDROID_WEB_KIT, userAgent.getBrowser());
        }

        [Test]
        public void testBotUserAgentString()
        {
            String[] botUserAgents =
            {
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
            foreach (String botUserAgent in botUserAgents)
            {
                UserAgent userAgent = UserAgent.parseUserAgentString(botUserAgent);
                Assert.AreEqual(BrowserType.ROBOT, userAgent.getBrowser().getBrowserType(),"'" + botUserAgent + "' parsed as " + userAgent.getBrowser());
                Assert.True(Browser.BOT.Equals(userAgent.getBrowser()) || Browser.BOT.Equals(userAgent.getBrowser().getGroup()),"'" + botUserAgent + "' parsed as " + userAgent.getBrowser());
            }
        }

        /**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#parseUserAgentString(java.lang.String)} 
	 * that checks for proper handling of a <code>null</code> userAgentString.
	 */

        [Test]
        public void testParseUserAgentStringNull()
        {
            UserAgent userAgent = UserAgent.parseUserAgentString(null);
            Assert.AreEqual(OperatingSystem.UNKNOWN, userAgent.getOperatingSystem());
            Assert.AreEqual(Browser.UNKNOWN, userAgent.getBrowser());
            Assert.Null(userAgent.getBrowserVersion());
        }

        /**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#ToString()}.
	 */

        [Test]
        public void testToString()
        {
            UserAgent userAgent =
                UserAgent.parseUserAgentString(
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
            Assert.AreEqual(OperatingSystem.WINDOWS_XP.ToString() + "-" + Browser.IE6.ToString(), userAgent.ToString());
        }

        /**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#valueOf(int)}.
	 */

        [Test]
        public void testValueOf()
        {
            UserAgent userAgent =
                UserAgent.parseUserAgentString(
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
            UserAgent retrievedUserAgent = UserAgent.valueOf(userAgent.getId());
            Assert.AreEqual(userAgent, retrievedUserAgent);
        }

        /**
	 * Test method for {@link eu.bitwalker.useragentutils.UserAgent#valueOf(String)}.
	 */

        [Test]
        public void testValueOf2()
        {
            UserAgent userAgent =
                UserAgent.parseUserAgentString(
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
            UserAgent retrievedUserAgent = UserAgent.valueOf(userAgent.ToString());
            Assert.AreEqual(userAgent, retrievedUserAgent);
        }

    }
}