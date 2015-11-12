using System;
using NUnit.Framework;

namespace eu.bitwalker.useragentutils.browser
{
    /**
     * @author pcollaog
     *
     */
    public abstract class AbstractUserAgentParameterizedTest
    {

        /**
	     * @param userAgentValue
	     * @param browserExpected
	     */
        public AbstractUserAgentParameterizedTest()
        {
        }

        protected void shouldParseUserAgent(String userAgentValue,
            Browser expectedBrowser, String expectedBrowserVersion,
            OperatingSystem expectedOS)
        {
            UserAgent userAgent = UserAgent
                .parseUserAgentString(userAgentValue);
            Assert.AreEqual(expectedBrowser, userAgent.getBrowser());

            Version browserVersion = userAgent.getBrowserVersion();
            if (null != browserVersion)
            {
                Assert.AreEqual(expectedBrowserVersion, browserVersion.ToString(), userAgentValue);
            }
            else
            {
                Assert.AreEqual(expectedBrowserVersion, browserVersion, userAgentValue);
            }

            OperatingSystem os = userAgent.getOperatingSystem();
            Assert.AreEqual(expectedOS, os, userAgentValue);
        }

    }
}