package org.xmllab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class BusStopSorter extends DefaultHandler {
    private final double targetLat;
    private final double targetLon;
    private final List<BusStop> busStops = new ArrayList<>();
    private BusStop currentStop;

    public BusStopSorter(double targetLat, double targetLon) {
        this.targetLat = targetLat;
        this.targetLon = targetLon;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double r = 6371000;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dPhi = phi2 - phi1;
        double dLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dPhi / 2) * Math.sin(dPhi / 2) +
            Math.cos(phi1) * Math.cos(phi2) *
                Math.sin(dLambda / 2) * Math.sin(dLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return r * c;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("node".equals(qName)) {
            currentStop = new BusStop();
            currentStop.lat = Double.parseDouble(attributes.getValue("lat"));
            currentStop.lon = Double.parseDouble(attributes.getValue("lon"));
            currentStop.valid = false;
        } else if ("tag".equals(qName) && currentStop != null) {
            String key = attributes.getValue("k");
            String value = attributes.getValue("v");

            switch (key) {
                case "highway":
                    if ("bus_stop".equals(value)) {
                        currentStop.valid = true;
                    }
                    break;
                case "name":
                    currentStop.name = value;
                    break;
                case "old_name":
                    currentStop.oldName = value;
                    break;
                case "wheelchair":
                    currentStop.wheelchair = value;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("node".equals(qName) && currentStop != null && currentStop.valid) {
            currentStop.distance = calculateDistance(targetLat, targetLon, currentStop.lat, currentStop.lon);
            busStops.add(currentStop);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        busStops.sort((a, b) -> Double.compare(a.distance, b.distance));
        busStops.forEach(System.out::println);
    }

    public static void main(String[] args) {
        if (args.length < 6 || !"-i".equals(args[0]) || !"-lat".equals(args[2]) || !"-lon".equals(args[4])) {
            System.out.println("Usage: java BusStopSorter -i <filename> -lat <latitude> -lon <longitude>");
            return;
        }
        String filename = args[1];
        double lat = Double.parseDouble(args[3]);
        double lon = Double.parseDouble(args[5]);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new java.io.File(filename), new BusStopSorter(lat, lon));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

