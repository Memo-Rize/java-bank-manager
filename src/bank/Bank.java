package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String accountNumber, String ownerName, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists: " + accountNumber);
        }
        accounts.put(accountNumber, new Account(accountNumber, ownerName, initialBalance));
    }

    public Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public double getTotalBalance() {
        double total = 0;
        for (Account account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }

    public void deposit(String accountNumber, double amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws InsufficientFundsException {
        getAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String fromNumber, String toNumber, double amount)
            throws InsufficientFundsException {
        Account from = getAccount(fromNumber);
        Account to = getAccount(toNumber);
        from.withdraw(amount);
        to.deposit(amount);
    }
}