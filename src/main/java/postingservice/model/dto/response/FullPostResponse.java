package postingservice.model.dto.response;

import postingservice.model.entity.Comment;
import postingservice.model.entity.Tag;
import postingservice.model.entity.ThreadPost;

import java.util.Date;
import java.util.Set;

public class FullPostResponse extends Response {

    private long postId;
    private String title;
    private String content;
    private int followerCount;
    private UserDto creator;
    private Date datePosted;
    private Set<Tag> tags;
    private Set<CommentResponse> comments;

    public FullPostResponse(int responseCode, String responseMessage, ThreadPost post, UserDto userDto, Set<CommentResponse> comments, int followerCount) {
        super(responseCode, responseMessage);
        this.postId = post.getPostId();
        this.title = post.getPostTitle();
        this.content = post.getPostContent();
        this.followerCount = followerCount;
        this.creator = userDto;
        this.datePosted = post.getDateCreated();
        this.tags = post.getTags();
        this.comments = comments;
    }

    public FullPostResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public FullPostResponse() {
    }

    public long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public UserDto getCreator() {
        return creator;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<CommentResponse> getComments() {
        return comments;
    }
}
