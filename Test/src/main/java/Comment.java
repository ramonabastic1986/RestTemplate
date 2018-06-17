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
                ", comment='" + comment + '\'' +
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




    @Override
    public boolean matches(Exchange exchange) {
        Name f = exchange.getIn().getBody(Name.class);

        for (Parent parents : f.getParents().getParents()) {
            for (Child children : parents.getChildren().getChildren()) {
                Integer age = children.getAge();

                AgeCateg ageCateg = null;

                try {
                    ageCateg = AgeCateg.getCateg(age);
                } catch (Exception e) {
                   //
                }

                if (ageCateg == AgeCateg.THIRD) {
                    return true;
                }
            }
        }

        return false;
    }


public class AggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Parents parents = newExchange.getIn().getBody(Name.class).getParents();
        List<String> names = new LinkedList<>();

            for (Parent parent : parents.getParents()) {
                for (Child child : parent.getChildren().getChildren())
                    names.add(child.getName());
            }


        if (oldExchange == null) {
            oldExchange = newExchange;
            List<String> childrenName = new LinkedList<>();
            childrenName.addAll(names);

            oldExchange.getIn().setBody(childrenName);
        }
        else {
            oldExchange.getIn().getBody(List.class).addAll(names);
        }

        return oldExchange;
    }
}
  onException(AgeCategException.class)
                .handled(true)
                .setBody(new FileToName())
                .aggregate(new AgeAggregationStrategy()).constant(true)
                .process(new NameProcessor(this.config.getProperty("file.path"))
                .completionTimeout(5000);
                         
    .otherwise()
               .process(new NoCategProcesor())
                .endChoice()
                .end();
