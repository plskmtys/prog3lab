package org.matyas;

public class Robot extends Jatekos {
  private int id;
  private static int next_id = 0;

  public Robot(){
    id = next_id;
    ++next_id;
  }

  public void lep(){
    System.out.println("<robot> Nev: " + toString() + " Kor: " + asztal.getKor() + " Tet: " + asztal.getTet());
  }

  public String toString(){
    return "Robot" + id;
  }
}
