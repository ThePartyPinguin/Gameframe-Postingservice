package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import postingservice.model.dto.request.AddCommentRequest;
import postingservice.model.dto.response.CommentResponse;
import postingservice.service.CommentService;

@RestController
@RequestMapping("/public/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping()
    public CommentResponse addComment(@RequestBody AddCommentRequest request){
        return this.commentService.saveCommentToPost(request);
    }


}
