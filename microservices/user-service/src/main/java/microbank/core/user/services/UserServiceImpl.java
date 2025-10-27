package microbank.core.user.services;

import com.mongodb.DuplicateKeyException;
import microbank.core.user.User;
import microbank.core.user.UserService;
import microbank.core.user.persistence.UserEntity;
import microbank.core.user.persistence.UserRepository;
import microbank.exceptions.InvalidInputException;
import microbank.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(int userId) {
        if (userId < 1) {
            throw new InvalidInputException(
                    "Invalid userId: " + userId
            );
        }
        UserEntity entity = userRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new NotFoundException(
                                "No user found for userId: " + userId
                        )
                );
        User response = userMapper.entityToApi(entity);
        return response;
    }

    @Override
    public User createUser(User user) {
        try {
            UserEntity entity = userMapper.apiToEntity(user);
            UserEntity newEntity = userRepository.save(entity);
            return userMapper.entityToApi(newEntity);
        } catch (DuplicateKeyException e) {
            throw new InvalidInputException(
                    "Duplicate key, UserId: "
                            + user.getUserId());
        }
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.findByUserId(userId).ifPresent(
                user -> userRepository.delete(user)
        );
    }
}
