package postingservice.model.dto.request;

import postingservice.model.dto.TagDto;

import java.util.List;

public class AddAllTagsRequest {

    private List<TagDto> tags;

    public AddAllTagsRequest() {

    }

    public AddAllTagsRequest(List<TagDto> tags) {
        this.tags = tags;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}
