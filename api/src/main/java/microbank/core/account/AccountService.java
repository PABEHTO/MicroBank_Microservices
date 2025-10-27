package microbank.core.account;

import org.springframework.web.bind.annotation.*;

public interface AccountService {
    @GetMapping(
            value = "/account",
            produces = "application/json"
    )
    Account getAccount(@RequestParam(value = "userId", required = true) int userId);

    @PostMapping(
            value = "/account",
            consumes = "application/json",
            produces = "application/json"
    )
    Account createAccount(@RequestBody Account account);

    @DeleteMapping(value = "/account")
    void deleteAccount(@RequestParam(value = "userId", required = true) int userId);
}
