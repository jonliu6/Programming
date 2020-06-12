This is a Java sample using PDFBox 2.0.20 to create a sample PDF if not exists and add a watermark.

Compile:
  javac -cp .;commons-logging-1.2.jar;fontbox-2.0.20.jar;pdfbox-2.0.20.jar PDFBoxDemo.java

Run:
  java -cp .;commons-logging-1.2.jar;fontbox-2.0.20.jar;pdfbox-2.0.20.jar PDFBoxDemo sample.pdf "This is draft!"