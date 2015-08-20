# This project intends to provide a complete open source BI, it's free and open. #
_All contributors around the world are welcome._

## All sub projects are : ##

  * to develop an ETL and BI framework
  * to build a complete software production line, to seduce IT Services & Software Engineering for using our products
  * to provide a robust, clear and comprehensive framework
  * to build a complete ready-made ETL and BI application

Most of the components are based on Apache project. Maven2, Cayenne, Felix, Axis2 ... Exceptions are : Bonita used to describe our internal processes, smartGWT for GUI making, RGraph for charting and reporting.

## Current Release ##

`We currently test googlecode integration toward our application lifecycle : `

**- JSON parser and generator**
This is a simple JSON parser, it encodes java JSON objects to JSON string and inversely.

Download jar :[json parser v1.0.5](http://groupby.googlecode.com/files/parser%20json%20v1.0.5.jar)
_Download source : [json parser v1.0.5 src.zip](http://groupby.googlecode.com/files/parser%20JSON%20v1.0.5%20src.zip)_
_SVN Code source here : [browse](http://code.google.com/p/groupby/source/browse/#), go directly to the svn repository and checkout the project with your favorite svn client_

**- Eclipse Felix projet**
This Eclipse project provides a Eclipse console Felix interface, it allows to debug OSGI bundles.

**- Apache Cayenne module**
This OSGI bundle provides a JPA service to our application.

**- smartGW simple test**
This is a simple servlet to test smartGWT capabilities, it runs to jOnas OSGI server.

**- OSGI GroupBy Control**
authentication, session, calls resolver, cache control, dispatch, business calls

**- OSGI GroupBy Model**
Apply transformation to multiple datas source, delivers an output datas on several format.

**- OSGI GroupBy View**
Takes a source and build a final format (ie pdf, html5, ..)