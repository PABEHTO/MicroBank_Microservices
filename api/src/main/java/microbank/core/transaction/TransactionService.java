package microbank.core.transaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TransactionService {
    @GetMapping(
            value = "/transaction",
            produces = "application/json"
    )
    List<Transaction> getTransactions(@RequestParam int userId);

    @PostMapping(
            value = "/transaction",
            consumes = "application/json",
            produces = "application/json"
    )
    Transaction createTransaction(@RequestBody Transaction transaction);

    @DeleteMapping(
            value = "/transaction"
    )
    void deleteTransactions(@RequestParam int userId);
}
