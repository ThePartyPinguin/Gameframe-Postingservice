package postingservice.model.dto.response;

import postingservice.model.dto.TagDto;

public class TagAddedResponse {

    private TagDto tagDto;

    public TagAddedResponse(TagDto tagDto) {
        this.tagDto = tagDto;
    }

    public TagDto getTagDto() {
        return tagDto;
    }
}
