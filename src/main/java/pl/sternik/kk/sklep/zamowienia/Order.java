package pl.sternik.kk.sklep.zamowienia;

public class Order {

    double amount;
    Platnosc platnosc;

    public Order(Platnosc type, double amount) {
        platnosc = type;
        this.amount = amount;
    }

    public boolean payForOrder() {
        return platnosc.zaplac(amount);
    }

    public boolean payForOrder(Platnosc platnosc) {
        return platnosc.zaplac(amount);
    }
    
    public static void main(String[] args) {
        Order order  = new Order(new Karta(), 100.1);
        order.payForOrder();
        
        order.payForOrder(new Gotowka());
    }
}
