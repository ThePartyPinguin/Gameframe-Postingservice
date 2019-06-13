package postingservice.model.dto;

public class TagDto {

    private long id;

    private long timesUsed;

    private String tagString;

    public TagDto(long id, long timesUsed, String tagString) {
        this.id = id;
        this.timesUsed = timesUsed;
        this.tagString = tagString;
    }

    public TagDto() {
    }

    public TagDto(String tagString) {
        this.tagString = tagString;
    }

    public long getId() {
        return id;
    }

    public long getTimesUsed() {
        return timesUsed;
    }

    public String getTagString() {
        return tagString;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimesUsed(long timesUsed) {
        this.timesUsed = timesUsed;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }
}
