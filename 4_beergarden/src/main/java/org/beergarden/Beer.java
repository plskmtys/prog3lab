package beergarden;
import java.io.Serializable;

public class Beer implements Serializable {
    String name;
    String style;
    double strength;

    public Beer (String nm, String sty, double stren) {
        name = nm;
        style = sty;
        strength = stren;
    }

    public String getName (){ return name; }

    public String getStyle (){ return style; }

    public double getStrength (){ return strength; }

    public String toString (){ return name + " " + style + " " + strength; }

    public Beer (String[] strings)
    {
        name = strings[1];
        style = strings[2];
        strength = Double.parseDouble(strings[3]);
    }
}