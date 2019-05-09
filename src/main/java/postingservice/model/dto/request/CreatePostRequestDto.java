package postingservice.model.dto.request;

public class CreatePostRequestDto {

    private long userId;

    private String postTitle;
    private String postContent;
    private String tagLine;

    public CreatePostRequestDto() {
    }

    public long getUserId() {
        return userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getTagLine() {
        return tagLine;
    }
}
