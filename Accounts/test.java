
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setName("Scooby");
		emp.setidNum("612275");
		emp.sethireDate("05/23/1969");
		emp.setweeklySal(500.50);
		double h = emp.getweeklySal();
		emp.getName();
		emp.getidNum();
		emp.gethireDate();
		emp.getweeklySal();
		Employee employ = new Employee("Scrappy", "55324", "11/03/1989");
		employ.setweeklySal(350.75);
		double j = employ.getweeklySal();
		System.out.println(emp.toString() + h);
		System.out.println(employ.toString() + j);
		if(emp.equals(employ))
			System.out.println("equal");
		else{System.out.println("not equal");}
		HourlyEmployee ee = new HourlyEmployee();
		HourlyEmployee em = new HourlyEmployee("Fred", "54321", "12/24/1996", 9.25);
		em.setweeklyHours(40);
		ee.setName("Daphne");
		ee.setidNum("741523");
		ee.sethireDate("03/21/2009");
		ee.setweeklyHours(60);
		ee.sethourlyRate(15);
		ee.getweeklyHours();
		ee.gethourlyRate();
		System.out.println(ee.toString());
		double s = ee.getweeklySal();
		System.out.println(s);
		System.out.println(em.toString());
		double st = em.getweeklySal();
		System.out.println(st);
		Manager m = new Manager();
		m.setName("Velma");
		m.setidNum("68532");
		m.sethireDate("01/31/2006");
		m.setannualSal(99000);
		m.getannualSal();
		System.out.println(m.toString());
		Manager ma = new Manager("Shaggy", "952136", "08/30/1988", 66000);
		System.out.println(ma.toString());
	}

}
