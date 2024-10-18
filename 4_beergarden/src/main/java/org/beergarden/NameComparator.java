package beergarden;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer> {

    public int compareStr (Beer b1, Beer b2)
    {
        return b1.name.compareTo(b2.name);
    }

    @Override
    public int compare(Beer o1, Beer o2) {
        return compareStr(o1, o2);
    }
}