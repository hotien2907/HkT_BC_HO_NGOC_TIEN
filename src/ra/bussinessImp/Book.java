package ra.bussinessImp;

import ra.bussiness.IBook;
import ra.run.BookManagement;

import java.util.Scanner;




public class Book implements IBook,Comparable<Book> {

    private int bookId;
    private String bookName;
    private String title;
    private int numberOfPages;
    private float importPrice;
    private float exportPrice;
    private float interest;
    private boolean bookStatus;

    public Book() {
    }

    public Book(int bookId, String bookName, String title, int numberOfPages, float importPrice, float exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }


    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        boolean checkId;
        do {
            checkId = false;

            System.out.print("Nhập mã sách: ");
            int newBookId = Integer.parseInt(scanner.nextLine());

            for (Book book : BookManagement.listBook) {
                if (book.getBookId() == newBookId) {
                    checkId = true;
                    System.out.println("\u001B[31mMã sách đã tồn tại. Vui lòng nhập lại.\u001B[0m");
                    break;
                }
            }

            if (!checkId) {
                this.bookId = newBookId;
            }
        } while (checkId);

        do {
            System.out.print("Nhập tên sách: ");
            this.bookName = scanner.nextLine().trim();
            if (this.bookName.isEmpty()) {
                System.out.println("\u001B[31mTên sách không được để trống. Vui lòng nhập lại.\u001B[0m");
            }
        } while (this.bookName.isEmpty());

        do {
            System.out.print("Nhập tiêu đề sách: ");
            this.title = scanner.nextLine().trim();
            if (this.title.isEmpty()) {
                System.out.println("\u001B[31mTiêu đề sách không được để trống. Vui lòng nhập lại.\u001B[0m");
            }
        } while (this.title.isEmpty());

        do {
            System.out.print("Nhập số trang sách: ");
            this.numberOfPages = Integer.parseInt(scanner.nextLine());
            if (this.numberOfPages <= 0) {
                System.out.println("\u001B[31mSố trang sách phải lớn hơn 0. Vui lòng nhập lại.\u001B[0m");
            }
        } while (this.numberOfPages <= 0);

        do {
            System.out.print("Nhập giá nhập sách: ");
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice <= 0) {
                System.out.println("\u001B[31mGiá nhập sách phải lớn hơn 0. Vui lòng nhập lại.\u001B[0m");
            }
        } while (this.importPrice <= 0);

        do {
            System.out.print("Nhập giá bán sách: ");
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice <= this.importPrice) {
                System.out.println("\u001B[31mGiá bán sách phải lớn hơn giá nhập sách. Vui lòng nhập lại.\u001B[0m");
            }
        } while (this.exportPrice <= this.importPrice);

        this.interest = this.exportPrice - this.importPrice;

        System.out.print("Nhập trạng thái sách (true/false): ");
        this.bookStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("Mã sách: " + this.bookId);
        System.out.println("Tên sách: " + this.bookName);
        System.out.println("Tiêu đề sách: " + this.title);
        System.out.println("Số trang sách: " + this.numberOfPages);
        System.out.println("Giá nhập sách: " + this.importPrice);
        System.out.println("Giá bán sách: " + this.exportPrice);
        System.out.println("Lợi nhuận: " + this.interest);
        System.out.println("Trạng thái sách: " + (this.bookStatus ? "Đang bán":"Không bán"));
    }
    @Override
    public int compareTo(Book o) {
        return Float.compare(o.getInterest(), this.getInterest());
    }

}
