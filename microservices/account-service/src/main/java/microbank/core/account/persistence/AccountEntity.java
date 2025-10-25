package microbank.core.account.persistence;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@CompoundIndex(
        name = "user-account-id",
        unique = true,
        def = "{'userId':1, 'accountId:1'}")
public class AccountEntity {
    @Id
    private String id;

    @Version
    private Integer version;

    private int userId;
    private int accountId;
    private int balance;
    private String accountNumber;

    public AccountEntity() {
    }

    public AccountEntity(int userId, int accountId, int balance, String accountNumber) {
        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
