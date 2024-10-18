package org.lambeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.*;

public class beerList {

    ArrayList<Beer> lt = new ArrayList<Beer>();

    static Map<String, Comparator<Beer>> comps;
    static {
        comps = new HashMap<>();
        comps.put("name", Comparator.comparing(Beer::getName));
        comps.put("style", Comparator.comparing(Beer::getStyle));
        comps.put("strength", Comparator.comparingDouble(Beer::getStrength));
    }

    static List<String> lparams;
    static{
        lparams = new LinkedList<>();
        lparams.add("name");
        lparams.add("style");
        lparams.add("strength");
    }

    protected void add (String[] cmd) {
        Beer b = new Beer(cmd);

        if (cmd.length > 3)
            lt.add(b);
    }

    protected void list(String[] cmd) {
        if (cmd.length > 1 && lparams.contains(cmd[1])) {
            lparams.remove(cmd[1]);
            lparams.add(0, cmd[1]);
        }

        Comparator<Beer> cmp = comps.get(lparams.get(0));
        for (int i = 1; i < lparams.size(); i++) {
            cmp = cmp.thenComparing(comps.get(lparams.get(i)));
        }

        Collections.sort(lt, cmp);

        for (Beer beer : lt) {
            System.out.println(beer.toString());
        }
    }

    protected void load (String[] cmd) throws IOException, ClassNotFoundException {
        String fw = System.getProperty("beers.txt");
        File f = new File(fw, cmd[1]);

        if (f.exists()) {
            FileInputStream fs = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fs);
            lt = (ArrayList<Beer>) in.readObject();
            in.close();
        }
    }


    protected void save (String[] cmd) throws IOException {
        String fw = System.getProperty("beers.txt");
        File f = new File(fw, cmd[1]);

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

