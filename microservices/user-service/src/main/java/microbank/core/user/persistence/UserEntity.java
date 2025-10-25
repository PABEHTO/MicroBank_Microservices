package microbank.core.user.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserEntity {
    @Id
    private String id;

    @Version
    private Integer version;

    @Indexed(unique = true)
    private int userId;

    private String userName;

    public UserEntity(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserEntity() {
    }
}
