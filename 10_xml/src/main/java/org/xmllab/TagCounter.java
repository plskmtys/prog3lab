package org.xmllab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashMap;
import java.util.Map;

public class TagCounter extends DefaultHandler {
    private final Map<String, Integer> tagCounts = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagCounts.put(qName, tagCounts.getOrDefault(qName, 0) + 1);
    }

    @Override
    public void endDocument() throws SAXException {
        tagCounts.forEach((tag, count) -> System.out.println(tag + ": " + count));
    }

    public static void main(String[] args) {
        if (args.length < 2 || !"-i".equals(args[0])) {
            System.out.println("Usage: java TagCounter -i <filename>");
            return;
        }
        String filename = args[1];

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new java.io.File(filename), new TagCounter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

