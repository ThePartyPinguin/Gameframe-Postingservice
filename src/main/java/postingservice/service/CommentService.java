package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postingservice.clients.IUserService;
import postingservice.model.dto.request.AddCommentRequest;
import postingservice.model.dto.response.CommentResponse;
import postingservice.model.dto.response.UserDto;
import postingservice.model.entity.Comment;
import postingservice.model.entity.ThreadPost;
import postingservice.repository.ICommentJpaImpl;
import postingservice.repository.IPostingJpaImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    private ICommentJpaImpl commentDao;

    @Autowired
    private IPostingJpaImpl postingDao;

    @Autowired
    private IUserService userService;

    public CommentResponse saveCommentToPost(AddCommentRequest request){

        Optional<ThreadPost> getPost = this.postingDao.findById(request.getPostId());

        if(!getPost.isPresent()){
            return new CommentResponse(404, "Requested post does not exist");
        }

        UserDto user = this.userService.getUserByUserId(request.getCommenterId()).getUserData();

        if(user == null){
            return new CommentResponse(404, "User not found");
        }

        Comment c = new Comment(request.getCommenterId(), request.getCommentContent(), new Date());

        Comment savedComment = this.commentDao.save(c);

        ThreadPost post = getPost.get();

        post.addComment(savedComment);

        this.postingDao.save(post);

        return new CommentResponse(200, "Comment added", savedComment, user);
    }

    public Set<CommentResponse> getCommentResponsesByPost(ThreadPost post){

        Set<CommentResponse> responses = new HashSet<>();

        for (Comment c : post.getComments()) {
            UserDto user = this.userService.getUserByUserId(c.getUserId()).getUserData();

            CommentResponse cr = new CommentResponse(200, "comment", c, user);
            responses.add(cr);
        }

        return responses;
    }
}
