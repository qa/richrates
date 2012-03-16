# RichRates

RichRates is a sample LGPL-licenced web application that demonstrates usage of Java EE 6 platform technologies
such as JSF 2, RichFaces 4, CDI and Bean Validation. The application consists of three pages. The first page contains a calculator with which it 
is possible to convert Euros to about 30 foreign currencies and vice versa. The second page contains a table 
with all available exchange rates for selected day. The last page displays a chart for one selected currency. 
It is possible to select time range for which this chart will be displayed. All data are provided by European 
Central Bank. Since it is a demo application, there are only data for last 90 days, excluding weekends and 
state holidays.

The application demonstrates lot of new JSF features, such as bean annotations, new scopes, implicit
navigation, View Declaration Language and JSF2's Ajax tag.

## Requirements

* Apache Maven 3.0.3 or newer
* OpenJDK 6 or Sun JDK 6 or newer
* JBoss AS 7.0.2 or newer

## Build and deploy

To build a project run the following from project root:

    mvn clean package 

This will create a WAR archive in directory "target". This WAR is deployable to JBoss AS 7.

## Running tests

There are three types of tests featured:
1. TestNG unit tests;
2. Arquillian functional tests; and
3. Selenium functional tests.

To execute unit tests, run 

    mvn clean package

To execute only Arquillian functional tests, run 

    mvn clean verify -P jbossas-managed-71,ftest

To execute both unit tests and functional tests, run

    mvn clean verify -P jbossas-managed-71,all-tests

All functional tests run in real Java EE container so the commands above will download JBoss AS 7.1.1.Final from a Maven repository, start the container, deploy the application, start Firefox, and execute the tests.

## Credits

The application uses data provided by [European Central Bank](http://www.ecb.europa.eu).
The images of flags provided by [RedpixArt Design](http://flags.redpixart.com).

