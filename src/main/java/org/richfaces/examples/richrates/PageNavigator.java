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

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * A bean storing user's selected page.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 */
@Named
@SessionScoped
public class PageNavigator implements Serializable {

    private static final long serialVersionUID = -1L;

    private String currentPage;

    public String getCurrentPage() {
        String requestedPage = getViewParameter("page");
        if (currentPage == null || !currentPage.equals(requestedPage)) {
            currentPage = requestedPage;
        }
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getSampleURI() {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler handler = context.getApplication().getNavigationHandler();

        if (handler instanceof ConfigurableNavigationHandler) {
            ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) handler;

            NavigationCase navCase = navigationHandler.getNavigationCase(context, null, "/mobile/" + getCurrentPage());

            if (navCase == null) {
                navCase = new NavigationCase("/mobile.xhtml", null, null, null, "/mobile/calculator.xhtml", null,
                    false, true);
            }

            return navCase.getToViewId(context);
        }

        return null;
    }

    private String getViewParameter(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String param = (String) fc.getExternalContext().getRequestParameterMap().get(name);
        if (param != null && param.trim().length() > 0) {
            return param;
        } else {
            return null;
        }
    }
}
