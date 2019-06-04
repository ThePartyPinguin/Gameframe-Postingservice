package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import postingservice.clients.IUserService;
import postingservice.clients.UserServiceCache;
import postingservice.model.dto.request.CreatePostRequestDto;
import postingservice.model.dto.response.BasicPostResponse;
import postingservice.model.dto.response.FullUserResponse;
import postingservice.model.dto.response.PostCreatedResponse;
import postingservice.model.dto.response.UserDto;
import postingservice.model.entity.ThreadPost;
import postingservice.repository.IPagingPostJpaDao;
import postingservice.repository.IPostingDao;

import java.util.*;

@Service
public class ThreadPostService {

    @Autowired
    private IPostingDao postingDao;

    @Autowired
    private UserServiceCache userService;

    @Autowired
    private IPagingPostJpaDao pagingPostDao;

    public PostCreatedResponse createNewPost(long userId, CreatePostRequestDto dto){

        if(userId != dto.getUserId()){
            return new PostCreatedResponse(401, "invalid credentials");
        }

        FullUserResponse response = this.userService.getUserByUserId(userId);

        if(response.getUserData() == null)
        {
            return new PostCreatedResponse(404, "User not found");
        }

        ThreadPost tp = new ThreadPost(userId, dto.getPostTitle(), dto.getPostContent(), new Date());

        System.out.println(tp.getDateCreated());

        ThreadPost savedPost = this.postingDao.save(tp);

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

            if(response.getUserData() != null){
                BasicPostResponse bpr = new BasicPostResponse(200, "post", post.getPostId(), post.getPostTitle(), new Random().nextInt(25), response.getUserData(), post.getDateCreated(), new ArrayList<>() );
                basicPostResponses.add(bpr);
            }
        }

        return basicPostResponses;

    }

}
