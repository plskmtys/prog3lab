package org.matyas;

public class Kezdo extends Jatekos {
  private String nev;

  public Kezdo(String n) {
    nev = n;
  }
  public void lep(){
    if(asztal.getKor() % 2 == 0){
      asztal.emel(1);
    }
    System.out.println("<kezdo> Nev: " + toString() + " Kor: " + asztal.getKor() + " Tet: " + asztal.getTet());
  }

  public String toString(){
    return nev;
  }
}
