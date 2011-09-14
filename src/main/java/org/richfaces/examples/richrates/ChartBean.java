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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;

import org.joda.time.DateTime;
import org.richfaces.event.DropEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.ExchangeRates;
import annotation.IssueDate;

/**
 * Bean used on the page with chart. Is is possible to draw a chart for one currency for selected time range.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
@Named
@SessionScoped
public class ChartBean implements Serializable {

    private static final long serialVersionUID = -1L;
    @Inject
    @IssueDate
    @Past(message = "To date: Future date cannot be selected.")
    private Date toDate;
    @Past(message = "From date: Future date cannot be selected.")
    private Date fromDate;
    @Inject
    @ExchangeRates
    private Map<Date, Map<String, Double>> currencies;
    private String selectedCurrency;
    private String draggedCurrency;
    private Logger logger;

    /**
     * Initializes class' fields.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void initialize() {
        logger = LoggerFactory.getLogger(ChartBean.class);
        selectedCurrency = "USD";
        fromDate = new DateTime(toDate).minusDays(30).toDate();
    }

    /**
     * Getter for start date.
     * 
     * @return start date for chart
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Setter for start date.
     * 
     * @param fromDate
     *            start date for chart
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Getter for end date.
     * 
     * @return end date for chart
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Setter for end date.
     * 
     * @param toDate
     *            end date for chart
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Getter for selected currency.
     * 
     * @return ISO code of the selected currency
     */
    public String getSelectedCurrency() {
        return selectedCurrency;
    }

    /**
     * Setter for selected currency.
     * 
     * @param selectedCurrency
     *            ISO code for selected currency
     */
    public void setSelectedCurrency(String selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    /**
     * Getter for dragged currency.
     * 
     * @return ISO code of the currency which was dragged
     */
    public String getDraggedCurrency() {
        return draggedCurrency;
    }

    /**
     * Setter for dragged currency.
     * 
     * @param draggedCurrency
     *            ISO code of the currency which was dragged and dropped
     */
    public void setDraggedCurrency(String draggedCurrency) {
        this.draggedCurrency = draggedCurrency;
    }

    /**
     * Getter for currency names, e.g. [EUR:Euro, USD:American Dollar].
     * 
     * @param currencyNames
     *            map containing a mapping of currency code to its long name
     */
    public void setCurrencyNames(Map<String, String> currencyNames) {
    }

    /**
     * Creates a data structure needed by chart component. It creates a set of points where X is date and Y is an
     * exchange rate.
     * 
     * @return set of points for chart
     */
    public String getCurrencyData() {
        StringBuilder data = new StringBuilder("[ ");

        DateTime toDate = new DateTime(this.toDate);
        DateTime actualDate = new DateTime(fromDate);

        while (actualDate.compareTo(toDate) <= 0) {
            if (currencies.get(actualDate.toDate()) == null) {
                actualDate = actualDate.plusDays(1);
                continue;
            }
            Number x = actualDate.getMillis();
            Number y = currencies.get(actualDate.toDate()).get(selectedCurrency).doubleValue();
            data.append("[");
            data.append(x);
            data.append(",");
            data.append(y);
            data.append("],");
            actualDate = actualDate.plusDays(1);
        }

        data.append("]");
        return data.toString();
    }

    /**
     * A method used to process drop event.
     * 
     * @param event
     *            a drop event on the chart
     */
    public void processDrop(DropEvent event) {
        selectedCurrency = (String) event.getDragValue();
    }

    @AssertTrue(message = "Dates are in wrong order.")
    public boolean isDatesCorrect() {
        logger.debug("Validating the order of dates");
        return new DateTime(fromDate).isBefore(toDate.getTime());
    }
}
