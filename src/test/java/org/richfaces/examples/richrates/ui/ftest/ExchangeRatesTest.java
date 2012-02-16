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
package org.richfaces.examples.richrates.ui.ftest;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.richfaces.examples.richrates.ui.AbstractWebDriverTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author <a href="mailto:jpapouse@redhat.com">Jan Papousek</a>
 */
public class ExchangeRatesTest extends AbstractWebDriverTest<ExchangeRatesPage>{
    
    private final class CurrentPageNumberEquals implements ExpectedCondition<Boolean> {
        private int pageNumber;
        public CurrentPageNumberEquals(int pageNumber) {
            this.pageNumber = pageNumber;
        }
        @Override
        public Boolean apply(WebDriver arg0) {
            return getPage().getCurrentPageNumber() == pageNumber;
        }
        
    }
    
    @Test
    public void testInit() {
        assertEquals(getPage().getCurrentPageNumber(), 1);
    }
    
    @Test
    public void testNextAndPrevious() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        getPage().next();
        wait.until(new CurrentPageNumberEquals(2));
        getPage().previous();
        wait.until(new CurrentPageNumberEquals(1));
    }
    
    @Override
    protected ExchangeRatesPage createPage(URL root) {
        return new ExchangeRatesPage(root);
    }
}
