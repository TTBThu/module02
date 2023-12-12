import java.util.Scanner;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private double markCss;
    private double markHtml;
    private double markJs;
    private double markAvg;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, double markCss, double markHtml, double markJs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.markCss = markCss;
        this.markHtml = markHtml;
        this.markJs = markJs;
        calculateAvg();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMarkCss() {
        return markCss;
    }

    public void setMarkCss(double markCss) {
        this.markCss = markCss;
        calculateAvg();
    }

    public double getMarkHtml() {
        return markHtml;
    }

    public void setMarkHtml(double markHtml) {
        this.markHtml = markHtml;
        calculateAvg();
    }

    public double getMarkJs() {
        return markJs;
    }

    public void setMarkJs(double markJs) {
        this.markJs = markJs;
        calculateAvg();
    }

    public double getMarkAvg() {
        return markAvg;
    }

    /**
     * Tính điểm trung bình của học viên
     */
    private void calculateAvg() {
        markAvg = (markCss + markHtml + markJs) / 3.0;
    }

    /**
     * Nhập thông tin cho học viên (có thêm tham số cho hàm nếu cần)
     */
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        setId(scanner.nextLine());
        System.out.print("Enter first name: ");
        setFirstName(scanner.nextLine());
        System.out.print("Enter last name: ");
        setLastName(scanner.nextLine());
        System.out.print("Enter CSS mark: ");
        setMarkCss(scanner.nextDouble());
        System.out.print("Enter HTML mark: ");
        setMarkHtml(scanner.nextDouble());
        System.out.print("Enter JS mark: ");
        setMarkJs(scanner.nextDouble());
        scanner.nextLine(); // Consume newline character
        calculateAvg();
    }

    /**
     * Hiển thị thông tin học viên ra màn hình
     */
    public void output() {
        System.out.println("Student ID: " + getId());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("CSS Mark: " + getMarkCss());
        System.out.println("HTML Mark: " + getMarkHtml());
        System.out.println("JS Mark: " + getMarkJs());
        System.out.println("Average Mark: " + getMarkAvg());
    }
}
