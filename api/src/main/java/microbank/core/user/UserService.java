package microbank.core.user;

import org.springframework.web.bind.annotation.*;

public interface UserService {
    @GetMapping(
            value = "/user/{userId}",
            produces = "application/json"
    )
    User getUser(@PathVariable int userId);

    @PostMapping(
            value = "/user",
            consumes = "application/json",
            produces = "application/json"
    )
    User createUser(@RequestBody User user);

    @DeleteMapping(value = "/user/{userId}")
    void deleteUser(@PathVariable int userId);
}
