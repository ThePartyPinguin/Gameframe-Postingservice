package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postingservice.model.dto.request.AddCommentRequest;
import postingservice.model.dto.response.CommentResponse;
import postingservice.service.CommentService;

@RestController
@RequestMapping("/private/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping()
    public CommentResponse addComment(@RequestHeader(name = "X-user-id") String userId, @RequestBody AddCommentRequest request){

        try{
            long userIdLong = Long.parseLong(userId);

            if(userIdLong != request.getCommenterId())
                return new CommentResponse(401, "Session user and given userId are not the same");

            return this.commentService.saveCommentToPost(request);
        }
        catch (NumberFormatException ex){
            return new CommentResponse(401, "Given userId is not valid: " + userId);
        }
    }


}
