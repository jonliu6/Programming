## Steps to compile, package the library
# In /src, compile the classes of GeoJSON Plain Old Java Objects
javac -d ../classes org/freecode/demo/geojson/*.java

# In /classes, package the GeoJSON POJOs into a .jar file
jar cvf ../dist/GeoJsonPOJOs-0.9.jar org

# In /src, compile the test class(es)
javac -cp .;../dist/GeoJsonPOJOs-0.9.jar -d ../classes org/freecode/demo/*.java

# In /classes, package the test class(es) into a .jar file
jar cvfm ../dist/GeoJsonTester-0.1.jar ../dist/manifest.txt org/freecode/demo/GeoJsonTester.class

# In /dist, run the demo application
java -jar GeoJsonTester-0.1.jar
