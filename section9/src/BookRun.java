import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BookRun {
    private static final String FILE_NAME = "books.txt";
    private ArrayList<Book> books;
    private Scanner scanner;

    public BookRun() {
        this.books = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadBooksFromFile();
    }

    public static void main(String[] args) {
        BookRun bookRun = new BookRun();
        bookRun.runMenu();
        bookRun.saveBooksToFile();
    }

    private void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (ArrayList<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMenu() {
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    sortBooksByExportPrice();
                    break;
                case 6:
                    filterBooksByPriceRange();
                    break;
                case 7:
                    searchBooksByAuthor();
                    break;
                case 8:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void printMenu() {
        System.out.println("*********************************MENU********************************");
        System.out.println("1. Nhập thông tin sách");
        System.out.println("2. Hiển thị thông tin sách");
        System.out.println("3. Cập nhật thông tin sách theo mã sách");
        System.out.println("4. Xóa sách theo mã sách");
        System.out.println("5. Sắp xếp sách theo giá bán tăng dần");
        System.out.println("6. Thống kê sách theo khoảng giá");
        System.out.println("7. Tìm kiếm sách theo tên tác giả");
        System.out.println("8. Thoát");
    }

    private void addBook() {
        Book book = new Book();
        book.inputData(scanner);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    private void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                book.displayData();
                System.out.println("------------------------------");
            }
        }
    }

    private void updateBook() {
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book foundBook = findBookById(bookId);

        if (foundBook != null) {
            System.out.println("Enter updated information:");
            foundBook.inputData(scanner);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book foundBook = findBookById(bookId);

        if (foundBook != null) {
            books.remove(foundBook);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    private void sortBooksByExportPrice() {
        if (!books.isEmpty()) {
            Collections.sort(books, (b1, b2) -> Float.compare(b1.getExportPrice(), b2.getExportPrice()));
            System.out.println("Books sorted by export price (ascending) successfully!");
        } else {
            System.out.println("No books available to sort.");
        }
    }

    private void filterBooksByPriceRange() {
        System.out.print("Enter the minimum price: ");
        float minPrice = scanner.nextFloat();

        System.out.print("Enter the maximum price: ");
        float maxPrice = scanner.nextFloat();

        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getExportPrice() >= minPrice && book.getExportPrice() <= maxPrice) {
                filteredBooks.add(book);
            }
        }

        if (!filteredBooks.isEmpty()) {
            System.out.println("Books within the price range:");
            for (Book book : filteredBooks) {
                book.displayData();
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No books within the specified price range.");
        }
    }

    private void searchBooksByAuthor() {
        System.out.print("Enter author name to search: ");
        String authorName = scanner.nextLine();

        ArrayList<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                foundBooks.add(book);
            }
        }

        if (!foundBooks.isEmpty()) {
            System.out.println("Books by author '" + authorName + "':");
            for (Book book : foundBooks) {
                book.displayData();
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No books found by author '" + authorName + "'.");
        }
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
