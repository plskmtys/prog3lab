package beergarden;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer> {

    public int compareStr (Beer b1, Beer b2)
    {
        return b1.style.compareTo(b2.style);
    }

    @Override
    public int compare(Beer o1, Beer o2) {
        return compareStr(o1, o2);
    }

}