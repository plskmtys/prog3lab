package org.lambeer;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {

    public int compareDb (Beer b1, Beer b2) {
        return Double.compare(b1.strength, b2.strength);
    }

    @Override
    public int compare(Beer o1, Beer o2) {
        return compareDb(o1, o2);
    }

}