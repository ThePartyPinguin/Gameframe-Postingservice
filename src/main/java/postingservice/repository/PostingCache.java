package postingservice.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import postingservice.model.entity.ThreadPost;

import java.util.Optional;

@ApplicationScope
public class PostingCache extends DaoCache<IPostingJpaImpl, ThreadPost, Long> implements IPostingDao {
    @Override
    public Optional<ThreadPost> findById(long id) {

        boolean exists = this.localCache.containsKey(id);

        Optional<ThreadPost> post;
        if(exists){
            post = Optional.of(this.localCache.get(id));
        }
        else{
            post = this.jpaRepository.findById(id);

            post.ifPresent(threadPost -> saveToCache(threadPost.postId, threadPost));
        }

        return post;
    }
}
