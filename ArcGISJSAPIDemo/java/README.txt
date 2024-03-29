## Steps to compile, package the library GeoJsonPOJOs
# In /src, compile the classes of GeoJSON Plain Old Java Objects
javac -d ../classes -cp ../lib/jackson-core-2.12.2.jar:../lib/jackson-annotations-2.12.2.jar:../lib/jackson-databind-2.12.2.jar org/freecode/demo/geojson/*.java

# In /classes, package the GeoJSON POJOs into a .jar file
jar cvf ../dist/GeoJsonPOJOs-1.0.jar org/freecode/demo/geojson

# In /src, compile the test class(es)
# use ":" to separate paths in Linux/Unix, ";" for Windows
javac -cp .:../dist/GeoJsonPOJOs-1.0.jar:../lib/jackson-core-2.12.2.jar:../lib/jackson-annotations-2.12.2.jar:../lib/jackson-databind-2.12.2.jar -d ../classes org/freecode/demo/GeoJsonTester.java

# In /classes, package the test class(es) into a .jar file
jar cvfm ../dist/GeoJsonTester-0.2.jar ../dist/manifest.txt org/freecode/demo/GeoJsonTester.class

# In /dist, run the demo application
java -jar GeoJsonTester-0.2.jar

## Steps to compile, package the library KMLPOJOs
# use ":" to separate paths in Linux/Unix, ";" for Windows
# In /src, compile the classes of KML Plain Old Java Objects
javac -d ../classes -cp ../lib/jaxb-api-2.4.0-b180830.0359.jar:../lib/jaxb-impl-2.1.jar:../lib/activation-1.1.1.jar org/freecode/demo/kml/*.java

# In /classes, package the KML POJOs into a .jar file
jar cvf ../dist/KmlPojos-0.9.jar org/freecode/demo/kml

# In /src, compile the test class(es)
# use ":" to separate paths in Linux/Unix, ";" for Windows
javac -cp .:../lib/jaxb-api-2.4.0-b180830.0359.jar:../lib/jaxb-impl-2.1.jar:../lib/activation-1.1.1.jar:../dist/KmlPojos-0.9.jar -d ../classes org/freecode/demo/KmlGeneratorTester.java

# In /classes, package the test class(es) into a .jar file
jar cvfm ../dist/KmlPojosTester-0.1.jar ../dist/manifest_kml.txt org/freecode/demo/KmlGeneratorTester.class

# In /dist, run the demo application
java -jar KmlPojosTester-0.1.jar
