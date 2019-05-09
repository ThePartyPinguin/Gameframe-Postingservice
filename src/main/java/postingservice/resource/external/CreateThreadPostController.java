package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postingservice.model.dto.request.CreatePostRequestDto;
import postingservice.model.dto.response.PostCreatedResponse;
import postingservice.service.ThreadPostService;

@RestController
@RequestMapping("/private")
public class CreateThreadPostController {

    @Autowired
    private ThreadPostService postingService;

    @PostMapping("/new")
    public PostCreatedResponse createNewPost(@RequestHeader("X-user-id") String userId, @RequestBody CreatePostRequestDto requestDto){
        return this.postingService.createNewPost(Long.parseLong(userId), requestDto);
    }


}
