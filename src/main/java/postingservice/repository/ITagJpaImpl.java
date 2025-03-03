package postingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postingservice.model.entity.Tag;

import java.util.Optional;

public interface ITagJpaImpl extends JpaRepository<Tag, Long> {
    boolean existsByTagString(String tagString);

    Optional<Tag> getByTagString(String tagString);

    Optional<Tag> getTagById(long id);
}
