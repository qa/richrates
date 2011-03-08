RichRates

= Introduction

RichRates is a sample LGPL-licenced RichFaces application that demonstrates usage of RichFaces 3.3.3 with JSF2 
and Facelets. The application consists of three pages. The first page contains a calculator with which it 
is possible to convert Euros to about 30 foreign currencies and vice versa. The second page contains a table 
with all available exchange rates for selected day. The last page displays a chart for one selected currency. 
It is possible to select time range for which this chart will be displayed. All data are provided by European 
Central Bank. Since it is a demo application, there are only data for last 90 days, excluding weekends and 
state holidays.

The application demonstrates lot of new JSF features, such as bean annotations, uses new scopes and implicit
navigation. Unfortunately, it is not possible to use View Declaration Language and JSF2's Ajax tags with
RichFaces 3.3.3.

= Requirements
Apache Maven 2.2.1
OpenJDK or Sun JDK 6
JBoss AS 5.1.0.GA or Tomcat 6.0.20

= Structure 

The project consists of two modules -- source (containing the application) and tests (containing Selenium tests).

= Build and deploy

To build a project run the following either from project root or from the directory "source":
  mvn clean install -Prelease
This will create several WAR archives in source/target. There are WAR archives for Tomcat and JEE application
server. If you decide to run RichRates from an application server, you need to provide JSF2 libraries.

= Running tests

For running tests, see the README file in the folder "tests".

= Credits

The application uses data provided by European Central Bank (http://www.ecb.europa.eu).
The images of flags provided by RedpixArt Design (http://flags.redpixart.com).

