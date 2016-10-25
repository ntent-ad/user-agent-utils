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

using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;
using System.Text.RegularExpressions;

namespace eu.bitwalker.useragentutils
{
    /**
     * Enum constants for most common browsers, including e-mail clients and bots.
     * @author harald -- @ported by thunder stumpges
     * 
     */

    public sealed class Browser
    {
        #region .NET specific initialization
        /*
         * List of Browsers for returning all of them (see method 'values')
         */
        private static readonly List<Browser> _values;
        static Browser()
        {
            // populate the list using reflection so we don't have to maintain it.
            _values = typeof(Browser).GetFields(BindingFlags.Static | BindingFlags.Public)
                .Where(pi => pi.FieldType == typeof(Browser))
                .Select(pi => { var p = (Browser)pi.GetValue(null); p.Key = pi.Name; return p; })
                .ToList();


            TopLevelBrowsers.browsers.Add(CHROME_MOBILE);
            // Bind Chrome versioned browsers with Chrome Mobile
            foreach (Browser browser in Browser.values())
            {
                if (browser.parent == CHROME && browser != CHROME_MOBILE)
                {
                    addChildBrowser(CHROME_MOBILE, browser);
                }
                else if (browser.parent == FIREFOX && browser != FIREFOX_MOBILE)
                {
                    addChildBrowser(FIREFOX_MOBILE, browser);
                }
            }

            addChildBrowser(OPERA_MOBILE, OPERA10);
            addChildBrowser(OPERA_MOBILE, OPERA11);
            addChildBrowser(OPERA_MOBILE, OPERA12);

        }
        /*
         * C# does not allow the Enum type to be Object like Java, so we need to mimic the values() function.
         */
        public static ReadOnlyCollection<Browser> values()
        {
            return _values.AsReadOnly();
        }
        #endregion .NET specific initialization

        /**
         * Bots
         */
        public static readonly Browser BOT = new Browser(Manufacturer.OTHER, null, 12, "Robot/Spider", new String[] { "AdsBot-Google", "adbeat.com", "Googlebot", "Mediapartners-Google", "Web Preview", "bot", 
			"Applebot", "spider", "crawler", "Feedfetcher", "Slurp", "Twiceler", "Nutch", "BecomeBot", "bingbot", "BingPreview/", "Google Web Preview", 
			"WordPress.com mShots", "Seznam", "facebookexternalhit", "YandexMarket", "Teoma", "ThumbSniper", "Phantom.js", "Accoona-AI-Agent", 
			"Arachmo", "B-l-i-t-z-B-O-T", "Cerberian Drtrs", "Charlotte", "Covario", "DataparkSearch",
			"FindLinks", "Holmes", "htdig", "ia_archiver", "ichiro", "igdeSpyder", "L.webis", "Larbin", "LinkWalker", "lwp-trivial", "mabontland",
			"Mnogosearch", "mogimogi", "MVAClient", "NetResearchServer", "NewsGator", "NG-Search", "Nymesis", "oegp", "Pompos", "PycURL", "Qseero", "SBIder",
			"ScoutJet", "Scrubby", "SearchSight", "semanticdiscovery",  "Snappy", "Sqworm", "StackRambler", "TinEye", "truwo", "updated", "voyager",
			"VYU2", "webcollage", "YahooSeeker", "yoogliFetchAgent", "Zao", "yahoo-ad-monitoring" }, null, BrowserType.ROBOT, RenderingEngine.OTHER, null);
        public static readonly Browser BOT_MOBILE = new Browser(Manufacturer.OTHER, Browser.BOT, 20, "Mobil Robot/Spider", new String[] { "Googlebot-Mobile" }, null, BrowserType.ROBOT, RenderingEngine.OTHER, null);

        /**
	     * Outlook email client
	     */
	    public static readonly Browser OUTLOOK = new Browser(	Manufacturer.MICROSOFT, null, 100, "Outlook", new string[] {"MSOffice"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, "MSOffice (([0-9]+))"); // before IE7
		    /**
		     * Microsoft Outlook 2007 identifies itself as MSIE7 but uses the html rendering engine of Word 2007.
		     * Example user agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; .NET CLR 1.1.4322; MSOffice 12)
		     */
		    public static readonly Browser OUTLOOK2007 = new Browser(	Manufacturer.MICROSOFT, Browser.OUTLOOK, 107, "Outlook 2007", new string[] {"MSOffice 12"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null); // before IE7
		    public static readonly Browser OUTLOOK2013 = new Browser(	Manufacturer.MICROSOFT, Browser.OUTLOOK, 109, "Outlook 2013", new string[] {"Microsoft Outlook 15"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null); // before IE7
		    /**
		     * Outlook 2010 is still using the rendering engine of Word. http://www.fixoutlook.org
		     */
		    public static readonly Browser OUTLOOK2010 = new Browser(	Manufacturer.MICROSOFT, Browser.OUTLOOK, 108, "Outlook 2010", new string[] {"MSOffice 14", "Microsoft Outlook 14"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null); // before IE7

	    /**
	     * Family of Internet Explorer browsers
	     */
        public static readonly Browser IE = new Browser(Manufacturer.MICROSOFT, null, 1, "Internet Explorer", new string[] { "MSIE", "Trident", "IE " }, new String[] { "Opera", "BingPreview", "Xbox", "Xbox One" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, "MSIE (([\\d]+)\\.([\\w]+))"); // before Mozilla
		    /**
		     * Since version 7 Outlook Express is identifying itself. By detecting Outlook Express we can not 
		     * identify the Internet Explorer version which is probably used for the rendering.
		     * Obviously this product is now called Windows Live Mail Desktop or just Windows Live Mail.
		     */
		    public static readonly Browser OUTLOOK_EXPRESS7 = new Browser(	Manufacturer.MICROSOFT, Browser.IE, 110, "Windows Live Mail", new string[] {"Outlook-Express/7.0"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.TRIDENT, null); // before IE7, previously known as Outlook Express. First released in 2006, offered with different name later
            /**
		     * Since 2007 the mobile edition of Internet Explorer identifies itself as IEMobile in the user-agent. 
		     * If previous versions have to be detected, use the operating system information as well.
		     */
            public static readonly Browser IEMOBILE = new Browser(Manufacturer.MICROSOFT, Browser.IE, 119, "IE Mobile", new String[] { "IEMobile" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE                public static readonly Browser IEMOBILE11 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 125, "IE Mobile 11", new String[] { "IEMobile/11" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE strings                public static readonly Browser IEMOBILE10 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 124, "IE Mobile 10", new String[] { "IEMobile/10" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE strings
                public static readonly Browser IEMOBILE9 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 123, "IE Mobile 9", new String[] { "IEMobile/9" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE strings
                public static readonly Browser IEMOBILE8 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 122, "IE Mobile 8", new String[] { "IEMobile 8", "IEMobile/8" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE strings
                public static readonly Browser IEMOBILE7 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 121, "IE Mobile 7", new String[] { "IEMobile 7", "IEMobile/7" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE strings
                public static readonly Browser IEMOBILE6 = new Browser(Manufacturer.MICROSOFT, Browser.IEMOBILE, 120, "IE Mobile 6", new String[] { "IEMobile 6" }, new String[] { "Opera" }, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, "IEMobile[\\s/](([\\d]+)\\.([\\w]+))"); // before MSIE
            public static readonly Browser IE_XBOX = new Browser(Manufacturer.MICROSOFT, Browser.IE, 360, "Xbox", new String[] { "xbox" }, new String[] {}, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
            public static readonly Browser IE11 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 95, "Internet Explorer 11", new String[] { "Trident/7", "IE 11." }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, "(?:Trident/7|IE)(?:\\.[0-9]*;)?(?:.*rv:| )(([0-9]+)\\.?([0-9]+))"); // before Mozilla
            public static readonly Browser IE10 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 92, "Internet Explorer 10", new String[] { "Trident/6", "MSIE 10" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE9 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 90, "Internet Explorer 9", new String[] { "MSIE 9" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE8 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 80, "Internet Explorer 8", new String[] { "MSIE 8", "OptimizedIE8" }, new String[] { "Trident/6", "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE7 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 70, "Internet Explorer 7", new String[] { "MSIE 7" }, new String[] { "OptimizedIE8", "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE6 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 60, "Internet Explorer 6", new String[] { "MSIE 6" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE5_5 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 55, "Internet Explorer 5.5", new String[] { "MSIE 5.5" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE
            public static readonly Browser IE5 = new Browser(Manufacturer.MICROSOFT, Browser.IE, 50, "Internet Explorer 5", new String[] { "MSIE 5" }, new String[] { "Opera" }, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null); // before MSIE

        /**
         * Family of Microsoft Edge browsers. Pretends to be Chrome and claims to be webkit compatible. 
         */
        public static readonly Browser EDGE = new Browser(Manufacturer.MICROSOFT, null, 300, "Microsoft Edge", new string[] {"Edge"}, null, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((12)\\.([0-9]*)))");
    public static readonly Browser EDGE13 = new Browser(Manufacturer.MICROSOFT, Browser.EDGE, 303, "Microsoft Edge", new String[] { "Edge/13" }, new String[] { "Mobile" }, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((13)\\.([0-9]*)))");
		    public static readonly Browser EDGE12 = new Browser(        Manufacturer.MICROSOFT, Browser.EDGE, 301, "Microsoft Edge", new string[] {"Edge/12"}, new String[] {"Mobile"}, BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((12)\\.([0-9]*)))"    );
		    public static readonly Browser EDGE_MOBILE12 = new Browser( Manufacturer.MICROSOFT, Browser.EDGE, 302, "Microsoft Edge Mobile", new string[] {"Mobile Safari", "Edge/12"}, null, BrowserType.MOBILE_BROWSER, RenderingEngine.EDGE_HTML, "(?:Edge/((12)\\.([0-9]*)))"    );

        /* 
            NTENT - before SAFARI and generic AppleWebKit  and generic AndroidWebKit
        */
        public static readonly Browser NTENT = new Browser(Manufacturer.NTENT, null, 1, "NTENT Browser", new[] { "NTENTBrowser" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, @"NTENTBrowser\/(([0-9]+)\.([\d]+)\.?([\d]+)?\.?([\d]+)?)");

        /**
	     * Google Chrome browser
	     */
        public static readonly Browser CHROME = new Browser( 		Manufacturer.GOOGLE, null, 1, "Chrome", new string[] { "Chrome", "CrMo", "CriOS" }, new string[] { "OPR/", "Web Preview", "Vivaldi" } , BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Chrome/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)" ); // before Mozilla
            public static readonly Browser ANDROID_WEB_KIT = new Browser(Manufacturer.GOOGLE, null, 2, "Android Webkit Browser", new String[] { "Android", "Build/" }, new String[] { "Windows", "Firefox", "Chrome", "Opera", "OPR/", "Web Preview", "Googlebot-Mobile" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Version/(([\\d]+)\\.([\\w]+))"); // Android WebKit also only identifies itself as AppleWebKit
		    public static readonly Browser CHROME_MOBILE = new Browser( 	Manufacturer.GOOGLE, Browser.CHROME, 100, "Chrome Mobile", new string[] { "CrMo","CriOS", "Mobile Safari" },  new String[] {"OPR/", "Web Preview" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "(?:CriOS|CrMo|Chrome)/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)" );
            public static readonly Browser CHROME55 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 60, "Chrome 55", new String[] { "Chrome/55" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME54 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 59, "Chrome 54", new String[] { "Chrome/54" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME53 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 58, "Chrome 53", new String[] { "Chrome/53" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME52 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 57, "Chrome 52", new String[] { "Chrome/52" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME51 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 56, "Chrome 51", new String[] { "Chrome/51" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME50 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 55, "Chrome 50", new String[] { "Chrome/50" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME49 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 54, "Chrome 49", new String[] { "Chrome/49" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME48 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 53, "Chrome 48", new String[] { "Chrome/48" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME47 = new Browser(Manufacturer.GOOGLE, Browser.CHROME, 52, "Chrome 47", new String[] { "Chrome/47" }, new String[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
		    public static readonly Browser CHROME46 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 51, "Chrome 46", new string[] { "Chrome/46" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME45 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 50, "Chrome 45", new string[] { "Chrome/45" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME44 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 49, "Chrome 44", new string[] { "Chrome/44" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME43 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 48, "Chrome 43", new string[] { "Chrome/43" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME42 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 47, "Chrome 42", new string[] { "Chrome/42" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME41 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 46, "Chrome 41", new string[] { "Chrome/41" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
            public static readonly Browser CHROME40 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 45, "Chrome 40", new string[] { "Chrome/40" }, new string[] { "OPR/", "Web Preview", "Vivaldi" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
		    public static readonly Browser CHROME39 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 44, "Chrome 39", new string[] { "Chrome/39" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME38 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 43, "Chrome 38", new string[] { "Chrome/38" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME37 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 42, "Chrome 37", new string[] { "Chrome/37" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME36 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 41, "Chrome 36", new string[] { "Chrome/36" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME35 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 40, "Chrome 35", new string[] { "Chrome/35" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME34 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 39, "Chrome 34", new string[] { "Chrome/34" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME33 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 38, "Chrome 33", new string[] { "Chrome/33" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME32 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 37, "Chrome 32", new string[] { "Chrome/32" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME31 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 36, "Chrome 31", new string[] { "Chrome/31" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME30 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 35, "Chrome 30", new string[] { "Chrome/30" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME29 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 34, "Chrome 29", new string[] { "Chrome/29" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME28 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 33, "Chrome 28", new string[] { "Chrome/28" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
            public static readonly Browser CHROME27 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 32, "Chrome 27", new string[] { "Chrome/27" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME26 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 31, "Chrome 26", new string[] { "Chrome/26" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME25 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 30, "Chrome 25", new string[] { "Chrome/25" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME24 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 29, "Chrome 24", new string[] { "Chrome/24" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME23 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 28, "Chrome 23", new string[] { "Chrome/23" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME22 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 27, "Chrome 22", new string[] { "Chrome/22" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME21 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 26, "Chrome 21", new string[] { "Chrome/21" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME20 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 25, "Chrome 20", new string[] { "Chrome/20" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
            public static readonly Browser CHROME19 = new Browser(      Manufacturer.GOOGLE, Browser.CHROME, 24, "Chrome 19", new string[] { "Chrome/19" }, new string[] { "OPR/", "Web Preview" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // before Mozilla
		    public static readonly Browser CHROME18 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 23, "Chrome 18", new string[] { "Chrome/18" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME17 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 22, "Chrome 17", new string[] { "Chrome/17" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME16 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 21, "Chrome 16", new string[] { "Chrome/16" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME15 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 20, "Chrome 15", new string[] { "Chrome/15" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME14 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 19, "Chrome 14", new string[] { "Chrome/14" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME13 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 18, "Chrome 13", new string[] { "Chrome/13" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME12 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 17, "Chrome 12", new string[] { "Chrome/12" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME11 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 16, "Chrome 11", new string[] { "Chrome/11" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME10 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 15, "Chrome 10", new string[] { "Chrome/10" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME9 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 10, "Chrome 9", new string[] { "Chrome/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
		    public static readonly Browser CHROME8 = new Browser( 		Manufacturer.GOOGLE, Browser.CHROME, 5, "Chrome 8", new string[] { "Chrome/8" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null ); // before Mozilla
			
	    public static readonly Browser OMNIWEB = new Browser(		    Manufacturer.OTHER, null, 2, "Omniweb", new string[] { "OmniWeb" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // 

        public static readonly Browser SAFARI = new Browser(			Manufacturer.APPLE, null, 1, "Safari", new string[] { "Safari" }, new string[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi","CFNetwork", "Web Preview", "Googlebot-Mobile", "Android", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Version/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)" );  // before AppleWebKit
		    public static readonly Browser BLACKBERRY10 = new Browser(  Manufacturer.BLACKBERRY, Browser.SAFARI, 10, "BlackBerry", new string[] { "BB10" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null);
		    public static readonly Browser MOBILE_SAFARI = new Browser(	Manufacturer.APPLE, Browser.SAFARI, 2, "Mobile Safari", new string[] { "Mobile Safari","Mobile/" }, new string[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null );  // before Safari
                public static readonly Browser MOBILE_SAFARI9 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 109, "Mobile Safari 9", new String[] { "Mobile/13" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
                public static readonly Browser MOBILE_SAFARI8 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 108, "Mobile Safari 8", new String[] { "Mobile/12" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
                public static readonly Browser MOBILE_SAFARI7 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 107, "Mobile Safari 7", new String[] { "Mobile/11" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
                public static readonly Browser MOBILE_SAFARI6 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 106, "Mobile Safari 6", new String[] { "Mobile/10" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
                public static readonly Browser MOBILE_SAFARI5 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 105, "Mobile Safari 5", new String[] { "Mobile/9" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
                public static readonly Browser MOBILE_SAFARI4 = new Browser(Manufacturer.APPLE, Browser.MOBILE_SAFARI, 104, "Mobile Safari 4", new String[] { "Mobile/8", "Mobile/7", "Mobile Safari/" }, new String[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null); // before Safari
		    public static readonly Browser SILK = new Browser(			Manufacturer.AMAZON, Browser.SAFARI, 15, "Silk", new string[] { "Silk/" }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Silk/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\-[\\w]+)?)" );  // http://en.wikipedia.org/wiki/Amazon_Silk
            public static readonly Browser SAFARI9 = new Browser(       Manufacturer.APPLE, Browser.SAFARI, 9, "Safari 9", new string[] { "Version/9" }, new string[] { "Applebot", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit
		    public static readonly Browser SAFARI8 = new Browser(		Manufacturer.APPLE, Browser.SAFARI, 8, "Safari 8", new string[] { "Version/8" }, new string[] { "Applebot", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit
		    public static readonly Browser SAFARI7 = new Browser(		Manufacturer.APPLE, Browser.SAFARI, 7, "Safari 7", new string[] { "Version/7" }, new string[]{"bing", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit
		    public static readonly Browser SAFARI6 = new Browser(		Manufacturer.APPLE, Browser.SAFARI, 6, "Safari 6", new string[] { "Version/6" }, new String[] { "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit
		    public static readonly Browser SAFARI5 = new Browser(		Manufacturer.APPLE, Browser.SAFARI, 3, "Safari 5", new string[] { "Version/5" }, new string[]{"Google Web Preview", "Coast/", "Googlebot-Mobile", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit
		    public static readonly Browser SAFARI4 = new Browser(		Manufacturer.APPLE, Browser.SAFARI, 4, "Safari 4", new string[] { "Version/4" }, new string[] { "Googlebot-Mobile", "Mobile/", "Build/", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null );  // before AppleWebKit

        /**
	     * Opera Coast mobile browser, http://en.wikipedia.org/wiki/Opera_Coast	
	     */
        public static readonly Browser COAST = new Browser(			Manufacturer.OPERA, null, 500, "Opera", new string[] { " Coast/" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Coast/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");   
		    public static readonly Browser COAST1 = new Browser(			Manufacturer.OPERA, Browser.COAST, 501, "Opera", new string[] { " Coast/1." }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, "Coast/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");   
		
	    public static readonly Browser OPERA = new Browser(			    Manufacturer.OPERA, null, 1, "Opera", new string[] { " OPR/", "Opera" }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Opera/(([\\d]+)\\.([\\w]+))");   // before MSIE
		    public static readonly Browser OPERA_MOBILE = new Browser(	Manufacturer.OPERA, Browser.OPERA, 100,"Opera Mobile", new string[] { "Mobile Safari", "Opera Mobi" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.BLINK, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)"); // Another Opera for mobile devices
		    public static readonly Browser OPERA_MINI = new Browser(	Manufacturer.OPERA, Browser.OPERA, 2, "Opera Mini", new string[] { "Opera Mini"}, null, BrowserType.MOBILE_BROWSER, RenderingEngine.PRESTO, null); // Opera for mobile devices
            public static readonly Browser OPERA43 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 43, "Opera 43", new String[] { "OPR/43." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA42 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 42, "Opera 42", new String[] { "OPR/42." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA41 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 41, "Opera 41", new String[] { "OPR/41." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA40 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 40, "Opera 40", new String[] { "OPR/40." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA39 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 39, "Opera 39", new String[] { "OPR/39." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA38 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 38, "Opera 38", new String[] { "OPR/38." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA37 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 37, "Opera 37", new String[] { "OPR/37." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA36 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 36, "Opera 36", new String[] { "OPR/36." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA35 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 35, "Opera 35", new String[] { "OPR/35." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA34 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 34, "Opera 34", new string[] { "OPR/34." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA33 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 33, "Opera 33", new string[] { "OPR/33." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA32 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 32, "Opera 32", new string[] { "OPR/32." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA31 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 31, "Opera 31", new string[] { "OPR/31." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA30 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 30, "Opera 30", new string[] { "OPR/30." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA29 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 29, "Opera 29", new string[] { "OPR/29." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA28 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 28, "Opera 28", new string[] { "OPR/28." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA27 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 27, "Opera 27", new string[] { "OPR/27." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA26 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 26, "Opera 26", new string[] { "OPR/26." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA25 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 25, "Opera 25", new string[] { "OPR/25." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA24 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 24, "Opera 24", new string[] { "OPR/24." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA23 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 23, "Opera 23", new string[] { "OPR/23." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA22 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 22, "Opera 22", new String[] { "OPR/22." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
            public static readonly Browser OPERA21 = new Browser(       Manufacturer.OPERA, Browser.OPERA, 21, "Opera 21", new String[] { "OPR/21." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA20 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 20, "Opera 20", new string[] { "OPR/20." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA19 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 19, "Opera 19", new string[] { "OPR/19." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA18 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 18, "Opera 18", new string[] { "OPR/18." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA17 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 17, "Opera 17", new string[] { "OPR/17." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA16 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 16, "Opera 16", new string[] { "OPR/16." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
		    public static readonly Browser OPERA15 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 15, "Opera 15", new string[] { "OPR/15." }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "OPR/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");		
		    public static readonly Browser OPERA12 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 12, "Opera 12", new string[] { "Opera/12", "Opera 12.", "Version/12." }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))");
		    public static readonly Browser OPERA11 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 11, "Opera 11", new string[] { "Opera/11", "Opera 11.", "Version/11." }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))");
		    public static readonly Browser OPERA10 = new Browser(		Manufacturer.OPERA, Browser.OPERA, 10, "Opera 10", new string[] { "Opera/9.8" }, new String[] { "Version/11.", "Version/12." }, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version/(([\\d]+)\\.([\\w]+))");  
		    public static readonly Browser OPERA9 = new Browser(			Manufacturer.OPERA, Browser.OPERA, 5, "Opera 9", new string[] { "Opera/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, null);  
	    public static readonly Browser KONQUEROR = new Browser(		Manufacturer.OTHER, null, 1, "Konqueror", new string[] { "Konqueror"}, new string[]{"Exabot"}, BrowserType.WEB_BROWSER, RenderingEngine.KHTML, "Konqueror/(([0-9]+)\\.?([\\w]+)?(-[\\w]+)?)" );  
			
	    public static readonly Browser DOLFIN2 = new Browser( 		Manufacturer.SAMSUNG, null, 1, "Samsung Dolphin 2", new string[] { "Dolfin/2" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null ); // webkit based browser for the bada os
	
	    /*
	     * Apple WebKit compatible client. Can be a browser or an application with embedded browser using UIWebView.
	     */
	    public static readonly Browser APPLE_WEB_KIT = new Browser(	Manufacturer.APPLE, null, 50, "Apple WebKit", new string[] { "AppleWebKit" }, new string[] { "bot", "preview", "OPR/", "Coast/", "Vivaldi", "Web Preview", "Googlebot-Mobile", "Android", "bingbot/" }, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null); // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit 
		    public static readonly Browser APPLE_ITUNES = new Browser(	Manufacturer.APPLE, Browser.APPLE_WEB_KIT, 52, "iTunes", new string[] { "iTunes" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null); // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit 
		    public static readonly Browser APPLE_APPSTORE = new Browser(	Manufacturer.APPLE, Browser.APPLE_WEB_KIT, 53, "App Store", new string[] { "MacAppStore" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null); // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit 
		    public static readonly Browser ADOBE_AIR = new Browser(	Manufacturer.ADOBE, Browser.APPLE_WEB_KIT, 1, "Adobe AIR application", new string[] { "AdobeAIR" }, null, BrowserType.APP, RenderingEngine.WEBKIT, null); // Microsoft Entrourage/Outlook 2010 also only identifies itself as AppleWebKit 

	    public static readonly Browser LOTUS_NOTES = new Browser( 	Manufacturer.OTHER, null, 3, "Lotus Notes", new string[] { "Lotus-Notes" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, "Lotus-Notes/(([\\d]+)\\.([\\w]+))");  // before Mozilla

	    public static readonly Browser CAMINO = new Browser(			Manufacturer.OTHER, null, 5, "Camino", new string[] { "Camino" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Camino/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)" );  // using Gecko Engine
		    public static readonly Browser CAMINO2 = new Browser(		Manufacturer.OTHER, Browser.CAMINO, 17, "Camino 2", new string[] { "Camino/2" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine

	    public static readonly Browser FLOCK = new Browser(			Manufacturer.OTHER, null, 4, "Flock", new string[]{"Flock"}, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Flock/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)");
		
	    public static readonly Browser FIREFOX = new Browser(		Manufacturer.MOZILLA, null, 10, "Firefox", new string[] { "Firefox" }, new string[] {"ggpht.com", "WordPress.com mShots"}, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Firefox/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");  // using Gecko Engine
		    public static readonly Browser FIREFOX3MOBILE = new Browser(	Manufacturer.MOZILLA, Browser.FIREFOX, 31, "Firefox 3 Mobile", new string[] { "Firefox/3.5 Maemo" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX_MOBILE = new Browser(	Manufacturer.MOZILLA, Browser.FIREFOX, 200, "Firefox Mobile", new string[] { "Mobile", "Android" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
                public static readonly Browser FIREFOX_MOBILE13 = new Browser(Manufacturer.MOZILLA, FIREFOX_MOBILE, 213, "Firefox Mobile 13", new String[] { "Firefox/13" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
			    public static readonly Browser FIREFOX_MOBILE23 = new Browser(Manufacturer.MOZILLA, FIREFOX_MOBILE, 223, "Firefox Mobile 23", new string[] { "Firefox/23" }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
            public static readonly Browser FIREFOX52 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 520, "Firefox 52", new String[] { "Firefox/52" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX51 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 510, "Firefox 51", new String[] { "Firefox/51" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX50 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 500, "Firefox 50", new String[] { "Firefox/50" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX49 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 490, "Firefox 49", new String[] { "Firefox/49" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX48 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 480, "Firefox 48", new String[] { "Firefox/48" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX47 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 470, "Firefox 47", new String[] { "Firefox/47" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX46 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 460, "Firefox 46", new String[] { "Firefox/46" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX45 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 450, "Firefox 45", new String[] { "Firefox/45" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX44 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 440, "Firefox 44", new String[] { "Firefox/44" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX43 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 430, "Firefox 43", new String[] { "Firefox/43" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null); // using Gecko Engine
            public static readonly Browser FIREFOX42 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 420, "Firefox 42", new string[] { "Firefox/42" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
			public static readonly Browser FIREFOX41 = new Browser(     Manufacturer.MOZILLA, Browser.FIREFOX, 410, "Firefox 41", new string[] { "Firefox/41" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX40 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 400, "Firefox 40", new string[] { "Firefox/40" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX39 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 390, "Firefox 39", new string[] { "Firefox/39" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX38 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 380, "Firefox 38", new string[] { "Firefox/38" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX37 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 370, "Firefox 37", new string[] { "Firefox/37" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX36 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 360, "Firefox 36", new string[] { "Firefox/36" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX35 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 350, "Firefox 35", new string[] { "Firefox/35" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX34 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 340, "Firefox 34", new string[] { "Firefox/34" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX33 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 330, "Firefox 33", new string[] { "Firefox/33" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX32 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 320, "Firefox 32", new string[] { "Firefox/32" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX31 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 310, "Firefox 31", new string[] { "Firefox/31" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX30 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 300, "Firefox 30", new string[] { "Firefox/30" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX29 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 290, "Firefox 29", new string[] { "Firefox/29" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX28 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 280, "Firefox 28", new string[] { "Firefox/28" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX27 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 108, "Firefox 27", new string[] { "Firefox/27" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX26 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 107, "Firefox 26", new string[] { "Firefox/26" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX25 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 106, "Firefox 25", new string[] { "Firefox/25" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX24 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 105, "Firefox 24", new string[] { "Firefox/24" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX23 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 104, "Firefox 23", new string[] { "Firefox/23" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine		
		    public static readonly Browser FIREFOX22 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 103, "Firefox 22", new string[] { "Firefox/22" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX21 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 102, "Firefox 21", new string[] { "Firefox/21" }, new string[]{"WordPress.com mShots"}, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX20 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 101, "Firefox 20", new string[] { "Firefox/20" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX19 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 100, "Firefox 19", new string[] { "Firefox/19" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX18 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 99, "Firefox 18", new string[] { "Firefox/18" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX17 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 98, "Firefox 17", new string[] { "Firefox/17" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX16 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 97, "Firefox 16", new string[] { "Firefox/16" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX15 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 96, "Firefox 15", new string[] { "Firefox/15" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX14 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 95, "Firefox 14", new string[] { "Firefox/14" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX13 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 94, "Firefox 13", new string[] { "Firefox/13" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX12 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 93, "Firefox 12", new string[] { "Firefox/12" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX11 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 92, "Firefox 11", new string[] { "Firefox/11" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX10 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 91, "Firefox 10", new string[] { "Firefox/10" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX9 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 90, "Firefox 9", new string[] { "Firefox/9" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX8 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 80, "Firefox 8", new string[] { "Firefox/8" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX7 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 70, "Firefox 7", new string[] { "Firefox/7" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX6 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 60, "Firefox 6", new string[] { "Firefox/6" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX5 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 50, "Firefox 5", new string[] { "Firefox/5" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX4 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 40, "Firefox 4", new string[] { "Firefox/4" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX3 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 30, "Firefox 3", new string[] { "Firefox/3" }, new string[] {"ggpht.com"}, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX2 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 20, "Firefox 2", new string[] { "Firefox/2" }, new string[]{"WordPress.com mShots"}, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser FIREFOX1_5 = new Browser(		Manufacturer.MOZILLA, Browser.FIREFOX, 15, "Firefox 1.5", new string[] { "Firefox/1.5" }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null );  // using Gecko Engine
		
	    /*
	     * Thunderbird email client, based on the same Gecko engine Firefox is using.
	     */
	    public static readonly Browser THUNDERBIRD = new Browser( 	Manufacturer.MOZILLA, null, 110, "Thunderbird", new string[] { "Thunderbird" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, "Thunderbird/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)" );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD12 = new Browser(  Manufacturer.MOZILLA, Browser.THUNDERBIRD, 185, "Thunderbird 12", new string[] { "Thunderbird/12" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD11 = new Browser(  Manufacturer.MOZILLA, Browser.THUNDERBIRD, 184, "Thunderbird 11", new string[] { "Thunderbird/11" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD10 = new Browser(  Manufacturer.MOZILLA, Browser.THUNDERBIRD, 183, "Thunderbird 10", new string[] { "Thunderbird/10" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD8 = new Browser(  	Manufacturer.MOZILLA, Browser.THUNDERBIRD, 180, "Thunderbird 8", new string[] { "Thunderbird/8" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD7 = new Browser(  	Manufacturer.MOZILLA, Browser.THUNDERBIRD, 170, "Thunderbird 7", new string[] { "Thunderbird/7" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD6 = new Browser(  	Manufacturer.MOZILLA, Browser.THUNDERBIRD, 160, "Thunderbird 6", new string[] { "Thunderbird/6" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD3 = new Browser(  	Manufacturer.MOZILLA, Browser.THUNDERBIRD, 130, "Thunderbird 3", new string[] { "Thunderbird/3" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine
		    public static readonly Browser THUNDERBIRD2 = new Browser(  	Manufacturer.MOZILLA, Browser.THUNDERBIRD, 120, "Thunderbird 2", new string[] { "Thunderbird/2" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null );  // using Gecko Engine	
	
	    public static readonly Browser VIVALDI = new Browser(       Manufacturer.OTHER, null, 108338, "Vivaldi", new string[] { "Vivaldi" }, new string[] {}, BrowserType.WEB_BROWSER, RenderingEngine.BLINK, "Vivaldi/(([\\d]+).([\\d]+).([\\d]+).([\\d]+))");
		
	    public static readonly Browser SEAMONKEY = new Browser(		Manufacturer.OTHER, null, 15, "SeaMonkey", new string[]{"SeaMonkey"}, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "SeaMonkey/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"); // using Gecko Engine
	
	    public static readonly Browser MOZILLA = new Browser(		Manufacturer.MOZILLA, null, 1, "Mozilla", new string[] { "Mozilla", "Moozilla" }, new string[] {"ggpht.com"}, BrowserType.WEB_BROWSER, RenderingEngine.OTHER, null); // rest of the mozilla browsers
	
	    public static readonly Browser CFNETWORK = new Browser(		Manufacturer.OTHER, null, 6, "CFNetwork", new string[] { "CFNetwork" }, null, BrowserType.UNKNOWN, RenderingEngine.OTHER, "CFNetwork/(([\\d]+)(?:\\.([\\d]))?(?:\\.([\\d]+))?)" ); // Mac OS X cocoa library
	
	    public static readonly Browser EUDORA = new Browser(			Manufacturer.OTHER, null, 7, "Eudora", new string[] { "Eudora", "EUDORA" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null );  // email client by Qualcomm
	
	    public static readonly Browser POCOMAIL = new Browser(		Manufacturer.OTHER, null, 8, "PocoMail", new string[] { "PocoMail" }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null );
	
	    public static readonly Browser THEBAT = new Browser(			Manufacturer.OTHER, null, 9, "The Bat!", new string[]{"The Bat"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null); // Email Client
	
	    public static readonly Browser NETFRONT = new Browser(		Manufacturer.OTHER, null, 10, "NetFront", new string[]{"NetFront"}, null, BrowserType.MOBILE_BROWSER, RenderingEngine.OTHER, null); // mobile device browser
	
	    public static readonly Browser EVOLUTION = new Browser(		Manufacturer.OTHER, null, 11, "Evolution", new string[]{"CamelHttpStream"}, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null); // http://www.go-evolution.org/Camel.Stream
    
	    public static readonly Browser LYNX = new Browser(			Manufacturer.OTHER, null, 13, "Lynx", new string[]{"Lynx"}, null, BrowserType.TEXT_BROWSER, RenderingEngine.OTHER, "Lynx/(([0-9]+)\\.([\\d]+)\\.?([\\w-+]+)?\\.?([\\w-+]+)?)");
    
	    public static readonly Browser DOWNLOAD = new Browser(   	Manufacturer.OTHER, null, 16, "Downloading Tool", new string[]{"cURL","wget", "ggpht.com", "Apache-HttpClient"}, null, BrowserType.TOOL, RenderingEngine.OTHER, null);
    
	    public static readonly Browser UNKNOWN = new Browser(		Manufacturer.OTHER, null, 14, "Unknown", new string[0], null, BrowserType.UNKNOWN, RenderingEngine.OTHER, null );


        private static class TopLevelBrowsers
        {
            public static List<Browser> browsers = new List<Browser>();
        }
	
	    /*
	     * An id for each browser version which is unique per manufacturer. 
	     */
	    private readonly short id;
	    private readonly string name;
	    private readonly string[] aliases;
	    private readonly string[] excludeList; // don't match when these values are in the agent-string
	    private readonly BrowserType browserType;
	    private readonly Manufacturer manufacturer;
	    private readonly RenderingEngine renderingEngine;
	    private readonly Browser parent;
	    private IList<Browser> children;
	    private Regex versionRegEx;
	
	    private Browser(Manufacturer manufacturer, Browser parent, int versionId, string name, string[] aliases, string[] exclude, BrowserType browserType, RenderingEngine renderingEngine, string versionRegexString) {
		    this.id =  (short) ( ( manufacturer.getId() << 10) + (short) versionId);
		    this.name = name;
		    this.parent = parent;
		    this.children = new List<Browser>();
		    this.aliases = aliases.Select(a=>a.ToLower()).ToArray();
		    this.excludeList = exclude == null ? new string[0] : exclude.Select(e=>e.ToLower()).ToArray();
		    this.browserType = browserType;
		    this.manufacturer = manufacturer;
		    this.renderingEngine = renderingEngine;
		    if (versionRegexString != null)
			    this.versionRegEx = new Regex(versionRegexString,RegexOptions.Compiled | RegexOptions.IgnoreCase);
		    if (this.parent == null) 
			    addTopLevelBrowser(this);
		    else 
			    addChildBrowser(this.parent,this);
	    }
        private static void addChildBrowser(Browser browser, Browser child)
        {
            browser.children.Add(child);
        }

        // create collection of top level browsers during initialization
        private static void addTopLevelBrowser(Browser browser) {
		    TopLevelBrowsers.browsers.Add(browser);
	    }
	
	    public short getId() {
		    return id;
	    }

	    public string getName() {
		    return name;
	    }
	
	    private Regex getVersionRegEx() {
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
	    public Version getVersion(string userAgentString) {
		    Regex pattern = this.getVersionRegEx();
		    if (userAgentString != null && pattern != null) {
			    Match matcher = pattern.Match(userAgentString);
			    if (matcher.Success) {
				    string fullVersionString = matcher.Groups[1].Value;
				    string majorVersion = matcher.Groups[2].Value;
				    string minorVersion = "0";
				    if (matcher.Groups.Count > 3) // usually but not always there is a minor version
					    minorVersion = matcher.Groups[3].Value;
				    return new Version (fullVersionString,majorVersion,minorVersion);
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

        public bool isInUserAgentString(string agentString)
        {
            if (agentString == null)
                return false;

            string agentStringLowerCase = agentString.ToLower();
            return isInUserAgentLowercaseString(agentStringLowerCase);
        }

        private bool isInUserAgentLowercaseString(string agentStringLowerCase) {
            return aliases.Any(agentStringLowerCase.Contains);
	    }
	
	    private Browser checkUserAgentLowercase(string agentLowercaseString) {
		    if (this.isInUserAgentLowercaseString(agentLowercaseString)) {
			
			    if (this.children.Count > 0) {
				    foreach (Browser childBrowser in this.children) {
					    Browser match = childBrowser.checkUserAgentLowercase(agentLowercaseString);
					    if (match != null) { 
						    return match;
					    }
				    }
			    }
			
			    // if children didn't match we continue checking the current to prevent false positives
			    if (!excludeList.Any(agentLowercaseString.Contains)) {
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
	    public static Browser parseUserAgentString(string agentString)
	    {
		    return parseUserAgentString(agentString, TopLevelBrowsers.browsers);
	    }

        public static Browser parseUserAgentLowercaseString(string agentString)
        {
            if (agentString == null)
            {
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
	    public static Browser parseUserAgentString(string agentString, List<Browser> browsers) {
            if (agentString != null) {
                String agentLowercaseString = agentString.ToLower();
                return parseUserAgentLowercaseString(agentLowercaseString, browsers);
            }
            return Browser.UNKNOWN;
        }

        private static Browser parseUserAgentLowercaseString(String agentLowercaseString, List<Browser> browsers) {
            foreach (Browser browser in browsers)
            {
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
            foreach (Browser browser in Browser.values())
            {
                if (browser.getId() == id)
                    return browser;
            }
		
            // same behavior as standard valueOf(string) method
            throw new ArgumentException(
                    "No enum const for id " + id);
        }

        #region .NET specific valueOf(key)
        /**
         * Returns the enum constant of this type with the specified key.
         * Throws IllegalArgumentException if the value does not exist.
         * @param key
         * @return 
         */
        public static Browser valueOf(string key)
        {
            var ret = _values.FirstOrDefault(x => x.Key == key);
            if (ret == null)
                throw new ArgumentException("No item found with that key.");
            return ret;
        }

        /*
         * Override ToString, return the Key property (the static property name, set by the static constructor). This matches the behavior of the Java enum type. 
         */
        public override string ToString()
        {
            return Key;
        }

        /*
         * The name of the property this instance is assigned to. Set by the static constructor and used in ToString.
         */
        private string Key;
        #endregion .NET specific valueOf(key)
    }
}