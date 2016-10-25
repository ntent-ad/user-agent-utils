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

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

import org.junit.Test;

/**
 * @author harald
 *
 */
public class BrowserTest {

	String[] ie6clients = new String[] {
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; T312461)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; XMPP Tiscali Communicator v.10.0.2; .NET CLR 2.0.50727)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
	};

	String[] ie7clients = new String[] {
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)",
			"Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0 ; .NET CLR 2.0.50215; SL Commerce Client v1.0; Tablet PC 2.0",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506)" // Windows Mail on Vista
	};

	String[] ie8clients = new String[] {
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; Media Center PC 5.0; .NET CLR 1.1.4322)",
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Win64; x64; .NET CLR 2.0.50727; SLCC1; Media Center PC 5.0; .NET CLR 3.0.04506)"
	};

	String[] ie9clients = new String[] {
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; InfoPath.3; MS-RTC LM 8; .NET4.0C; .NET4.0E)",
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0"
	};

	String[] ie10clients = new String[] {
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0"
	};

	String[] ie11clients = new String[] {
			"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko",
			"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; ASU2JS; rv:11.0) like Gecko", // 64bit Win8
	};

	String[] edgeClients = new String[] {
			"Mozilla/5.0 (Windows NT 6.4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36 Edge/12.0",
			"Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240",
			"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10532"
	};

	String[] edgeMobileClients = new String[] {
			"Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; DEVICE INFO) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.0"
	};

	String[] ie55clients = new String[] {
			"Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; .NET CLR 1.1.4322)",
			"Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)",
			"Mozilla/4.0 (compatible; MSIE 5.5; Windows 95)"
	};

	String[] ieTooOld = new String[] {
			"Mozilla/4.0 (compatible; MSIE 4.01; Windows 95)",
			"Mozilla/4.0 (compatible; MSIE 4.0; Windows 95; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
			"Mozilla/2.0 (compatible; MSIE 3.03; Windows 3.1)"
	};

	String[] outlook2007 = new String[] {
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; .NET CLR 1.1.4322; MSOffice 12)"
	};

	String[] outlook2010 = new String[] {
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; GTB6.4; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 1.1.4322; .NET CLR 3.5.30729; .NET CLR 3.0.30729; OfficeLiveConnector.1.3; OfficeLivePatch.0.0; MSOffice 14)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; Media Center PC 6.0; SLCC2; ms-office; MSOffice 14)"
	};

	String[] outlook2013 = new String[] {
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Win64; x64; Trident/6.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.3; Tablet PC 2.0; Microsoft Outlook 15.0.4481; ms-office; MSOffice 15)"
	};

	String[] outookExpress = new String[] {
			"Outlook-Express/7.0 (MSIE 6.0; Windows NT 5.1; SV1; SIMBAR={xxx}; .NET CLR 2.0.50727; .NET CLR 1.1.4322; TmstmpExt)",
			"Outlook-Express/7.0 (MSIE 7.0; Windows NT 5.1; InfoPath.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; TmstmpExt)"
	};

	String[] ieMobile6 = new String[] {
			"HTC_TyTN Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.12)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.12) Vodafone/1.0/HTC_s710/1.22.172.3",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.8) PPC; 240x320; HTC_TyTN/1.0 Profile/MIDP-2.0 Configuration/CLDC-1.1"
	};

	String[] ieMobile7 = new String[] {
			"HTC_TouchDual Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6)",
			"PPC; 240x320; HTC_P3450/1.0 Profile/MIDP-2.0 Configuration/CLDC-1.1 Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6) PPC; MDA Vario/3.0 Profile/MIDP-2.0 Configuration/CLDC-1.1",
			"Palm750/v0005 Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6) UP.Link/6.3.0.0.0"
	};

	String[] ieMobile9 = new String[] {
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)"
	};

	String[] ieMobile10 = new String[] {
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 920)"
	};

	String[] ieMobile11 = {
			"Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; HTC; HTC6990LVW) like Gecko"
	};

	String[] ie7Rss = new String[] {
			"Windows-RSS-Platform/1.0 (MSIE 7.0; Windows NT 5.1)",
			"Windows-RSS-Platform/1.0 (MSIE 7.0; Windows NT 6.0)",
			"Windows-RSS-Platform/1.0 (MSIE 7.0; Windows NT 6.1)"
	};

	String[] ie8Rss = new String[] {
			"Windows-RSS-Platform/2.0 (MSIE 8.0; Windows NT 6.0)",
	};

	String[] ie9Rss = new String[] {
			"Windows-RSS-Platform/2.0 (MSIE 9.0; Windows NT 6.0)"
	};

	String[] ie10Rss = new String[] {
			"Windows-RSS-Platform/2.0 (MSIE 10.0; Windows NT 6.0)"
	};

	String[] ie11Rss = new String[] {
			"Windows-RSS-Platform/2.0 (IE 11.0; Windows NT 6.1)"
	};

	String[] lotusNotes = new String[] {
			"Mozilla/4.0 (compatible; Lotus-Notes/5.0; Windows-NT)",
			"Mozilla/4.0 (compatible; Lotus-Notes/6.0; Windows-NT)"
	};

	String[] lynxClient = new String[] {
			"Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 OpenSSL/0.9.7d",
			"Lynx/2.7.1ac-0.102+intl+csuite libwww-FM/2.14"
	};

	String[] konqueror = new String[] {
			"Mozilla/5.0 (compatible; konqueror/3.3; linux 2.4.21-243-smp4G) (KHTML, like Geko)",
			"Mozilla/6.0 (compatible; Konqueror/4.2; i686 FreeBSD 6.4; 20060308)",
			"Mozilla/5.0 (compatible; Konqueror/3.1; Linux 2.4.21-20.0.1.ELsmp; X11; i686; , en_US, en, de)"
	};

	String[] chromeMobile = new String[] {
			"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7",
			"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7",
			"Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) CriOS/40.0.2214.73 Mobile/11D167 Safari/9537.53",
			"Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7",
			"Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; en) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3"
	};

	String[] chromeMobile31 = new String[] {
			"Mozilla/5.0 (Linux; Android 4.1.2; LT22i Build/6.2.A.1.100) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36"
	};

	String[] chrome = new String[] {
			"Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/532.9 (KHTML, like Gecko) Chrome/5.0.310.0 Safari/532.9",
			"Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/532.9 (KHTML, like Gecko) Chrome/5.0.309.0 Safari/532.9"
	};

	String[] chrome8 = new String[] {
			"Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.558.0 Safari/534.10",
			"Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML, like Gecko) Ubuntu/10.10 Chrome/8.1.0.0 Safari/540.0"
	};

	String[] chrome9 = new String[] {
			"Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML,like Gecko) Chrome/9.1.0.0 Safari/540.0",
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.14 (KHTML, like Gecko) Chrome/9.0.600.0 Safari/534.14"
	};

	String[] chrome10 = new String[] {
			"Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.15 (KHTML, like Gecko) Ubuntu/10.10 Chromium/10.0.613.0 Chrome/10.0.613.0 Safari/534.15"
	};

	String[] chrome11 = new String[] {
			"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.697.0 Safari/534.24"
	};

	String[] chrome12 = new String[] {
			"Mozilla/5.0 (X11; CrOS i686 12.0.742.91) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.93 Safari/534.30"
	};

	String[] chrome13 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_7) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1"
	};

	String[] chrome14 = new String[] {
			"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.815.0 Safari/535.1"
	};

	String[] chrome29 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
	};

	String[] chrome31 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36"
	};

	String[] chrome32 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.77 Safari/537.36",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36"
	};

	String[] chrome36 = new String[] {
			"	Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36"
	};

	String[] chrome33 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36"
	};

	String[] chrome39 = new String[] {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
	};

	String[] firefox3 = new String[] {
			"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.14) Gecko/2009090216 Ubuntu/9.04 (jaunty) Firefox/3.0.14"
	};

	String[] firefox4 = new String[] {
			"Mozilla/5.0 (X11; Linux x86_64; rv:2.0b4) Gecko/20100818 Firefox/4.0b4",
			"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b9pre) Gecko/20101228 Firefox/4.0b9pre"
	};

	String[] firefox5 = new String[] {
			"Mozilla/5.0 (Windows NT 6.1; U; ru; rv:5.0.1.6) Gecko/20110501 Firefox/5.0.1 Firefox/5.0.1",
			"Mozilla/5.0 (X11; U; Linux i586; de; rv:5.0) Gecko/20100101 Firefox/5.0"
	};

	String[] firefox6 = new String[] {
			"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0a2) Gecko/20110612 Firefox/6.0a2"
	};

	String[] firefox19 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:19.0) Gecko/20100101 Firefox/19.0"
	};

	String[] firefox20 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:20.0) Gecko/20100101 Firefox/20.0"
	};

	String[] firefox25 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:25.0) Gecko/20100101 Firefox/25.0"
	};

	String[] firefox28 = new String[] {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:28.0) Gecko/20100101 Firefox/28.0"
	};

	String[] firefox36 = new String[] {
			"Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0"
	};

	String[] firefox3mobile = {
			"Mozilla/5.0 (X11; U; Linux armv7l; en-US; rv:1.9.2a1pre) Gecko/20091127 Firefox/3.5 Maemo Browser 1.5.6 RX-51 N900"
	};

	String[] firefoxMobile = {
			"Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/0.0"
	};

	String[] firefoxMobile13 = {
			"Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0"
	};

	String[] firefoxMobile23 = {
			"Mozilla/5.0 (Android; Mobile; rv:23.0) Gecko/23.0 Firefox/23.0"
	};

	String[] safari = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/525.28.3 (KHTML, like Gecko) Version/3.2.3 Safari/525.28.3",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-gb) AppleWebKit/523.10.6 (KHTML, like Gecko) Version/3.0.4 Safari/523.10.6"
	};

	String[] safari8 = {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10) AppleWebKit/600.1.25 (KHTML, like Gecko) Version/8.0 Safari/600.1.25"
	};

	String[] safari7 = {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9) AppleWebKit/537.71 (KHTML, like Gecko) Version/7.0 Safari/537.71"
	};

	String[] safari6 = {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/536.28.10 (KHTML, like Gecko) Version/6.0.3 Safari/536.28.10"
	};

	String[] safari5 = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-us) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16",
			"Mozilla/5.0 (Windows; U; Windows NT 6.1; ja-JP) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_5; en-us) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; da-dk) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1"
	};

	String[] safari4 = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_2; en-us) AppleWebKit/531.21.8 (KHTML, like Gecko) Version/4.0.4 Safari/531.21.10",
			"Mozilla/5.0 (Windows; U; Windows NT 6.1; es-ES) AppleWebKit/531.22.7 (KHTML, like Gecko) Version/4.0.5 Safari/531.22.7",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/4.0.1 Safari/530.18"
	};

	String[] mobileSafari = {
			"Mozilla/5.0 (iPod; U; CPU iPhone OS 2_0 like Mac OS X; de-de) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5A347 Safari/525.20", // Mobile
			// Safari
			// 3.1.1
			"Mozilla/5.0 (iPod; U; CPU like Mac OS X; en) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/3A101a Safari/419.3" // Mobile Safari 3.0
	};

	String[] mobileSafari4 = {
			"Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B367 Safari/531.21.10",
			"Mozilla/5.0 (iPod; U; CPU iPhone OS 4_1 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7"
	};

	String[] mobileSafari5 = {
			"Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3",
			"Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3"
	};

	String[] mobileSafari6 = {
			"Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B350 Safari/8536.25"
	};

	String[] dolfin = {
			"Mozilla/5.0 (SAMSUNG; SAMSUNG-GT-S8500/S8500NEJE5; U; Bada/1.0; fr-fr) AppleWebKit/533.1 (KHTML, like Gecko) Dolfin/2.0 Mobile WVGA SMM-MMS/1.2.0 NexPlayer/3.0 profile/MIDP-2.1 configuration/CLDC-1.1 OPN-B"
	};

	// similar to Safari, but doesn't mention Safari in the user-agent string
	String[] appleMail = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-us) AppleWebKit/533.18.1 (KHTML, like Gecko)"
	};

	String[] omniWeb = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/531.9+(KHTML, like Gecko, Safari/528.16) OmniWeb/v622.10.0",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US) AppleWebKit/525.18 (KHTML, like Gecko, Safari/525.20) OmniWeb/v622.3.0.105198"
	};

	String[] opera = {
			"Opera/8.0 (Macintosh; PPC Mac OS X; U; en)",
	};

	String[] opera9 = {
			"Opera/9.52 (Windows NT 5.1; U; en)",
			"Opera/9.20 (Macintosh; Intel Mac OS X; U; en)"
	};

	String[] opera10 = {
			"Opera/9.80 (Windows NT 5.2; U; en) Presto/2.2.15 Version/10.10",
			"Opera/9.80 (Macintosh; Intel Mac OS X; U; en) Presto/2.6.30 Version/10.61"
	};

	String[] opera11 = {
			"Opera/9.80 (Windows NT 6.1; WOW64; U; pt) Presto/2.10.229 Version/11.62",
			"Opera/9.80 (Windows NT 6.0; U; pl) Presto/2.10.229 Version/11.62"
	};

	String[] opera12 = {
			"Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00",
			"Opera/12.0(Windows NT 5.1;U;en)Presto/22.9.168 Version/12.00"
	};

	String[] opera15 = {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.52 Safari/537.36 OPR/15.0.1147.100"
	};

	String[] opera16 = {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36 OPR/16.0.1196.73"
	};

	String[] opera17 = {
			"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36 OPR/17.0.1241.53"
	};

	String[] opera18 = {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36 OPR/18.0.1284.63",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 OPR/18.0.1284.68",
			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36 OPR/18.0.1284.49",

	};

	String[] opera19 = {
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.76 Safari/537.36 OPR/19.0.1326.39 (Edition Next)",
			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.76 Safari/537.36 OPR/19.0.1326.56",
			"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36 OPR/19.0.1326.59",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36 OPR/19.0.1326.63",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36 OPR/19.0.1326.63"
	};

	String[] opera20 = {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.91 Safari/537.36 OPR/20.0.1387.37 (Edition Next)",
			"Mozilla/5.0 (Windows NT 6.3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.46 Safari/537.36 OPR/20.0.1387.16 (Edition Developer)"
	};

	String[] opera23 = {
			"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36 OPR/23.0.1522.60"
	};

	String[] opera24 = {
			"	Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36 OPR/24.0.1558.51 (Edition Next)"
	};

	String[] opera27 = {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36 OPR/27.0.1689.76"
	};

	String[] opera31 = {
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36 OPR/31.0.1889.174",
			"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36 OPR/31.0.1889.174",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36 OPR/31.0.1889.174"
	};

	String[] operaMini = {
			"Opera/9.60 (J2ME/MIDP; Opera Mini/4.2.13337/458; U; en) Presto/2.2.0",
			"Opera/9.80 (J2ME/MIDP; Opera Mini/5.0.16823/1428; U; en) Presto/2.2.0",
			"Opera/9.80 (J2ME/MIDP; Opera Mini/9 (Compatible; MSIE:9.0; iPhone; BlackBerry9700; AppleWebKit/24.746; U; en) Presto/2.5.25 Version/10.54"
	};

	String[] operaMobi = {
			"Opera (Android 2.2; Opera Mobi/-2118645896; U; pl) Presto/2.7.60 Version/13.1"
	};

	String[] operaMobi10 = {
			"Opera/9.80 (S60; SymbOS; Opera Mobi/498; U; sv) Presto/2.4.18 Version/10.00"
	};

	String[] operaMobi11 = {
			"Opera/9.80 (S60; SymbOS; Opera Mobi/SYB-1103211396; U; es-LA) Presto/2.7.81 Version/11.00",
			"Opera/9.80 (Android 2.3.3; Linux; Opera Mobi/ADR-1111101157; U; es-ES) Presto/2.9.201 Version/11.50"
	};

	String[] operaMobi12 = {
			"Opera/12.02 (Android 4.1; Linux; Opera Mobi/ADR-1111101157; U; en-US) Presto/2.9.201 Version/12.02"
	};

	String[] operaCoast = {
			"Mozilla/5.0 (iPad; CPU OS 6_1_3 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Coast/1.0.2.62956 Mobile/10B329 Safari/7534.48.3 ",
			"Mozilla/5.0 (iPad; CPU OS 7_0_2 like Mac OS X) AppleWebKit/537.51.1 (KHTML like Gecko) Coast/1.1.2.64598 Mobile/11B511 Safari/7534.48.3"
	};

	String[] operaMobile = {
			"Mozilla/5.0 (Linux; Android 2.3.4; MT11i Build/4.0.2.A.0.62) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.123 Mobile Safari/537.22 OPR/14.0.1025.52315",
			"Mozilla/5.0 (Linux; Android 4.1.2; HTC One SV Build/JZO54K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.81 Mobile Safari/537.36 OPR/28.0.1764.90386"
	};

	String[] camino2 = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en; rv:1.9.0.19) Gecko/2010111021 Camino/2.0.6 (MultiLang) (like Firefox/3.0.19)",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en; rv:1.9.0.18) Gecko/2010021619 Camino/2.0.2 (like Firefox/3.0.18)"
	};

	String[] camino = {
			"Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; it; rv:1.8.1.21) Gecko/20090327 Camino/1.6.7 (MultiLang) (like Firefox/2.0.0.21pre)",
			"Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.8.0.4) Gecko/20060613 Camino/1.0.2"
	};

	String[] flock = {
			"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008100716 Firefox/3.0.3 Flock/2.0"
	};

	String[] seaMonkey = {
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.13) Gecko/20100914 Mnenhy/0.8.3 SeaMonkey/2.0.8"
	};

	String[] bots = {
			"Mozilla/5.0 (compatible; Googlebot/2.1; http://www.google.com/bot.html)",
			"Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
			"Googlebot-Image/1.0",
			"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.8 (KHTML, like Gecko; Google Web Preview) Chrome/19.0.1084.36 Safari/536.8",
			"Mediapartners-Google",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5 (Applebot/0.1)",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko) BingPreview/1.0b",
			"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0; BingPreview/1.0b) like Gecko",
			"Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53 BingPreview/1.0b",
			"Version/7.0 Mobile/11A465 Safari/9537.53 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
			"Mozilla/5.0 (iPad; CPU OS 5_0 like Mac OS X) AppleWebKit/537.36 (KHTML, like Gecko; Google Web Preview) Version/5.1 Mobile/9A334 Safari/7534.48.3",
			"Mozilla/5.0 (X11; Linux x86_64; rv:10.0.12) Gecko/20100101 Firefox/21.0 WordPress.com mShots",
			"Mozilla/5.0 (compatible; Seznam screenshot-generator 2.1; +http://fulltext.sblog.cz/screenshot/)",
			"facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)",
			"Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Exabot-Thumbnails)",
			"Mozilla/5.0 (compatible; YandexMarket/1.0; +http://yandex.com/bots)",
			"Mozilla/5.0 (compatible; Ask Jeeves/Teoma; +http://about.ask.com/en/docs/about/webmasters.shtml)",
			"ThumbSniper (http://thumbsniper.com)",
			"Phantom.js bot",

			// http://www.useragentstring.com/pages/Crawlerlist/
			"Mozilla/5.0 (compatible; 008/0.83; http://www.80legs.com/webcrawler.html) Gecko/2008032620",
			"ABACHOBot",
			"Accoona-AI-Agent/1.1.2 (aicrawler at accoonabot dot com)",
			"Accoona-AI-Agent/1.1.2",
			"Accoona-AI-Agent/1.1.1 (crawler at accoona dot com)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0) AddSugarSpiderBot www.idealobserver.com",
			"Mozilla/5.0 (compatible; AnyApexBot/1.0; +http://www.anyapex.com/bot.html)",
			"Mozilla/4.0 (compatible; Arachmo)",
			"Mozilla/4.0 (compatible; B-l-i-t-z-B-O-T)",
			"Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)",
			"Baiduspider+(+http://www.baidu.com/search/spider_jp.html)",
			"Baiduspider+(+http://www.baidu.com/search/spider.htm)",
			"BaiDuSpider",
			"Mozilla/5.0 (compatible; BecomeBot/3.0; MSIE 6.0 compatible; +http://www.become.com/site_owners.html)",
			"Mozilla/5.0 (compatible; BecomeBot/2.3; MSIE 6.0 compatible; +http://www.become.com/site_owners.html)",
			"Mozilla/5.0 (compatible; BeslistBot; nl; BeslistBot 1.0; http://www.beslist.nl/",
			"BillyBobBot/1.0 (+http://www.billybobbot.com/crawler/)",
			"Bimbot/1.0",
			"Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
			"Mozilla/5.0 (compatible; bingbot/2.0 +http://www.bing.com/bingbot.htm)",
			"Mozilla/4.0 (compatible; BlitzBot)",
			"BlitzBOT@tricus.net (Mozilla compatible)",
			"BlitzBOT@tricus.com (Mozilla compatible)",
			"boitho.com-dc/0.85 ( http://www.boitho.com/dcbot.html )",
			"boitho.com-dc/0.83 ( http://www.boitho.com/dcbot.html )",
			"boitho.com-dc/0.82 ( http://www.boitho.com/dcbot.html )",
			"boitho.com-dc/0.81 ( http://www.boitho.com/dcbot.html )",
			"boitho.com-dc/0.79 ( http://www.boitho.com/dcbot.html )",
			"boitho.com-robot/1.1",
			"boitho.com-robot/1.0",
			"btbot/0.4 (+http://www.btbot.com/btbot.html)",
			"CatchBot/2.0; +http://www.catchbot.com",
			"CatchBot/1.0; +http://www.catchbot.com",
			"CatchBot/1.0; http://www.catchbot.com",
			"Mozilla/4.0 (compatible; Cerberian Drtrs Version-3.2-Build-1)",
			"Mozilla/4.0 (compatible; Cerberian Drtrs Version-3.2-Build-0)",
			"Mozilla/5.0 (compatible; Charlotte/1.1; http://www.searchme.com/support/)",
			"Mozilla/5.0 (compatible; Charlotte/1.0t; http://www.searchme.com/support/)",
			"Mozilla/5.0 (compatible; Charlotte/1.0b; http://www.searchme.com/support/)",
			"Mozilla/5.0 (compatible; Charlotte/1.0b; http://www.betaspider.com/)",
			"Mozilla/5.0 (X11; U; Linux i686 (x86_64); en-US; rv:1.8.1.11) Gecko/20080109 (Charlotte/0.9t; http://www.searchme.com/support/) (Charlotte/0.9t; http://www.searchme.com/support/)",
			"Mozilla/5.0 (X11; U; Linux i686 (x86_64); en-US; rv:1.8.1.11) Gecko/20080109 (Charlotte/0.9t; http://www.searchme.com/support/)",
			"Mozilla/5.0 (compatible; Charlotte/0.9t; http://www.searchme.com/support/)",
			"Mozilla/5.0 (compatible; Charlotte/0.9t; +http://www.searchme.com/support/)",
			"ConveraCrawler/0.9e (+http://ews.converasearch.com/crawl.htm)",
			"ConveraCrawler/0.9d (+http://www.authoritativeweb.com/crawl)",
			"ConveraCrawler/0.9d ( http://www.authoritativeweb.com/crawl)",
			"ConveraCrawler/0.9 (+http://www.authoritativeweb.com/crawl)",
			"cosmos/0.9_(robot@xyleme.com)",
			"Covario-IDS/1.0 (Covario; http://www.covario.com/ids; support at covario dot com)",
			"DataparkSearch/4.37-23012006 ( http://www.dataparksearch.org/)",
			"DataparkSearch/4.36 ( http://www.dataparksearch.org/)",
			"DataparkSearch/4.35-02122005 ( http://www.dataparksearch.org/)",
			"DataparkSearch/4.35 ( http://www.dataparksearch.org/)",
			"DiamondBot",
			"Mozilla/5.0 (compatible; discobot/1.0; +http://discoveryengine.com/discobot.html)",
			"Mozilla/5.0 (compatible; DotBot/1.1; http://www.dotnetdotcom.org/, crawler@dotnetdotcom.org)",
			"DotBot/1.0.1 (http://www.dotnetdotcom.org/#info, crawler@dotnetdotcom.org)",
			"EmeraldShield.com WebBot (http://www.emeraldshield.com/webbot.aspx)",
			"envolk[ITS]spider/1.6 (+http://www.envolk.com/envolkspider.html)",
			"envolk[ITS]spider/1.6 ( http://www.envolk.com/envolkspider.html)",
			"EsperanzaBot(+http://www.esperanza.to/bot/)",
			"Exabot/2.0",
			"FAST Enterprise Crawler 6 used by Schibsted (webcrawl@schibstedsok.no)",
			"FAST Enterprise Crawler 6 / Scirus scirus-crawler@fast.no; http://www.scirus.com/srsapp/contactus/",
			"FAST Enteprise Crawler/6 (www dot fastsearch dot com)",
			"FAST-WebCrawler/3.8 (atw-crawler at fast dot no; http://fast.no/support/crawler.asp)",
			"FAST-WebCrawler/3.7/FirstPage (atw-crawler at fast dot no;http://fast.no/support/crawler.asp)",
			"FAST-WebCrawler/3.7 (atw-crawler at fast dot no; http://fast.no/support/crawler.asp)",
			"FAST-WebCrawler/3.6/FirstPage (atw-crawler at fast dot no;http://fast.no/support/crawler.asp)",
			"FAST-WebCrawler/3.6 (atw-crawler at fast dot no; http://fast.no/support/crawler.asp)",
			"FAST-WebCrawler/3.6",
			"FAST-WebCrawler/3.x Multimedia (mm dash crawler at fast dot no)",
			"FAST-WebCrawler/3.x Multimedia",
			"Mozilla/4.0 (compatible: FDSE robot)",
			"findlinks/2.0.1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.6-beta6 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.6-beta4 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.6-beta1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.5-beta7 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.4-beta1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta9 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta8 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta6 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta4 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta2 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.3-beta1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.2-a5 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.1-a5 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.1-a1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1.1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a9 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a8 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a8 ( http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a7 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a5 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a4 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1-a3 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.1 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.06 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.0.9 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.0.8 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"findlinks/1.0 (+http://wortschatz.uni-leipzig.de/findlinks/)",
			"Mozilla/4.0 compatible FurlBot/Furl Search 2.0 (FurlBot; http://www.furl.net; wn.furlbot@looksmart.net)",
			"FyberSpider (+http://www.fybersearch.com/fyberspider.php)",
			"FyberSpider",
			"g2Crawler nobody@airmail.net",
			"Gaisbot/3.0+(robot06@gais.cs.ccu.edu.tw;+http://gais.cs.ccu.edu.tw/robot.php)",
			"Gaisbot/3.0+(robot05@gais.cs.ccu.edu.tw;+http://gais.cs.ccu.edu.tw/robot.php)",
			"Gaisbot/3.0 (jerry_wu@openfind.com.tw; http://gais.cs.ccu.edu.tw/robot.php)",
			"GalaxyBot/1.0 (http://www.galaxy.com/galaxybot.html)",
			"genieBot (http://64.5.245.11/faq/faq.html)",
			"genieBot ((http://64.5.245.11/faq/faq.html))",
			"Gigabot/3.0 (http://www.gigablast.com/spider.html)",
			"Gigabot/2.0/gigablast.com/spider.html",
			"Gigabot/2.0 (http://www.gigablast.com/spider.html)",
			"Gigabot/2.0",
			"Gigabot/1.0",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; Girafabot [girafa.com])",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 4.0; Girafabot; girafabot at girafa dot com; http://www.girafa.com)",
			"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; Girafabot; girafabot at girafa dot com; http://www.girafa.com)",
			"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
			"Googlebot/2.1 (+http://www.googlebot.com/bot.html)",
			"Googlebot/2.1 (+http://www.google.com/bot.html)",
			"Googlebot-Image/1.0",
			"Mozilla/5.0 GurujiBot/1.0 (+http://www.guruji.com/en/WebmasterFAQ.html)",
			"Mozilla/5.0 GurujiBot/1.0 ( http://www.guruji.com/en/WebmasterFAQ.html)",
			"Mozilla/5.0 (compatible; GurujiBot/1.0; +http://www.guruji.com/en/WebmasterFAQ.html)",
			"GurujiBot/1.0 (+http://www.guruji.com/WebmasterFAQ.html)",
			"GurujiBot/1.0 (+http://www.guruji.com/en/WebmasterFAQ.html)",
			"HappyFunBot/1.1 ( http://www.happyfunsearch.com/bot.html)",
			"hl_ftien_spider_v1.1",
			"hl_ftien_spider",
			"holmes/3.9 (someurl.co.cc)",
			"holmes/3.12.4 (http://morfeo.centrum.cz/bot)",
			"holmes/3.12.3 (http://morfeo.centrum.cz/bot)",
			"holmes/3.12.2 (http://morfeo.centrum.cz/bot)",
			"holmes/3.12.1 (http://morfeo.centrum.cz/bot)",
			"htdig/3.1.6 (unconfigured@htdig.searchengine.maintainer)",
			"htdig/3.1.6 (mathieu.peltier@inrialpes.fr)",
			"htdig/3.1.5 (webmaster@online-medien.de)",
			"htdig/3.1.5 (root@localhost)",
			"htdig/3.1.5 (infosys@storm.rmi.org)",
			"htdig/3.1.5",
			"iaskspider/2.0(+http://iask.com/help/help_index.html)",
			"iaskspider",
			"ia_archiver/8.9 (Windows NT 3.1; en-US;)",
			"ia_archiver/8.9 (Windows 3.9; en-US;)",
			"ia_archiver/8.9 (Linux 1.0; en-US;)",
			"ia_archiver/8.8 (Windows XP 7.2; en-US;)",
			"ia_archiver/8.8 (Windows XP 3.0; en-US;)",
			"ia_archiver/8.2 (Windows 7.6; en-US;)",
			"ia_archiver/8.1 (Windows 2000 1.9; en-US;)",
			"ia_archiver/8.0 (Windows 2.4; en-US;)",
			"ia_archiver",
			"iCCrawler (http://www.iccenter.net/bot.htm)",
			"ichiro/4.0 (http://help.goo.ne.jp/door/crawler.html)",
			"ichiro/3.0 (http://help.goo.ne.jp/door/crawler.html)",
			"ichiro/2.0+(http://help.goo.ne.jp/door/crawler.html)",
			"ichiro/2.0 (ichiro@nttr.co.jp)",
			"ichiro/2.0 (http://help.goo.ne.jp/door/crawler.html)",
			"igdeSpyder (compatible; igde.ru; +http://igde.ru/doc/tech.html)",
			"IRLbot/3.0 (compatible; MSIE 6.0; http://irl.cs.tamu.edu/crawler/)",
			"IRLbot/3.0 (compatible; MSIE 6.0; http://irl.cs.tamu.edu/crawler)",
			"IRLbot/2.0 (compatible; MSIE 6.0; http://irl.cs.tamu.edu/crawler)",
			"IRLbot/2.0 (+http://irl.cs.tamu.edu/crawler)",
			"IRLbot/2.0 ( http://irl.cs.tamu.edu/crawler)",
			"IssueCrawler",
			"Jaxified Bot (+http://www.jaxified.com/crawler/)",
			"Jyxobot/1",
			"Mozilla/5.0 (compatible; KoepaBot BETA; http://www.koepa.nl/bot.html)",
			"L.webis/0.87 (http://webalgo.iit.cnr.it/index.php?pg=lwebis)",
			"LapozzBot/1.4 (+http://robot.lapozz.com)",
			"Mozilla/5.0 larbin@unspecified.mail",
			"larbin_2.6.3 zumesun@hotmail.com",
			"larbin_2.6.3 tangyi858@163.com",
			"larbin_2.6.3 ltaa_web_crawler@groupes.epfl.ch",
			"larbin_2.6.3 larbin2.6.3@unspecified.mail",
			"larbin_2.6.3 gqnmgsp@ruc.edu.cn",
			"larbin_2.6.3 ghary@sohu.com",
			"larbin_2.6.3 capveg@cs.umd.edu",
			"larbin_2.6.3 (wgao@genieknows.com)",
			"larbin_2.6.3 (ltaa_web_crawler@groupes.epfl.ch)",
			"larbin_2.6.3 (larbin@behner.org)",
			"larbin_2.6.3 (larbin2.6.3@unspecified.mail)",
			"larbin_2.6.2 vitalbox1@hotmail.com",
			"larbin_2.6.2 pierre@micro-fun.ch",
			"larbin_2.6.2 listonATccDOTgatechDOTedu",
			"larbin_2.6.2 larbin@correa.org",
			"larbin_2.6.2 larbin2.6.2@unspecified.mail",
			"larbin_2.6.2 kalou@kalou.net",
			"larbin_2.6.2 dthunen@princeton.edu",
			"larbin_2.6.2 (vitalbox1@hotmail.com)",
			"larbin_2.6.2 (pierre@micro-fun.ch)",
			"larbin_2.6.2 (larbin@correa.org)",
			"larbin_2.6.2 (larbin2.6.2@unspecified.mail)",
			"larbin_2.6.1 larbin2.6.1@unspecified.mail",
			"larbin_2.5.0 (larbin2.5.0@unspecified.mail)",
			"larbin_xy250 larbin2.6.3@unspecified.mail",
			"larbin_test nobody@airmail.etn",
			"larbin_test (nobody@airmail.etn)",
			"LARBIN-EXPERIMENTAL efp@gmx.net",
			"ldspider (http://code.google.com/p/ldspider/wiki/Robots)",
			"LexxeBot/1.0 (lexxebot@lexxe.com)",
			"Linguee Bot (http://www.linguee.com/bot; bot@linguee.com)",
			"LinkWalker/2.0",
			"LinkWalker",
			"lmspider lmspider@scansoft.com",
			"lmspider (lmspider@scansoft.com)",
			"lwp-trivial/1.41",
			"lwp-trivial/1.38",
			"lwp-trivial/1.36",
			"lwp-trivial/1.35",
			"lwp-trivial/1.33",
			"http://www.mabontland.com",
			"magpie-crawler/1.1 (U; Linux amd64; en-GB; +http://www.brandwatch.net)",
			"Mediapartners-Google/2.1",
			"Mozilla/5.0 (compatible; MJ12bot/v1.2.4; http://www.majestic12.co.uk/bot.php?+)",
			"Mozilla/5.0 (compatible; MJ12bot/v1.2.3; http://www.majestic12.co.uk/bot.php?+)",
			"MJ12bot/v1.0.8 (http://majestic12.co.uk/bot.php?+)",
			"MJ12bot/v1.0.7 (http://majestic12.co.uk/bot.php?+)",
			"MJ12bot/v1.0.6 (http://majestic12.co.uk/bot.php?+)",
			"MJ12bot/v1.0.5 (http://majestic12.co.uk/bot.php?+)",
			"Mnogosearch-3.1.21",
			"mogimogi/1.0",
			"Mozilla/5.0 (compatible; MojeekBot/2.0; http://www.mojeek.com/bot.html)",
			"MojeekBot/0.2 (archi; http://www.mojeek.com/bot.html)",
			"Moreoverbot/5.1 ( http://w.moreover.com; webmaster@moreover.com) Mozilla/5.0",
			"Moreoverbot/5.00 (+http://www.moreover.com; webmaster@moreover.com)",
			"Moreoverbot/5.00 (+http://www.moreover.com)",
			"Morning Paper 1.0 (robots.txt compliant!)",
			"msnbot/2.1",
			"msnbot/2.0b",
			"msnbot/1.1 (+http://search.msn.com/msnbot.htm)",
			"msnbot/1.1",
			"msnbot/1.0 (+http://search.msn.com/msnbot.htm)",
			"msnbot/0.9 (+http://search.msn.com/msnbot.htm)",
			"msnbot/0.11 ( http://search.msn.com/msnbot.htm)",
			"MSNBOT/0.1 (http://search.msn.com/msnbot.htm)",
			"MSRBOT (http://research.microsoft.com/research/sv/msrbot/)",
			"MSRBOT",
			"MVAClient",
			"Mozilla/5.0 (compatible; mxbot/1.0; +http://www.chainn.com/mxbot.html)",
			"Mozilla/5.0 (compatible; mxbot/1.0; http://www.chainn.com/mxbot.html)",
			"NetResearchServer/4.0(loopimprovements.com/robot.html)",
			"NetResearchServer/3.5(loopimprovements.com/robot.html)",
			"NetResearchServer/2.8(loopimprovements.com/robot.html)",
			"NetResearchServer/2.7(loopimprovements.com/robot.html)",
			"NetResearchServer/2.5(loopimprovements.com/robot.html)",
			"NetResearchServer(http://www.look.com)",
			"Mozilla/5.0 (compatible; NetSeer crawler/2.0; +http://www.netseer.com/crawler.html; crawler@netseer.com)",
			"NewsGator/2.5 (http://www.newsgator.com; Microsoft Windows NT 5.1.2600.0; .NET CLR 1.1.4322.2032)",
			"NewsGator/2.0 Bot (http://www.newsgator.com)",
			"NG-Search/0.9.8 (http://www.ng-search.com)",
			"NG-Search/0.86 (+http://www.ng-search.com)",
			"NG-Search/0.86 ( http://www.ng-search.com)",
			"nicebot",
			"noxtrumbot/1.0 (crawler@noxtrum.com)",
			"Nusearch Spider (www.nusearch.com)",
			"nuSearch Spider (compatible; MSIE 4.01; Windows NT)",
			"NutchCVS/0.8-dev (Nutch; http://lucene.apache.org/nutch/bot.html; nutch-agent@lucene.apache.org)",
			"NutchCVS/0.7.2 (Nutch; http://lucene.apache.org/nutch/bot.html; nutch-agent@lucene.apache.org)",
			"NutchCVS/0.7.1 (Nutch; http://lucene.apache.org/nutch/bot.html; nutch-agent@lucene.apache.org)",
			"NutchCVS/0.7.1 (Nutch running at UW; http://crawlers.cs.washington.edu/; sycrawl@cs.washington.edu)",
			"NutchCVS/0.7 (Nutch; http://lucene.apache.org/nutch/bot.html; nutch-agent@lucene.apache.org)",
			"NutchCVS/0.06-dev (Nutch; http://www.nutch.org/docs/en/bot.html; nutch-agent@lists.sourceforge.net)",
			"NutchCVS/0.06-dev (Nutch; http://www.nutch.org/docs/en/bot.html; jagdeepssandhu@hotmail.com)",
			"NutchCVS/0.05 (Nutch; http://www.nutch.org/docs/en/bot.html; nutch-agent@lists.sourceforge.net)",
			"Nymesis/1.0 (http://nymesis.com)",
			"Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 4.0; obot)",
			"oegp v. 1.3.0",
			"omgilibot/0.4 +http://omgili.com",
			"omgilibot/0.3 +http://www.omgili.com/Crawler.html",
			"omgilibot/0.3 http://www.omgili.com/Crawler.html",
			"OmniExplorer_Bot/6.70 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/6.65a (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/6.63b (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/6.62 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/6.60 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/6.47 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/5.91c (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/5.28 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/5.25 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/5.20 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/5.01 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/4.80 (+http://www.omni-explorer.com) WorldIndexer",
			"OmniExplorer_Bot/4.32 (+http://www.omni-explorer.com) WorldIndexer",
			"OOZBOT/0.20 ( -- ; http://www.setooz.com/oozbot.html ; agentname at setooz dot_com )",
			"OOZBOT/0.17 (--; http://www.setooz.com/oozbot.html; pvvpr at iiit dot ac dot in)",
			"Orbiter (+http://www.dailyorbit.com/bot.htm)",
			"PageBitesHyperBot/600 (http://www.pagebites.com/)",
			"Mozilla/5.0 (compatible; Peew/1.0; http://www.peew.de/crawler/)",
			"polybot 1.0 (http://cis.poly.edu/polybot/)",
			"Pompos/1.3 http://dir.com/pompos.html",
			"Pompos/1.2 http://pompos.iliad.fr",
			"Pompos/1.1 http://pompos.iliad.fr",
			"PostPost/1.0 (+http://postpo.st/crawlers)",
			"psbot/0.1 (+http://www.picsearch.com/bot.html)",
			"PycURL/7.23.1",
			"PycURL/7.19.7",
			"PycURL/7.19.5",
			"PycURL/7.19.3",
			"PycURL/7.19.0",
			"PycURL/7.18.2",
			"PycURL/7.18.0",
			"PycURL/7.16.4",
			"PycURL/7.15.5",
			"PycURL/7.13.2",
			"PycURL",
			"Qseero v1.0.0",
			"radian6_default_(www.radian6.com/crawler)",
			"RAMPyBot - www.giveRAMP.com/0.1 (RAMPyBot - www.giveRAMP.com; http://www.giveramp.com/bot.html; support@giveRAMP.com)",
			"RufusBot (Rufus Web Miner; http://64.124.122.252/feedback.html)",
			"SandCrawler - Compatibility Testing",
			"SBIder/0.8-dev (SBIder; http://www.sitesell.com/sbider.html; http://support.sitesell.com/contact-support.html)",
			"Mozilla/5.0 (compatible; ScoutJet; http://www.scoutjet.com/)",
			"Scrubby/2.2 (http://www.scrubtheweb.com/)",
			"Mozilla/5.0 (compatible; Scrubby/2.2; +http://www.scrubtheweb.com/)",
			"Mozilla/5.0 (compatible; Scrubby/2.2; http://www.scrubtheweb.com/)",
			"Scrubby/2.1 (http://www.scrubtheweb.com/)",
			"Mozilla/5.0 (compatible; Scrubby/2.1; +http://www.scrubtheweb.com/abs/meta-check.html)",
			"SearchSight/2.0 (http://SearchSight.com/)",
			"Seekbot/1.0 (http://www.seekbot.net/bot.html) RobotsTxtFetcher/1.2",
			"Seekbot/1.0 (http://www.seekbot.net/bot.html) HTTPFetcher/2.1",
			"Seekbot/1.0 (http://www.seekbot.net/bot.html) HTTPFetcher/0.3",
			"Seekbot/1.0 (http://www.seekbot.net/bot.html)",
			"semanticdiscovery/0.1",
			"Sensis Web Crawler (search_comments\\at\\sensis\\dot\\com\\dot\\au)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0) SEOChat::Bot v1.1",
			"SeznamBot/2.0 (+http://fulltext.seznam.cz/)",
			"SeznamBot/2.0 (+http://fulltext.sblog.cz/robot/)",
			"Shim-Crawler(Mozilla-compatible; http://www.logos.ic.i.u-tokyo.ac.jp/crawler/; crawl@logos.ic.i.u-tokyo.ac.jp)",
			"Shim-Crawler",
			"ShopWiki/1.0 ( +http://www.shopwiki.com/wiki/Help:Bot)",
			"Mozilla/4.0 (compatible: Shoula robot)",
			"Mozilla/5.0 (compatible; SiteBot/0.1; +http://www.sitebot.org/robot/)",
			"Mozilla/5.0 (compatible; SiteBot/0.1; http://www.sitebot.org/robot/)",
			"Snappy/1.1 ( http://www.urltrends.com/ )",
			"sogou spider",
			"Sosospider+(+http://help.soso.com/webspider.htm)",
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) Speedy Spider (http://www.entireweb.com/about/search_tech/speedy_spider/)",
			"Mozilla/5.0 (compatible; Speedy Spider; http://www.entireweb.com/about/search_tech/speedy_spider/)",
			"Speedy Spider (Entireweb; Beta/1.3; http://www.entireweb.com/about/search_tech/speedyspider/)",
			"Speedy Spider (Entireweb; Beta/1.2; http://www.entireweb.com/about/search_tech/speedyspider/)",
			"Speedy Spider (Entireweb; Beta/1.1; http://www.entireweb.com/about/search_tech/speedyspider/)",
			"Speedy Spider (Entireweb; Beta/1.0; http://www.entireweb.com/about/search_tech/speedyspider/)",
			"Speedy Spider (Beta/1.0; www.entireweb.com)",
			"Speedy Spider (http://www.entireweb.com/about/search_tech/speedy_spider/)",
			"Speedy Spider (http://www.entireweb.com/about/search_tech/speedyspider/)",
			"Speedy Spider (http://www.entireweb.com)",
			"Sqworm/2.9.85-BETA (beta_release; 20011115-775; i686-pc-linux-gnu)",
			"StackRambler/2.0 (MSIE incompatible)",
			"StackRambler/2.0",
			"Mozilla/5.0 (compatible; suggybot v0.01a, http://blog.suggy.com/was-ist-suggy/suggy-webcrawler/)",
			"SurveyBot/2.3+(Whois+Source)",
			"SurveyBot/2.3 (Whois Source)",
			"SynooBot/0.7.1 (SynooBot; http://www.synoo.de/bot.html; webmaster@synoo.com)",
			"Mozilla/2.0 (compatible; Ask Jeeves/Teoma; +http://sp.ask.com/docs/about/tech_crawling.html)",
			"Mozilla/2.0 (compatible; Ask Jeeves/Teoma; +http://about.ask.com/en/docs/about/webmasters.shtml)",
			"Mozilla/2.0 (compatible; Ask Jeeves/Teoma)",
			"TerrawizBot/1.0 (+http://www.terrawiz.com/bot.html)",
			"TheSuBot/0.2 (www.thesubot.de)",
			"TheSuBot/0.1 (www.thesubot.de)",
			"Thumbnail.CZ robot 1.1 (http://thumbnail.cz/why-no-robots-txt.html)",
			"TinEye/1.1 (http://tineye.com/crawler.html)",
			"TinEye",
			"truwoGPS/1.0 (GNU/Linux; U; i686; en-US; +http://www.lan4lano.net/browser.html )",
			"TurnitinBot/2.1 (http://www.turnitin.com/robot/crawlerinfo.html)",
			"TurnitinBot/2.0 http://www.turnitin.com/robot/crawlerinfo.html",
			"TurnitinBot/1.5 http://www.turnitin.com/robot/crawlerinfo.html",
			"TurnitinBot/1.5 (http://www.turnitin.com/robot/crawlerinfo.html)",
			"TurnitinBot/1.5 http://www.turnitin.com/robot/crawlerinfo.html",
			"TurnitinBot/1.5 (http://www.turnitin.com/robot/crawlerinfo.html)",
			"Mozilla/5.0 (compatible; TweetedTimes Bot/1.0; http://tweetedtimes.com)",
			"TwengaBot",
			"updated/0.1-beta (updated; http://www.updated.com; updated@updated.com)",
			"Mozilla/5.0 (compatible; Urlfilebot/2.2; +http://urlfile.com/bot.html)",
			"Mozilla/4.0 (compatible; Vagabondo/4.0Beta; webcrawler at wise-guys dot nl; http://webagent.wise-guys.nl/; http://www.wise-guys.nl/)",
			"Mozilla/4.0 (compatible; Vagabondo/2.2; webcrawler at wise-guys dot nl; http://webagent.wise-guys.nl/)",
			"Mozilla/5.0 (compatible; Vagabondo/2.1; webcrawler at wise-guys dot nl; http://webagent.wise-guys.nl/)",
			"Mozilla/3.0 (Vagabondo/2.0 MT; webcrawler@NOSPAMexperimental.net; http://aanmelden.ilse.nl/?aanmeld_mode=webhints)",
			"Mozilla/4.0 (compatible; MSIE 5.0; Windows 95) VoilaBot BETA 1.2 (http://www.voila.com/)",
			"Vortex/2.2 (+http://marty.anstey.ca/robots/vortex/)",
			"Vortex/2.2 ( http://marty.anstey.ca/robots/vortex/)",
			"VORTEX/1.2 ( http://marty.anstey.ca/robots/vortex/)",
			"voyager/2.0 (http://www.kosmix.com/crawler.html)",
			"voyager/1.0",
			"VYU2 (GNU; OpenRISC)",
			"webcollage/1.93",
			"webcollage/1.129",
			"webcollage/1.125",
			"webcollage/1.117",
			"webcollage/1.114",
			"Websquash.com (Add url robot)",
			"http://www.almaden.ibm.com/cs/crawler [wf84]",
			"WoFindeIch Robot 1.0(+http://www.search.wofindeich.com/robot.php)",
			"WoFindeIch Robot 1.0( http://www.search.wofindeich.com/robot.php)",
			"WomlpeFactory/0.1 (+http://www.Womple.com/bot.html)",
			"2Xaldon_WebSpider/2.0.b1",
			"yacybot (x86 Windows XP 5.1; java 1.6.0_12; Europe/de) http://yacy.net/bot.html",
			"yacybot (x86 Windows XP 5.1; java 1.6.0_11; Europe/de) http://yacy.net/bot.html",
			"yacybot (x86 Windows XP 5.1; java 1.6.0; Europe/de) http://yacy.net/yacy/bot.html",
			"yacybot (x86 Windows 2000 5.0; java 1.6.0_16; Europe/de) http://yacy.net/bot.html",
			"yacybot (ppc Mac OS X 10.5.2; java 1.5.0_13; Europe/de) http://yacy.net/bot.html",
			"yacybot (ppc Mac OS X 10.4.10; java 1.5.0_07; Europe/de) http://yacy.net/bot.html",
			"yacybot (i386 Mac OS X 10.5.7; java 1.5.0_16; Europe/de) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.9-023stab046.2-smp; java 1.6.0_05; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.8-022stab070.5-enterprise; java 1.4.2-03; Europe/en) yacy.net",
			"yacybot (i386 Linux 2.6.31-16-generic; java 1.6.0_15; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.26-2-686; java 1.6.0_0; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.24-28-generic; java 1.6.0_20; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.24-24-generic; java 1.6.0_07; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.24-23-generic; java 1.6.0_16; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.23; java 1.6.0_17; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.23; java 1.6.0_04; Europe/en) http://yacy.net/bot.html",
			"yacybot (i386 Linux 2.6.22-14-generic; java 1.6.0_03; Europe/de) http://yacy.net/bot.html",
			"yacybot (amd64 Windows 7 6.1; java 1.6.0_17; Europe/de) http://yacy.net/bot.html",
			"yacybot (amd64 Linux 2.6.28-18-generic; java 1.6.0_0; Europe/en) http://yacy.net/bot.html",
			"yacybot (amd64 Linux 2.6.16-2-amd64-k8-smp; java 1.5.0_10; Europe/en) http://yacy.net/yacy/bot.html",
			"Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
			"Mozilla/5.0 (compatible; Yahoo! Slurp China; http://misc.yahoo.com.cn/help.html)",
			"YahooSeeker/1.2 (compatible; Mozilla 4.0; MSIE 5.5; yahooseeker at yahoo-inc dot com ; http://help.yahoo.com/help/us/shop/merchant/)",
			"YahooSeeker-Testing/v3.9 (compatible; Mozilla 4.0; MSIE 5.5; http://search.yahoo.com/)",
			"Mozilla/5.0 (compatible; YandexBot/3.0; +http://yandex.com/bots)",
			"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)",
			"Yasaklibot/v1.2 (http://www.Yasakli.com/bot.php)",
			"Yeti/1.0 (NHN Corp.; http://help.naver.com/robots/)",
			"Yeti/1.0 (+http://help.naver.com/robots/)",
			"Mozilla/5.0 (compatible; YodaoBot/1.0; http://www.yodao.com/help/webmaster/spider/; )",
			"yoogliFetchAgent/0.1",
			"Mozilla/5.0 (compatible; YoudaoBot/1.0; http://www.youdao.com/help/webmaster/spider/; )",
			"Zao/0.1 (http://www.kototoi.org/zao/)",
			"Mozilla/4.0 (compatible; Zealbot 1.0)",
			"zspider/0.9-dev http://feedback.redkolibri.com/",
			"Mozilla/4.0 compatible ZyBorg/1.0 DLC (wn.zyborg@looksmart.net; http://www.WISEnutbot.com)",
			"Mozilla/4.0 compatible ZyBorg/1.0 Dead Link Checker (wn.zyborg@looksmart.net; http://www.WISEnutbot.com)",
			"Mozilla/4.0 compatible ZyBorg/1.0 Dead Link Checker (wn.dlc@looksmart.net; http://www.WISEnutbot.com)",
			"Mozilla/4.0 compatible ZyBorg/1.0 (wn.zyborg@looksmart.net; http://www.WISEnutbot.com)",
			"Mozilla/4.0 compatible ZyBorg/1.0 (wn-16.zyborg@looksmart.net; http://www.WISEnutbot.com)",
			"Mozilla/4.0 compatible ZyBorg/1.0 (wn-14.zyborg@looksmart.net; http://www.WISEnutbot.com)"
	};

	String[] mobileBot = {
			"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7 (compatible; Googlebot-Mobile/2.1; +http://www.google.com/bot.html)",
			"DoCoMo/2.0 N905i(c100;TB;W24H16) (compatible; Googlebot-Mobile/2.1; +http://www.google.com/bot.html)"
	};

	String[] tools = {
			"curl/7.19.5 (i586-pc-mingw32msvc) libcurl/7.19.5 OpenSSL/0.9.8l zlib/1.2.3",
			"Wget/1.8.1",
			"Apache-HttpClient/release (java 1.5)"
	};

	String[] thunderbird3 = {
			"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.12) Gecko/20101027 Thunderbird/3.1.6",
			"Mozilla/5.0 (Windows; U; Windows NT 6.1; sv-SE; rv:1.9.2.8) Gecko/20100802 Thunderbird/3.1.2 ThunderBrowse/3.3.2"
	};

	String[] thunderbird2 = {
			"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-GB; rv:1.8.1.14) Gecko/20080421 Thunderbird/2.0.0.14",
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US; rv:1.8.1.17) Gecko/20080914 Thunderbird/2.0.0.17"
	};

	String[] silk = {
			"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us; Silk/1.1.0-80) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16 Silk-Accelerated=true"
	};

	String[] iTunes = {
			"iTunes/10.2.2 (Windows; Microsoft Windows XP Home Edition Service Pack 3 (Build 2600)) AppleWebKit/533.21.1"
	};

	String[] appStore = {
			"MacAppStore/1.1.2 (Macintosh; U; Intel Mac OS X 10.7.3; en) AppleWebKit/534.53.11 "
	};

	String[] airApp = {
			"Mozilla/5.0 (Windows; U; cs-CZ) AppleWebKit/526.9+ (KHTML, like Gecko) AdobeAIR/1.5.1"
	};

	String[] blackberry10 = {
			"Mozilla/5.0 (BB10; Touch) AppleWebKit/537.1 (KHTML, like Gecko) Version/10.0.0.1337 Mobile Safari/537.1",
			"Mozilla/5.0 (BB10; Kbd) AppleWebKit/537.10+ (KHTML, like Gecko) Version/10.1.0.1485 Mobile Safari/537.10+"
	};

	String[] vivaldi = {
			"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.89 Vivaldi/1.0.83.38 Safari/537.36",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.114 Safari/537.36 Vivaldi/1.0.111.2",
			"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.114 Safari/537.36 Vivaldi/1.0.111.2",
	};

	String[] androidWebKit = {
			"Mozilla/5.0 (Linux; U; Android 2.1; en-us; Nexus One Build/ERD62) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17",
			"Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
	};

	// proxy server with fake browser reference
	String[] proxy = {
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7 (via ggpht.com)" // Gmail proxy server
	};

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.Browser#isInUserAgentString(java.lang.String)} .
	 */
	@Test
	public void testIsBrowser() {
		assertTrue(Browser.SAFARI
				.isInUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"));
	}

	/**
	 *
	 */
	@Test
	public void testVersionDetection() {
		testVersions("Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 OpenSSL/0.9.7d", new Version("2.8.5rel.1", "2", "8"));
		testVersions("Mozilla/6.0 (compatible; Konqueror/4.2; i686 FreeBSD 6.4; 20060308)", new Version("4.2", "4", "2"));
		testVersions("Mozilla/5.0 (compatible; Konqueror/3.1-rc5; i686 Linux; 20021219)", new Version("3.1-rc5", "3", "1"));
		testVersions("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.14) Gecko/2009090216 Ubuntu/9.04 (jaunty) Firefox/3.0.14", new Version("3.0.14", "3", "0"));
		testVersions("Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0 ; .NET CLR 2.0.50215; SL Commerce Client v1.0; Tablet PC 2.0",
				new Version("7.0b", "7", "0b")); // is minor here b or 0b?
		testVersions("Opera/9.80 (Windows NT 5.2; U; en) Presto/2.2.15 Version/10.10", new Version("10.10", "10", "10"));
		testVersions("Opera/8.0 (Macintosh; PPC Mac OS X; U; en)", new Version("8.0", "8", "0"));
		testVersions("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.558.0 Safari/534.10",
				new Version("8.0.558.0", "8", "0"));
		testVersions("Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML,like Gecko) Chrome/9.1.0.0 Safari/540.0",
				new Version("9.1.0.0", "9", "1"));
		testVersions("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-gb) AppleWebKit/523.10.6 (KHTML, like Gecko) Version/3.0.4 Safari/523.10.6",
				new Version("3.0.4", "3", "0"));
		testVersions("Mozilla/5.0 (Linux; U; Android 2.1; en-us; Nexus One Build/ERD62) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17",
				new Version("4.0", "4", "0"));
		testVersions(
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; GTB6.4; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 1.1.4322; .NET CLR 3.5.30729; .NET CLR 3.0.30729; OfficeLiveConnector.1.3; OfficeLivePatch.0.0; MSOffice 14)",
				new Version("14", "14", "0"));
		testVersions("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; it; rv:1.8.1.21) Gecko/20090327 Camino/1.6.7 (MultiLang) (like Firefox/2.0.0.21pre)",
				new Version("1.6.7", "1", "6"));
		testVersions("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008100716 Firefox/3.0.3 Flock/2.0", new Version("2.0", "2", "0"));
		testVersions("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US; rv:1.8.1.17) Gecko/20080914 Thunderbird/2.0.0.17", new Version("2.0.0.17", "2", "0"));
		testVersions("Mozilla/4.0 (compatible; Lotus-Notes/5.0; Windows-NT)", new Version("5.0", "5", "0"));
		testVersions("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.13) Gecko/20100914 Mnenhy/0.8.3 SeaMonkey/2.0.8", new Version("2.0.8", "2", "0"));
		testVersions(
				"Mozilla/5.0 (SymbianOS/9.2; U; Series60/3.1 NokiaE90-1/07.24.0.3; Profile/MIDP-2.0 Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML, like Gecko) Safari/413 UP.Link/6.2.3.18.0",
				null);
		testVersions("Mozilla/5.0 (compatible; Googlebot/2.1; http://www.google.com/bot.html)", null); // no version information for some browsers
		testVersions("Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7",
				new Version("16.0.912.75", "16", "0"));
		testVersions(
				"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us; Silk/1.1.0-80) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16 Silk-Accelerated=true",
				new Version("1.1.0-80", "1", "1"));
		testVersions(
				"Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; en) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3",
				new Version("19.0.1084.60", "19", "0"));
		testVersions("Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7",
				new Version("16.0.912.75", "16", "0"));
		testVersions("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.52 Safari/537.36 OPR/15.0.1147.100",
				new Version("15.0.1147.100", "15", "0"));
		testVersions("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36 OPR/16.0.1196.73",
				new Version("16.0.1196.73", "16", "0"));
		testVersions("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36",
				new Version("29.0.1547.65", "29", "0"));
		testVersions("Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko", new Version("11.0", "11", "0"));
		testVersions("Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240",
				new Version("12.10240", "12", "10240"));
		testVersions(
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C)",
				new Version("7.0", "7", "0")); // issue #31
		testVersions("Mozilla/5.0 (Linux; Android 4.1.2; LT22i Build/6.2.A.1.100) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36",
				new Version("31.0.1650.59", "31", "0"));
		testVersions("Windows-RSS-Platform/2.0 (IE 11.0; Windows NT 6.1)", new Version("11.0", "11", "0"));
		testVersions("Mozilla/5.0 (iPad; CPU OS 7_0_3 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Coast/1.1.3.65998 Mobile/11B511 Safari/7534.48.3",
				new Version("1.1.3.65998", "1", "1"));
		testVersions("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36 OPR/23.0.1522.60",
				new Version("23.0.1522.60", "23", "0"));
		testVersions("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36",
				new Version("42.0.2311.135", "42", "0"));
		testVersions("Windows-RSS-Platform/2.0 (IE 11.0; Windows NT 6.1)", new Version("11.0", "11", "0"));
		testVersions("Mozilla/5.0 (iPad; CPU OS 7_0_3 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Coast/1.1.3.65998 Mobile/11B511 Safari/7534.48.3",
				new Version("1.1.3.65998", "1", "1"));
		testVersions("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36 OPR/23.0.1522.60",
				new Version("23.0.1522.60", "23", "0"));
		testVersions(
				"Mozilla/5.0 (Linux; Android 4.1.2; HTC One SV Build/JZO54K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.81 Mobile Safari/537.36 OPR/28.0.1764.90386",
				new Version("28.0.1764.90386", "28", "0"));
	}

	private void testVersions(String ua, Version expectedVersion) {
		Version version = Browser.parseUserAgentString(ua).getVersion(ua);
		assertEquals(expectedVersion, version);
	}

	/**
	 * Test method for {@link eu.bitwalker.useragentutils.Browser#parseUserAgentString(java.lang.String)} .
	 */
	@Test
	public void testParseUserAgentString() {
		testAgents(ie55clients, Browser.IE5_5);
		testAgents(ie6clients, Browser.IE6);
		testAgents(ie7clients, Browser.IE7);
		testAgents(ie8clients, Browser.IE8);
		testAgents(ie9clients, Browser.IE9);
		testAgents(ie10clients, Browser.IE10);
		testAgents(ie11clients, Browser.IE11);
		testAgents(edgeClients, Browser.EDGE12);
		testAgents(edgeMobileClients, Browser.EDGE_MOBILE12);
		testAgents(ieTooOld, Browser.IE);
		testAgents(outlook2007, Browser.OUTLOOK2007);
		testAgents(outlook2010, Browser.OUTLOOK2010);
		testAgents(outookExpress, Browser.OUTLOOK_EXPRESS7);
		testAgents(ieMobile6, Browser.IEMOBILE6);
		testAgents(ieMobile7, Browser.IEMOBILE7);
		testAgents(ieMobile9, Browser.IEMOBILE9);
		testAgents(ieMobile10, Browser.IEMOBILE10);
		testAgents(ieMobile11, Browser.IEMOBILE11);
		testAgents(ie7Rss, Browser.IE7);
		testAgents(ie8Rss, Browser.IE8);
		testAgents(ie9Rss, Browser.IE9);
		testAgents(ie10Rss, Browser.IE10);
		testAgents(ie11Rss, Browser.IE11);
		testAgents(lotusNotes, Browser.LOTUS_NOTES);
		testAgents(lynxClient, Browser.LYNX);
		testAgents(konqueror, Browser.KONQUEROR);
		testAgents(chromeMobile, Browser.CHROME_MOBILE);
		testAgents(chromeMobile31, Browser.CHROME31);
		testAgents(chrome, Browser.CHROME);
		testAgents(chrome8, Browser.CHROME8);
		testAgents(chrome9, Browser.CHROME9);
		testAgents(chrome10, Browser.CHROME10);
		testAgents(chrome11, Browser.CHROME11);
		testAgents(chrome12, Browser.CHROME12);
		testAgents(chrome13, Browser.CHROME13);
		testAgents(chrome14, Browser.CHROME14);
		testAgents(chrome29, Browser.CHROME29);
		testAgents(chrome31, Browser.CHROME31);
		testAgents(chrome32, Browser.CHROME32);
		testAgents(chrome33, Browser.CHROME33);
		testAgents(chrome36, Browser.CHROME36);
		testAgents(chrome39, Browser.CHROME39);
		testAgents(firefox3, Browser.FIREFOX3);
		testAgents(firefox4, Browser.FIREFOX4);
		testAgents(firefox5, Browser.FIREFOX5);
		testAgents(firefox6, Browser.FIREFOX6);
		testAgents(firefox19, Browser.FIREFOX19);
		testAgents(firefox20, Browser.FIREFOX20);
		testAgents(firefox25, Browser.FIREFOX25);
		testAgents(firefox28, Browser.FIREFOX28);
		testAgents(firefox36, Browser.FIREFOX36);
		testAgents(firefox3mobile, Browser.FIREFOX3MOBILE);
		testAgents(firefoxMobile, Browser.FIREFOX_MOBILE);
		testAgents(firefoxMobile13, Browser.FIREFOX_MOBILE13);
		testAgents(firefoxMobile23, Browser.FIREFOX_MOBILE23);
		testAgents(safari, Browser.SAFARI);
		testAgents(dolfin, Browser.DOLFIN2);
		testAgents(safari8, Browser.SAFARI8);
		testAgents(safari7, Browser.SAFARI7);
		testAgents(safari6, Browser.SAFARI6);
		testAgents(safari5, Browser.SAFARI5);
		testAgents(safari4, Browser.SAFARI4);
		testAgents(mobileSafari, Browser.MOBILE_SAFARI);
		testAgents(mobileSafari4, Browser.MOBILE_SAFARI4);
		testAgents(mobileSafari5, Browser.MOBILE_SAFARI5);
		testAgents(mobileSafari6, Browser.MOBILE_SAFARI6);
		testAgents(appleMail, Browser.APPLE_WEB_KIT);
		testAgents(androidWebKit, Browser.ANDROID_WEB_KIT);
		testAgents(omniWeb, Browser.OMNIWEB);
		testAgents(operaMini, Browser.OPERA_MINI);
		testAgents(operaMobi, Browser.OPERA_MOBILE);
		testAgents(operaMobi10, Browser.OPERA10);
		testAgents(operaMobi11, Browser.OPERA11);
		testAgents(operaMobi12, Browser.OPERA12);
		testAgents(opera9, Browser.OPERA9);
		testAgents(opera, Browser.OPERA);
		testAgents(opera10, Browser.OPERA10);
		testAgents(opera11, Browser.OPERA11);
		testAgents(opera12, Browser.OPERA12);
		testAgents(opera15, Browser.OPERA15);
		testAgents(opera16, Browser.OPERA16);
		testAgents(opera17, Browser.OPERA17);
		testAgents(opera18, Browser.OPERA18);
		testAgents(opera19, Browser.OPERA19);
		testAgents(opera20, Browser.OPERA20);
		testAgents(opera23, Browser.OPERA23);
		testAgents(opera24, Browser.OPERA24);
		testAgents(opera27, Browser.OPERA27);
		testAgents(opera31, Browser.OPERA31);
		testAgents(operaCoast, Browser.COAST1);
		testAgents(camino2, Browser.CAMINO2);
		testAgents(camino, Browser.CAMINO);
		testAgents(flock, Browser.FLOCK);
		testAgents(seaMonkey, Browser.SEAMONKEY);
		testAgents(bots, Browser.BOT);
		testAgents(mobileBot, Browser.BOT_MOBILE);
		testAgents(tools, Browser.DOWNLOAD);
		testAgents(proxy, Browser.DOWNLOAD);
		testAgents(thunderbird3, Browser.THUNDERBIRD3);
		testAgents(thunderbird2, Browser.THUNDERBIRD2);
		testAgents(silk, Browser.SILK);
		testAgents(iTunes, Browser.APPLE_ITUNES);
		testAgents(appStore, Browser.APPLE_APPSTORE);
		testAgents(airApp, Browser.ADOBE_AIR);
		testAgents(blackberry10, Browser.BLACKBERRY10);
		testAgents(vivaldi, Browser.VIVALDI);
	}

	@Test
	public void testCustomUserAgentParsing() {
		// Test limited to the big browser families. As Camino can not be detected
		// any longer, the second best match is Firefox3 (a child of Firefox).
		for (String agentString : camino2) {
			assertEquals(Browser.FIREFOX3,
					Browser.parseUserAgentString(agentString, Arrays.asList(Browser.IE, Browser.CHROME, Browser.APPLE_WEB_KIT, Browser.FIREFOX)));
		}
		// When there is no match in the given set, return UNKNOWN
		for (String agentString : opera9) {
			assertEquals(Browser.UNKNOWN,
					Browser.parseUserAgentString(agentString, Arrays.asList(Browser.IE, Browser.CHROME, Browser.APPLE_WEB_KIT, Browser.FIREFOX)));
		}
	}

	private void testAgents(String[] agentStrings, Browser expectedBrowser) {
		for (String agentString : agentStrings) {
			assertEquals(agentString, expectedBrowser, Browser.parseUserAgentString(agentString));
		}
	}

	@Test
	public void testIncompleteUAString() {
		try {
			Browser browser = Browser.parseUserAgentString("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us) AppleWebKit/531.21.11 (KHTML, like");
			browser.getVersion("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us) AppleWebKit/531.21.11 (KHTML, like");
			Browser browser2 = Browser.parseUserAgentString("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.8) Gecko/2009032608 Firefox");
			browser2.getVersion("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.8) Gecko/2009032608 Firefox");
			Browser browser3 = Browser.parseUserAgentString("Mozilla/4.0 (compatible; MSIE 8");
			browser3.getVersion("Mozilla/4.0 (compatible; MSIE 8");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test if generated id values are unique.
	 */
	@Test
	public void testUniqueIdValues() {

		Map<Short, Browser> retrievedIdValues = new HashMap<Short, Browser>();

		for (Browser browser : Browser.values()) {
			assertTrue(browser.toString() + "==" + retrievedIdValues.get(browser.getId()), !retrievedIdValues.containsKey(browser.getId()));
			retrievedIdValues.put(browser.getId(), browser);
		}
	}

	private static class BrowserData {
		final String name;
		final String version;
		final String userAgent;

		BrowserData(String name, String version, String userAgent) {
			this.name = name;
			this.version = version;
			this.userAgent = userAgent;
		}

		@Override
		public String toString() {
			return "BrowserData [name=" + name + ", version=" + version + ", userAgent=" + userAgent + "]";

		}
	}

	private static void testBrowserParser(Collection<BrowserData> browserDatas, Browser group, int minVersion, int maxVersion) throws Exception {
		testBrowserParser(browserDatas, group, minVersion, maxVersion, null);
	}

	private static void testBrowserParser(Collection<BrowserData> browserDatas, Browser group, int minVersion, int maxVersion, Browser alternate)
			throws Exception {
		for (BrowserData browserData : browserDatas) {
			Browser parsedBrowser = Browser.parseUserAgentString(browserData.userAgent);
			Browser parsedGroup = parsedBrowser.getGroup();

			assertNotNull(parsedBrowser);
			if (alternate != null) {
				assertTrue(browserData + ": " + parsedGroup + " - " + parsedBrowser,
						(parsedGroup == group) || (parsedGroup == alternate) ||
								parsedBrowser.name().startsWith(group.name()) ||
								parsedBrowser.name().startsWith(alternate.name()));
			} else {
				assertTrue(browserData + ": " + parsedGroup + " - " + parsedBrowser,
						(parsedGroup == group) || (parsedGroup == alternate) ||
								parsedBrowser.name().startsWith(group.name()));
			}

			int version = -1;
			Version parsedVersion = parsedBrowser.getVersion(browserData.userAgent);
			if (parsedVersion != null) {
				version = Integer.parseInt(parsedVersion.getMajorVersion());
			}

			if (version != -1 && version >= minVersion && version <= maxVersion && !browserData.version.isEmpty() && browserData.version.length() < 3) {
				if (!checkVersion(browserData, parsedBrowser, parsedVersion))
					return;
				assertTrue(browserData + ": parsed as " + parsedBrowser + " and version is " + version,
						browserData.name.startsWith(parsedBrowser.getName()) || parsedBrowser.getName().startsWith(browserData.name) ||
								browserData.name.startsWith(parsedGroup.getName()) && checkVersion(browserData, parsedBrowser, parsedVersion));
			} else {
				if (alternate == null) {
					assertTrue(browserData + ": parsed as " + parsedBrowser,
							browserData.name.startsWith(parsedBrowser.getName()) ||
									browserData.name.startsWith(parsedGroup.getName()));
					assertTrue(browserData + ": parsed as " + parsedBrowser,
							parsedGroup == group);
				} else {
					assertTrue(browserData + ": parsed as " + parsedBrowser,
							browserData.name.startsWith(parsedBrowser.getName()) ||
									browserData.name.startsWith(parsedGroup.getName()) ||
									browserData.name.startsWith(alternate.getName()) ||
									alternate == parsedBrowser || alternate == parsedGroup);
					if (!(browserData.name.startsWith(parsedBrowser.getName()) || browserData.name.startsWith(parsedGroup.getName()))) {
						System.out.println("Warning: " + browserData + " expected as " + group + " but parsed was " + parsedBrowser);
					}
				}
			}
		}
	}

	private static boolean checkVersion(BrowserData browserData, Browser browser, Version version) {
		return version.getMajorVersion().equals(browserData.version) ||
				(browser == Browser.IE10 && version.getMajorVersion().equals("8")) ||
				(browser == Browser.IE8 && version.getMajorVersion().equals("7")) ||
				(browser == Browser.IE8 && browserData.version.equals("6")) ||
				(browser == Browser.IE6 && browserData.version.equals("5"));
	}

	private static String readFile(String filename) throws IOException, URISyntaxException {
		URL url = BrowserTest.class.getResource("/uapages/" + filename);
		if (url == null)
			throw new IOException("Can't open resource " + filename);
		return new String(Files.readAllBytes(Paths.get(url.toURI())), "UTF-8");
	}

	private static Collection<BrowserData> filterHtml(String html) throws Exception {
		final Pattern beginBrowsersList = Pattern.compile("<div\\sid=[\"']liste[^>]*>", Pattern.CASE_INSENSITIVE);
		final Pattern endBrowsersList = Pattern.compile("</div>", Pattern.CASE_INSENSITIVE);
		final Pattern browsersData = Pattern.compile("<h4>([^<\\d]+)(\\d*)?[^<]*</h4>\\s*<ul>(.+?)</ul>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		final List<BrowserData> lines = new ArrayList<BrowserData>();

		html = HtmlParserUtils.extractArea(html, beginBrowsersList, endBrowsersList, "Browsers list");
		Matcher m = browsersData.matcher(html);
		while (m.find()) {
			String name = m.group(1).trim();
			String version = m.group(2);
			for (String s : HtmlParserUtils.parseTagsInnerHtml(m.group(3), "li")) {
				lines.add(new BrowserData(name, version, HtmlParserUtils.getInnerText(s)));
			}
		}
		return lines;
	}

	private static Collection<BrowserData> convertHtmlToCollection(String pageName) throws Exception {
		return filterHtml(readFile(pageName + ".html"));
	}

	@Test
	public void userAgentsTest() throws Exception {
		testBrowserParser(convertHtmlToCollection("InternetExplorer"), Browser.IE, 5, 11, Browser.BOT);
		testBrowserParser(convertHtmlToCollection("IEMobile"), Browser.IEMOBILE, 6, 11);
		testBrowserParser(convertHtmlToCollection("Firefox"), Browser.FIREFOX, 1, 40);
		testBrowserParser(convertHtmlToCollection("Chrome"), Browser.CHROME, 8, 41);
		testBrowserParser(convertHtmlToCollection("Safari"), Browser.SAFARI, 4, 8, Browser.APPLE_WEB_KIT);
		testBrowserParser(convertHtmlToCollection("AndroidWebkitBrowser"), Browser.ANDROID_WEB_KIT, 0, 3, Browser.SAFARI);
		testBrowserParser(convertHtmlToCollection("Opera"), Browser.OPERA, 9, 28);
		testBrowserParser(convertHtmlToCollection("OperaMobile"), Browser.OPERA_MOBILE, 0, 28, Browser.OPERA);
		testBrowserParser(convertHtmlToCollection("OperaMini"), Browser.OPERA_MINI, 0, 5, Browser.OPERA);
	}

}
