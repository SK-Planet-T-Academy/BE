package com.asac7_hackathon.hackathon.domain.comments.model;

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
    private Integer id;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false, name= "create_at")
    private LocalDateTime createAt;

    @Column(nullable = true, name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany
    @JoinColumn
    private List<Like> like;
}
