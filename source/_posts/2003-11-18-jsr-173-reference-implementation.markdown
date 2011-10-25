---
author: ianp
date: '2003-11-18 19:11:04'
layout: post
slug: jsr-173-reference-implementation
status: publish
title: JSR-173 Reference Implementation
wordpress_id: '21'
categories:
- Java
- Technology
- XML
---

I've been looking at the new streaming API for XML
([JSR-173](http;//www.jcp.org/en/jsr/detail?id=173)), I've been
generally impressed but have found a bug in the reference
implementation, here's the details, using this test program:

~~~~ {lang="Java" line="1"}
import java.io.*;
import javax.xml.stream.*;
public class StaxWriterTest {
    static String nsURI = "http://ianp.org/nsURI";
    static String nsPrefix = "a";
    static int depth = 0; // Used to pretty print the output.
    static XMLStreamWriter w;
    public static void main(String[] args) {
        try {
            w.writeStartDocument();
            indent(1);
            w.writeStartElement(nsURI, "root");
            w.writeNamespace(nsPrefix, nsURI);
            indent(0);
            w.writeEmptyElement(nsURI, "levelOne");
            w.writeAttribute(nsURI, "foo", "foo");
            indent(0);
            w.writeStartElement(nsURI, "levelOne");
            w.writeEndElement();
            indent(1);
            w.writeStartElement(nsURI, "levelOne");
            indent(1);
            w.writeEmptyElement(nsURI, "levelTwo");
            w.writeAttribute(nsURI, "foo", "foo");
            indent(-2);
            w.writeEndElement();
            indent(0);
            w.writeStartElement(nsURI, "levelOne");
            w.writeEndElement();
            indent(-1);
            w.writeEndElement();
            w.flush();
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    static void indent(int d) {
        try {
            if (d < 0) { depth += d; }
            for (int i = 0; i < depth; ++i)
            w.writeCharacters("  ");
            if (d > 0) { depth += d; }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
~~~~

This is using version 7 of the reference implementation, by the way. The
program should produce this output:

~~~~ {lang="XML" line="1"}
<?xml version='1.0' encoding='utf-8'?>
<a:root xmlns:a="http://ianp.org/nsURI">
    <a:levelOne a:foo="foo"/>
    <a:levelOne/></a:levelOne>
    <a:levelOne>
        <a:levelTwo a:foo="foo"/>
    </a:levelOne>
    <a:levelOne/></a:levelOne>
</a:root>
~~~~

But actually produces this:

~~~~ {lang="XML" line="1"}
<?xml version='1.0' encoding='utf-8'?>
<a:root xmlns:a="http://ianp.org/nsURI">
    <a:levelOne a:foo="foo"/>
    <a:levelOne/></a:levelOne>
    <a:levelOne/>
        <a:levelTwo a:foo="foo"/>
    </a:levelOne>
    <a:levelOne/></a:levelOne>
</a:root>
~~~~

Line 5 is generated as an empty element instead of a start element. I've
pointed this out the to JCP committee, we'll see if they repsond.
