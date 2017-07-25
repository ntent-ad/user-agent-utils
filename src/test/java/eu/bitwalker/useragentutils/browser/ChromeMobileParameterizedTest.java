package eu.bitwalker.useragentutils.browser;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import eu.bitwalker.useragentutils.*;

public class ChromeMobileParameterizedTest extends
		AbstractUserAgentParameterizedTest {

	public ChromeMobileParameterizedTest(String userAgentValue,
			Browser expectedBrowser, String browserVersionExpected,
			OperatingSystem osExpected) {
		super(userAgentValue, expectedBrowser, browserVersionExpected,
				osExpected);
	}

	@Parameters
	public static Collection<Object[]> testData() {
		return asList(new Object[][] {
				// chromeMobile
				{
						"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7",
						Browser.CHROME_MOBILE, "16.0.912.75", OperatingSystem.ANDROID4_TABLET },
				{
						"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7",
						Browser.CHROME_MOBILE, "16.0.912.75", OperatingSystem.ANDROID4 },
				{
						"Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; en) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3",
						Browser.CHROME_MOBILE, "19.0.1084.60", OperatingSystem.iOS5_IPHONE },
				{
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36",
						Browser.ANDROID_WEB_VIEW, "4.0", OperatingSystem.ANDROID4 },
				{
						"Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36",
						Browser.ANDROID_WEB_VIEW, "4.0", OperatingSystem.ANDROID5 },
				{
						"Mozilla/5.0 (Linux; U; Android 4.1.1; en-gb; Build/KLP) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30",
						Browser.ANDROID_WEB_KIT, "4.0", OperatingSystem.ANDROID4_TABLET }

		});
	}
}
