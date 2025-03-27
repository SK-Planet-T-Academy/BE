package com.asac7_hackathon.hackathon.domain.comments.service;

import com.asac7_hackathon.hackathon.domain.comments.dto.CommentRequestDto;
import com.asac7_hackathon.hackathon.domain.comments.dto.CommentResponseDto;
import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import com.asac7_hackathon.hackathon.domain.comments.repository.CommentRepo;
import com.asac7_hackathon.hackathon.domain.comments.repository.CommentRepository;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.repository.PostRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository repository;
    private final CommentRepo repo;


    public CommentResponseDto find(Integer id) {
        Comment retrieved = repository.getById(id);
        return CommentResponseDto.from(retrieved);
    }


    public List<CommentResponseDto> findAll(Integer postId) {
        List<Comment> retrievedAll = repository.findAll();
        List<Comment> retrieved = retrievedAll.stream().filter(v -> Objects.equals(v.getPost().getPostId(),
            Long.valueOf(postId))).toList();
        return retrieved.stream().map(CommentResponseDto::from).toList();
    }


    public CommentResponseDto save(CommentRequestDto request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException(""));
        Comment comment = request.toEntity(post);

        Comment saved = repository.save(comment);
        return CommentResponseDto.from(saved);
    }


    public CommentResponseDto patchContent(Integer id, CommentRequestDto request) {
        Comment comment = repository.getById(id);
        comment.setContent(request.getContent());
        repo.patchContent(id, comment);
        Comment retrieved = repository.getById(id);
        return CommentResponseDto.from(retrieved);
    }


    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
