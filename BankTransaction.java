import java.util.ArrayList;
import java.util.List;

// Transaction interface
interface Transaction {
    void processTransaction();
}

// Account class
class Account {
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}

// DepositTransaction class
class DepositTransaction implements Transaction {
    private Account account;
    private double amount;

    public DepositTransaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        account.deposit(amount);
        System.out.println("Deposited " + amount + " into " + account.getName() + "'s account");
        System.out.println("Current balance: " + account.getBalance());
    }
}

// WithdrawalTransaction class
class WithdrawalTransaction implements Transaction {
    private Account account;
    private double amount;

    public WithdrawalTransaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        if (account.withdraw(amount)) {
            System.out.println("Amount withdrawn:  " + amount + " from " + account.getName() + "'s account");
            System.out.println("Current balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance in " + account.getName() + "'s account");
        }
    }
}

// FundTransferTransaction class
class FundTransferTransaction implements Transaction {
    private Account sender;
    private Account receiver;
    private double amount;

    public FundTransferTransaction(Account sender, Account receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            System.out.println("Transferred " + amount + " from " + sender.getName() + " to " + receiver.getName());
            System.out.println("Sender balance: " + sender.getBalance());
            System.out.println("Receiver balance: " + receiver.getBalance());
        } else {
            System.out.println("Insufficient funds in " + sender.getName() + "'s account");
        }
    }
}

// BankSystem class
class BankSystem {
    public void processTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            t.processTransaction();
            System.out.println("----------------------------");
        }
    }
}

// Main class
public class BankTransaction {
    public static void main(String[] args) {
        Account sender = new Account("Party A (sender)", 7000);
        Account receiver = new Account("Party B (receiver)", 200);

        System.out.println("Sender's balance before transaction: " + sender.getBalance());
        System.out.println("Receiver's balance before transaction: " + receiver.getBalance());

        // Transactions
        DepositTransaction deposit = new DepositTransaction(sender, 1000);
        WithdrawalTransaction withdraw = new WithdrawalTransaction(sender, 1500);
        FundTransferTransaction transfer = new FundTransferTransaction(sender, receiver, 2000);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(deposit);
        transactions.add(withdraw);
        transactions.add(transfer);

        BankSystem bankSystem = new BankSystem();
        bankSystem.processTransactions(transactions);

        System.out.println("Sender's balance after transaction: " + sender.getBalance());
        System.out.println("Receiver's balance after transaction: " + receiver.getBalance());
    }
}
