package org.matyas;

public class Main {
  public static void main(String[] args) {
    Asztal asztal = new Asztal();
    for (int i = 0; i < 2; ++i) {
      Kezdo k = new Kezdo("" + i);
      asztal.addJatekos(k);
    }

    Robot r = new Robot();
    asztal.addJatekos(r);

    for(int i = 0; i < 3; ++i){
      asztal.kor();
    }

    Asztal ures = new Asztal();
    try{
      ures.kor();
    } catch (NincsJatekos n) {
      System.out.println("Nincs Jatekos!");
    }

    asztal.ujJatek();
    asztal.addJatekos(new Nyuszi("kek"));
    asztal.addJatekos(new Mester(20));
    for(int i = 0; i < 10; ++i){
      asztal.kor();
    }
  }
}
