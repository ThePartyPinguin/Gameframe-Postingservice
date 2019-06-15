package postingservice.model.dto.response;

import postingservice.model.entity.Comment;

public class CommentResponse extends Response{

    private Comment comment;
    private UserDto user;

    public CommentResponse(int responseCode, String responseMessage, Comment comment, UserDto user) {
        super(responseCode, responseMessage);
        this.comment = comment;
        this.user = user;
    }

    public CommentResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public Comment getComment() {
        return comment;
    }

    public UserDto getUser() {
        return user;
    }
}
