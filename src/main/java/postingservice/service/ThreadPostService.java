package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import postingservice.clients.UserServiceCache;
import postingservice.model.dto.request.CreatePostRequestDto;
import postingservice.model.dto.response.*;
import postingservice.model.entity.Tag;
import postingservice.model.entity.ThreadPost;
import postingservice.repository.IPagingPostJpaDao;
import postingservice.repository.IPostingJpaImpl;

import java.util.*;

@Service
public class ThreadPostService {

    @Autowired
    private IPostingJpaImpl postingJpa;

    @Autowired
    private UserServiceCache userService;

    @Autowired
    private IPagingPostJpaDao pagingPostDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private FollowService followService;

    @Autowired
    private CommentService commentService;

    public PostCreatedResponse createNewPost(long userId, CreatePostRequestDto dto){

        if(userId != dto.getUserId()){
            return new PostCreatedResponse(401, "invalid credentials");
        }

        FullUserResponse response = this.userService.getUserByUserId(userId);

        if(response.getUserData() == null)
        {
            return new PostCreatedResponse(404, "User not found");
        }

        List<Tag> tags = this.tagService.saveTags(dto.getTagLine());

        ThreadPost tp = new ThreadPost(userId, dto.getPostTitle(), dto.getPostContent(), new Date());

        tp.setTagIds(tags);

        ThreadPost savedPost = this.postingJpa.save(tp);

        if(savedPost == null){
            return new PostCreatedResponse(502, "Something went wrong trying to save your post");
        }

        return new PostCreatedResponse(200, "Post saved", savedPost);
    }

    public List<BasicPostResponse> getPostPage(int page, int perPageCount){

        Pageable pageable = PageRequest.of(page, perPageCount);

        Page<ThreadPost> posts = this.pagingPostDao.findAllByOrderByPostIdDesc(pageable);

        List<BasicPostResponse> basicPostResponses = new ArrayList<>();

        for (ThreadPost post : posts) {

            FullUserResponse response = this.userService.getUserByUserId(post.getCreatorId());

            int followCount = this.followService.getFollowCountForPost(post.getPostId());

            if(response.getUserData() != null){

                BasicPostResponse bpr = new BasicPostResponse(200, "post", post, followCount, response.getUserData());
                basicPostResponses.add(bpr);
            }
        }

        return basicPostResponses;

    }

    public FullPostResponse getFullPostResponseById(long postId){

        ThreadPost p = this.getPostById(postId);

        if(p == null)
            return new FullPostResponse(404, "Post not found");

        UserDto creator = this.userService.getUserByUserId(p.getCreatorId()).getUserData();

        Set<CommentResponse> comments = this.commentService.getCommentResponsesByPost(p);

        int followCount = this.followService.getFollowCountForPost(p.getPostId());

        return new FullPostResponse(200, "Post", p, creator, comments, followCount);

    }

    public BasicPostResponse getBasicPostResponseById(long postId){

        ThreadPost p = this.getPostById(postId);

        if(p == null)
            return new BasicPostResponse(404, "Post not found");

        UserDto creator = this.userService.getUserByUserId(p.getCreatorId()).getUserData();

        int followCount = this.followService.getFollowCountForPost(p.getPostId());

        return new BasicPostResponse(200, "Post", p, followCount, creator);

    }

    public ThreadPost getPostById(long postId){
        return this.postingJpa.findById(postId).orElse(null);
    }

}
