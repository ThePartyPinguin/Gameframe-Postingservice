package postingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postingservice.model.dto.TagDto;
import postingservice.model.entity.Tag;
import postingservice.repository.ITagJpaImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private ITagJpaImpl tagDao;

    public List<Tag> saveTags(String tagLine) {

        String[] tags = tagLine.split("#");

        List<TagDto> finalTags = new ArrayList<>();

        for (String tag : tags) {
            System.out.println(tag);
            String finalTag = tag.replaceAll("\\s", "").toLowerCase();

            if(finalTag.equals(""))
                continue;
            finalTags.add(new TagDto(finalTag));
        }

        List<Tag> tagsList = new ArrayList<>();
        for (TagDto tag :
                finalTags) {
            tagsList.add(this.tagDao.save(new Tag(tag.getId(), tag.getTagString())));
        }

        return tagsList;
    }


}
