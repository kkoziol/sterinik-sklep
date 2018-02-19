package pl.sternik.kk.sklep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

public class Z38 {

	public static void main(String[] args) {
		Employee emp = new Employee(1, "Stefan",new BigDecimal("5001"));
		emp.setAddress(new Address("Cicha 11"));
		
		System.out.println("Z:" + emp);
//		Z38.zapiszPracObiekt("stefan.obj", emp);
		Employee wczytany = Z38.wczytajPracObiekt("stefan.obj");
		System.out.println("w:" + wczytany);
	}
	static public int zapiszPracObiekt(String nazwaPliku, Employee emp) {
		File file = new File(nazwaPliku);
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));) {
			os.writeObject(emp);
		} catch (IOException e) {
			System.out.println("Wystąpił błąd podczas zapisu do pliku " + nazwaPliku + " : " + e.getMessage());
		}
		return 1;
	}
	public static Employee wczytajPracObiekt(String nazwaPliku) {
		File file = new File(nazwaPliku);
		Employee emp = new Employee();
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));) {
			emp = (Employee) is.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (ClassCastException e) {
			System.out.println("ClassCastException: " + e.getMessage());
		}
		return emp;
	}
}
