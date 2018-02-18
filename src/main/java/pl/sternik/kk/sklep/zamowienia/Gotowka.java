package pl.sternik.kk.sklep.zamowienia;

public class Gotowka implements Platnosc {

    @Override
    public boolean zaplac(double kwota) {
        System.out.println("Przyjmuje gotóweczke");
        return true;
    }

}
