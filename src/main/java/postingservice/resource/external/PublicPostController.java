package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;
import postingservice.model.dto.response.BasicPostResponse;
import postingservice.model.dto.response.FullPostResponse;
import postingservice.service.ThreadPostService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/public")
public class PublicPostController {

    @Autowired
    private ThreadPostService postService;


    @GetMapping("/basic")
    public Resources<BasicPostResponse> getPostPageList(@RequestParam int page, @RequestParam int perPageCount){

        List<BasicPostResponse> posts = this.postService.getPostPage(page, perPageCount);


        for (BasicPostResponse post : posts) {
            long postId = post.getPostId();

            Link selfLink = new Link("/post/public?postId="+postId).withRel("full_post");
            post.add(selfLink);
        }
        Link link = linkTo(PublicPostController.class).withSelfRel();
        return new Resources<>(posts, link);
    }

    @GetMapping()
    public FullPostResponse getPostById(@RequestParam long postId){
        return this.postService.getFullPostResponseById(postId);
    }
}
