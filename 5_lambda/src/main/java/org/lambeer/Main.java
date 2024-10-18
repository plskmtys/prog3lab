package org.lambeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ln = null;
        beerList lt = new beerList();
        Map<String, Command> commands = new HashMap<>();
        commands.put("add", lt::add);
        commands.put("list", lt::list);
        commands.put("load", ld -> {
            try{
                lt.load(ld);
            } catch(IOException io){
                System.out.println("IOException");
            } catch(ClassNotFoundException cn){
                System.out.println("ClassNotFoundException");
            }
        });
        commands.put("save", (cmd) -> {
            try {
                lt.save(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        commands.put("search", lt::search);
        commands.put("find", lt::find);
        commands.put("delete", (cmd) -> lt.delete(cmd[1]));
        commands.put("exit", (cmd) -> System.exit(0));


        while ((ln = br.readLine()) != null){
            String[] strings = ln.split(" ");
            Command command = commands.get(strings[0]);
            if (command != null) {
                command.execute(strings);
            } else {
                System.out.println("Ismeretlen parancs: " + strings[0]);
            }
        }
        br.close();
    }

}