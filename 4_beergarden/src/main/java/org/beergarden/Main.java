package beergarden;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ln = null;
        beerList lt = new beerList();

        while ((ln = br.readLine()) != null)
        {
            String[] strings = ln.split(" ");

            if (strings[0].equals("exit"))
                System.exit(0);

            else if (strings[0].equals("add"))
                lt.add(strings);

            else if (strings[0].equals("list"))
                lt.list(strings);

            else if (strings[0].equals("load"))
                lt.load(strings, "Serialized");

            else if (strings[0].equals("save"))
                lt.save(strings, "Serialized");

            else if (strings[0].equals("search"))
                lt.search(strings);

            else if (strings[0].equals("find"))
                lt.find(strings);

            else if (strings[0].equals("delete"))
                lt.delete(strings[1]);
        }
        br.close();
    }

}