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

import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.richfaces.examples.richrates.annotation.ExchangeRates;
import org.richfaces.examples.richrates.annotation.IssueDate;

/**
 * Validates that the first date is sooner than the other.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @since 4.2.1
 */
@RequestScoped
@FacesValidator("dateValidator")
public class DateValidator implements Validator {

    @Inject
    @ExchangeRates
    private Map<Date, Map<String, Double>> currencies;
    @Inject
    @IssueDate
    private Date issueDate;

    /**
     * A validator for dates. It checks that tha date is not in future and that is is in the period of last 90 days.
     * 
     * @param context
     *            a Faces context
     * @param component
     *            a calendar that is being checked
     * @param value
     *            value of the calendar
     * @throws ValidatorException
     *             if the date is sooner than 90 days ago, is in future, or there are no exchange rates for the selected
     *             date
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        DateTime fromDate = new DateTime(issueDate).minusDays(89);
        DateTime toDate = new DateTime(issueDate);
        DateTime selectedDate = new DateTime(value);

        // check that the selected date is not in future
        if (selectedDate.compareTo(toDate) > 0) {
            FacesMessage message = new FacesMessage(
                "Date: There are only exchange rates for last 90 days available (excluding weekends and holidays). Select sooner date.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        // check that the selected date is not sooner than oldest exchange rates list
        if (selectedDate.compareTo(fromDate) < 0) {
            FacesMessage message = new FacesMessage(
                "Date: There are only exchange rates for last 90 days available (excluding weekends and holidays). Select later date.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        // handle special cases --
        if (!currencies.keySet().contains(selectedDate.toDate())) {
            FacesMessage message = new FacesMessage(
                "Date: There are no exchange rates for selected day available. Select later date.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
