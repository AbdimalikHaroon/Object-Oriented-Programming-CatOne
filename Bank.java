// abstract class BankAccount
abstract class BankAccount {
    String accountNumber;
    String accountHolder;
    double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Abstract method
    public abstract void calculateInterest();

    // Concrete method deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("The amount deposited is: " + amount);
            System.out.println("The updated balance is: " + balance);
        } else {
            System.out.println("Cannot deposit amount: " + amount);
        }
    }

    // Concrete method withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("The amount withdrawn is: " + amount);
            System.out.println("The updated balance is: " + balance);
        } else if (amount > balance){
            System.out.println("Insufficient balance amount. Your balance is: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount: " + amount);
        }
    }
}

// SavingsAccount class that extends BankAccount
class SavingsAccount extends BankAccount {
    double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    // Implementation of calculateInterest() - balance * interestRate / 100
    @Override
    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("The interest gained is: " + interest);
    }
}

// CurrentAccount class that extends BankAccount
class CurrentAccount extends BankAccount {
    double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void calculateInterest() {

    }

    // Override the withdraw method
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) {
            balance -= amount;
            System.out.println("The amount withdrawn is: " + amount);
            System.out.println("The updated balance is: " + balance);
        } else if (amount > (balance + overdraftLimit)){
            System.out.println("The amount withdrawn is above the overdraft limit");
        } else {
            System.out.println("Invalid withdrawal amount: " + amount);
        }
    }
}

public class Bank {
    public static void main(String[] args) {
        // SavingsAccount object and related methods
        SavingsAccount acc1 = new SavingsAccount("150352", "Abdimalik Haroon", 0, 2.0);
        acc1.deposit(5000);
        acc1.calculateInterest();
        System.out.println("The current balance is: " + acc1.balance);

        // CurrentAccount object and related methods
        CurrentAccount acc2 = new CurrentAccount("150352", "Abdimalilk Haroon", 5000, 2000);
        acc2.withdraw(6500);
        acc2.withdraw(1000);
    }
}