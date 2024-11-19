package org.xmllab;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

public class JDomBusStopExtractor {
    public static void main(String[] args) {
        if (args.length < 4 || !"-i".equals(args[0]) || !"-o".equals(args[2])) {
            System.out.println("Usage: java JDomBusStopExtractor -i <input_file> -o <output_file>");
            return;
        }
        String inputFile = args[1];
        String outputFile = args[3];

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(inputFile));
            Element root = document.getRootElement();

            filterBusStops(root);

            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(outputFile));
            System.out.println("Filtered bus stops written to: " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void filterBusStops(Element root) {
        Iterator<Element> iterator = root.getChildren().iterator();

        while (iterator.hasNext()) {
            Element node = iterator.next();
            if (!"node".equals(node.getName())) {
                iterator.remove();
                continue;
            }

            boolean isBusStop = node.getChildren("tag").stream()
                .anyMatch(tag -> "highway".equals(tag.getAttributeValue("k")) &&
                    "bus_stop".equals(tag.getAttributeValue("v")));
            if (!isBusStop) {
                iterator.remove();
            }
        }
    }
}

