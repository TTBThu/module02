import java.util.Scanner;

public class StudentClass implements IStudentManagement {
    private String classId;
    private String className;
    private String descriptions;
    private int classStatus;

    public StudentClass(String classId, String className, String descriptions, int classStatus) {
        this.classId = classId;
        this.className = className;
        this.descriptions = descriptions;
        this.classStatus = classStatus;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã lớp (5 ký tự, bắt đầu bằng 'J'): ");
        this.classId = scanner.nextLine();

        while (!isValidClassId(classId)) {
            System.out.print("Mã lớp không hợp lệ. Nhập lại mã lớp (5 ký tự, bắt đầu bằng 'J'): ");
            this.classId = scanner.nextLine();
        }

        System.out.print("Nhập tên lớp (tối đa 10 ký tự): ");
        this.className = scanner.nextLine();
        System.out.print("Nhập mô tả lớp: ");
        this.descriptions = scanner.nextLine();
        System.out.print("Nhập trạng thái lớp: ");
        this.classStatus = scanner.nextInt();
    }

    private boolean isValidClassId(String classId) {
        return classId.length() == 5 && classId.charAt(0) == 'J';
    }

    @Override
    public void displayData() {
        System.out.println("Thông tin lớp:");
        System.out.println("Mã lớp: " + classId);
        System.out.println("Tên lớp: " + className);
        System.out.println("Mô tả lớp: " + descriptions);
        System.out.println("Trạng thái lớp: " + classStatus);
    }

    public static void main(String[] args) {
        StudentClass studentClass = new StudentClass("", "", "", 0);
        studentClass.inputData();
        studentClass.displayData();
    }
}
