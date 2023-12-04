import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class project {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> emailList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            System.out.print("Nhập email thứ " + i + ": ");
            String email = scanner.nextLine();

            // Kiểm tra định dạng email
            if (isValidEmail(email)) {
                emailList.add(email);
            } else {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                i--; // Nếu email không hợp lệ, giảm biến i để nhập lại email
            }
        }
        Collections.sort(emailList);
        System.out.println("\nDanh sách email sau khi sắp xếp từ A-Z:");
        for (String email : emailList) {
            System.out.println(email);
        }
    }

    // Phương thức kiểm tra định dạng email
    private static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
}
