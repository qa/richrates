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
import javax.validation.constraints.Min;

/**
 * Bean for the main page. It is possible to convert from Euro to another currency or from another currency to Euro.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
@Named
@SessionScoped
public class CalculatorBean implements Serializable {

    private static final long serialVersionUID = -1L;
    private static final int FROM_EURO = 0;
    private static final int TO_EURO = 1;
    @Inject
    private RatesBean ratesBean;
    private Date selectedDate;
    private Map<Date, Map<String, Double>> currencies;
    private String selectedCurrency;
    @Min(value = 0, message = "Amount: Has to be a positive number.")
    private double amount;
    private double result;
    private int direction;

    /**
     * Initialization of class fields.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void initialize() {
        selectedCurrency = "USD";
        direction = FROM_EURO;
        amount = 0;
        selectedDate = ratesBean.getIssueDate();
        currencies = ratesBean.getCurrencies();
    }

    /**
     * Returns the amount.
     * 
     * @return amount with which calculation will be performed
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     * 
     * @param amount
     *            amount with which calculation will be performed
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns result.
     * 
     * @return result of the calcuation
     */
    public double getResult() {
        return result;
    }

    /**
     * Returns the selected date.
     * 
     * @return date with which calculation will be performed
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * Sets selected date.
     * 
     * @param selectedDate
     *            date for which calculation will be performed
     */
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * Returns selected currency. It can be whatever currency but Euro.
     * 
     * @return ISO code of currency for which calculation will be performed
     */
    public String getSelectedCurrency() {
        return selectedCurrency;
    }

    /**
     * Sets seleted currency.
     * 
     * @param selectedCurrency
     *            ISO code of currency other than Euro (EUR) that will be used in calculation
     */
    public void setSelectedCurrency(String selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    /**
     * Returns the direction of calculation (from/to Euro).
     * 
     * @return direction of calculation
     */
    public boolean getFromEuro() {
        return direction == FROM_EURO;
    }

    /**
     * Sets the map containing all data needed for calculations. Key is a date and value is a map whose key is a string
     * containing ISO code of currency and value is a double number expressing the exchange rate.
     * 
     * @param currencies
     *            a map containing all exchange rates for all available dates
     */
    public void setCurrencies(Map<Date, Map<String, Double>> currencies) {
        this.currencies = currencies;
    }

    /**
     * Calculates the result for selected amount, date and currency. It depends on whether it converts from or to Euro.
     */
    public void calculate() {
        if (direction == FROM_EURO) {
            result = amount * currencies.get(selectedDate).get(selectedCurrency);
        } else {
            result = amount / currencies.get(selectedDate).get(selectedCurrency);
        }
    }

    /**
     * Swaps selected currencies, i.e. "from Euro" changes to "to Euro" and vice versa.
     */
    public void swap() {
        direction = direction == FROM_EURO ? TO_EURO : FROM_EURO;
        calculate();
    }

    /**
     * Preparing auxiliary variables after a new currency was selected.
     */
    public void selectCurrency() {
        calculate();
    }
}
