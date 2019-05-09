package postingservice.model.dto.response;

public class FullUserResponse {

    private long userId;

    private String userName;

    private String fullName;

    private String email;

    public FullUserResponse() {
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
