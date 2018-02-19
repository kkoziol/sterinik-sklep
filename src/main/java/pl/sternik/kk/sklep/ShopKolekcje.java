package pl.sternik.kk.sklep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import pl.sternik.kk.sklep.parser.BadArticleIDException;

public class ShopKolekcje {
	private final List<Employee> pracownicy = new ArrayList<Employee>();
	// klucze – obiekty klasy Article, wartości – liczności dostępnych towarów
	private final Map<Article, Integer> slownikProduktow = new HashMap<Article, Integer>();

	public boolean hasEmployee(Employee prac) {
		return pracownicy.contains(prac);
	}

	public void addEmployee(Employee prac) {
		// najpierw spr. czy taki juz jest
		if (!hasEmployee(prac)) {
			pracownicy.add(prac);
		}
	}

	public void delAllEmployee() {
		pracownicy.clear();
	}

	public boolean hasArticle(Article art) {
		return slownikProduktow.containsKey(art);
	}

	public void addArticle(Article art, int quantity) {
		int ile = 0;
		// albo nowy artykul albo tylko dodatkowa ilość istniejacego, wiec
		// najpierw spr. czy jest juz taki art na liscie
		boolean jestArt = hasArticle(art);
		// jesli jest to pobierz jego licznosc
		if (jestArt) {
			ile = getArticleQuantity(art);
		}
		// odpowiednio albo doda nowy z podaną ilością, albo zwiększy ilość dla
		// istniejącego
		slownikProduktow.put(art, ile + quantity);
	}

	public int getArticleQuantity(Article art) {
		int ile = 0;
		if (slownikProduktow.containsKey(art)) {
			ile = slownikProduktow.get(art);
		}
		return ile;
	}

	public static void main(String[] args) throws BadArticleIDException {
		ShopKolekcje sklep = new ShopKolekcje();

		// wstępna lista produktow dostępnych w sklepie
		sklep.addArticle(new Article(1001, "Mleko", "Łaciate 3,2%", 2.4), 16);
		sklep.addArticle(new Article(1022, "Chleb", "Oliwski krojony", 1.7), 11);
		sklep.addArticle(new Article(1033, "Cukier", "Cukier biały", 3.3), 4);
		sklep.addArticle(new Article(1044, "Masło", "Warlubie 82%", 4.2), 2);
		// poczatkowy stan słownika
		System.out.println("----- stan słownika produktów -----");
		System.out.println(sklep.slownikProduktow);

		// lista pracownikow
		sklep.addEmployee(new Employee(790909034, "Nowaką"));
		sklep.addEmployee(new Employee(660101034, "Kowalski"));
		Employee prac = new Employee(771212067, "Ważny");
		sklep.addEmployee(prac);

		System.out.println("----- pracownicy -----");
		sklep.pokazListe(sklep.pracownicy);
		System.out.println("Czy jest Ważny?-->" + sklep.hasEmployee(prac));
		System.out.println("Czy jest Ważny?-->" + sklep.hasEmployee(new Employee(771212067, "Ważny")));

		System.out.println(sklep.getSortedEmployees());

		System.out.println("Przed: Zapisem lub wczytaniem");

		String nazwaPliku = "pracownicy.txt";
		File plik = new File(nazwaPliku);
		if (!plik.exists()) {
			sklep.zapiszListePracownikow(nazwaPliku);
		} else {
		    sklep.delAllEmployee();
			sklep.wczytajListePracownikow(nazwaPliku);
		}
		System.out.println("Po wczytane:");
		sklep.pokazListe(sklep.pracownicy);
	}

	private void wczytajListePracownikow(String nazwaPliku) {
		int id = 0;
		String name = "";

		File file = new File(nazwaPliku);
//		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			 try (BufferedReader br = new BufferedReader(new
			 InputStreamReader(new FileInputStream(file), "UTF-8"))) {

		    String line = br.readLine();
			while (line != null) {
				Scanner s = new Scanner(line);
				id = s.nextInt();
				name = s.next();
				addEmployee(new Employee(id, name));
				s.close();
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Błąd odczytu z pliku " + nazwaPliku);
			System.exit(-1);
		}
	}

	private void zapiszListePracownikow(String nazwaPliku) {
		File file = new File(nazwaPliku);
		// Jeśli plik nie istnieje, zapisz do niego posortowaną listę
		// pracowników sklepu
		if (!file.exists()) {
//			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				 try (BufferedWriter bw = new BufferedWriter(new
				 OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
//				 file.createNewFile();
				for (Employee e : getSortedEmployees()) {
					bw.write(e.getId() + " " + e.getName());
					bw.newLine();
				}
				bw.flush();
			} catch (IOException e) {
				System.out.println("Błąd zapisu pliku " + nazwaPliku);
			}
		}
	}

	public List<Employee> getSortedEmployees() {
		List<Employee> posortowana = new ArrayList<>();
		posortowana.addAll(0, pracownicy);
		// Collections.copy(posortowana, pracownicy);
		// for (Employee employee : pracownicy) {
		// posortowana.add(employee);
		// }
		Collections.sort(posortowana);
		return posortowana;
	}

	public List<Employee> getSortedEmployeesByID() {
		List<Employee> posortowana = new ArrayList<>();
		posortowana.addAll(0, pracownicy);
		Collections.sort(posortowana, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getId() == o2.getId() ? 0 : o1.getId() - o2.getId();
			}

		});

		// Collections.sort(listaEmp, new Comparator<Person>() {
		// @Override
		// public int compare(Person obj1, Person obj2) {
		// int id1 = obj1.getId();
		// int id2 = obj2.getId();
		// return id1 == id2 ? 0 : id1 < id2 ? -1 : 1;
		// }
		// });
		return posortowana;
	}

	public String pokazListe(List<Employee> lista) {
		String wynik = "";
		for (Employee li : lista) {
			System.out.println(li);
			wynik = wynik + li;
		}
		return wynik;
	}
}
