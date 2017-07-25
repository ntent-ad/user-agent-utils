/*
 * Copyright (c) 2008-2014, Harald Walker (bitwalker.eu) and contributing developers 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the
 * following conditions are met:
 * 
 * * Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * 
 * * Neither the name of bitwalker nor the names of its
 * contributors may be used to endorse or promote products
 * derived from this software without specific prior written
 * permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.bitwalker.useragentutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enum constants for most common browsers, including e-mail clients and bots.
 * 
 * @author harald
 *
 */

public enum Browser {

	/**
	 * Bots
	 */
	BOT(Manufacturer.OTHER, null, 12, "Robot/Spider", new String[] { "AdsBot-Google", "adbeat.com", "Googlebot", "Mediapartners-Google", "Web Preview", "bot", 
			"Applebot", "spider", "crawler", "Feedfetcher", "Slurp", "Twiceler", "Nutch", "BecomeBot", "bingbot", "BingPreview/", "Google Web Preview", 
			"WordPress.com mShots", "Seznam", "facebookexternalhit", "YandexMarket", "Teoma", "ThumbSniper", "Phantom.js", "Accoona-AI-Agent", 
			"Arachmo", "B-l-i-t-z-B-O-T", "Cerberian Drtrs", "Charlotte", "Covario", "DataparkSearch",
			"FindLinks", "Holmes", "htdig", "ia_archiver", "ichiro", "igdeSpyder", "L.webis", "Larbin", "LinkWalker", "lwp-trivial", "mabontland",
			"Mnogosearch", "mogimogi", "MVAClient", "NetResearchServer", "NewsGator", "NG-Search", "Nymesis", "oegp", "Pompos", "PycURL", "Qseero", "SBIder",
			"ScoutJet", "Scrubby", "SearchSight", "semanticdiscovery",  "Snappy", "Sqworm", "StackRambler", "TinEye", "truwo", "updated", "voyager",
			"VYU2", "webcollage", "YahooSeeker", "yoogliFetchAgent", "Zao", "yahoo-ad-monitoring" }, null, BrowserType.ROBOT, RenderingEngine.OTHER, null),
	BOT_MOBILE(Manufacturer.OTHER, Browser.BOT, 20, "Mobil Robot/Spider", new String[] { "Googlebot-Mobile" }, null, BrowserType.ROBOT, RenderingEngine.OTHER, null),

	/**
	 * Outlook email client
	 */
	OUTLOOK(Manufacturer.MICROSOFT, null, 100, "Outlook", new String[] { "MSOffice" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, "MSOffice (([0-9]+))"), // before IE7
	/**
	 * Microsoft Outlook 2007 identifies itself as MSIE7 but uses the html rendering engine of Word 2007. Example user agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; .NET CLR 1.1.4322; MSOffice 12)
	 */
	OUTLOOK2007(Manufacturer.MICROSOFT, Browser.OUTLOOK, 107, "Outlook 2007", new String[] { "MSOffice 12" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null), // before IE7
	OUTLOOK2013(Manufacturer.MICROSOFT, Browser.OUTLOOK, 109, "Outlook 2013", new String[] { "Microsoft Outlook 15" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null), // before IE7
	/**
	 * Outlook 2010 is still using the rendering engine of Word. http://www.fixoutlook.org
	 */
	OUTLOOK2010(Manufacturer.MICROSOFT, Browser.OUTLOOK, 108, "Outlook 2010", new String[] { "MSOffice 14", "Microsoft Outlook 14" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null), // before IE7

	/**
	 * Family of Internet Explorer browsers
	 */
	IE(Manufacturer.MICROSOFT, null, 1, "Internet Explorer", new String[] { "MSIE", "Trident", "IE " }, new String[] { "Opera", "BingPreview", "Xbox", "Xbox One" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, "MSIE (([\\d]+)\\.([\\w]+))"), // before Mozilla
	/**
	 * Since version 7 Outlook Express is identifying itself. By detecting Outlook Express we can not identify the Internet Explorer version which is probably used for the rendering. Obviously this product is now called Windows Live Mail Desktop or just Windows Live Mail.
	 */
	OUTLOOK_EXPRESS7(Manufacturer.MICROSOFT, Browser.IE, 110, "Windows Live Mail", new String[] { "Outlook-Express/7.0" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.TRIDENT, null), // before IE7, previously known as Outlook Express. First released in 2006, offered with different name later
	/**
	 * Since 2007 the mobile edition of Internet Explorer identifies itself as IEMobile in the user-agent. If previous versions have to be detected, use the operating system information as well.
	 */
	IEMOBILE(Manufacturer.MICROSOFT, Browser.IE, 119, "IE Mobile", new String[] { "IEMobile" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE
	IEMOBILE11(Manufacturer.MICROSOFT, Browser.IEMOBILE, 125, "IE Mobile 11", new String[] { "IEMobile/11" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE strings
	IEMOBILE10(Manufacturer.MICROSOFT, Browser.IEMOBILE, 124, "IE Mobile 10", new String[] { "IEMobile/10" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE strings
	IEMOBILE9(Manufacturer.MICROSOFT, Browser.IEMOBILE, 123, "IE Mobile 9", new String[] { "IEMobile/9" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE strings
	IEMOBILE8(Manufacturer.MICROSOFT, Browser.IEMOBILE, 122, "IE Mobile 8", new String[] { "IEMobile 8", "IEMobile/8" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE strings
	IEMOBILE7(Manufacturer.MICROSOFT, Browser.IEMOBILE, 121, "IE Mobile 7", new String[] { "IEMobile 7", "IEMobile/7" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE strings
	IEMOBILE6(Manufacturer.MICROSOFT, Browser.IEMOBILE, 120, "IE Mobile 6", new String[] { "IEMobile 6" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"), // before MSIE
	IE_XBOX(Manufacturer.MICROSOFT, Browser.IE, 360, "Xbox", new String[] { "xbox" }, new String[] {}, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null),
	IE11(Manufacturer.MICROSOFT, Browser.IE, 95, "Internet Explorer 11", new String[] { "rv:11.", "IE 11." }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, "(?:Trident/7|IE)(?:\\.[0-9]*;)?(?:.*rv:| )(([0-9]+)\\.?([0-9]+))"), // before Mozilla
	IE10(Manufacturer.MICROSOFT, Browser.IE, 92, "Internet Explorer 10", new String[] { "MSIE 10" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE9(Manufacturer.MICROSOFT, Browser.IE, 90, "Internet Explorer 9", new String[] { "MSIE 9" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE8(Manufacturer.MICROSOFT, Browser.IE, 80, "Internet Explorer 8", new String[] { "MSIE 8", "OptimizedIE8" }, new String[] { "Trident/6", "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE7(Manufacturer.MICROSOFT, Browser.IE, 70, "Internet Explorer 7", new String[] { "MSIE 7" }, new String[] { "OptimizedIE8", "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE6(Manufacturer.MICROSOFT, Browser.IE, 60, "Internet Explorer 6", new String[] { "MSIE 6" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE5_5(Manufacturer.MICROSOFT, Browser.IE, 55, "Internet Explorer 5.5", new String[] { "MSIE 5.5" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE
	IE5(Manufacturer.MICROSOFT, Browser.IE, 50, "Internet Explorer 5", new String[] { "MSIE 5" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null), // before MSIE

	/**
	 * Family of Microsoft Edge browsers. Pretends to be Chrome and claims to be webkit compatible.
	 */
	EDGE(Manufacturer.MICROSOFT, null, 300, "Microsoft Edge", new String[] { "Edge" }, null, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/(([0-9]+)\\.([0-9]*)))"),
	EDGE15(Manufacturer.MICROSOFT, Browser.EDGE, 306, "Microsoft Edge", new String[] { "Edge/15" }, new String[] { "Mobile" }, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((15)\\.([0-9]*)))"),
	EDGE14(Manufacturer.MICROSOFT, Browser.EDGE, 304, "Microsoft Edge", new String[] { "Edge/14" }, new String[] { "Mobile" }, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((14)\\.([0-9]*)))"),
	EDGE13(Manufacturer.MICROSOFT, Browser.EDGE, 303, "Microsoft Edge", new String[] { "Edge/13" }, new String[] { "Mobile" }, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((13)\\.([0-9]*)))"),
	EDGE12(Manufacturer.MICROSOFT, Browser.EDGE, 301, "Microsoft Edge", new String[] { "Edge/12" }, new String[] { "Mobile" }, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((12)\\.([0-9]*)))"),
	EDGE_MOBILE12(Manufacturer.MICROSOFT, Browser.EDGE, 302, "Microsoft Edge Mobile", new String[] { "Edge/12" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((12)\\.([0-9]*)))"),
	EDGE_MOBILE13(Manufacturer.MICROSOFT, Browser.EDGE, 305, "Microsoft Edge Mobile", new String[] { "Edge/13" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((13)\\.([0-9]*)))"),

	/**
	 * Google Chrome browser
	 */
	CHROME(Manufacturer.GOOGLE, null, 1, "Chrome", new String[] { "Chrome", "CrMo", "CriOS" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Chrome/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"), // before Mozilla
	ANDROID_WEB_KIT(Manufacturer.GOOGLE, null, 2, "Android Webkit Browser", new String[] { "Android", "Build/" }, new String[] { "Windows", "Firefox", "Chrome", "Opera", "OPR/", "Web Preview", "Googlebot-Mobile" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Version/(([\\d]+)\\.([\\w]+))"), // Android WebKit also only identifies itself as AppleWebKit
	ANDROID_WEB_VIEW(Manufacturer.GOOGLE, Browser.CHROME, 200, "Chrome Web View", new String[] {"wv", "Version"}, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Version/(([\\d]+)\\.([\\w]+))"),
	CHROME_MOBILE(Manufacturer.GOOGLE, Browser.CHROME, 100, "Chrome Mobile", new String[] { "CrMo", "CriOS", "Mobile Safari" }, new String[] { "OPR/", "Web Preview" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "(?:CriOS|CrMo|Chrome)/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	CHROME59(Manufacturer.GOOGLE, Browser.CHROME, 64, "Chrome 59", new String[] { "Chrome/59" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME58(Manufacturer.GOOGLE, Browser.CHROME, 63, "Chrome 58", new String[] { "Chrome/58" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME57(Manufacturer.GOOGLE, Browser.CHROME, 62, "Chrome 57", new String[] { "Chrome/57" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME56(Manufacturer.GOOGLE, Browser.CHROME, 61, "Chrome 56", new String[] { "Chrome/56" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME55(Manufacturer.GOOGLE, Browser.CHROME, 60, "Chrome 55", new String[] { "Chrome/55" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME54(Manufacturer.GOOGLE, Browser.CHROME, 59, "Chrome 54", new String[] { "Chrome/54" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME53(Manufacturer.GOOGLE, Browser.CHROME, 58, "Chrome 53", new String[] { "Chrome/53" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME52(Manufacturer.GOOGLE, Browser.CHROME, 57, "Chrome 52", new String[] { "Chrome/52" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME51(Manufacturer.GOOGLE, Browser.CHROME, 56, "Chrome 51", new String[] { "Chrome/51" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME50(Manufacturer.GOOGLE, Browser.CHROME, 55, "Chrome 50", new String[] { "Chrome/50" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME49(Manufacturer.GOOGLE, Browser.CHROME, 54, "Chrome 49", new String[] { "Chrome/49" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME48(Manufacturer.GOOGLE, Browser.CHROME, 53, "Chrome 48", new String[] { "Chrome/48" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME47(Manufacturer.GOOGLE, Browser.CHROME, 52, "Chrome 47", new String[] { "Chrome/47" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME46(Manufacturer.GOOGLE, Browser.CHROME, 51, "Chrome 46", new String[] { "Chrome/46" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME45(Manufacturer.GOOGLE, Browser.CHROME, 50, "Chrome 45", new String[] { "Chrome/45" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME44(Manufacturer.GOOGLE, Browser.CHROME, 49, "Chrome 44", new String[] { "Chrome/44" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME43(Manufacturer.GOOGLE, Browser.CHROME, 48, "Chrome 43", new String[] { "Chrome/43" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME42(Manufacturer.GOOGLE, Browser.CHROME, 47, "Chrome 42", new String[] { "Chrome/42" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME41(Manufacturer.GOOGLE, Browser.CHROME, 46, "Chrome 41", new String[] { "Chrome/41" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME40(Manufacturer.GOOGLE, Browser.CHROME, 45, "Chrome 40", new String[] { "Chrome/40" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME39(Manufacturer.GOOGLE, Browser.CHROME, 44, "Chrome 39", new String[] { "Chrome/39" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME38(Manufacturer.GOOGLE, Browser.CHROME, 43, "Chrome 38", new String[] { "Chrome/38" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME37(Manufacturer.GOOGLE, Browser.CHROME, 42, "Chrome 37", new String[] { "Chrome/37" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME36(Manufacturer.GOOGLE, Browser.CHROME, 41, "Chrome 36", new String[] { "Chrome/36" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME35(Manufacturer.GOOGLE, Browser.CHROME, 40, "Chrome 35", new String[] { "Chrome/35" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME34(Manufacturer.GOOGLE, Browser.CHROME, 39, "Chrome 34", new String[] { "Chrome/34" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME33(Manufacturer.GOOGLE, Browser.CHROME, 38, "Chrome 33", new String[] { "Chrome/33" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME32(Manufacturer.GOOGLE, Browser.CHROME, 37, "Chrome 32", new String[] { "Chrome/32" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME31(Manufacturer.GOOGLE, Browser.CHROME, 36, "Chrome 31", new String[] { "Chrome/31" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME30(Manufacturer.GOOGLE, Browser.CHROME, 35, "Chrome 30", new String[] { "Chrome/30" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME29(Manufacturer.GOOGLE, Browser.CHROME, 34, "Chrome 29", new String[] { "Chrome/29" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME28(Manufacturer.GOOGLE, Browser.CHROME, 33, "Chrome 28", new String[] { "Chrome/28" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME27(Manufacturer.GOOGLE, Browser.CHROME, 32, "Chrome 27", new String[] { "Chrome/27" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME26(Manufacturer.GOOGLE, Browser.CHROME, 31, "Chrome 26", new String[] { "Chrome/26" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME25(Manufacturer.GOOGLE, Browser.CHROME, 30, "Chrome 25", new String[] { "Chrome/25" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME24(Manufacturer.GOOGLE, Browser.CHROME, 29, "Chrome 24", new String[] { "Chrome/24" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME23(Manufacturer.GOOGLE, Browser.CHROME, 28, "Chrome 23", new String[] { "Chrome/23" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME22(Manufacturer.GOOGLE, Browser.CHROME, 27, "Chrome 22", new String[] { "Chrome/22" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME21(Manufacturer.GOOGLE, Browser.CHROME, 26, "Chrome 21", new String[] { "Chrome/21" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME20(Manufacturer.GOOGLE, Browser.CHROME, 25, "Chrome 20", new String[] { "Chrome/20" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME19(Manufacturer.GOOGLE, Browser.CHROME, 24, "Chrome 19", new String[] { "Chrome/19" }, new String[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME18(Manufacturer.GOOGLE, Browser.CHROME, 23, "Chrome 18", new String[] { "Chrome/18" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME17(Manufacturer.GOOGLE, Browser.CHROME, 22, "Chrome 17", new String[] { "Chrome/17" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME16(Manufacturer.GOOGLE, Browser.CHROME, 21, "Chrome 16", new String[] { "Chrome/16" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME15(Manufacturer.GOOGLE, Browser.CHROME, 20, "Chrome 15", new String[] { "Chrome/15" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME14(Manufacturer.GOOGLE, Browser.CHROME, 19, "Chrome 14", new String[] { "Chrome/14" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME13(Manufacturer.GOOGLE, Browser.CHROME, 18, "Chrome 13", new String[] { "Chrome/13" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME12(Manufacturer.GOOGLE, Browser.CHROME, 17, "Chrome 12", new String[] { "Chrome/12" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME11(Manufacturer.GOOGLE, Browser.CHROME, 16, "Chrome 11", new String[] { "Chrome/11" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME10(Manufacturer.GOOGLE, Browser.CHROME, 15, "Chrome 10", new String[] { "Chrome/10" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME9(Manufacturer.GOOGLE, Browser.CHROME, 10, "Chrome 9", new String[] { "Chrome/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla
	CHROME8(Manufacturer.GOOGLE, Browser.CHROME, 5, "Chrome 8", new String[] { "Chrome/8" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before Mozilla

	OMNIWEB(Manufacturer.OTHER, null, 2, "Omniweb", new String[] { "OmniWeb" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), //

	SAFARI(Manufacturer.APPLE, null, 1, "Safari", new String[] { "Safari" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Web Preview", "Googlebot-Mobile", "Android", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Version/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"), // before AppleWebKit
	BLACKBERRY10(Manufacturer.BLACKBERRY, Browser.SAFARI, 10, "BlackBerry", new String[] { "BB10" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null),
	MOBILE_SAFARI(Manufacturer.APPLE, Browser.SAFARI, 2, "Mobile Safari", new String[] { "Mobile Safari", "Mobile/" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI10(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 110, "Mobile Safari 10", new String[] { "Mobile/14" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI9(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 109, "Mobile Safari 9", new String[] { "Mobile/13" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI8(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 108, "Mobile Safari 8", new String[] { "Mobile/12" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI7(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 107, "Mobile Safari 7", new String[] { "Mobile/11" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI6(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 106, "Mobile Safari 6", new String[] { "Mobile/10" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI5(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 105, "Mobile Safari 5", new String[] { "Mobile/9" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	MOBILE_SAFARI4(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 104, "Mobile Safari 4", new String[] { "Mobile/8", "Mobile/7", "Mobile Safari/" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // before Safari
	SILK(Manufacturer.AMAZON, Browser.SAFARI, 15, "Silk", new String[] { "Silk/" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Silk/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\-[\\w]+)?)"), // http://en.wikipedia.org/wiki/Amazon_Silk
	SAFARI10(Manufacturer.APPLE, Browser.SAFARI, 10, "Safari 10", new String[] { "Version/10" }, new String[] { "Applebot", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI9(Manufacturer.APPLE, Browser.SAFARI, 9, "Safari 9", new String[] { "Version/9" }, new String[] { "Applebot", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI8(Manufacturer.APPLE, Browser.SAFARI, 8, "Safari 8", new String[] { "Version/8" }, new String[] { "Applebot", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI7(Manufacturer.APPLE, Browser.SAFARI, 7, "Safari 7", new String[] { "Version/7" }, new String[] { "bing", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI6(Manufacturer.APPLE, Browser.SAFARI, 6, "Safari 6", new String[] { "Version/6" }, new String[] { "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI5(Manufacturer.APPLE, Browser.SAFARI, 5, "Safari 5", new String[] { "Version/5" }, new String[] { "Google Web Preview", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit
	SAFARI4(Manufacturer.APPLE, Browser.SAFARI, 4, "Safari 4", new String[] { "Version/4" }, new String[] { "Googlebot-Mobile", "Mobile/", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // before AppleWebKit

	/**
	 * Opera Coast mobile browser, http://en.wikipedia.org/wiki/Opera_Coast
	 */
	COAST(Manufacturer.OPERA, null, 500, "Opera", new String[] { " Coast/" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Coast/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	COAST1(Manufacturer.OPERA, Browser.COAST, 501, "Opera", new String[] { " Coast/1." }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Coast/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),

	OPERA(Manufacturer.OPERA, null, 1, "Opera", new String[] { " OPR/", "Opera" }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Opera/(([\\d]+)\\.([\\w]+))"), // before MSIE
	OPERA_MOBILE(Manufacturer.OPERA, Browser.OPERA, 100, "Opera Mobile", new String[] { "Mobile Safari", "Opera Mobi" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.BLINK, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"), // Another Opera for mobile devices
	OPERA_MINI(Manufacturer.OPERA, Browser.OPERA, 2, "Opera Mini", new String[] { "Opera Mini" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.PRESTO, null), // Opera for mobile devices
	OPERA45(Manufacturer.OPERA, Browser.OPERA, 45, "Opera 45", new String[] { "OPR/45." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA44(Manufacturer.OPERA, Browser.OPERA, 44, "Opera 44", new String[] { "OPR/44." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA43(Manufacturer.OPERA, Browser.OPERA, 43, "Opera 43", new String[] { "OPR/43." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA42(Manufacturer.OPERA, Browser.OPERA, 42, "Opera 42", new String[] { "OPR/42." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA41(Manufacturer.OPERA, Browser.OPERA, 41, "Opera 41", new String[] { "OPR/41." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA40(Manufacturer.OPERA, Browser.OPERA, 40, "Opera 40", new String[] { "OPR/40." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA39(Manufacturer.OPERA, Browser.OPERA, 39, "Opera 39", new String[] { "OPR/39." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA38(Manufacturer.OPERA, Browser.OPERA, 38, "Opera 38", new String[] { "OPR/38." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA37(Manufacturer.OPERA, Browser.OPERA, 37, "Opera 37", new String[] { "OPR/37." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA36(Manufacturer.OPERA, Browser.OPERA, 36, "Opera 36", new String[] { "OPR/36." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA35(Manufacturer.OPERA, Browser.OPERA, 35, "Opera 35", new String[] { "OPR/35." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA34(Manufacturer.OPERA, Browser.OPERA, 34, "Opera 34", new String[] { "OPR/34." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA33(Manufacturer.OPERA, Browser.OPERA, 33, "Opera 33", new String[] { "OPR/33." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA32(Manufacturer.OPERA, Browser.OPERA, 32, "Opera 32", new String[] { "OPR/33." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA31(Manufacturer.OPERA, Browser.OPERA, 31, "Opera 31", new String[] { "OPR/31." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA30(Manufacturer.OPERA, Browser.OPERA, 30, "Opera 30", new String[] { "OPR/30." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA29(Manufacturer.OPERA, Browser.OPERA, 29, "Opera 29", new String[] { "OPR/29." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA28(Manufacturer.OPERA, Browser.OPERA, 28, "Opera 28", new String[] { "OPR/28." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA27(Manufacturer.OPERA, Browser.OPERA, 27, "Opera 27", new String[] { "OPR/27." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA26(Manufacturer.OPERA, Browser.OPERA, 26, "Opera 26", new String[] { "OPR/26." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA25(Manufacturer.OPERA, Browser.OPERA, 25, "Opera 25", new String[] { "OPR/25." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA24(Manufacturer.OPERA, Browser.OPERA, 24, "Opera 24", new String[] { "OPR/24." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA23(Manufacturer.OPERA, Browser.OPERA, 23, "Opera 23", new String[] { "OPR/23." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA22(Manufacturer.OPERA, Browser.OPERA, 22, "Opera 22", new String[] { "OPR/22." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA21(Manufacturer.OPERA, Browser.OPERA, 21, "Opera 21", new String[] { "OPR/21." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA20(Manufacturer.OPERA, Browser.OPERA, 20, "Opera 20", new String[] { "OPR/20." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA19(Manufacturer.OPERA, Browser.OPERA, 19, "Opera 19", new String[] { "OPR/19." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA18(Manufacturer.OPERA, Browser.OPERA, 18, "Opera 18", new String[] { "OPR/18." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA17(Manufacturer.OPERA, Browser.OPERA, 17, "Opera 17", new String[] { "OPR/17." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA16(Manufacturer.OPERA, Browser.OPERA, 16, "Opera 16", new String[] { "OPR/16." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA15(Manufacturer.OPERA, Browser.OPERA, 15, "Opera 15", new String[] { "OPR/15." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"),
	OPERA12(Manufacturer.OPERA, Browser.OPERA, 12, "Opera 12", new String[] { "Opera/12", "Opera 12.", "Version/12." }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))"),
	OPERA11(Manufacturer.OPERA, Browser.OPERA, 11, "Opera 11", new String[] { "Opera/11", "Opera 11.", "Version/11." }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))"),
	OPERA10(Manufacturer.OPERA, Browser.OPERA, 10, "Opera 10", new String[] { "Opera/9.8" }, new String[] { "Version/11.", "Version/12." }, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))"),
	OPERA9(Manufacturer.OPERA, Browser.OPERA, 5, "Opera 9", new String[] { "Opera/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, null),
	KONQUEROR(Manufacturer.OTHER, null, 1, "Konqueror", new String[] { "Konqueror" }, new String[] { "Exabot" }, BrowserType.WEB_BROWSER, RenderingEngine.KHTML, "Konqueror/(([0-9]+)\\.?([\\w]+)?(-[\\w]+)?)"),

	DOLFIN2(Manufacturer.SAMSUNG, null, 1, "Samsung Dolphin 2", new String[] { "Dolfin/2" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null), // webkit based browser for the bada os

	/*
	 * Apple WebKit compatible client. Can be a browser or an application with embedded browser using UIWebView.
	 */
	APPLE_WEB_KIT(Manufacturer.APPLE, null, 50, "Apple WebKit", new String[] { "AppleWebKit" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "Web Preview", "Googlebot-Mobile", "Android", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null), // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit
	APPLE_ITUNES(Manufacturer.APPLE, Browser.APPLE_WEB_KIT, 52, "iTunes", new String[] { "iTunes" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null), // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit
	APPLE_APPSTORE(Manufacturer.APPLE, Browser.APPLE_WEB_KIT, 53, "App Store", new String[] { "MacAppStore" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null), // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit
	ADOBE_AIR(Manufacturer.ADOBE, Browser.APPLE_WEB_KIT, 1, "Adobe AIR application", new String[] { "AdobeAIR" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null), // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit

	LOTUS_NOTES(Manufacturer.OTHER, null, 3, "Lotus Notes", new String[] { "Lotus-Notes" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, "Lotus-Notes/(([\\d]+)\\.([\\w]+))"), // before Mozilla

	CAMINO(Manufacturer.OTHER, null, 5, "Camino", new String[] { "Camino" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Camino/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"), // using Gecko Engine
	CAMINO2(Manufacturer.OTHER, Browser.CAMINO, 17, "Camino 2", new String[] { "Camino/2" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine

	FLOCK(Manufacturer.OTHER, null, 4, "Flock", new String[] { "Flock" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Flock/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"),

	FIREFOX(Manufacturer.MOZILLA, null, 10, "Firefox", new String[] { "Firefox" }, new String[] { "ggpht.com", "WordPress.com mShots" }, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Firefox/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"), // using Gecko Engine
	FIREFOX3MOBILE(Manufacturer.MOZILLA, Browser.FIREFOX, 31, "Firefox 3 Mobile", new String[] { "Firefox/3.5 Maemo" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX_MOBILE(Manufacturer.MOZILLA, Browser.FIREFOX, 200, "Firefox Mobile", new String[] { "Mobile", "Android" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX_MOBILE13(Manufacturer.MOZILLA, FIREFOX_MOBILE, 213, "Firefox Mobile 13", new String[] { "Firefox/13" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null ),  // using Gecko Engine
	FIREFOX_MOBILE23(Manufacturer.MOZILLA, FIREFOX_MOBILE, 223, "Firefox Mobile 23", new String[] { "Firefox/23" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null ),  // using Gecko Engine
	FIREFOX56(Manufacturer.MOZILLA, Browser.FIREFOX, 560, "Firefox 56", new String[] { "Firefox/56" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX55(Manufacturer.MOZILLA, Browser.FIREFOX, 550, "Firefox 55", new String[] { "Firefox/55" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX54(Manufacturer.MOZILLA, Browser.FIREFOX, 540, "Firefox 54", new String[] { "Firefox/54" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX53(Manufacturer.MOZILLA, Browser.FIREFOX, 530, "Firefox 53", new String[] { "Firefox/53" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX52(Manufacturer.MOZILLA, Browser.FIREFOX, 520, "Firefox 52", new String[] { "Firefox/52" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX51(Manufacturer.MOZILLA, Browser.FIREFOX, 510, "Firefox 51", new String[] { "Firefox/51" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX50(Manufacturer.MOZILLA, Browser.FIREFOX, 500, "Firefox 50", new String[] { "Firefox/50" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX49(Manufacturer.MOZILLA, Browser.FIREFOX, 490, "Firefox 49", new String[] { "Firefox/49" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX48(Manufacturer.MOZILLA, Browser.FIREFOX, 480, "Firefox 48", new String[] { "Firefox/48" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX47(Manufacturer.MOZILLA, Browser.FIREFOX, 470, "Firefox 47", new String[] { "Firefox/47" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX46(Manufacturer.MOZILLA, Browser.FIREFOX, 460, "Firefox 46", new String[] { "Firefox/46" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX45(Manufacturer.MOZILLA, Browser.FIREFOX, 450, "Firefox 45", new String[] { "Firefox/45" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX44(Manufacturer.MOZILLA, Browser.FIREFOX, 440, "Firefox 44", new String[] { "Firefox/44" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX43(Manufacturer.MOZILLA, Browser.FIREFOX, 430, "Firefox 43", new String[] { "Firefox/43" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX42(Manufacturer.MOZILLA, Browser.FIREFOX, 420, "Firefox 42", new String[] { "Firefox/42" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX41(Manufacturer.MOZILLA, Browser.FIREFOX, 410, "Firefox 41", new String[] { "Firefox/41" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX40(Manufacturer.MOZILLA, Browser.FIREFOX, 400, "Firefox 40", new String[] { "Firefox/40" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX39(Manufacturer.MOZILLA, Browser.FIREFOX, 390, "Firefox 39", new String[] { "Firefox/39" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX38(Manufacturer.MOZILLA, Browser.FIREFOX, 380, "Firefox 38", new String[] { "Firefox/38" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX37(Manufacturer.MOZILLA, Browser.FIREFOX, 370, "Firefox 37", new String[] { "Firefox/37" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX36(Manufacturer.MOZILLA, Browser.FIREFOX, 360, "Firefox 36", new String[] { "Firefox/36" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX35(Manufacturer.MOZILLA, Browser.FIREFOX, 350, "Firefox 35", new String[] { "Firefox/35" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX34(Manufacturer.MOZILLA, Browser.FIREFOX, 340, "Firefox 34", new String[] { "Firefox/34" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX33(Manufacturer.MOZILLA, Browser.FIREFOX, 330, "Firefox 33", new String[] { "Firefox/33" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX32(Manufacturer.MOZILLA, Browser.FIREFOX, 320, "Firefox 32", new String[] { "Firefox/32" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX31(Manufacturer.MOZILLA, Browser.FIREFOX, 310, "Firefox 31", new String[] { "Firefox/31" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX30(Manufacturer.MOZILLA, Browser.FIREFOX, 300, "Firefox 30", new String[] { "Firefox/30" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX29(Manufacturer.MOZILLA, Browser.FIREFOX, 290, "Firefox 29", new String[] { "Firefox/29" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX28(Manufacturer.MOZILLA, Browser.FIREFOX, 280, "Firefox 28", new String[] { "Firefox/28" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX27(Manufacturer.MOZILLA, Browser.FIREFOX, 108, "Firefox 27", new String[] { "Firefox/27" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX26(Manufacturer.MOZILLA, Browser.FIREFOX, 107, "Firefox 26", new String[] { "Firefox/26" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX25(Manufacturer.MOZILLA, Browser.FIREFOX, 106, "Firefox 25", new String[] { "Firefox/25" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX24(Manufacturer.MOZILLA, Browser.FIREFOX, 105, "Firefox 24", new String[] { "Firefox/24" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX23(Manufacturer.MOZILLA, Browser.FIREFOX, 104, "Firefox 23", new String[] { "Firefox/23" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX22(Manufacturer.MOZILLA, Browser.FIREFOX, 103, "Firefox 22", new String[] { "Firefox/22" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX21(Manufacturer.MOZILLA, Browser.FIREFOX, 102, "Firefox 21", new String[] { "Firefox/21" }, new String[] { "WordPress.com mShots" }, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX20(Manufacturer.MOZILLA, Browser.FIREFOX, 101, "Firefox 20", new String[] { "Firefox/20" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX19(Manufacturer.MOZILLA, Browser.FIREFOX, 100, "Firefox 19", new String[] { "Firefox/19" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX18(Manufacturer.MOZILLA, Browser.FIREFOX, 99, "Firefox 18", new String[] { "Firefox/18" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX17(Manufacturer.MOZILLA, Browser.FIREFOX, 98, "Firefox 17", new String[] { "Firefox/17" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX16(Manufacturer.MOZILLA, Browser.FIREFOX, 97, "Firefox 16", new String[] { "Firefox/16" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX15(Manufacturer.MOZILLA, Browser.FIREFOX, 96, "Firefox 15", new String[] { "Firefox/15" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX14(Manufacturer.MOZILLA, Browser.FIREFOX, 95, "Firefox 14", new String[] { "Firefox/14" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX13(Manufacturer.MOZILLA, Browser.FIREFOX, 94, "Firefox 13", new String[] { "Firefox/13" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX12(Manufacturer.MOZILLA, Browser.FIREFOX, 93, "Firefox 12", new String[] { "Firefox/12" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX11(Manufacturer.MOZILLA, Browser.FIREFOX, 92, "Firefox 11", new String[] { "Firefox/11" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX10(Manufacturer.MOZILLA, Browser.FIREFOX, 91, "Firefox 10", new String[] { "Firefox/10" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX9(Manufacturer.MOZILLA, Browser.FIREFOX, 90, "Firefox 9", new String[] { "Firefox/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX8(Manufacturer.MOZILLA, Browser.FIREFOX, 80, "Firefox 8", new String[] { "Firefox/8" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX7(Manufacturer.MOZILLA, Browser.FIREFOX, 70, "Firefox 7", new String[] { "Firefox/7" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX6(Manufacturer.MOZILLA, Browser.FIREFOX, 60, "Firefox 6", new String[] { "Firefox/6" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX5(Manufacturer.MOZILLA, Browser.FIREFOX, 50, "Firefox 5", new String[] { "Firefox/5" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX4(Manufacturer.MOZILLA, Browser.FIREFOX, 40, "Firefox 4", new String[] { "Firefox/4" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX3(Manufacturer.MOZILLA, Browser.FIREFOX, 30, "Firefox 3", new String[] { "Firefox/3" }, new String[] { "ggpht.com" }, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX2(Manufacturer.MOZILLA, Browser.FIREFOX, 20, "Firefox 2", new String[] { "Firefox/2" }, new String[] { "WordPress.com mShots" }, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine
	FIREFOX1_5(Manufacturer.MOZILLA, Browser.FIREFOX, 15, "Firefox 1.5", new String[] { "Firefox/1.5" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null), // using Gecko Engine

	/*
	 * Thunderbird email client, based on the same Gecko engine Firefox is using.
	 */
	THUNDERBIRD(Manufacturer.MOZILLA, null, 110, "Thunderbird", new String[] { "Thunderbird" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, "Thunderbird/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"), // using Gecko Engine
	THUNDERBIRD12(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 185, "Thunderbird 12", new String[] { "Thunderbird/12" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD11(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 184, "Thunderbird 11", new String[] { "Thunderbird/11" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD10(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 183, "Thunderbird 10", new String[] { "Thunderbird/10" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD8(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 180, "Thunderbird 8", new String[] { "Thunderbird/8" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD7(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 170, "Thunderbird 7", new String[] { "Thunderbird/7" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD6(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 160, "Thunderbird 6", new String[] { "Thunderbird/6" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD3(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 130, "Thunderbird 3", new String[] { "Thunderbird/3" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine
	THUNDERBIRD2(Manufacturer.MOZILLA, Browser.THUNDERBIRD, 120, "Thunderbird 2", new String[] { "Thunderbird/2" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null), // using Gecko Engine

	VIVALDI(Manufacturer.OTHER, null, 108338, "Vivaldi", new String[] { "Vivaldi" }, new String[] {}, BrowserType.WEB_BROWSER, RenderingEngine.BLINK, "Vivaldi/(([\\d]+).([\\d]+).([\\d]+).([\\d]+))"),

	SEAMONKEY(Manufacturer.OTHER, null, 15, "SeaMonkey", new String[] { "SeaMonkey" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "SeaMonkey/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"), // using Gecko Engine

	MOZILLA(Manufacturer.MOZILLA, null, 1, "Mozilla", new String[] { "Mozilla", "Moozilla" }, new String[] { "ggpht.com" }, BrowserType.WEB_BROWSER, RenderingEngine.OTHER, null), // rest of the mozilla browsers

	CFNETWORK(Manufacturer.OTHER, null, 6, "CFNetwork", new String[] { "CFNetwork" }, null, BrowserType.UNKNOWN, RenderingEngine.OTHER, "CFNetwork/(([\\d]+)(?:\\.([\\d]))?(?:\\.([\\d]+))?)"), // Mac OS X cocoa library

	EUDORA(Manufacturer.OTHER, null, 7, "Eudora", new String[] { "Eudora", "EUDORA" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null), // email client by Qualcomm

	POCOMAIL(Manufacturer.OTHER, null, 8, "PocoMail", new String[] { "PocoMail" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null),

	THEBAT(Manufacturer.OTHER, null, 9, "The Bat!", new String[] { "The Bat" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null), // Email Client

	NETFRONT(Manufacturer.OTHER, null, 10, "NetFront", new String[] { "NetFront" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.OTHER, null), // mobile device browser

	EVOLUTION(Manufacturer.OTHER, null, 11, "Evolution", new String[] { "CamelHttpStream" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null), // http://www.go-evolution.org/Camel.Stream

	LYNX(Manufacturer.OTHER, null, 13, "Lynx", new String[] { "Lynx" }, null, BrowserType.TEXT_BROWSER, RenderingEngine.OTHER, "Lynx/(([0-9]+)\\.([\\d]+)\\.?([\\w-+]+)?\\.?([\\w-+]+)?)"),

	DOWNLOAD(Manufacturer.OTHER, null, 16, "Downloading Tool", new String[] { "cURL", "wget", "ggpht.com", "Apache-HttpClient" }, null, BrowserType.TOOL, RenderingEngine.OTHER, null),

	UNKNOWN(Manufacturer.OTHER, null, 14, "Unknown", new String[0], null, BrowserType.UNKNOWN, RenderingEngine.OTHER, null),
	@Deprecated
	APPLE_MAIL(Manufacturer.APPLE, null, 51, "Apple Mail", new String[0], null, BrowserType.EMAIL_CLIENT, RenderingEngine.WEBKIT, null); // not detectable any longer.

	private static final class TopLevelBrowsers {
		private static final List<Browser> browsers = new ArrayList<Browser>();
	}

	static {
		TopLevelBrowsers.browsers.add(CHROME_MOBILE);
		// Bind Chrome versioned browsers with Chrome Mobile
		for (Browser browser : Browser.values()) {
			if (browser.parent == CHROME && browser != CHROME_MOBILE) {
				addChildBrowser(CHROME_MOBILE, browser);
			} else if (browser.parent == FIREFOX && browser != FIREFOX_MOBILE) {
				addChildBrowser(FIREFOX_MOBILE, browser);
			}
		}
		addChildBrowser(OPERA_MOBILE, OPERA10);
		addChildBrowser(OPERA_MOBILE, OPERA11);
		addChildBrowser(OPERA_MOBILE, OPERA12);
	}
	/*
	 * An id for each browser version which is unique per manufacturer.
	 */
	private final short id;
	private final String name;
	private final String[] aliases;
	private final String[] excludeList; // don't match when these values are in the agent-string
	private final BrowserType browserType;
	private final Manufacturer manufacturer;
	private final RenderingEngine renderingEngine;
	private final Browser parent;
	private List<Browser> children;
	private Pattern versionRegEx;

	private Browser(Manufacturer manufacturer, Browser parent, int versionId, String name, String[] aliases, String[] exclude, BrowserType browserType, RenderingEngine renderingEngine, String versionRegexString) {
		this.id = (short) ((manufacturer.getId() << 10) + (short) versionId);
		this.name = name;
		this.parent = parent;
		this.children = new ArrayList<Browser>();
		this.aliases = Utils.toLowerCase(aliases);
		this.excludeList = Utils.toLowerCase(exclude);
		this.browserType = browserType;
		this.manufacturer = manufacturer;
		this.renderingEngine = renderingEngine;
		if (versionRegexString != null) {
			this.versionRegEx = Pattern.compile(versionRegexString, Pattern.CASE_INSENSITIVE);
		}
		if (this.parent == null) {
			addTopLevelBrowser(this);
		} else {
			addChildBrowser(this.parent, this);
		}
	}

	private static boolean addChildBrowser(Browser browser, Browser child) {
		return browser.children.add(child);
	}

	// create collection of top level browsers during initialization
	private static void addTopLevelBrowser(Browser browser) {
		TopLevelBrowsers.browsers.add(browser);
	}

	public short getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private Pattern getVersionRegEx() {
		if (this.versionRegEx == null) {
			if (this.getGroup() != this)
				return this.getGroup().getVersionRegEx();
			else
				return null;
		}
		return this.versionRegEx;
	}

	/**
	 * Detects the detailed version information of the browser. Depends on the userAgent to be available.
	 * Returns null if it can not detect the version information.
	 * @return Version
	 */
	public Version getVersion(String userAgentString) {
		Pattern pattern = this.getVersionRegEx();
		if (userAgentString != null && pattern != null) {
			Matcher matcher = pattern.matcher(userAgentString);
			if (matcher.find()) {
				String fullVersionString = matcher.group(1);
				String majorVersion = matcher.group(2);
				String minorVersion = "0";
				if (matcher.groupCount() > 2) { // usually but not always there is a minor version
					minorVersion = matcher.group(3);
				}
				return new Version(fullVersionString, majorVersion, minorVersion);
			}
		}
		return null;
	}

	/**
	 * @return the browserType
	 */
	public BrowserType getBrowserType() {
		return browserType;
	}

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @return the rendering engine
	 */
	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	/**
	 * @return top level browser family
	 */
	public Browser getGroup() {
		if (this.parent != null) {
			return parent.getGroup();
		}
		return this;
	}

    /*
     * Checks if the given user-agent string matches to the browser.
     * Only checks for one specific browser.
     */
    public boolean isInUserAgentString(String agentString)
    {
        if (agentString == null)
            return false;

        String agentStringLowerCase = Utils.toLowerCaseAscii(agentString);
        return isInUserAgentLowercaseString(agentStringLowerCase);
    }

    private boolean isInUserAgentLowercaseString(String agentStringLowerCase) {
        return Utils.contains(agentStringLowerCase, aliases);
    }

    private Browser checkUserAgentLowercase(String agentLowercaseString) {
        if (this.isInUserAgentLowercaseString(agentLowercaseString)) {

            if (this.children.size() > 0) {
                for (Browser childBrowser : this.children) {
                    Browser match = childBrowser.checkUserAgentLowercase(agentLowercaseString);
                    if (match != null) {
                        return match;
                    }
                }
            }

            // if children didn't match we continue checking the current to prevent false positives
            if (!Utils.contains(agentLowercaseString, excludeList)) {
                return this;
            }

		}
		return null;
	}

	/**
	 * Iterates over all Browsers to compare the browser signature with
	 * the user agent string. If no match can be found Browser.UNKNOWN will
	 * be returned.
	 * Starts with the top level browsers and only if one of those matches
	 * checks children browsers.
	 * Steps out of loop as soon as there is a match.
	 * @param agentString
	 * @return Browser
	 */
	public static Browser parseUserAgentString(String agentString)
	{
		return parseUserAgentString(agentString, TopLevelBrowsers.browsers);
	}

	public static Browser parseUserAgentLowercaseString(String agentString)
	{
		if (agentString == null) {
			return Browser.UNKNOWN;
		}
		return parseUserAgentLowercaseString(agentString, TopLevelBrowsers.browsers);
	}

	/**
     * Iterates over the given Browsers (incl. children) to compare the browser
     * signature with the user agent string.
     * If no match can be found Browser.UNKNOWN will be returned.
     * Steps out of loop as soon as there is a match.
     * Be aware that if the order of the provided Browsers is incorrect or if the set is too limited it can lead to false matches!
	 * @param agentString
	 * @return Browser
	 */
	public static Browser parseUserAgentString(String agentString, List<Browser> browsers)
	{
		if (agentString != null) {
			String agentLowercaseString = Utils.toLowerCaseAscii(agentString);
			return parseUserAgentLowercaseString(agentLowercaseString, browsers);
		}
		return Browser.UNKNOWN;
	}

	private static Browser parseUserAgentLowercaseString(String agentLowercaseString, List<Browser> browsers) {
		for (Browser browser : browsers) {
			Browser match = browser.checkUserAgentLowercase(agentLowercaseString);
			if (match != null) {
				return match; // either current operatingSystem or a child object
			}
		}
		return Browser.UNKNOWN;
	}

	/**
	 * Returns the enum constant of this type with the specified id.
	 * Throws IllegalArgumentException if the value does not exist.
	 * @param id
	 * @return
	 */
	public static Browser valueOf(short id)
	{
		for (Browser browser : Browser.values())
		{
			if (browser.getId() == id)
				return browser;
		}

		// same behavior as standard valueOf(string) method
		throw new IllegalArgumentException(
				"No enum const for id " + id);
	}

}
