import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;

class Product implements IProduct, Serializable {
    private static final long serialVersionUID = 4516961914718691601L;
    private static ArrayList<Product> productList = new ArrayList<>();
    private static final float MIN_INTEREST_RATE = 0.2f;
    private String id;
    private String name;
    private double importPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;

    // Constructors
    public Product() {
    }

    public Product(String id, String name, double importPrice, double exportPrice, String description, boolean status, int categoryId) {
        setId(id);
        setName(name);
        setImportPrice(importPrice);
        setExportPrice(exportPrice);
        setDescription(description);
        setStatus(status);
        setCategoryId(categoryId);
        calculateProfit();
    }

    // Thêm phương thức để load dữ liệu từ file
    private static void loadDataFromFile() {
        try (ObjectInputStream oisProducts = new ObjectInputStream(new FileInputStream("products.txt"))) {
            Object readObject = oisProducts.readObject();

            if (readObject instanceof ArrayList) {
                productList = (ArrayList<Product>) readObject;
            } else {
                System.out.println("Dữ liệu không hợp lệ trong tệp tin.");
            }
        } catch (EOFException e) {
            productList = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin. Dữ liệu mới sẽ được tạo khi bạn lưu thông tin.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra xem một sản phẩm với ID đã cho đã tồn tại trong file
    private static boolean isIdInFile(String productId) {
        loadDataFromFile();
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
    // Phương thức kiểm tra xem một sản phẩm với tên đã cho đã tồn tại trong file
    private static boolean isNameInFile(String productName) {
        loadDataFromFile();
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public static float getMinInterestRate() {
        return MIN_INTEREST_RATE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        // Validate the id format
        if (id.matches("P\\d{3}")) {
            // Check if the ID already exists in the file
            if (!isIdInFile(id)) {
                this.id = id;
            } else {
                System.out.println("ID đã tồn tại. Hãy nhập ID khác.");
                this.id = null;
            }
        } else {
            System.out.println("ID phải gồm 4 kí tự, bắt đầu bằng 'P', duy nhất.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Validate name length
        if (name.length() >= 6 && name.length() <= 30) {
            // Check if the name already exists in the file
            if (!isNameInFile(name)) {
                this.name = name;
            } else {
                System.out.println("Tên sản phẩm đã tồn tại, hãy nhập tên khác.");
            }
        } else {
            System.out.println("Tên sản phẩm không hợp lệ. Vui lòng nhập lại.");
        }
    }

    public double getImportPrice() {
        return importPrice;
    }


    public void setImportPrice(double importPrice) {
        if (importPrice >= 0) {
            this.importPrice = importPrice;
        } else {
            System.out.println("Giá nhập phải là một số thực lớn hơn 0.");
        }
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        if (exportPrice >= getImportPrice() + getImportPrice() * MIN_INTEREST_RATE) {
            this.exportPrice = exportPrice;
        } else {
            System.out.println("Giá bán không hợp lệ.");
        }
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        // Ensure description is not empty
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            System.out.println("Mô tar sản phẩm không được bỏ trống.");
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        // Validate the category ID
        while (true) {
            // Check if the entered ID exists in the category file
            if (Category.isIdInFile(categoryId)) {
                this.categoryId = categoryId;
                break;
            } else {
                System.out.println("Category ID does not exist. Please enter a valid Category ID.");
                // Optionally, you can ask the user to enter a new Category ID here
                // or handle it in some way.
                break;
            }
        }
    }

    public String getCategoryName() {
        return Category.getCategoryNameById(categoryId);
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Enter product information:");

        // Input for id
        while (true) {
            System.out.print("Enter product ID (Pxxx): ");
            String inputId = sc.nextLine();
            setId(inputId);

            // Check if the ID is valid, break the loop if it is
            if (getId() != null) {
                break;
            }
        }

        // Input for name
        while (true) {
            System.out.print("Enter product name (gồm 6-30 kí tự, duy nhất): ");
            String inputName = sc.nextLine();
            setName(inputName);
            if (getName() != null) {
                break;
            }
        }

        // Input for import price
        do {
            System.out.print("Enter import price: ");
            double inputImportPrice = sc.nextDouble();
            sc.nextLine(); // Consume the newline character left in the buffer
            setImportPrice(inputImportPrice);

        } while (getImportPrice() < 0);

        // Input for export price
        do {
            System.out.print("Enter export price (có giá trị lớn hơn giá nhập ít nhất " + MIN_INTEREST_RATE + " lần): ");
            double inputExportPrice = sc.nextDouble();
            sc.nextLine(); // Consume the newline character left in the buffer
            setExportPrice(inputExportPrice);

        } while (getExportPrice() <= getImportPrice() * MIN_INTEREST_RATE);

        // Input for description
        while (true) {
            System.out.print("Enter product description (không được bỏ trống): ");
            String inputDescription = sc.nextLine();

            if (!inputDescription.isEmpty()) {
                setDescription(inputDescription);
                break;
            }
        }

        // Input for status
        while (true) {
            System.out.print("Status (true/false): ");
            String inputStatus = sc.nextLine().toLowerCase();

            if (inputStatus.equals("true") || inputStatus.equals("false")) {
                setStatus(Boolean.parseBoolean(inputStatus));
                break;
            } else {
                System.out.println("Chỉ nhận giá trị true/false.");
            }
        }

        // Input for category ID
        while (true) {
            System.out.print("Enter category ID: ");
            int inputCategoryId = sc.nextInt();
            setCategoryId(inputCategoryId);

            // Break the loop if a valid category ID is entered
            if (getCategoryId() != 0) {
                break;
            }
        }

        // Calculate profit
        calculateProfit();
    }

    @Override
    public void displayData() {
        System.out.println("Product Information:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Status: " + (status ? "Còn hàng" : "Ngừng kinh doanh"));
        System.out.println("Category: " + getCategoryName()); // Display category name instead of categoryId
        System.out.println("Profit: " + profit);
    }

    @Override
    public void calProfit() {
        calculateProfit();
        System.out.println("Profit: " + profit);
    }

    // Private method to calculate profit
    private void calculateProfit() {
        profit = exportPrice - importPrice;
    }
}