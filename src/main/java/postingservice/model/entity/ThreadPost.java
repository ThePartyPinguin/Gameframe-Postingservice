package postingservice.model.entity;

import postingservice.model.dto.TagDto;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "THREAD_POSTS")
public class ThreadPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    public long postId;

    @Column(name = "CREATOR_ID")
    private long creatorId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT", length = 1048576)
    private String postContent;

    @Column(name = "POST_DATE_CREATED")
    private Date dateCreated;

    @Column(name = "POST_EDITED")
    private boolean edited;

    @Column(name = "POST_DATE_EDITED")
    private Date dateEdited;

    @Column(name = "POST_TAG_IDS")
    @ManyToMany()
    @JoinTable(name = "POSTS_TAGS", joinColumns = @JoinColumn(name = "POST_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags;

    public ThreadPost() {
        tags = new HashSet<>();
    }

    public ThreadPost(long creatorId, String postTitle, String postContent, Date dateCreated) {
        this.creatorId = creatorId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.dateCreated = dateCreated;
        tags = new HashSet<>();
    }

    public void setEdited(Date dateEdited){
        this.edited = true;
        this.dateEdited = dateEdited;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public boolean isEdited() {
        return edited;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTagIds(List<Tag> tags){

        if(this.tags == null)
            this.tags = new HashSet<>();

        this.tags.addAll(tags);

    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
