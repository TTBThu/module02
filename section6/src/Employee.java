import java.util.Scanner;

public class Employee implements BaseEntity {
    private int id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean checkId(Object obj) {
        if (obj instanceof Integer) {
            int otherId = (Integer) obj;
            return this.id == otherId;
        }
        return false;
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        this.id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        this.salary = scanner.nextDouble();
    }

    @Override
    public void show() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Salary: " + salary);
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.input();
        employee.show();
    }
}
