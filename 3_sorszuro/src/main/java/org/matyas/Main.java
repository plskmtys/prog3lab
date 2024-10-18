package org.matyas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 1) throw new Exception("hiba");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while((s = br.readLine()) != null){
            if (s.matches(".*" + args[0] + ".*"))
						System.out.println(s);
        }
  }
}