package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Comment;
import mk.ukim.finki.wp.lab.repository.jpa.CommentRepository;
import mk.ukim.finki.wp.lab.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }
}
