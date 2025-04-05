import java.util.ArrayList;
import java.util.List;

// Transaction interface
interface Transaction {
    void processTransaction();
}

// DepositTransaction class to deposit money
class DepositTransaction implements Transaction {
    double balance;
    double amount;

    public DepositTransaction(double balance, double amount) {
        this.balance = balance;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        balance += amount;
        System.out.println("The amount deposited: " + amount);
        System.out.println("The updated balance: " + balance);
    }
}

// WithdrawalTransaction class to withdraw money
class WithdrawalTransaction implements Transaction {
    double balance;
    double amount;

    public WithdrawalTransaction(double balance, double amount) {
        this.balance = balance;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("The amount withdrawn: " + amount);
            System.out.println("The updated balance: " + balance);
        } else {
            System.out.println("Insufficient account balance");
        }
    }
}

// FundTransferTransaction class
class FundTransferTransaction implements Transaction {
    double senderBalance;
    double receiverBalance;
    double amount;

    public FundTransferTransaction(double senderBalance, double receiverBalance, double amount) {
        this.senderBalance = senderBalance;
        this.receiverBalance = receiverBalance;
        this.amount = amount;
    }

    @Override
    public void processTransaction() {
        if (senderBalance >= amount) {
            senderBalance -= amount;
            receiverBalance += amount;
            System.out.println("The amount transferred: " + amount);
            System.out.println("Updated sender balance: " + senderBalance);
            System.out.println("Updated receiver balance: " + receiverBalance);
        } else {
            System.out.println("Insufficient balance in sender's account");
        }
    }
}

// BankSystem class to process transactions
class BankSystem {
    public void processTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transaction.processTransaction();
        }
    }
}

public class BankTransaction {
    public static void main(String[] args) {
        double senderBalance = 7000;
        double receiverBalance = 200;

        System.out.println("Sender's balance before transaction: " + senderBalance);
        System.out.println("Receiver's balance before transaction: " + receiverBalance);

        DepositTransaction deposit = new DepositTransaction(senderBalance, 1000);
        WithdrawalTransaction withdraw = new WithdrawalTransaction(senderBalance, 1500);
        FundTransferTransaction transfer = new FundTransferTransaction(senderBalance, receiverBalance, 2000);

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(deposit);
        transactions.add(withdraw);
        transactions.add(transfer);

        BankSystem bankSystem = new BankSystem();
        bankSystem.processTransactions(transactions);

        System.out.println("Sender's balance after transaction: " + senderBalance);
        System.out.println("Receiver's balance after transaction: " + receiverBalance);
    }
}
