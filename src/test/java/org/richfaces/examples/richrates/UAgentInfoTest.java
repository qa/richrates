/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2012, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.richfaces.examples.richrates;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @since 4.2.0
 */
public class UAgentInfoTest {

    private static final String IPHONE_USER_AGENT = "HTTP_USER_AGENT=Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1C25 Safari/419.3";
    private static final String IPAD_USER_AGENT = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";
    private static final String HTC_TATTOO_USER_AGENT = "Mozilla/5.0 (Linux; U; Android 1.6; en-gb; HTC Tattoo Build/DRC79) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2";

    @Test
    public void testIPhone() {
        UAgentInfo info = new UAgentInfo(IPHONE_USER_AGENT, null);
        Assert.assertTrue(info.detectTierIphone(), "iPhone is a phone.");
        Assert.assertFalse(info.detectTierTablet(), "iPhone is not any tablet.");
    }
    
    @Test
    public void testIPad() {
        UAgentInfo info = new UAgentInfo(IPAD_USER_AGENT, null);
        Assert.assertFalse(info.detectTierIphone(), "iPad is not any phone.");
        Assert.assertTrue(info.detectTierTablet(), "iPad is a tablet.");
    }
    
    @Test
    public void testHtcTattoo() {
        UAgentInfo info = new UAgentInfo(HTC_TATTOO_USER_AGENT, null);
        Assert.assertTrue(info.detectAndroid(), "HTC Tattoo is an Android device.");
        Assert.assertTrue(info.detectAndroidPhone(), "HTC Tattoo is an Android phone.");
        Assert.assertFalse(info.detectAndroidTablet(), "HTC Tattoo is not any Android tablet.");
    }
}
