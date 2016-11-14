import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ebastic on 11/9/2016.
 */
public class Comments {

    private Embeded _embedded;
    private Object _links;
    private Object page;

    public Embeded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embeded _embedded) {
        this._embedded = _embedded;
    }

    public Object get_links() {
        return _links;
    }

    public void set_links(Object _links) {
        this._links = _links;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

}
