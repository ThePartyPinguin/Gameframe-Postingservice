package postingservice.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_POST_FOLLOWS")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLLOW_ID")
    private long followId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "POST_ID")
    private long postId;

    public Follow(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Follow() {
    }

    public long getFollowId() {
        return followId;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }
}
