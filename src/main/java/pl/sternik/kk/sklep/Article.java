package pl.sternik.kk.sklep;


public class Article {

    private int id;
    private String name;
    private String description;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;

    }

    public Article() {
        super();
        this.name = "Brak nazwy";
        this.description = "Brak opisu";
        this.price = 0.0d;
    }

    public static void main(String[] args) throws BadArticleIDException {

        Article a = new Article();
        a.setId(3);
        a.setName("Mleko");
        a.setDescription("Mleko tłuste 3.5%");
        a.setPrice(3.5);

        System.out.println(a);

    }
}