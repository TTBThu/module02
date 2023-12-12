import java.util.ArrayList;
import java.util.Scanner;

class Author {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + "'}";
    }
}

class Book {
    private int id;
    private String title;
    private String genre;
    private Author author;

    public Book(int id, String title, String genre, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', genre='" + genre + "', author=" + author + "}";
    }
}

class LibraryManager {
    private ArrayList<Author> authors;
    private ArrayList<Book> books;

    public LibraryManager() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
    public ArrayList<Author> getAuthors() {
        return authors;
    }
    public void displayAuthors() {
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    public void updateAuthor(int authorId, String newName) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                author = new Author(authorId, newName);
                System.out.println("Thông tin tác giả đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy tác giả có id=" + authorId);
    }

    public void deleteAuthor(int authorId) {
        authors.removeIf(author -> author.getId() == authorId);
        System.out.println("Tác giả đã được xóa.");
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void searchBooksByGenre(String genre) {
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(book);
            }
        }
    }

    public void searchBooksByAuthor(String authorName) {
        for (Book book : books) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                System.out.println(book);
            }
        }
    }

    public void updateBook(int bookId, String newTitle, String newGenre) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                book = new Book(bookId, newTitle, newGenre, book.getAuthor());
                System.out.println("Thông tin sách đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sách có id=" + bookId);
    }
}

public class program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager libraryManager = new LibraryManager();

        while (true) {
            System.out.println("Menu chính:");
            System.out.println("1.Quản lý tác giả");
            System.out.println("2.Quản lý sách");
            System.out.println("3.Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageAuthors(libraryManager, scanner);
                    break;
                case 2:
                    manageBooks(libraryManager, scanner);
                    break;
                case 3:
                    System.out.println("Chương trình kết thúc.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void manageAuthors(LibraryManager libraryManager, Scanner scanner) {
        while (true) {
            System.out.println("Menu quản lý tác giả:");
            System.out.println("1.Thêm tác giả");
            System.out.println("2.Hiển thị tác giả");
            System.out.println("3.Cập nhật thông tin tác giả (theo id)");
            System.out.println("4.Xóa tác giả");
            System.out.println("5.Quay lại");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập id tác giả: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên tác giả: ");
                    String authorName = scanner.nextLine();
                    libraryManager.addAuthor(new Author(authorId, authorName));
                    System.out.println("Tác giả đã được thêm.");
                    break;
                case 2:
                    System.out.println("Danh sách tác giả:");
                    libraryManager.displayAuthors();
                    break;
                case 3:
                    System.out.print("Nhập id tác giả cần cập nhật: ");
                    int updateAuthorId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên mới cho tác giả: ");
                    String newAuthorName = scanner.nextLine();
                    libraryManager.updateAuthor(updateAuthorId, newAuthorName);
                    break;
                case 4:
                    System.out.print("Nhập id tác giả cần xóa: ");
                    int deleteAuthorId = scanner.nextInt();
                    libraryManager.deleteAuthor(deleteAuthorId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void manageBooks(LibraryManager libraryManager, Scanner scanner) {
        while (true) {
            System.out.println("Menu quản lý sách:");
            System.out.println("1.Thêm sách");
            System.out.println("2.Tìm kiếm sách theo chủ đề");
            System.out.println("3.Tìm kiếm sách theo tên tác giả");
            System.out.println("4.Cập nhật sách (theo id)");
            System.out.println("5.Quay lại");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập id sách: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên sách: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Nhập chủ đề sách: ");
                    String bookGenre = scanner.nextLine();
                    System.out.print("Nhập id tác giả của sách: ");
                    int authorId = scanner.nextInt();
                    Author author = getAuthorById(libraryManager, authorId);
                    if (author != null) {
                        libraryManager.addBook(new Book(bookId, bookTitle, bookGenre, author));
                        System.out.println("Sách đã được thêm.");
                    } else {
                        System.out.println("Không tìm thấy tác giả có id=" + authorId + ". Vui lòng thêm tác giả trước.");
                    }
                    break;
                case 2:
                    System.out.print("Nhập chủ đề sách cần tìm kiếm: ");
                    String searchGenre = scanner.nextLine();
                    System.out.println("Kết quả tìm kiếm sách theo chủ đề '" + searchGenre + "':");
                    libraryManager.searchBooksByGenre(searchGenre);
                    break;
                case 3:
                    System.out.print("Nhập tên tác giả cần tìm kiếm: ");
                    String searchAuthor = scanner.nextLine();
                    System.out.println("Kết quả tìm kiếm sách theo tác giả '" + searchAuthor + "':");
                    libraryManager.searchBooksByAuthor(searchAuthor);
                    break;
                case 4:
                    System.out.print("Nhập id sách cần cập nhật: ");
                    int updateBookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên mới cho sách: ");
                    String newBookTitle = scanner.nextLine();
                    System.out.print("Nhập chủ đề mới cho sách: ");
                    String newBookGenre = scanner.nextLine();
                    libraryManager.updateBook(updateBookId, newBookTitle, newBookGenre);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static Author getAuthorById(LibraryManager libraryManager, int authorId) {
        for (Author author : libraryManager.getAuthors()) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }
}
