package postingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import postingservice.model.entity.ThreadPost;

public interface IPagingPostJpaDao extends PagingAndSortingRepository<ThreadPost, Long> {

    Page<ThreadPost> findAllByOrderByPostIdDesc(Pageable pageable);

}
