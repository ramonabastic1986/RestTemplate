import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;




/**
 * Created by fstancu on 11/9/2016.
 */
public class Comment {

    private long id;
    private String name;
    private String email;
    private String comment;
    private Date date;
    private Object _links;

    public Comment() {
    }

    public Comment(String name, String email, String comment, Date date) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bllablalala='" + comment + '\'' +
                ", date=" + date + '\'' +
                ", _links=" + _links +
                '}';
    }

    public Object get_links() {
        return _links;
    }

    public void set_links(Object _links) {
        this._links = _links;
    }

}

public void sortRoutea() {
        List<RouteDefinition> routes = camelContext.getRoutes();
        List<RouteDefinition> nonNules = routes.stream().filter(item -> item != null).collect(Collectors.toList());

        // todo
        Collections.sort(nonNules, (route1, route2) ->  reverseCompare(route1.getFirst(), route2.getFirst()))
    }

    public static int reverseCompare(Integer one, Integer two) {
        int result = Integer.compare(rone, two);

        if (result == 1) {
            return -1;
        }

        if (result == -1) {
            return 1;
        }

        return 0;
    }
    
    
https://github.com/MachineLearning-lover/Java/tree/master/planning/src/main/java/com/vmi/planning/Entities
