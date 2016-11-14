import java.util.List;

/**
 * Created by fstancu on 11/9/2016.
 */
public interface CommentWebServiceClient {

    Comment save(Comment comment);
    Comment update(Comment comment);
    Comment findById(Long commentId);
    List<Comment> findAll();
    void delete(Long commentId);

}
