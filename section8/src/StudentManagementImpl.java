import java.util.Scanner;

public class StudentManagementImpl implements IStudentManagement {
    private float studentMark;

    // Override phương thức inputData từ interface
    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập điểm của sinh viên: ");
        studentMark = scanner.nextFloat();
    }

    // Override phương thức displayData từ interface
    @Override
    public void displayData() {
        System.out.println("Thông tin sinh viên:");
        System.out.println("Điểm: " + studentMark);
    }

    public static void main(String[] args) {
        // Sử dụng lớp triển khai interface
        IStudentManagement studentManagement = new StudentManagementImpl();
        studentManagement.inputData();
        studentManagement.displayData();
    }
}