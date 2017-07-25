using System;
using NUnit.Framework;

namespace  eu.bitwalker.useragentutils.browser {


public class ChromeMobileParameterizedTest :
		AbstractUserAgentParameterizedTest {

    // chromeMobile
				[TestCase(
						"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7",
						"CHROME_MOBILE", "16.0.912.75", "ANDROID4_TABLET" )]
				[TestCase(
						"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7",
						"CHROME_MOBILE", "16.0.912.75", "ANDROID4" )]
				[TestCase(
						"Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; en) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3",
						"CHROME_MOBILE", "19.0.1084.60", "iOS5_IPHONE" )]
                [TestCase(
                    "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36",
                    "ANDROID_WEB_VIEW", "4.0", "ANDROID4")]
				[TestCase(
                    "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36",
                    "ANDROID_WEB_VIEW", "4.0", "ANDROID5")]
				[TestCase(
                    "Mozilla/5.0 (Linux; U; Android 4.1.1; en-gb; Build/KLP) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30",
                    "ANDROID_WEB_KIT", "4.0", "ANDROID4_TABLET")]

            [Test]
            public void testData(String userAgentValue,
                    string expectedBrowserStr, String expectedBrowserVersion,
                    string expectedOSStr)
            {
                shouldParseUserAgent(userAgentValue, Browser.valueOf(expectedBrowserStr), expectedBrowserVersion, OperatingSystem.valueOf(expectedOSStr));
            }
        }
}
