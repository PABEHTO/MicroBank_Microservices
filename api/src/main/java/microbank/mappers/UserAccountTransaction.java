package microbank.mappers;

import microbank.core.account.Account;
import microbank.core.transaction.Transaction;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserAccountTransaction {
    int userId;
    String userName;
    Account account;
    List<Transaction> transaction;

    public UserAccountTransaction(int userId,
                                  String userName,
                                  Account account,
                                  List<Transaction> transaction) {
        this.userId = userId;
        this.userName = userName;
        this.account = account;
        this.transaction = transaction;
    }

    public UserAccountTransaction() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
