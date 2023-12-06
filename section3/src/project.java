import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class project {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<product> productList = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1. Nhập sản phẩm");
            System.out.println("2. Tính giá bán cho sản phẩm");
            System.out.println("3. Hiển thị danh sách sản phẩm theo giá bán tăng dần");
            System.out.println("4. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    inputProducts();
                    break;
                case 2:
                    salePrices();
                    break;
                case 3:
                    displayProducts();
                    break;
                case 4:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 4);
    }

    private static void inputProducts() {
        System.out.print("Nhập số lượng sản phẩm: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1) + ":");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Tên sản phẩm: ");
            String name = scanner.nextLine();
            System.out.print("Giá sản phẩm: ");
            double price = scanner.nextDouble();
            System.out.print("Chiết khấu (%): ");
            double discount = scanner.nextDouble();

            // Khởi tạo sản phẩm mới và thêm vào danh sách
            product product = new product(id, name, price, discount, 0); //
            productList.add(product);

            scanner.nextLine();
        }
    }

    private static void salePrices() {
        for (product product : productList) {
            product.calculatePriceOut();
            System.out.println("Giá bán cho sản phẩm " + product.getName() + ": " + product.getSalePrice());
        }
    }

    private static void displayProducts() {
        Collections.sort(productList, Comparator.comparing(product::getSalePrice));
        System.out.println("Danh sách sản phẩm:");
        for (product product : productList) {
            System.out.println(product.getName() + " - Giá bán: " + product.getSalePrice());
        }
    }
}
