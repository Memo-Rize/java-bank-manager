package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        bank.createAccount("A001", "Alice", 1000.0);
        bank.createAccount("A002", "Bob", 500.0);
    }

    @Test
    public void testCreateAccount() {
        Account alice = bank.getAccount("A001");
        assertEquals("Alice", alice.getOwnerName());
        assertEquals(1000.0, alice.getBalance(), 0.001);
    }

    @Test
    public void testDuplicateAccountThrows() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            public void execute() {
                bank.createAccount("A001", "Charlie", 100.0);
            }
        });
    }

    @Test
    public void testDeposit() {
        bank.deposit("A001", 250.0);
        assertEquals(1250.0, bank.getAccount("A001").getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        bank.withdraw("A001", 300.0);
        assertEquals(700.0, bank.getAccount("A001").getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, new org.junit.jupiter.api.function.Executable() {
            public void execute() throws InsufficientFundsException {
                bank.withdraw("A001", 5000.0);
            }
        });
    }

    @Test
    public void testTransfer() throws InsufficientFundsException {
        bank.transfer("A001", "A002", 200.0);
        assertEquals(800.0, bank.getAccount("A001").getBalance(), 0.001);
        assertEquals(700.0, bank.getAccount("A002").getBalance(), 0.001);
    }

    @Test
    public void testTotalBalance() {
        assertEquals(1500.0, bank.getTotalBalance(), 0.001);
    }

    @Test
    public void testUnknownAccountThrows() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            public void execute() {
                bank.getAccount("UNKNOWN");
            }
        });
    }
}