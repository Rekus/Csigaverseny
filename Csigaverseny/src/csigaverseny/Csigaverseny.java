
package csigaverseny;

import java.util.Random;

public class Csigaverseny {

    public static void main(String[] args) {
        Csigaverseny verseny = new Csigaverseny();
        verseny.indit();
    }
    
    private void indit() {
        Csiga[] csigak = {new Csiga("piros"), new Csiga("zöld"), new Csiga("kék")};
        
        Fogadas fogadas = new Fogadas(csigak);
        fogadas.lehetosegek();
        fogadas.fogad("piros");
        
        for (int kor = 0; kor < 5; kor++) {
            System.out.println("Kör " + (kor + 1) + " eredményei:");
            for (Csiga csiga : csigak) {
                csiga.mozog();
                csiga.printHelyezes();
            }
            checkAcceleration(csigak);
            System.out.println("-------------------");
        }
        
        determineWinner(fogadas, csigak);
    }
    
    private void checkAcceleration(Csiga[] csigak) {
        Random rand = new Random();
        for (Csiga csiga : csigak) {
            if (rand.nextDouble() < 0.2) {
                csiga.kapGyorsitot();
            }
        }
    }
    
    private void determineWinner(Fogadas fogadas, Csiga[] csigak) {
        System.out.println("Verseny vége!");
        Csiga nyertes = csigak[0];
        for (Csiga csiga : csigak) {
            if (csiga.getMessze() > nyertes.getMessze()) {
                nyertes = csiga;
            }
        }
        
        System.out.println("A nyertes színe: " + nyertes.getSzin());
        fogadas.nyertesE(nyertes.getSzin());
    }
}

class Csiga {
    private final String szin;
    private int messze;
    
    public Csiga(String szin) {
        this.szin = szin;
        messze = 0;
    }
    
    public void mozog() {
        Random rand = new Random();
        messze += rand.nextInt(4); // 0..3
    }
    
    public void kapGyorsitot() {
        messze *= 2;
    }
    
    public String getSzin() {
        return szin;
    }
    
    public int getMessze() {
        return messze;
    }
    
    public void printHelyezes() {
        System.out.println(szin + ": " + messze);
    }
}

class Fogadas {
    private final Csiga[] csigak;
    private String fogadott;
    
    public Fogadas(Csiga[] csigak) {
        this.csigak = csigak;
        fogadott = null;
    }
    
    public void lehetosegek() {
        System.out.println("Lehetőségek: piros, zöld, kék");
    }
    
    public void fogad(String szin) {
        fogadott = szin;
        System.out.println("Fogadás: " + fogadott);
    }
    
    public void nyertesE(String nyertesSzin) {
        if (fogadott != null && fogadott.equals(nyertesSzin)) {
            System.out.println("Gratulálok, nyertél!");
        } else {
            System.out.println("Sajnos nem nyertél.");
        }
    }
}
