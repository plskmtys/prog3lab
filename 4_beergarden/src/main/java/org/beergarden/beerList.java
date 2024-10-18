package beergarden;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class beerList {

    ArrayList<Beer> lt = new ArrayList<Beer>();

    protected void add (String[] cmd) {
        Beer b = new Beer(cmd);

        if (cmd.length > 3)
            lt.add(b);
    }

    protected void list (String[] cmd) {
        ArrayList<Beer> tmp = new ArrayList<Beer>(lt);

        if (cmd[1].equals("name"))
            Collections.sort(tmp, new NameComparator());

        else if (cmd[1].equals("style"))
            Collections.sort(tmp, new StyleComparator());

        else if (cmd[1].equals("strength"))
            Collections.sort(tmp, new StrengthComparator());

        for (Beer beer : tmp) {
            System.out.println(beer.toString());
        }
    }

    protected void load (String[] cmd, String fnm) throws IOException, ClassNotFoundException {
        String fw = System.getProperty("beers.txt");
        File f = new File(fw, fnm);

        if (f.exists()) {
            FileInputStream fs = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fs);
            lt = (ArrayList<Beer>) in.readObject();
            in.close();
        }
    }


    protected void save (String[] cmd, String fnm) throws IOException {
        String fw = System.getProperty("beers.txt");
        File f = new File(fw, fnm);

        if (!f.exists())
            f.createNewFile();

        if (f.exists()) {
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fs);
            out.writeObject(lt);
            out.close();
        }
    }


    protected void search (String[] strings) {
        if(strings.length == 2){
            for(Beer str: this.lt) {
                if (str.getName().equals(strings[1]))
                    System.out.println(str.toString());
            }
        } else if(strings.length == 3){
            if(strings[1].equals("name")){
                for(Beer str: this.lt) {
                    if (str.getName().equals(strings[2]))
                        System.out.println(str.toString());
                }
            } else if(strings[1].equals("style")){
                for(Beer str: this.lt) {
                    if (str.getStyle().equals(strings[2]))
                        System.out.println(str.toString());
                }
            } else if(strings[1].equals("strength")){
                for(Beer str: this.lt) {
                    if (str.getStrength() == Double.parseDouble(strings[2]))
                        System.out.println(str.toString());
                }
            } else {
                System.out.println("Valid parameters: name, style, strength");
            }
        } else {
            System.out.println("Usage: search [name/style/strength] <string>");
        }
    }

    protected void find (String[] strings) {
        if(strings.length == 2){
            for(Beer str: this.lt) {
                if (str.getName().contains(strings[1]))
                    System.out.println(str.toString());
            }
        } else if(strings.length == 3){
            if(strings[1].equals("name")){
                for(Beer str: this.lt) {
                    if (str.getName().contains(strings[2]))
                        System.out.println(str.toString());
                }
            } else if(strings[1].equals("style")){
                for(Beer str: this.lt) {
                    if (str.getStyle().contains(strings[2]))
                        System.out.println(str.toString());
                }
            } else if(strings[1].equals("strength")){
                for(Beer str: this.lt) {
                    if (str.getStrength() >= Double.parseDouble(strings[2]))
                        System.out.println(str.toString());
                }
            } else if(strings[1].equals("weaker")){
                for(Beer str: this.lt) {
                    if (str.getStrength() <= Double.parseDouble(strings[2]))
                        System.out.println(str.toString());
                }
            }
            else {
                System.out.println("Valid parameters: name, style, strength, weaker");
            }
        } else {
            System.out.println("Usage: find [name/style/strength/weaker] <string>");
        }
    }


    protected void delete (String b) {
        for(Beer str: lt) {
            if (str.getName().equals(b)) {
                lt.remove(str);
                return;
            }
        }
    }
}

