Sales predictor v0.1
====================

Sales predictor is a small Relex homework exercise.

Motivation
----------

Purpose of this exercise is to show basic command of Java, programming and statistical modeling.
This project simulates predicting sales of some product using generated data.


Getting started
---------------

The project is basic Maven-based Java project. Prerequisites are Java 8 and Maven 3. 

Some basic statistical method and functions are available through [Apache commons math library](https://commons.apache.org/proper/commons-math/javadocs/api-3.5/index.html)

To run, 

    mvn package
    java -jar target/${jar.file.name}.jar

The data is in two csv-files in src/main/resources

If you're familiar with some statistical / exploratory tools, using them to play with the data is encouraged.

Rules and target
----------------

* The implementation must be written in Java programming language.
* Using outside libraries is allowed.
* The resulting project must be runnable by itself (after maven has retrieved the libraries)

There is no correct answer, just beating the ready implementations by some margin enough. If you find that the problem
is taking days, you're probably aiming too high or this guide has been unclear. 

