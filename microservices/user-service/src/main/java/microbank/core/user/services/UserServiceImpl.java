package microbank.core.user.services;

import microbank.core.user.User;
import microbank.core.user.UserService;
import microbank.exceptions.InvalidInputException;
import microbank.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(int userId) {
        if (userId < 1) {
            throw new NotFoundException();
        }
        return new User(userId, "User_" + userId);
    }
}
