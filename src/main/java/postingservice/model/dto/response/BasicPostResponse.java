package postingservice.model.dto.response;

import postingservice.model.dto.TagDto;
import postingservice.model.entity.Tag;
import postingservice.model.entity.ThreadPost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicPostResponse extends Response {

    private long postId;
    private String title;
    private int followerCount;
    private UserDto creator;
    private Date datePosted;
    private List<TagDto> tags;

    public BasicPostResponse(int responseCode, String responseMessage, ThreadPost post, int followerCount, UserDto creator) {
        super(responseCode, responseMessage);
        this.postId = post.getPostId();
        this.title = post.getPostTitle();
        this.followerCount = followerCount;
        this.creator = creator;
        this.datePosted = post.getDateCreated();

        this.tags = new ArrayList<>();
        for (Tag t : post.getTags()) {
            this.tags.add(new TagDto(t.getId(), t.getTimesUsed(), t.getTagString()));
        }
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
