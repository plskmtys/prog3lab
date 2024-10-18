package org.matyas;
import java.io.*;
import java.util.LinkedList;

public class Functions {
    /*template:
    protected void fun(String[] cmd){

    }
    */

    // cmds: exit, reclist, pwd, cd, ls, rm, mkdir, cp, head, mv, cat, wc, grep, tail, help

    protected static void help(){
        System.out.println("commands:\nexit, reclist, pwd, cd, ls, rm, mkdir, cp, head, mv, cat, wc, grep, tail, help");
    }

    protected static void exit(String[] cmd){
        //exit
        System.exit(0);
    }

    protected static void reclist(String[] cmd, File f){
        //recursively list dirs and files
    }

    protected static void pwd(String[] cmd, File f) throws IOException {
        //print working dir
        while(f.isFile()){
            f = f.getParentFile();
        }

        System.out.println(f.getCanonicalPath());
    }
    
    protected static File cd(String[] cmd, File f) throws IOException {
        //cd
        while(f.isFile()){
            f = f.getParentFile();
        }

        if(cmd[1].equals("..")){
            File f1 = f.getParentFile();
            if(f1 != null){
                f = f1;
            }
        } else {
            f = new File(f.getCanonicalPath(), cmd[1]);
        }

        return f;
    }

    protected static void ls(String[] cmd, File f){
        //ls
        while(f.isFile()){
            f = f.getParentFile();
        }
        File[] filesList = f.listFiles();
        if(cmd.length > 1){
            if(cmd[1]. equals("-l")) {
                if (filesList != null) {
                    for (File file : filesList) {
                        if (file.isDirectory()) {
                            System.out.println("d - " + file.getName() + " - " + file.getTotalSpace() + " bytes");
                        } else if (file.isFile()) {
                            System.out.println("f - " + file.getName() + " - " + file.getTotalSpace() + " bytes");
                        }
                    }
                } else {
                    System.out.println("Hiba");
                }
            }
        } else {
            if (filesList != null) {
                for (File file : filesList) {
                    if (file.isDirectory()) {
                        System.out.println(file.getName());
                    } else {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Hiba");
            }
        }
    }

    protected static void rm(String[] cmd, File f){
        //rm
    }

    protected static void mkdir(String[] cmd, File f){
        //mkdir
    }

    protected static void cp(String[] cmd, File f) throws Exception {
        //cp
        
    }

    protected static void head(String[] cmd, File f) throws Exception {
        //head -n <n> <file>
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length < 4)
            throw new Exception("Használat: head -n <hány sor> <fájlnév>");
        if(!cmd[1].equals("-n"))
            throw new Exception("Használat: head -n <hány sor> <fájlnév>");
        
        File source = new File(f, cmd[3]);
        BufferedReader br = new BufferedReader(new FileReader(source));
        String s = br.readLine();
        if (s == null)
            System.out.println("üres a file");
        else
            System.out.println(s);
        int n = 1;
        while ((s = br.readLine()) != null && n < Integer.parseInt(cmd[2])) {
            System.out.println(s);
            ++n;
        }
        br.close();
    }

    // innen
    protected static File mv(String[] cmd, File f) throws Exception {
        //mv
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length > 2) {
            File oldf = new File(f, cmd[1]);
            File newf = new File(f, cmd[2]);
            if (newf.exists()) {
                System.out.println("már van ilyen fájl");
                return f;
            }
            if (!oldf.exists()) {
                System.out.println("nincs ilyen fájl");
                return f;
            }
            boolean success = oldf.renameTo(newf);
            if (!success)
                System.out.print("fájl átnevezése sikertelen");
            f = newf;
        } else
            System.out.print("hibás paraméterek");
        return f;
    }

    protected static void cat(String[] cmd, File f) throws Exception {
        //cat
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length < 2)
            throw new Exception("Használat: cat <fájlnév>");
        File source = new File(f, cmd[1]);
        BufferedReader br = new BufferedReader(new FileReader(source));
        String s = br.readLine();
        if (s == null)
            System.out.println("üres a file");
        else
            System.out.println(s);
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        br.close();
    }

    protected static void wc(String[] cmd, File f) throws Exception {
        //wc
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length < 2)
            throw new Exception("Használat: wc <fájlnév>");
        int sorok = 0;
        int szavak = 0;
        int betuk = 0;
        File source = new File(f, cmd[1]);
        BufferedReader br = new BufferedReader(new FileReader(source));
        String s = null;
        while ((s = br.readLine()) != null) {
            sorok++;
            String[] words = s.split("\\s+");
            szavak += words.length;
            for (String word : words) {
                betuk += word.length();
            }
        }

        System.out.println("Sorok száma: " + sorok);
        System.out.println("Szavak száma: " + szavak);
        System.out.println("Betűk száma: " + betuk);
        br.close();
    }
    //idaig

    protected static void grep(String[] cmd, File f) throws Exception {
        //grep
		while (f.isFile())
			f = f.getParentFile();
		if (cmd.length < 3)
			System.out.println("Hiba");
		else {
			File source = new File(f, cmd[2]);
			if (!source.exists())
				System.out.println("Hiba");
			else {
				BufferedReader br = new BufferedReader(new FileReader(source));
				String s = null;
				while ((s = br.readLine()) != null) {
					if (s.matches(".*" + cmd[1] + ".*"))
						System.out.println(s);
                }
                br.close();
            }
		}
	}

    protected static void tail(String[] cmd, File f) throws Exception {
        //tail -n <n> <file>
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length < 4)
            throw new Exception("Használat: tail -n <hány sor> <fájlnév>");
        if(!cmd[1].equals("-n"))
            throw new Exception("Használat: tail -n <hány sor> <fájlnév>");
        File source = new File(f, cmd[3]);
        BufferedReader br = new BufferedReader(new FileReader(source));
        String s = br.readLine();
        LinkedList<String> sorok = new LinkedList<String>();
        if (s == null)
            System.out.println("üres a file");
        else
            sorok.addLast(s);
        int n = 1;
        while ((s = br.readLine()) != null && n < Integer.parseInt(cmd[2])) {
            sorok.addLast(s);
            ++n;
        }
        while((s = br.readLine()) != null){
            sorok.addLast(s);
            sorok.removeFirst();
        }
        for(String sor : sorok)
            System.out.println(sor);
        br.close();
    }

    public static void length(String[] cmd, File f) throws Exception {
        while (f.isFile())
            f = f.getParentFile();
        if (cmd.length < 2)
            throw new Exception("Használat: length <fájlnév>");
        File source = new File(f, cmd[1]);
        BufferedReader br = new BufferedReader(new FileReader(source));
        String s = br.readLine();
        int n = 0;
        if (s == null)
            System.out.println("üres a file");
        else
            ++n;
        while ((s = br.readLine()) != null) {
            ++n;
        }
        System.out.println(n);
        br.close();
    }
}
