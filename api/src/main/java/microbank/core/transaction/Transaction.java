package microbank.core.transaction;

public class Transaction {
    private int userId;
    private int transactionId;
    private int amount;

    public Transaction(int userId, int transactionId, int amount) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public Transaction() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
