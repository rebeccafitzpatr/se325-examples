package se325.example09.helloworld;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity //etity is an object that has its own lifecycle and needs to be persisted in the database. MArk the entity class with the JPA annotation
public class Message {

    @Id //mark the primary key value
    @GeneratedValue //if we do not supply a value for id, leave it null, then when we first  persist it to a database, JPA will fill it for us and make sure that it is unique.
    private Long id;

    //because we put the annotation in the fields, so this is field access

    private String content; //these are standard fields.
    private LocalDateTime creationTime;

    @ElementCollection //This means that the comments list is tied completely to this one message. Different messages cannot share a same comment. This is an embeddable class - Comment must be tied to an entity class.
    private List<Comment> comments = new ArrayList<>();

    public Message() {
    }

    public Message(String content) {
        this(content, LocalDateTime.now());
    }

    public Message(String content, LocalDateTime creationTime) {
        this.content = content;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
