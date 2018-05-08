Java WkHtmlToPdf Wrapper
=========

wkhtmltopdf and wkhtmltoimage => wkhtmltox
[wkhtmltopdf](https://github.com/wkhtmltopdf/wkhtmltopdf) 

A Java based wrapper for the [wkhtmltopdf](http://wkhtmltopdf.org/) command line tool. 
As the name implies, it uses WebKit to convert HTML documents to PDFs.

This is based on [jhonnymertz/java-wkhtmltopdf-wrapper](https://github.com/jhonnymertz/java-wkhtmltopdf-wrapper)

wkhtmltopdf http://google.com google.pdf
wkhtmltoimage http://google.com google.pdf

https://wkhtmltopdf.org/usage/wkhtmltopdf.txt


Requirements
------------
**[wkhtmltopdf](http://wkhtmltopdf.org/) must be installed and working on your system.**

Usage
------------
```java
WkHtmlToPdf pdf = new WkHtmlToPdf();

pdf.addSources(Source.from("<html><head><meta charset=\"utf-8\"></head><h1>Müller</h1></html>", "UTF-8"));
pdf.addSources(Source.fromUrl("http://www.google.com"));

// Add a Table of contents
pdf.addToc();

// The `wkhtmltopdf` shell command accepts different types of options such as global, page, headers and footers, and toc. Please see `wkhtmltopdf -H` for a full explanation.
// All options are passed as array, for example:
pdf.addArguments(
	Argument.from(Option.PageOption.EnableJavascript),
	Argument.from(Option.HeaderAndFooterOption.HeaderHtml, "file:///example.html")
);
pdf.addArguments(Option.NoFooterLine.NoFooterLine);

// Save the PDF
pdf.save(Paths.get("output.pdf"));
```

Maven dependency
------------
```xml
<dependencies>
	<dependency>
		<groupId>com.ztomic</groupId>
		<artifactId>wkhtmltopdf-java</artifactId>
		<version>0.0.1</version>
	</dependency>
</dependencies>
<repositories>
	<repository>
		<id>bintray</id>
		<name>bintray</name>
		<url>http://jcenter.bintray.com/</url>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>
</repositories>
```
