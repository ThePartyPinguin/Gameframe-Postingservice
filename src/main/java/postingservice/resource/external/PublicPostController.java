package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postingservice.model.dto.response.BasicPostResponse;
import postingservice.model.dto.response.FullPostResponse;
import postingservice.service.ThreadPostService;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicPostController {

    @Autowired
    private ThreadPostService postService;


    @GetMapping("/basic")
    public List<BasicPostResponse> getPostPageList(@RequestParam int page, @RequestParam int perPageCount){
        return this.postService.getPostPage(page, perPageCount);
    }

    @GetMapping()
    public FullPostResponse getPostById(@RequestParam long postId){
        return this.postService.getPostById(postId);
    }
}
