package postingservice.model.entity;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "POST_DATE_CREATED")
    private Date dateCreated;

    @Column(name = "POST_EDITED")
    private boolean edited;

    @Column(name = "POST_DATE_EDITED")
    private Date dateEdited;

    public ThreadPost() {
    }

    public ThreadPost(long creatorId, String postTitle, String postContent, Date dateCreated) {
        this.creatorId = creatorId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.dateCreated = dateCreated;
    }

    public void setEdited(Date dateEdited){
        this.edited = true;
        this.dateEdited = dateEdited;
    }

    public long getPostId() {
        return postId;
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
}
