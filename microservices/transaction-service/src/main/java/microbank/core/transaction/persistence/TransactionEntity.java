package microbank.core.transaction.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions",
        indexes = {@Index(
                name = "transactions_index",
                unique = true,
                columnList = "userId, transactionId")})
public class TransactionEntity {
    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private int userId;
    private int transactionId;
    private int amount;

    public TransactionEntity() {
    }

    public TransactionEntity(int userId, int transactionId, int amount) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
