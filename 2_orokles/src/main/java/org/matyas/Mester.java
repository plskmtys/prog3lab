package org.matyas;

public class Mester extends Jatekos{
  private int fokozat;

  public Mester(int f){
    fokozat = f;
  }

  @Override
  public void lep() {
    asztal.emel(asztal.getTet()*fokozat/100);
    System.out.println("<mester> Nev: " + toString() + " Kor: " + asztal.getKor() + " Tet: " + asztal.getTet());
  }

  @Override
  public String toString(){
    return "Mester "+ fokozat;
  }
}
