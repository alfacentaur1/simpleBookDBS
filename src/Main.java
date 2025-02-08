import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean logged_in = false;

        while (true) {
            System.out.println("Are you a new user? (y/n)");
            String newUser = scanner.nextLine();

            if (newUser.equals("y")) {
                System.out.println("Would you like to create an account? (y/n)");
                String createUser = scanner.nextLine();

                if (createUser.equals("y")) {
                    String username;
                    boolean occupied;

                    do {
                        occupied = false;
                        System.out.println("Enter your username: ");
                        username = scanner.nextLine();

                        for (User user : users) {
                            if (username.equals(user.getUsername())) {
                                occupied = true;
                                System.out.println("Username is already taken. Try again.");
                                break;
                            }
                        }
                    } while (occupied);

                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();

                    users.add(new User(username, password));
                    System.out.println("Your account has been successfully created.");
                    System.out.println("You can now proceed to login.");
                    break;

                } else if (createUser.equals("n")) {
                    System.out.println("Shutting down...");
                    return;
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            } else if (newUser.equals("n")) {
                System.out.println("You can now proceed to login.");
                break;
            }
        }

        while (true) {
            int password_tries = 3;
            System.out.println("Welcome to login. Please enter your username: ");
            String username = scanner.nextLine();
            boolean found = false;

            for (User user : users) {
                if (username.equals(user.getUsername())) {
                    found = true;
                    break;
                }
            }

            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();

            while (password_tries > 0) {
                for (User user2 : users) {
                    if (username.equals(user2.getUsername())) {
                        if (user2.checkPassword(password)) {
                            logged_in = true;
                            System.out.println("You are successfully logged in.");
                            break;
                        } else {
                            password_tries--;
                            System.out.printf("Password does not match. You have %d tries left. Try again.%n", password_tries);
                        }
                    }
                }
                break;
            }
            if (logged_in) break;
        }

        if (logged_in) {
            do {
                System.out.println("Welcome to book database!");
                System.out.println("What action would you like?");
                System.out.println("1) Add a new book");
                System.out.println("2) Remove a book");
                System.out.println("3) Edit a book");
                System.out.println("4) List all books by its name");
                System.out.println("5) List all available authors");
                System.out.println("6) Quit");
                String action = scanner.nextLine();

                if (action.equals("1")) {
                    System.out.println("Enter the book title: ");
                    String title = scanner.nextLine();
                    for (Book book : books) {
                        if (book.getTitle().equals(title)) {
                            System.out.println("Title is already in database. Try another book.");
                        }
                    }
                    System.out.println("Enter the book author: ");
                    String author = scanner.nextLine();
                    System.out.println("Enter the book publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.println("Enter the book pages: ");
                    int pages = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the book rating: ");
                    int rating = Integer.parseInt(scanner.nextLine());
                    Book newBook = new Book(title, author, publisher, pages, rating);
                    books.add(newBook);
                } else if (action.equals("2")) {
                    boolean removed = false;
                    System.out.println("Enter the book title to remove, please: ");
                    String title = scanner.nextLine();
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getTitle().equals(title)) {
                            books.remove(i);
                            removed = true;
                            System.out.println("Book successfully removed.");
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("Book title does not match any book in the database. Returning to menu.");
                    }
                } else if (action.equals("3")) {
                    System.out.println("Enter the book title to edit, please: ");
                    String title = scanner.nextLine();

                    boolean found = false;
                    for (Book book : books) {
                        if (book.getTitle().equals(title)) {
                            found = true;
                            System.out.println("Enter what category you would like to edit.");
                            System.out.println("1) Title");
                            System.out.println("2) Author");
                            System.out.println("3) Publisher");
                            System.out.println("4) Pages");
                            System.out.println("5) Rating");
                            System.out.println("6) Exit");
                            System.out.println("Enter your choice: ");
                            String choice = scanner.nextLine();

                            if (choice.equals("1")) {
                                System.out.println("Enter the new book title: ");
                                book.setTitle(scanner.nextLine());
                                System.out.println("Title has been successfully edited.");
                            } else if (choice.equals("2")) {
                                System.out.println("Enter the book author: ");
                                book.setAuthor(scanner.nextLine());
                                System.out.println("Author has been successfully edited.");
                            } else if (choice.equals("3")) {
                                System.out.println("Enter the book publisher: ");
                                book.setPublisher(scanner.nextLine());
                                System.out.println("Publisher has been successfully edited.");
                            } else if (choice.equals("4")) {
                                System.out.println("Enter the book pages: ");
                                book.setPages(Integer.parseInt(scanner.nextLine()));
                                System.out.println("Pages have been successfully edited.");
                            } else if (choice.equals("5")) {
                                System.out.println("Enter the book rating: ");
                                book.setRating(Integer.parseInt(scanner.nextLine()));
                                System.out.println("Rating has been successfully edited.");
                            } else {
                                System.out.println("Invalid choice, returning to menu.");
                            }
                        }
                    }
                } else if (action.equals("4")) {
                    System.out.println("Listing all available books...");
                    for (Book book : books) {
                        System.out.println(book.getTitle());
                    }
                } else if (action.equals("5")) {
                    ArrayList<String> allAuthors = new ArrayList<>();
                    for (Book book : books) {
                        if (!allAuthors.contains(book.getAuthor())) {
                            allAuthors.add(book.getAuthor());
                        }
                    }
                    System.out.println("Listing all available authors...");
                    for (String author : allAuthors) {
                        System.out.println(author);
                    }
                } else if (action.equals("6")) {
                    System.out.println("Thank you for using our book database. Goodbye!");
                    logged_in = false;
                    break;
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            } while (logged_in);
        } else {
            System.out.println("Login process has failed, please try again. Shutting down...");
        }

        scanner.close();
    }
}

