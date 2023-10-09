package ra.run;

import ra.bussinessImp.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookManagement {

    public static List<Book> listBook = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số sách và nhập thông tin sách");
            System.out.println("2. Hiển thị thông tin các sách");
            System.out.println("3. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi trạng thái của sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.println("Nhập vào lựa chọn : ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchBookByName();
                    break;
                case 6:
                    updateBookStatus();
                    break;
                case 7:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }

        } while (option != 7);
    }

    public static void addBooks() {
        System.out.print("Nhập số sách cần nhập thông tin: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.inputData();
            listBook.add(book);
        }
    }

    public static void displayBooks() {
        if (listBook.isEmpty()) {
            System.out.println("\u001B[31mDanh sách sách rỗng.\u001B[0m");
        } else {
            for (Book book : listBook) {
                book.displayData();
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    public static void deleteBook() {
        System.out.print("Nhập mã sách cần xóa: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        for (Book book : listBook) {
            if (book.getBookId() == bookId) {
                listBook.remove(book);
                System.out.println("Đã xóa sách có mã " + bookId);
                return;
            }
        }

        System.out.println("\u001B[32mKhông tìm thấy sách có mã " + bookId + "\u001B[0m");
    }

    public static void updateBookStatus() {
        System.out.print("Nhập mã sách cần thay đổi trạng thái: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        for (Book book : listBook) {
            if (book.getBookId() == bookId) {
                boolean status = book.isBookStatus();
                book.setBookStatus(!status);
                System.out.println("Đã thay đổi trạng thái của sách có mã " + bookId);
                return;
            }
        }

        System.out.println("\u001B[32mKhông tìm thấy sách có mã " + bookId + "\u001B[0m");
    }

    public static void sortBooksByInterest() {
        if (listBook.isEmpty()) {
            System.out.println("\u001B[31mDanh sách sách rỗng.\u001B[0m");
        } else {
            Collections.sort(listBook);
            System.out.println("\u001B[32mĐã sắp xếp sách theo lợi nhuận giảm dần.\u001B[0m");
            for (Book book : listBook) {
                System.out.println("Tên sách: " + book.getBookName() + ", Lợi nhuận: " + book.getInterest());
            }
        }
    }

    public static void searchBookByName() {
        System.out.print("Nhập tên sách cần tìm: ");
        String bookName = scanner.nextLine().toLowerCase();

        boolean check = false;
        for (Book book : listBook) {
            if (book.getBookName().toLowerCase().contains(bookName)) {
                book.displayData();
                System.out.println("------------");
                check = true;
            }
        }

        if (!check) {
            System.out.println("\u001B[32mKhông tìm thấy sách có tên " + bookName + "\u001B[0m");
        }
    }
}
