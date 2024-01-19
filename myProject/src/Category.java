import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;

public class Category implements ICategory, Serializable {
    private static final long serialVersionUID = 8134796001290206769L;
    private static ArrayList<Category> categoryList = new ArrayList<>();
    private int id;
    private String name;
    private String description;
    private boolean status;

    public Category() {}

    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    // Assuming the loadDataFromFile method reads data from a file and populates categoryList
    private static void loadDataFromFile() {
        try (ObjectInputStream oisCategories = new ObjectInputStream(new FileInputStream("categories.txt"))) {
            Object readObject = oisCategories.readObject();

            if (readObject instanceof ArrayList) {
                categoryList = (ArrayList<Category>) readObject;
            } else {
                System.out.println("Dữ liệu không hợp lệ trong tệp tin.");
            }
        } catch (EOFException e) {
            categoryList = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin. Dữ liệu mới sẽ được tạo khi bạn lưu thông tin.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to check if a category with the given ID already exists in the file
    public static boolean isIdInFile(int id) {
        loadDataFromFile();
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return true;
            }
        }
        return false;
    }
    // Method to check if a category with the given name already exists in the file
    private static boolean isNameInFile(String categoryName) {
        loadDataFromFile();
        for (Category category : categoryList) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }

    // Implementing ICategory interface methods
    @Override
    public void inputData(Scanner sc) {
        System.out.println("Enter Category Information:");

        // Input id
        System.out.print("ID (phải là số nguyên lớn hơn 0, duy nhất): ");
        while (true) {
            while (!sc.hasNextInt() || (id = sc.nextInt()) <= 0) {
                System.out.println("Invalid input. ID must be a positive integer.");
                sc.next(); // clear the buffer
            }
            if (!isIdInFile(id)) {
                break;
            } else {
                if (isIdInFile(id)) {
                    System.out.println("ID này đã tồn tại. Vui lòng nhập lại");
                }
                System.out.print("Enter a new ID: ");
            }
        }

        // Input name
        System.out.print("Name (must be unique, 6-30 characters): ");
        while (true) {
            name = sc.next();
            if (name.length() >= 6 && name.length() <= 30 && !isNameInFile(name)) {
                break;
            } else {
                System.out.println("Tên danh mục đã tồn tại hoặc không hợp lệ. Vui lòng nhập lại.");
                System.out.print("Enter a new name: ");
            }
        }

        // Input description
        System.out.print("Description (không bỏ trống): ");
        sc.nextLine();
        while (true) {
            description = sc.nextLine();
            if (!description.isEmpty()) {
                break;
            } else {
                System.out.println("Trường này không thể bỏ trống.");
            }
        }

        // Input status
        while (true) {
            System.out.print("Status (true/false): ");
            String statusInput = sc.next();
            if (statusInput.equalsIgnoreCase("true") || statusInput.equalsIgnoreCase("false")) {
                status = Boolean.parseBoolean(statusInput);
                break;
            } else {
                System.out.println("Chỉ nhận true/false khi nhập.");
            }
        }
    }

    @Override
    public void displayData() {
        System.out.println("Category Information:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Status: " + (status ? "Active" : "Inactive"));
    }

    // Additional method to get category name based on category ID
    public static String getCategoryNameById(int categoryId) {
        loadDataFromFile();
        for (Category category : categoryList) {
            if (category.getId() == categoryId) {
                return category.getName();
            }
        }
        return "Unknown Category"; // Replace with appropriate default value or throw an exception
    }
}
