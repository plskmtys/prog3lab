package org.matyas;

public class Nyuszi extends Jatekos {
  String szin;

  public Nyuszi(String sz){
    szin = sz;
  }

  @Override
  public void lep() {
    if(asztal.getTet() <= 50){
      asztal.emel(5);
    } else {
      System.out.println( asztal.getTet() + ", Huha!");
    }
    System.out.println("<nyuszi> Szin: " + toString() + " Kor: " + asztal.getKor() + " Tet: " + asztal.getTet());
  }

  @Override
  public String toString() {
    return szin;
  }
}
