package postingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postingservice.model.entity.Follow;

import java.util.Set;

public interface IFollowJpaImpl extends JpaRepository<Follow, Long> {

    boolean existsByUserIdAndPostId(long userId, long postId);

    Follow findByUserIdAndPostId(long userId, long postId);

    Set<Follow> findAllByUserId(long userId);

    Set<Follow> findAllByPostId(long postId);
}
