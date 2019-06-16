package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postingservice.clients.IUserService;
import postingservice.model.dto.response.AllUserFollowPostResponse;
import postingservice.model.dto.response.BasicPostResponse;
import postingservice.model.dto.response.Response;
import postingservice.model.entity.Follow;
import postingservice.model.entity.ThreadPost;
import postingservice.repository.IFollowJpaImpl;
import postingservice.repository.IPostingJpaImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FollowService {

    @Autowired
    private IFollowJpaImpl followDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostingJpaImpl postingDao;

    @Autowired
    private ThreadPostService postService;

    private Response basicCheckResponse;
    private ThreadPost currentPost;

    public Response addPostFollow(long userId, long postId){

        boolean basicCheck = basicCheck(userId, postId);

        if(!basicCheck)
            return this.basicCheckResponse;

        boolean exists = this.followDao.existsByUserIdAndPostId(userId, postId);

        if(exists){
            return new Response(500, "Already following post");
        }

        Follow f = new Follow(userId, postId);

        this.followDao.save(f);

        return new Response(200, "You started following: " + this.currentPost.getPostTitle());
    }

    public Response stopFollowingPost(long userId, long postId){

        boolean basicCheck = basicCheck(userId, postId);

        if(!basicCheck)
            return this.basicCheckResponse;

        boolean exists = this.followDao.existsByUserIdAndPostId(userId, postId);

        if(!exists){
            return new Response(500, "You're not following post");
        }

        Follow f = this.followDao.findByUserIdAndPostId(userId, postId);

        this.followDao.delete(f);

        return new Response(200, "You stopped following this response");
    }


    public AllUserFollowPostResponse getFollowedPostsByUser(long userId){
        boolean userCheck = checkUser(userId);

        if(!userCheck)
            return new AllUserFollowPostResponse(this.basicCheckResponse.getResponseCode(), this.basicCheckResponse.getResponseMessage());

        Set<Follow> follows = this.followDao.findAllByUserId(userId);

        Set<BasicPostResponse> posts = new HashSet<>();

        for (Follow f : follows) {
            BasicPostResponse postResponse = this.postService.getBasicPostResponseById(f.getPostId());

            if(postResponse.getResponseCode() == 200)
                posts.add(postResponse);
        }

        return new AllUserFollowPostResponse(200, "Posts found", posts);

    }

    public int getFollowCountForPost(long postId){
        return this.followDao.findAllByPostId(postId).size();
    }



    public boolean isUserFollowing(long userId, long postId){
        return this.followDao.existsByUserIdAndPostId(userId, postId);
    }

    private boolean basicCheck(long userId, long postId){

        boolean checkUser = this.checkUser(userId);


        boolean checkPost = this.checkPost(postId);

        return checkUser && checkPost;
    }

    private boolean checkPost(long postId){

        Optional<ThreadPost> post = this.postingDao.findById(postId);

        if(!post.isPresent()){
            this.basicCheckResponse = new Response(404, "Post does not exist");
            return false;
        }

        this.currentPost = post.get();
        return true;
    }

    private boolean checkUser(long userId){
        boolean userExists = this.userService.getUserByUserId(userId).getUserData() != null;

        if(!userExists){
            this.basicCheckResponse = new Response(404, "Given user does not exist");
            return false;
        }
        return true;
    }


}
