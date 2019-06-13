package postingservice.model.dto.response;

import postingservice.model.dto.TagDto;

import java.util.List;

public class AllTagsResponse {

    private List<TagDto> tags;

    public AllTagsResponse(List<TagDto> tags) {
        this.tags = tags;
    }

    public AllTagsResponse() {

    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}
