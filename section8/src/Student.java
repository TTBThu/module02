import java.util.ArrayList;
import java.util.Scanner;

public class Student implements IStudentManagement {
    private String studentId;
    private String studentName;
    private int age;
    private boolean sex;
    private StudentClass classStudent;
    private ArrayList<Float> listMarkJavaScript;
    private ArrayList<Float> listMarkJavaCore;
    private ArrayList<Float> listMarkJavaWeb;
    private float avgMark;
    private String GPA;
    private boolean studentStatus;

    public Student(String studentId, String studentName, int age, boolean sex, StudentClass classStudent) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.sex = sex;
        this.classStudent = classStudent;
        this.listMarkJavaScript = new ArrayList<>();
        this.listMarkJavaCore = new ArrayList<>();
        this.listMarkJavaWeb = new ArrayList<>();
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mã sinh viên (6 ký tự, bắt đầu bằng 'S'): ");
        this.studentId = scanner.nextLine();

        while (!isValidStudentId(studentId)) {
            System.out.print("Mã sinh viên không hợp lệ. Nhập lại mã sinh viên (6 ký tự, bắt đầu bằng 'S'): ");
            this.studentId = scanner.nextLine();
        }

        System.out.print("Nhập tên sinh viên (20-50 ký tự): ");
        this.studentName = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        this.age = scanner.nextInt();
        System.out.print("Nhập giới tính (true - Nam, false - Nữ): ");
        this.sex = scanner.nextBoolean();
        System.out.println("Nhập thông tin lớp sinh viên:");
        classStudent = new StudentClass("", "", "", 0);
        classStudent.inputData();
        System.out.println("Nhập điểm môn lập trình JavaScript (lần cuối): ");
        float markJavaScript = scanner.nextFloat();
        listMarkJavaScript.add(markJavaScript);
        System.out.println("Nhập điểm môn lập trình Java Core (lần cuối): ");
        float markJavaCore = scanner.nextFloat();
        listMarkJavaCore.add(markJavaCore);
        System.out.println("Nhập điểm môn lập trình Java Web (lần cuối): ");
        float markJavaWeb = scanner.nextFloat();
        listMarkJavaWeb.add(markJavaWeb);

        calAvgMark();
        getGPA();
    }

    private boolean isValidStudentId(String studentId) {
        return studentId.length() == 6 && studentId.charAt(0) == 'S';
    }

    @Override
    public void displayData() {
        System.out.println("Thông tin sinh viên:");
        System.out.println("Mã sinh viên: " + studentId);
        System.out.println("Tên sinh viên: " + studentName);
        System.out.println("Tuổi: " + age);
        System.out.println("Giới tính: " + (sex ? "Nam" : "Nữ"));
        System.out.println("Thông tin lớp:");
        classStudent.displayData();
        System.out.println("Điểm môn lập trình JavaScript (lần cuối): " + listMarkJavaScript.get(0));
        System.out.println("Điểm môn lập trình Java Core (lần cuối): " + listMarkJavaCore.get(0));
        System.out.println("Điểm môn lập trình Java Web (lần cuối): " + listMarkJavaWeb.get(0));
        System.out.println("Điểm trung bình: " + avgMark);
        System.out.println("Xếp loại: " + GPA);
    }

    @Override
    public void calAvgMark() {
        avgMark = (listMarkJavaScript.get(0) + listMarkJavaCore.get(0) + listMarkJavaWeb.get(0)) / 3;
    }

    @Override
    public void getGPA() {
        if (avgMark >= 9) {
            GPA = "Giỏi";
        } else if (avgMark >= 7) {
            GPA = "Khá";
        } else if (avgMark >= 5) {
            GPA = "Trung bình";
        } else {
            GPA = "Yếu";
        }
    }

    public static void main(String[] args) {
        StudentClass studentClass = new StudentClass("", "", "", 0);
        Student student = new Student("", "", 0, true, studentClass);
        student.inputData();
        student.displayData();
    }
}
