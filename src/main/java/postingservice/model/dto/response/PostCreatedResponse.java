package postingservice.model.dto.response;

import postingservice.model.entity.ThreadPost;

public class PostCreatedResponse extends Response{

    private ThreadPost post;

    public PostCreatedResponse(int responseCode, String responseMessage, ThreadPost post) {
        super(responseCode, responseMessage);
        this.post = post;
    }


    public PostCreatedResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }



    public ThreadPost getPost() {
        return post;
    }
}
