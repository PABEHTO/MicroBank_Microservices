package microbank.core.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionService {
    @GetMapping(
            value = "/transaction",
            produces = "application/json"
    )
    List<Transaction> getTransactions(@RequestParam int userId);
}
