package com.asac7_hackathon.hackathon.domain.comments.model;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false, name= "create_at")
    private LocalDateTime createAt;

    @Column(nullable = true, name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
