package org.xmllab;

public class BusStop {
    String name;
    String oldName;
    String wheelchair;
    boolean valid;
    double lat, lon, distance;

    @Override
    public String toString() {
        return "Megálló:\n\tNév: " + name +
            (oldName != null ? " (" + oldName + ")" : "") +
            "\n\tKerekesszék: " + wheelchair +
            "\n\tTávolság: " + String.format("%.2f m", distance);
    }
}
