package postingservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postingservice.model.dto.response.AllUserFollowPostResponse;
import postingservice.model.dto.response.Response;
import postingservice.service.FollowService;

@RestController
@RequestMapping("/private/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping
    public Response followPost(@RequestHeader(name = "X-user-id") String userId, @RequestParam long postId){
        try{
            long userIdLong = Long.parseLong(userId);
            return this.followService.addPostFollow(userIdLong, postId);
        }
        catch (NumberFormatException ex){
            return new Response(401, "Given userId is not valid: " + userId);
        }
    }

    @GetMapping("/ami")
    public Response userFollowingPost(@RequestHeader(name = "X-user-id") String userId, @RequestParam long postId){
        try{
            long userIdLong = Long.parseLong(userId);


            boolean isFollowing = this.followService.isUserFollowing(userIdLong, postId);

            return isFollowing ? new Response(200, "true") : new Response(201, "false");
        }
        catch (NumberFormatException ex){
            return new Response(401, "Given userId is not valid: " + userId);
        }
    }

    @RequestMapping(value = "/stop", method = RequestMethod.PUT)
    public Response stopFollowingPost(@RequestHeader(name = "X-user-id") String userId, @RequestParam long postId){
        try{
            long userIdLong = Long.parseLong(userId);

            return this.followService.stopFollowingPost(userIdLong, postId);
        }
        catch (NumberFormatException ex){
            return new Response(401, "Given userId is not valid: " + userId);
        }
    }

    @GetMapping("/me")
    public AllUserFollowPostResponse getFollowedPostsByUser(@RequestHeader(name = "X-user-id") String userId){
        try{
            long userIdLong = Long.parseLong(userId);

            return this.followService.getFollowedPostsByUser(userIdLong);
        }
        catch (NumberFormatException ex){
            return new AllUserFollowPostResponse(401, "Given userId is not valid: " + userId);
        }
    }

}
