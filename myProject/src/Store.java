import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static ArrayList<Category> categoryList = new ArrayList<>();
    private static ArrayList<Product> productList = new ArrayList<>();

    private static void loadDataFromFile() {
        // Load categories
        try (ObjectInputStream oisCategories = new ObjectInputStream(new FileInputStream("categories.txt"))) {
            Object readObject = oisCategories.readObject();

            if (readObject instanceof ArrayList) {
                categoryList = (ArrayList<Category>) readObject;
            } else {
                System.out.println("Dữ liệu không hợp lệ trong tệp tin danh mục.");
            }
        } catch (EOFException e) {
            categoryList = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin danh mục. Dữ liệu mới sẽ được tạo khi bạn lưu thông tin.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Load products
        try (ObjectInputStream oisProducts = new ObjectInputStream(new FileInputStream("products.txt"))) {
            Object readObject = oisProducts.readObject();

            if (readObject instanceof ArrayList) {
                productList = (ArrayList<Product>) readObject;
            } else {
                System.out.println("Dữ liệu không hợp lệ trong tệp tin sản phẩm.");
            }
        } catch (EOFException e) {
            productList = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin sản phẩm. Dữ liệu mới sẽ được tạo khi bạn lưu thông tin.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadDataFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== QUẢN LÝ KHO =====");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    manageCategories(scanner);
                    break;
                case 2:
                    manageProducts(scanner);
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 3);
    }

    private static void manageCategories(Scanner scanner) {
        int choice;

        do {
            System.out.println("===== QUẢN LÝ DANH MỤC =====");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Cập nhật danh mục");
            System.out.println("3. Xóa danh mục");
            System.out.println("4. Tìm kiếm danh mục theo tên danh mục");
            System.out.println("5. Thống kê số lượng sản phẩm trong danh mục");
            System.out.println("6. Quay lại");
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    addCategory(scanner);
                    break;
                case 2:
                    updateCategory(scanner);
                    break;
                case 3:
                    deleteCategory(scanner);
                    break;
                case 4:
                    searchCategoryByName(scanner);
                    break;
                case 5:
                    countProductsInCategory(scanner);
                    break;
                case 6:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 6);
    }

    private static void manageProducts(Scanner scanner) {
        int choice;

        do {
            System.out.println("===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Cập nhật sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị sản phẩm theo tên A-Z");
            System.out.println("5. Hiển thị sản phẩm theo lợi nhuận từ cao-thấp");
            System.out.println("6. Tìm kiếm sản phẩm");
            System.out.println("7. Quay lại");
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    loadDataFromFile();
                    addProduct(scanner);
                    break;
                case 2:
                    loadDataFromFile();
                    updateProduct(scanner);
                    break;
                case 3:
                    deleteProduct(scanner);
                    break;
                case 4:
                    loadDataFromFile();
                    displayProductsByName();
                    break;
                case 5:
                    loadDataFromFile();
                    displayProductsByProfit();
                    break;
                case 6:
                    loadDataFromFile();
                    searchProduct(scanner);
                    break;
                case 7:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 7);
    }

    private static void addCategory(Scanner scanner) {
        Category category = new Category();
        category.inputData(scanner);
        categoryList.add(category);

        saveCategoriesToFile();
        System.out.println("Danh mục mới đã được thêm thành công.");
    }
    private static boolean isNameUnique(String name) {
        for (Category category : categoryList) {
            if (category.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
    private static void updateCategory(Scanner scanner) {
        System.out.print("Nhập ID danh mục cần cập nhật: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Category categoryToUpdate = findCategoryById(categoryId);

        if (categoryToUpdate != null) {
            System.out.println("Nhập thông tin mới cho danh mục:");

            System.out.print("Name (không trùng nhau, từ 6-30 kí tự): ");
            while (true) {
                String newName = scanner.next();
                if (newName.length() >= 6 && newName.length() <= 30 && isNameUnique(newName)) {
                    categoryToUpdate.setName(newName);
                    break;
                } else {
                    System.out.println("Tên danh mục đã tồn tại hoặc không hợp lệ. Vui lòng nhập lại.");
                    System.out.print("Enter a new name: ");
                }
            }

            System.out.print("Description (không bỏ trống): ");
            scanner.nextLine();
            while (true) {
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) {
                    categoryToUpdate.setDescription(newDescription);
                    break;
                } else {
                    System.out.println("Trường này không thể bỏ trống.");
                }
            }

            System.out.print("Status (true/false): ");
            while (!scanner.hasNextBoolean()) {
                System.out.println("Chỉ nhận true/false khi nhập.");
                scanner.next(); // clear the buffer
            }
            categoryToUpdate.setStatus(scanner.nextBoolean());

            saveCategoriesToFile();
            System.out.println("Danh mục đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + categoryId);
        }
    }

    private static void deleteCategory(Scanner scanner) {
        System.out.print("Nhập ID danh mục cần xóa: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Category categoryToDelete = findCategoryById(categoryId);

        if (categoryToDelete != null) {
            if (isCategoryEmpty(categoryId)) {
                categoryList.remove(categoryToDelete);

                saveCategoriesToFile();
                System.out.println("Danh mục đã được xóa thành công.");
            } else {
                System.out.println("Danh mục đang có sản phẩm tham chiếu. Không thể xóa.");
            }
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + categoryId);
        }
    }

    private static boolean isCategoryEmpty(int categoryId) {
        for (Product product : productList) {
            if (product.getCategoryId() == categoryId) {
                return false;
            }
        }
        return true;
    }

    private static void searchCategoryByName(Scanner scanner) {
        loadDataFromFile();

        System.out.print("Nhập tên danh mục cần tìm kiếm: ");
        String categoryName = scanner.nextLine().toLowerCase(); // Chuyển tất cả sang chữ thường

        ArrayList<Category> searchResults = new ArrayList<>();

        for (Category category : categoryList) {
            if (category.getName().toLowerCase().contains(categoryName)) {
                searchResults.add(category);
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println("Kết quả tìm kiếm:");
            for (Category category : searchResults) {
                category.displayData();
            }
        } else {
            System.out.println("Không tìm thấy danh mục nào có tên chứa \"" + categoryName + "\"");
        }
    }

    private static void countProductsInCategory(Scanner scanner) {
        System.out.print("Nhập ID danh mục cần thống kê: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        int productCount = 0;

        for (Product product : productList) {
            if (product.getCategoryId() == categoryId) {
                productCount++;
            }
        }

        System.out.println("Số lượng sản phẩm trong danh mục: " + productCount);
    }

    private static void addProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner);
        productList.add(product);

        saveProductsToFile();
        System.out.println("Sản phẩm mới đã được thêm thành công.");
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String productId = scanner.nextLine();

        Product productToUpdate = findProductById(productId);

        if (productToUpdate != null) {
            System.out.println("Nhập thông tin mới cho sản phẩm:");

            // Update name
            System.out.print("Name (không trùng nhau, từ 6-30 kí tự): ");
            while (true) {
                String newName = scanner.nextLine();
                if (newName.length() >= 6 && newName.length() <= 30) {
                    productToUpdate.setName(newName);
                    break;
                } else {
                    System.out.println("Tên sản phẩm không hợp lệ. Vui lòng nhập lại.");
                    System.out.print("Enter a new name: ");
                }
            }

            // Update import price
            do {
                System.out.print("Enter import price: ");
                double newImportPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character left in the buffer
                productToUpdate.setImportPrice(newImportPrice);

            } while (productToUpdate.getImportPrice() < 0);

            // Update export price
            do {
                System.out.print("Enter export price (có giá trị lớn hơn giá nhập ít nhất " + Product.getMinInterestRate() + " lần): ");
                double newExportPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character left in the buffer
                productToUpdate.setExportPrice(newExportPrice);

            } while (productToUpdate.getExportPrice() <= productToUpdate.getImportPrice() * Product.getMinInterestRate());

            // Update description
            System.out.print("Enter product description (không được bỏ trống): ");
            while (true) {
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) {
                    productToUpdate.setDescription(newDescription);
                    break;
                } else {
                    System.out.println("Mô tả sản phẩm không được bỏ trống.");
                    System.out.print("Enter a new description: ");
                }
            }

            // Update status
            System.out.print("Status (true/false): ");
            while (!scanner.hasNextBoolean()) {
                System.out.println("Chỉ nhận giá trị true/false.");
                scanner.next(); // clear the buffer
            }
            productToUpdate.setStatus(scanner.nextBoolean());

            // Update category ID
            System.out.print("Enter category ID: ");
            int newCategoryId = scanner.nextInt();
            productToUpdate.setCategoryId(newCategoryId);

            saveProductsToFile();
            System.out.println("Sản phẩm đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID " + productId);
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String productId = scanner.nextLine();

        Product productToDelete = findProductById(productId);

        if (productToDelete != null) {
            productList.remove(productToDelete);

            saveProductsToFile();
            System.out.println("Sản phẩm đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID " + productId);
        }
    }

    private static void displayProductsByName() {
        sortProductsByName();
        System.out.println("Danh sách sản phẩm theo tên A-Z:");
        for (Product product : productList) {
            product.displayData();
        }
    }

    private static void displayProductsByProfit() {
        sortProductsByProfit();
        System.out.println("Danh sách sản phẩm theo lợi nhuận từ cao đến thấp:");
        for (Product product : productList) {
            product.displayData();
        }
    }

    private static void sortProductsByName() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = 0; j < productList.size() - i - 1; j++) {
                if (productList.get(j).getName().compareToIgnoreCase(productList.get(j + 1).getName()) > 0) {
                    // Swap products at index j and j+1
                    Product temp = productList.get(j);
                    productList.set(j, productList.get(j + 1));
                    productList.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortProductsByProfit() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = 0; j < productList.size() - i - 1; j++) {
                if (productList.get(j).getProfit() < productList.get(j + 1).getProfit()) {
                    // Swap products at index j and j+1
                    Product temp = productList.get(j);
                    productList.set(j, productList.get(j + 1));
                    productList.set(j + 1, temp);
                }
            }
        }
    }

    private static void searchProduct(Scanner scanner) {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine().toLowerCase();

        ArrayList<Product> searchResults = new ArrayList<>();

        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword)
                    || product.getDescription().toLowerCase().contains(keyword)) {
                searchResults.add(product);
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : searchResults) {
                product.displayData();
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào chứa từ khóa \"" + keyword + "\"");
        }
    }

    private static Category findCategoryById(int categoryId) {
        for (Category category : categoryList) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    private static Product findProductById(String productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private static void saveCategoriesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("categories.txt"))) {
            oos.writeObject(categoryList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveProductsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
            oos.writeObject(productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
