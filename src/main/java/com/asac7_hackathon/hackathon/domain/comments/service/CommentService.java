package com.asac7_hackathon.hackathon.domain.comments.service;

import com.asac7_hackathon.hackathon.domain.comments.dto.CommentRequestDto;
import com.asac7_hackathon.hackathon.domain.comments.dto.CommentResponseDto;
import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import com.asac7_hackathon.hackathon.domain.comments.repository.CommentRepo;
import com.asac7_hackathon.hackathon.domain.comments.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository repository;
    private final CommentRepo repo;


    public CommentResponseDto find(Integer id) {
        Comment retrieved = repository.getById(id);
        return CommentResponseDto.from(retrieved);
    }


//    public List<CommentResponseDto> findAll() {
//        List<Comment> retrievedAll = repository.findAll();
//        return retrievedAll.stream().map(CommentResponseDto::from).toList();
//    }


    public CommentResponseDto save(CommentRequestDto request) {
        Comment comment = request.toEntity();
        Comment saved = repository.save(comment);
        return CommentResponseDto.from(saved);
    }


    public CommentResponseDto patchContent(Integer id, CommentRequestDto request) {
        Comment comment = request.toEntity();
        repo.patchContent(id, comment);
        Comment retrieved = repository.getById(id);
        return CommentResponseDto.from(retrieved);
    }


    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
