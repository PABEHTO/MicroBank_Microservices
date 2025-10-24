package microbank.core.user;

public class User {
    int userId;
    String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User() {
        this.userId = 0;
        this.userName = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
