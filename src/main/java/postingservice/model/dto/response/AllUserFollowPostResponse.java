package postingservice.model.dto.response;

import java.util.Set;

public class AllUserFollowPostResponse extends Response {

    private Set<BasicPostResponse> posts;

    public AllUserFollowPostResponse(int responseCode, String responseMessage, Set<BasicPostResponse> posts) {
        super(responseCode, responseMessage);
        this.posts = posts;
    }

    public AllUserFollowPostResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public Set<BasicPostResponse> getPosts() {
        return posts;
    }
}
