package pl.sternik.kk.sklep;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import pl.sternik.kk.sklep.parser.ArticleIdAlreadyUsedException;
import pl.sternik.kk.sklep.parser.BadArticleIDException;

public class Article {

    private int id;
    private String name;
    private String description;
    private double price;

    static int counter;

    private static final Logger log = Logger.getLogger("Article");

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
        printChange();
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }

    public Article(int id, String name, String description, double price) throws BadArticleIDException {
        super();
        if (id < 0) {
            throw new BadArticleIDException("Id nie moze być ujemne!");
        }
        if (id < counter) {
            throw new ArticleIdAlreadyUsedException(id);
        }
        counter = id;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Article() {
        super();
        counter++;
        this.id = counter;
        this.name = "Brak nazwy";
        this.description = "Brak opisu";
        this.price = 0.0d;
    }

    protected void printChange() {
        log.debug("Zmieniono wartość pola");
    }

    public static boolean findDataWOpisie(String opis) {
        boolean wynik = false;

        Pattern p = Pattern.compile("\\d\\d-\\d\\d-\\d\\d\\d\\d");
        Matcher m = p.matcher(opis);

        while (m.find()) {
            String mozeData = m.group();
            // http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
//            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat df = DateFormat.getDateInstance();
            
            Date data = new Date();

            try {
                data = df.parse(mozeData);
                System.out.println("[Wykryto datę w opisie produktu: \"" + opis + "\"] " + df.format(data) + "na pozycji " + m.start());
                wynik = true;
            } catch (ParseException e) {
                System.out.println("Błąd parsowania daty ze stringu: " + mozeData + " " + e.getMessage());
            }
        }
        return wynik;
    }

    public static void main(String[] args) {
        Article a;
        try {
            a = new Article(-3, "Mleko", "Mleko tłuste 3.5%", 3.5);
            System.out.println(a);
            a.setPrice(4);
            System.out.println(a);
        } catch (ArticleIdAlreadyUsedException e) {
            e.printStackTrace();
        } catch (BadArticleIDException e) {
            e.printStackTrace();
        }
    }
}