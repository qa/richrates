/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2011, Red Hat, Inc. and individual contributors
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

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * A bean used on the page with table.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
@ManagedBean
@ViewScoped
public class TableBean implements Serializable {

    private static final long serialVersionUID = -1L;
    @ManagedProperty(value = "#{ratesBean.issueDate}")
    private Date selectedDate;
    @ManagedProperty(value = "#{ratesBean.currencies}")
    private Map<Date, Map<String, Double>> currencies;

    /**
     * Initializes private fields of the class.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void initialize() {
    }

    /**
     * Getter for the selected date.
     * @return date for which a table should be displayed
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * Setter for selected date
     * @param selectedDate date for which a table should be displayed
     */
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * Getter for exchange rates.
     * 
     * @return map containing a mapping of dates, ISO codes of currencies and exchange rates
     */
    public Map<Date, Map<String, Double>> getCurrencies() {
        return currencies;
    }

    /**
     * Setter for exchange rates.
     * 
     * @param currencies
     *            map containing a mapping of dates, ISO codes of currencies and exchange rates
     */
    public void setCurrencies(Map<Date, Map<String, Double>> currencies) {
        this.currencies = currencies;
    }
}
