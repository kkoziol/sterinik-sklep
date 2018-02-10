package pl.sternik.kk.sklep;

import java.math.BigDecimal;

public class Employee extends Person {
//	private static final long serialVersionUID = 9029245770547750504L;
	
	BigDecimal salary;
	
    public Employee() {
		super();
	}

	public Employee(int id, String name) {
		super(id, name);
	}
	public Employee(int id, String name, BigDecimal salary) {
		super(id, name);
		this.salary = salary;
	}

	@Override
    public boolean getAuthorization() {
        if (getName() != null) {
            return true;
        }
        return false;
    }

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}
	
	

	
	
}
