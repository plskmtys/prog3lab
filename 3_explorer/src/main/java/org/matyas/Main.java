package org.matyas;
import java.io.*;

public class Main extends Functions {
    public static void main(String[] args) throws Exception {
        String wd = System.getProperty("user.dir");
        File f = new File(wd);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        while((line = br.readLine()) != null){
            String[] cmd = line.split(" ");
            if(cmd[0].equals("exit")){
                exit(cmd);
            } else if(cmd[0].equals("pwd")) {
                pwd(cmd, f);
            } else if(cmd[0].equals("cd")){
                f = cd(cmd, f);
            } else if(cmd[0].equals("ls")){
                ls(cmd, f);
            } else if(cmd[0].equals("mv")){
                f = mv(cmd, f);
            } else if(cmd[0].equals("cat")){
                cat(cmd, f);
            } else if(cmd[0].equals("wc")){
                wc(cmd, f);
            } else if(cmd[0].equals("grep")){
                grep(cmd, f);
            } else if(cmd[0].equals("tail")){
                tail(cmd, f);
            } else if(cmd[0].equals("head")){
                head(cmd, f);
            } else if(cmd[0].equals("cp")){
                cp(cmd, f);
            } else if(cmd[0].equals("rm")){
                rm(cmd, f);
            } else if(cmd[0].equals("mkdir")){
                mkdir(cmd, f);
            } else if(cmd[0].equals("length")){
                length(cmd, f);
            }
            
            else if(cmd[0].equals("help")){
                help();
            } else {
                help();
            }
        }
        br.close();
    }
}