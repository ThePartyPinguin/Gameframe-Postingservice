package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postingservice.clients.IUserService;
import postingservice.model.dto.request.CreatePostRequestDto;
import postingservice.model.dto.response.FullUserResponse;
import postingservice.model.dto.response.PostCreatedResponse;
import postingservice.model.entity.ThreadPost;
import postingservice.repository.IPostingDao;

import java.util.Date;

@Service
public class ThreadPostService {

    @Autowired
    private IPostingDao postingDao;

    @Autowired
    private IUserService userService;

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

}
