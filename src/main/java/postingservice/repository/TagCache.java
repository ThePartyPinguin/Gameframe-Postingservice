package postingservice.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import postingservice.model.entity.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScope
public class TagCache extends DaoCache<ITagJpaImpl, Tag, Long> implements ITagDao {


    @Override
    public boolean exists(String tagString) {

        if(this.localCache.values().stream().anyMatch(tag -> tag.getTagString().equalsIgnoreCase(tagString)))
        {
            return true;
        }

        return this.jpaRepository.existsByTagString(tagString);
    }

    @Override
    public Tag getByTagString(String tagString) {
        System.out.println("localcache: " + this.localCache.values());
        Optional<Tag> foundTag = this.localCache.values().stream().filter(tag -> tag.getTagString().equalsIgnoreCase(tagString)).findFirst();

        if(foundTag.isPresent()){
            return foundTag.get();
        }

        foundTag = this.jpaRepository.getByTagString(tagString);

        return foundTag.orElse(null);

    }

    @Override
    public List<Tag> getTagsById(Set<Long> tagIds) {
        List<Tag> foundTags = new ArrayList<>();
        
        List<Long> notFoundIds = new ArrayList<>();
        
        for (long id : tagIds) {
            Optional<Tag> t = this.localCache.values().stream().filter(tag -> tag.getId() == id).findFirst();
            
            if(!t.isPresent())
            {
                notFoundIds.add(id);
                continue;
            }
            
            foundTags.add(t.get());
        }
        
        if(notFoundIds.size() > 0)
        {
            for (long id : notFoundIds) {
                Optional<Tag> t = this.jpaRepository.getTagById(id);

                if(!t.isPresent())
                    continue;

                saveToCache(id, t.get());
                foundTags.add(t.get());
            }
        }

        return foundTags;
    }
}
