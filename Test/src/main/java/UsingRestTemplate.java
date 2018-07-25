import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fstancu on 11/9/2016.
 */

public class UsingRestTemplate implements CommentWebServiceClient {
    public Comment save(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        return restTemplate.postForObject("http://localhost:8080/api/comment", comment, Comment.class);
    }

    public Comment update(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        String id = Main.getParsedCommentId(comment.get_links().toString());
        System.out.println(id);
        restTemplate.put("http://localhost:8080/api/comment/" + id, comment);

        return null;
    }

    public Comment findById(Long commentId) {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        return restTemplate.getForObject("http://localhost:8080/api/comment/" + commentId, Comment.class);
    }

    public List<Comment> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        Comments comments = restTemplate.getForObject("http://localhost:8080/api/comment", Comments.class);

        return Arrays.asList(comments.get_embedded().getComment());
    }

    public void delete(Long commentId) {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        restTemplate.delete("http://localhost:8080/api/comment/" + commentId);
    }
}
