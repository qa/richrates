RichRates

= Introduction

RichRates is a sample LGPL-licenced RichFaces application that demonstrates usage of RichFaces 4 with JSF2 
and Facelets. The application consists of three pages. The first page contains a calculator with which it 
is possible to convert Euros to about 30 foreign currencies and vice versa. The second page contains a table 
with all available exchange rates for selected day. The last page displays a chart for one selected currency. 
It is possible to select time range for which this chart will be displayed. All data are provided by European 
Central Bank. Since it is a demo application, there are only data for last 90 days, excluding weekends and 
state holidays.

The application demonstrates lot of new JSF features, such as bean annotations, new scopes, implicit
navigation, View Declaration Language and JSF2's Ajax tag.

= Requirements
Apache Maven 2.2.1 or newer
OpenJDK or Sun JDK 6
JBoss AS 6 or JBoss AS 7

= Build and deploy

To build a project run the following either from project root:
  mvn clean install 
This will create a WAR archive in directory "target". This WAR is deployable to JBoss AS 6 and JBoss AS 7.

= Credits

The application uses data provided by European Central Bank (http://www.ecb.europa.eu).
The images of flags provided by RedpixArt Design (http://flags.redpixart.com).

