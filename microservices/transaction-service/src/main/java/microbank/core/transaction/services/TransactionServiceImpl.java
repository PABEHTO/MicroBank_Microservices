package microbank.core.transaction.services;

import microbank.core.transaction.Transaction;
import microbank.core.transaction.TransactionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<Transaction> getTransactions(int userId) {
        return List.of(
          new Transaction(userId, userId + 0, 1000),
          new Transaction(userId, userId + 1, 1000),
          new Transaction(userId, userId + 2, -1000)
        );
    }
}
