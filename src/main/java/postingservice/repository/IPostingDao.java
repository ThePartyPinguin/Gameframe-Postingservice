package postingservice.repository;

import postingservice.model.entity.ThreadPost;

public interface IPostingDao {

    ThreadPost save(ThreadPost post);

}
