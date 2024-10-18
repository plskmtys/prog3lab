package org.matyas;

import java.util.Random;

public class Asztal {
  private Jatekos[] jatekosok;
  private double tet;
  private int kor;
  private double goal;

  public Asztal(){
    jatekosok = new Jatekos[0];
    ujJatek();
  }

  public void ujJatek(){
    tet = 0;
    kor = 1;
    Random r = new Random();
    goal = 100 * r.nextDouble();
  }

  public void addJatekos(Jatekos j){
    if(jatekosok.length > 9){
      System.out.println("Tele van az asztal!");
      return;
    }
    Jatekos[] temp = new Jatekos[jatekosok.length+1];
    for(int i = 0; i < jatekosok.length; ++i) {
      temp[i] = jatekosok[i];
    }
    j.setAsztal(this);
    temp[jatekosok.length] = j;
    jatekosok = temp;
  }

  public int getKor(){return kor;}

  public void emel(double d){
    tet += d;
  }

  public void kor() throws NincsJatekos {
    if(jatekosok.length == 0) throw new NincsJatekos("Nincs Jatekos!");

    for(Jatekos j : jatekosok){
      j.lep();
    }
    ++kor;
  }

  public double getTet(){return tet;}
}
