package postingservice.model.dto.request;

public class AddCommentRequest {

    private long commenterId;
    private long postId;
    private String commentContent;

    public AddCommentRequest() {
    }

    public long getCommenterId() {
        return commenterId;
    }

    public long getPostId() {
        return postId;
    }

    public String getCommentContent() {
        return commentContent;
    }
}
