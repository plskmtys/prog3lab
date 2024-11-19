package org.xmllab;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JDomTagCounter {
    public static void main(String[] args) {
        if (args.length < 2 || !"-i".equals(args[0])) {
            System.out.println("Usage: java JDomTagCounter -i <filename>");
            return;
        }
        String filename = args[1];

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(filename));
            Map<String, Integer> tagCounts = new HashMap<>();
            countTags(document.getRootElement(), tagCounts);

            tagCounts.forEach((tag, count) -> System.out.println(tag + ": " + count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void countTags(Element element, Map<String, Integer> tagCounts) {
        String name = element.getName();
        tagCounts.put(name, tagCounts.getOrDefault(name, 0) + 1);

        for (Element child : element.getChildren()) {
            countTags(child, tagCounts);
        }
    }
}

