package com.example.demo;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add book");
            System.out.println("2. View all books");
            System.out.println("3. Update book availability");
            System.out.println("4. Delete book");
            System.out.println("5. Add member");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.print("Is available (true/false): ");
                    boolean available = scanner.nextBoolean();

                    Book book = new Book(title, author, isbn, available);
                    library.addBookToDatabase(book);
                    break;

                case 2:
                    System.out.println("Sort books by:");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. ISBN");
                    System.out.println("4. Availability");
                    System.out.println("0. No sorting");

                    System.out.print("Choose option: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();

                    library.readBooksFromDatabase(sortChoice);
                    break;


                case 3:
                    System.out.print("Enter ISBN: ");
                    String updateIsbn = scanner.nextLine();

                    System.out.print("New availability (true/false): ");
                    boolean newAvailability = scanner.nextBoolean();

                    library.updateAvailability(updateIsbn, newAvailability);
                    break;

                case 4:
                    System.out.print("Enter ISBN: ");
                    String deleteIsbn = scanner.nextLine();

                    library.deleteBook(deleteIsbn);
                    break;

                case 5:
                    System.out.println("Enter ID: ");
                    int memberID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter name: ");
                    String memberName = scanner.nextLine();

                    LibraryMember member = new LibraryMember(memberID, memberName);
                    library.AddmembertoDatabase(member);
                    break;

                case 0:
                    System.out.println("Exit.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}