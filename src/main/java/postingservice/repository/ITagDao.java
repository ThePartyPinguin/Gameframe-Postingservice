package postingservice.repository;


import postingservice.model.entity.Tag;

import java.util.List;
import java.util.Set;

public interface ITagDao {

    Tag save(Tag post);

    boolean exists(String tagString);

    Tag getByTagString(String tagString);

    List<Tag> getTagsById(Set<Long> tagId);

}
