package com.haddad.blogin.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {




    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name="postId")
    private Post post;


    public Comment(){

    }
    public Comment(Long id, String content, Date createAt){
        this.commentId = id;
        this.content = content;
        this.createAt = createAt;
    }

    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


}
