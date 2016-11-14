import com.google.gson.Gson;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ebastic on 11/10/2016.
 */
public class HttpClientTemplate implements CommentWebServiceClient {

    public String getResponseAsString(CloseableHttpResponse response) throws IOException {
        StringBuffer result = new StringBuffer();

        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);}
        } finally {
            response.close();
        }

        return result.toString();
    }


    public Comment save(Comment comment)  {
        Gson gson = new Gson();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/comment");
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(comment));
            entity.setContentType("application/json");
            System.out.println(gson.toJson(comment));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resp = null;
        try {
            resp = getResponseAsString(response1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(resp.toString());
        Comment newComment = gson.fromJson(resp.toString(), Comment.class);

        return newComment;
    }

    public Comment update(Comment comment) {
        Gson gson = new Gson();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String id = Main.getParsedCommentId(comment.get_links().toString());
        System.out.println(id);
        HttpPut httpPut = new HttpPut("http://localhost:8080/api/comment/" + id);

        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(comment));
            entity.setContentType("application/json");
            System.out.println(gson.toJson(comment));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPut.setEntity(entity);
        try {
            httpclient.execute(httpPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Comment findById(Long commentId) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/comment/" + commentId);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resp = null;
        try {
            resp = getResponseAsString(response1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Comment comment = gson.fromJson(resp.toString(), Comment.class);

        return comment;
    }

    public List<Comment> findAll(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/comment/");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resp = null;
        try {
            resp = getResponseAsString(response1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Comments comments = gson.fromJson(resp.toString(), Comments.class);
        List<Comment> comm = Arrays.asList(comments.get_embedded().getComment());
        System.out.println(comm.size());

        return comm;
    }

    public void delete(Long commentId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api/comment/" + commentId);

        try {
            httpclient.execute(httpDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
