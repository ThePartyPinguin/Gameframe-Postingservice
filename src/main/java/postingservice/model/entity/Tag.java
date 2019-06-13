package postingservice.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAGS")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private long id;

    @Column(name = "TAG_STRING")
    private String tagString;

    @Column(name = "TAG_TIMES_USED")
    private long TimesUsed;

    @Column(name = "POST_ID")
    @ManyToMany(mappedBy = "tags")
    private Set<ThreadPost> posts;

    public Tag(long tagId, String tagString) {
        this.id = tagId;
        this.tagString = tagString;
        this.posts = new HashSet<>();
    }

    public Tag() {
        this.posts = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public Set<ThreadPost> getPosts() {
        return posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPosts(Set<ThreadPost> posts) {
        this.posts = posts;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

    public long getTimesUsed() {
        return TimesUsed;
    }

    public void setTimesUsed(long timesUsed) {
        TimesUsed = timesUsed;
    }
}
