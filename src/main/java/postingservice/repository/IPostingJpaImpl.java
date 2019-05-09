package postingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postingservice.model.entity.ThreadPost;

public interface IPostingJpaImpl extends JpaRepository<ThreadPost, Long> {
}
