package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    // The method receives comment object to be persisted in the database
    // Creates an instance of EntityManager
    // Starts a transaction in try block to handle DB exception
    // Then transaction is committed if it is successful
    // Otherwise transaction is rolled back in case of unsuccessful transaction
    public void saveComments(Comment comment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            //persist() method changes the state of the model object from transient state to persistence state
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e);
        }
    }

    // Get all the comments for the image
    public List<Comment> getAllComments(Integer imageId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Comment> comments = new ArrayList<>();

        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c where c.id=:imageId", Comment.class).setParameter("imageId", imageId);
        comments = query.getResultList();

        return comments;
    }
}
