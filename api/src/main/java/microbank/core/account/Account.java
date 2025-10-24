package microbank.core.account;

public class Account {
    private int userId;
    private int accountId;
    private int balance;
    private String accountNumber;

    public Account(int userId, int accountId, int balance, String accountNumber) {
        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Account() {
        userId = 0;
        accountId = 0;
        balance = -1000;
        accountNumber = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
