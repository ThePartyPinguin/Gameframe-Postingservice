package postingservice.repository;

import postingservice.model.entity.ThreadPost;

import java.util.Optional;

public interface IPostingDao {

    ThreadPost save(ThreadPost post);

    Optional<ThreadPost> findById(long id);
}
