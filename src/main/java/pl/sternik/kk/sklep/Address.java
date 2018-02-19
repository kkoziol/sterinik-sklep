package pl.sternik.kk.sklep;

public class Address {

    private String adres;

    Address() {
        adres = "";
    }

    Address(String adr) {
        this.adres = adr;
    }

    @Override
    public String toString() {
        return this.adres.toString();
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

}
