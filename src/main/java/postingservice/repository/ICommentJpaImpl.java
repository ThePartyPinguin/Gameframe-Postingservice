package postingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postingservice.model.entity.Comment;

public interface ICommentJpaImpl extends JpaRepository<Comment, Long> {
}
