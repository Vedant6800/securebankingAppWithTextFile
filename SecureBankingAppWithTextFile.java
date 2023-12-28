import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecureBankingAppWithTextFile {

    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String textFilePath = "accounts.txt";

    // ANSI color codes for formatting
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        loadAccountsFromTextFile();

        while (true) {
            displayWelcomeScreen();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    saveAccountsToTextFile();
                    System.out.println("\nExiting the program. Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayWelcomeScreen() {
        System.out.println(RED + "**********************************************" + RESET);
        System.out.println(RED + "      Welcome to the Secure Banking App        " + RESET);
        System.out.println(RED + "**********************************************" + RESET);
        System.out.println("1. " + GREEN + BOLD + "Create Account" + RESET);
        System.out.println("2. " + BLUE + BOLD + "Login" + RESET);
        System.out.println("3. " + YELLOW + BOLD + "Exit" + RESET);
    }

    private static int getUserChoice() {
        System.out.print(BOLD + "Enter your choice: " + RESET);
        return scanner.nextInt();
    }

    private static void createAccount() {
        clearScreen();
        System.out.println("\n" + CYAN + BOLD + "Create a New Account" + RESET);
        System.out.print("Enter your username: ");
        String username = scanner.next();

        if (!accounts.containsKey(username)) {
            System.out.print("Enter your password: ");
            String password = scanner.next();

            System.out.print("Enter initial deposit amount: $");
            double initialDeposit = scanner.nextDouble();

            if (initialDeposit >= 0) {
                Account newAccount = new Account(username, password, initialDeposit);
                accounts.put(username, newAccount);
                System.out.println(GREEN + "\nAccount created successfully." + RESET);
            } else {
                System.out.println("Invalid initial deposit amount. Please enter a non-negative value.");
            }
        } else {
            System.out.println("Username already exists. Please choose another username.");
        }
        pressEnterToContinue();
    }

    private static void login() {
        clearScreen();
        System.out.println("\n" + CYAN + BOLD + "Login to Your Account" + RESET);
        System.out.print("Enter your username: ");
        String username = scanner.next();

        if (accounts.containsKey(username)) {
            System.out.print("Enter your password: ");
            String password = scanner.next();

            Account account = accounts.get(username);

            if (account.validatePassword(password)) {
                double balance = account.getBalance();
                System.out.println(GREEN + "\nLogin successful. Welcome, " + username + "!" + RESET);
                showMenu(username, balance);
            } else {
                System.out.println("Incorrect password. Login failed.");
            }
        } else {
            System.out.println("Username not found. Please create an account first.");
        }
        pressEnterToContinue();
    }

    private static void showMenu(String username, double balance) {
        while (true) {
            clearScreen();
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    deposit(username, depositAmount);
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(username, withdrawAmount);
                    break;

                case 3:
                    checkBalance(username);
                    break;

                case 4:
                    saveAccountsToTextFile();
                    System.out.println(GREEN + "\nLogging out. Goodbye, " + username + "!" + RESET);
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            pressEnterToContinue();
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + CYAN + BOLD + "Main Menu" + RESET);
        System.out.println("1. " + BLUE + BOLD + "Deposit" + RESET);
        System.out.println("2. " + YELLOW + BOLD + "Withdraw" + RESET);
        System.out.println("3. " + GREEN + BOLD + "Check Balance" + RESET);
        System.out.println("4. " + RED + BOLD + "Logout" + RESET);
    }

    private static void deposit(String username, double amount) {
        if (amount > 0) {
            Account account = accounts.get(username);
            account.deposit(amount);
            System.out.println(GREEN + "\n$" + amount + " deposited successfully. Updated balance: $" + account.getBalance() + RESET);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw(String username, double amount) {
        Account account = accounts.get(username);
        double currentBalance = account.getBalance();

        if (amount > 0 && amount <= currentBalance) {
            account.withdraw(amount);
            System.out.println(YELLOW + "\n$" + amount + " withdrawn successfully. Updated balance: $" + account.getBalance() + RESET);
        } else if (amount > currentBalance) {
            System.out.println("Insufficient funds. Cannot withdraw $" + amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void checkBalance(String username) {
        Account account = accounts.get(username);
        System.out.println(GREEN + "\nYour current balance: $" + account.getBalance() + RESET);
    }

    private static void loadAccountsFromTextFile() {
        try (Scanner fileScanner = new Scanner(new File(textFilePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                double balance = Double.parseDouble(parts[2]);
                Account account = new Account(username, password, balance);
                accounts.put(username, account);
            }
        } catch (FileNotFoundException e) {
            // File does not exist, it's fine for now.
        }
    }

    private static void saveAccountsToTextFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(textFilePath))) {
            for (Account account : accounts.values()) {
                writer.println(account.getUsername() + "," + account.getPassword() + "," + account.getBalance());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Account {
    private String username;
    private String password;
    private double balance;

    public Account(String username, String password, double initialDeposit) {
        this.username = username;
        this.password = password;
        this.balance = initialDeposit;
    }

    public boolean validatePassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
