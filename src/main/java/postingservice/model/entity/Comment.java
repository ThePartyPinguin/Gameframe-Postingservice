package postingservice.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    public long Id;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "COMMENT_EDITED")
    private boolean edited;

    @Column(name = "DATE_EDITED")
    private Date dateEdited;

    public Comment(long userId, String commentContent, Date dateCreated) {
        this.userId = userId;
        this.commentContent = commentContent;
        this.dateCreated = dateCreated;
    }

    public Comment() {
    }

    public long getId() {
        return Id;
    }

    public long getUserId() {
        return userId;
    }

    public void editComment(String commentContent){
        this.commentContent = commentContent;

        this.dateEdited = new Date();
        this.edited = true;
    }

    public String getCommentContent() {
        return commentContent;
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
