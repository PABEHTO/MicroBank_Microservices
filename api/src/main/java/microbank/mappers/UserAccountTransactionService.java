package microbank.mappers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserAccountTransactionService {
    @GetMapping(
            value = "/composite",
            produces = "application/json"
    )
    UserAccountTransaction getUserAccountTransaction(@RequestParam int userID);
}
