<h1><img src="http://enroute.osgi.org/img/enroute-logo-64.png" witdh=40px style="float:left;margin: 0 1em 1em 0;width:40px">
OSGi logger implementation</h1>

Dmytro and I created this design originaly in maven-bnd for our internal project www.observato.net and happy to share it as we think it is useful to others.

This repository is a sample logger wrapper for slf4j api including loggback implementation. You should use this project as example how to:

1. use config admin for factory setting

2. wrap other non OSGi jars

3. control logging level in runtime using configuration admin

