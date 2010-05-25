#!/bin/bash
mvn -e exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.classpathScope="runtime" -Dexec.args="-database.0 file:target/data/list-manager-test"
