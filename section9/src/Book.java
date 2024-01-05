import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Book implements IBook {
    private static int maxBookId = 0;

    private int bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private Date created;
    private String descriptions;

    public Book() {
        this.bookId = ++maxBookId;
    }

    public Book(String bookName, float importPrice, float exportPrice, String author, Date created, String descriptions) {
        this();
        this.setBookName(bookName);
        this.setImportPrice(importPrice);
        this.setExportPrice(exportPrice);
        this.setAuthor(author);
        this.setCreated(created);
        this.setDescriptions(descriptions);
    }

    // Getter and setter methods

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        if (bookName != null && bookName.length() == 4 && bookName.startsWith("B")) {
            this.bookName = bookName;
        } else {
            throw new IllegalArgumentException("Invalid book name");
        }
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        if (importPrice > 0) {
            this.importPrice = importPrice;
        } else {
            throw new IllegalArgumentException("Import price must be greater than 0");
        }
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        if (exportPrice > this.importPrice) {
            this.exportPrice = exportPrice;
        } else {
            throw new IllegalArgumentException("Export price must be greater than import price");
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && author.length() >= 6 && author.length() <= 50) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author must be between 6 and 50 characters");
        }
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        if (descriptions != null && descriptions.length() <= 500) {
            this.descriptions = descriptions;
        } else {
            throw new IllegalArgumentException("Descriptions must be less than or equal to 500 characters");
        }
    }

    // Implementation of IBook interface

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Enter book information:");

        System.out.print("Book Name (4 characters, starts with B): ");
        this.setBookName(scanner.next());

        System.out.print("Import Price: ");
        this.setImportPrice(scanner.nextFloat());

        System.out.print("Export Price (greater than import price): ");
        this.setExportPrice(scanner.nextFloat());

        System.out.print("Author (between 6 and 50 characters): ");
        this.setAuthor(scanner.next());

        System.out.print("Created Date (dd/MM/yyyy): ");
        String dateString = scanner.next();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.setCreated(sdf.parse(dateString));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use dd/MM/yyyy");
        }

        System.out.print("Descriptions (up to 500 characters): ");
        this.setDescriptions(scanner.next());
    }

    @Override
    public void displayData() {
        System.out.println("Book Information:");
        System.out.println("Book ID: " + this.getBookId());
        System.out.println("Book Name: " + this.getBookName());
        System.out.println("Import Price: " + this.getImportPrice());
        System.out.println("Export Price: " + this.getExportPrice());
        System.out.println("Author: " + this.getAuthor());
        System.out.println("Created Date: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getCreated()));
        System.out.println("Descriptions: " + this.getDescriptions());
    }
}
