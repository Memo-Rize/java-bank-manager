package bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Java Bank Account Manager ===");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1": createAccount(scanner, bank); break;
                    case "2": deposit(scanner, bank); break;
                    case "3": withdraw(scanner, bank); break;
                    case "4": viewBalance(scanner, bank); break;
                    case "5": listAccounts(bank); break;
                    case "6": transfer(scanner, bank); break;
                    case "7": running = false; System.out.println("Goodbye."); break;
                    default:  System.out.println("Unknown option: " + choice);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Create account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View balance");
        System.out.println("5. List all accounts");
        System.out.println("6. Transfer");
        System.out.println("7. Exit");
        System.out.print("Choose: ");
    }

    private static void createAccount(Scanner scanner, Bank bank) {
        System.out.print("Account number: ");
        String number = scanner.nextLine().trim();
        System.out.print("Owner name: ");
        String owner = scanner.nextLine().trim();
        System.out.print("Initial balance: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        bank.createAccount(number, owner, amount);
        System.out.println("Account created.");
    }

    private static void deposit(Scanner scanner, Bank bank) {
        System.out.print("Account number: ");
        String number = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        bank.deposit(number, amount);
        System.out.println("Deposited " + amount);
    }

    private static void withdraw(Scanner scanner, Bank bank) throws InsufficientFundsException {
        System.out.print("Account number: ");
        String number = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        bank.withdraw(number, amount);
        System.out.println("Withdrew " + amount);
    }

    private static void viewBalance(Scanner scanner, Bank bank) {
        System.out.print("Account number: ");
        String number = scanner.nextLine().trim();
        System.out.println("Balance: " + bank.getAccount(number).getBalance());
    }

    private static void listAccounts(Bank bank) {
        for (Account account : bank.getAllAccounts()) {
            System.out.println(account);
        }
    }

    private static void transfer(Scanner scanner, Bank bank) throws InsufficientFundsException {
        System.out.print("From account: ");
        String from = scanner.nextLine().trim();
        System.out.print("To account: ");
        String to = scanner.nextLine().trim();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        bank.transfer(from, to, amount);
        System.out.println("Transferred " + amount);
    }
}