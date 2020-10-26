package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    // save/create the comments from comment object
    public void saveComments(Comment comment){
        repository.saveComments(comment);
    }

    // return all the comments for the image by imageId
    public List<Comment> getAllComments(Integer imageId){
        return repository.getAllComments(imageId);
    }
}
