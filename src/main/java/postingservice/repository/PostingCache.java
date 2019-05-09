package postingservice.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import postingservice.model.entity.ThreadPost;

@ApplicationScope
public class PostingCache extends DaoCache<IPostingJpaImpl, ThreadPost, Long> implements IPostingDao {
    @Override
    public void init(IPostingJpaImpl jpaRepository) {

    }
}
