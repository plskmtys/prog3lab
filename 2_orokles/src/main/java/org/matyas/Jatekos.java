package org.matyas;

public abstract class Jatekos {
  protected Asztal asztal;

  public void lep(){
    System.out.println("Kor: " + asztal.getKor() + "\nTet: " + asztal.getTet());
  }

  public void setAsztal(Asztal a){
    asztal = a;
  }
}
