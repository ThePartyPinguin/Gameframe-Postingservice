package postingservice.model.dto.response;

import java.util.Date;
import java.util.List;

public class BasicPostResponse extends Response {

    private long postId;
    private String title;
    private int followerCount;
    private UserDto creator;
    private Date datePosted;
    private List<TagDto> tags;

    public BasicPostResponse(int responseCode, String responseMessage, long postId, String title, int followerCount, UserDto creator, Date datePosted, List<TagDto> tags) {
        super(responseCode, responseMessage);
        this.postId = postId;
        this.title = title;
        this.followerCount = followerCount;
        this.creator = creator;
        this.datePosted = datePosted;
        this.tags = tags;
    }

    public BasicPostResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public UserDto getCreator() {
        return creator;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public List<TagDto> getTags() {
        return tags;
    }
}
