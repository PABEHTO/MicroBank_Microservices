package microbank.core.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountService {
    @GetMapping(
            value = "/account",
            produces = "application/json"
    )
    Account getAccount(@RequestParam(value = "userId", required = true) int userId);
}
